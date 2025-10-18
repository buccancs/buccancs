#!/usr/bin/env python3
"""
UI Analyzer for Android Apps
Converts UI dumps to AI-understandable format and detects layout issues
"""

import json
import xml.etree.ElementTree as ET
from dataclasses import dataclass, asdict
from typing import List, Optional, Dict, Tuple
from pathlib import Path
import base64
import re


@dataclass
class Rectangle:
    """Represents element bounds"""
    left: int
    top: int
    right: int
    bottom: int
    
    @property
    def width(self) -> int:
        return self.right - self.left
    
    @property
    def height(self) -> int:
        return self.bottom - self.top
    
    @property
    def area(self) -> int:
        return self.width * self.height
    
    @property
    def center_x(self) -> int:
        return self.left + self.width // 2
    
    @property
    def center_y(self) -> int:
        return self.top + self.height // 2
    
    def overlaps(self, other: 'Rectangle') -> bool:
        """Check if this rectangle overlaps with another"""
        return not (self.right < other.left or self.left > other.right or
                   self.bottom < other.top or self.top > other.bottom)
    
    def to_dict(self) -> dict:
        return {
            'left': self.left,
            'top': self.top,
            'right': self.right,
            'bottom': self.bottom,
            'width': self.width,
            'height': self.height,
            'centerX': self.center_x,
            'centerY': self.center_y
        }


@dataclass
class UiElement:
    """Represents a UI element from hierarchy"""
    element_id: str
    element_type: str
    text: str
    content_desc: str
    bounds: Rectangle
    clickable: bool
    enabled: bool
    focused: bool
    scrollable: bool
    children: List['UiElement']
    
    # Computed properties
    is_likely_truncated: bool = False
    has_text_wrapping: bool = False
    has_overlapping_elements: bool = False
    touch_target_too_small: bool = False
    
    def to_dict(self) -> dict:
        """Convert to dictionary for JSON export"""
        return {
            'id': self.element_id,
            'type': self.element_type,
            'text': self.text,
            'contentDesc': self.content_desc,
            'bounds': self.bounds.to_dict(),
            'clickable': self.clickable,
            'enabled': self.enabled,
            'focused': self.focused,
            'scrollable': self.scrollable,
            'issues': {
                'likelyTruncated': self.is_likely_truncated,
                'textWrapping': self.has_text_wrapping,
                'overlappingElements': self.has_overlapping_elements,
                'touchTargetTooSmall': self.touch_target_too_small
            },
            'children': [child.to_dict() for child in self.children]
        }


@dataclass
class UiIssue:
    """Represents a detected UI issue"""
    severity: str  # CRITICAL, HIGH, MEDIUM, LOW
    issue_type: str
    element: UiElement
    description: str
    suggestion: str
    confidence: float = 1.0
    
    def to_dict(self) -> dict:
        return {
            'severity': self.severity,
            'type': self.issue_type,
            'element': {
                'type': self.element.element_type,
                'text': self.element.text,
                'bounds': self.element.bounds.to_dict(),
                'id': self.element.element_id
            },
            'description': self.description,
            'suggestion': self.suggestion,
            'confidence': self.confidence
        }


class UiHierarchyParser:
    """Parse Android UI Automator XML dumps"""
    
    @staticmethod
    def parse_bounds(bounds_str: str) -> Rectangle:
        """Parse bounds string: '[96,600][984,744]'"""
        coords = re.findall(r'\d+', bounds_str)
        if len(coords) != 4:
            return Rectangle(0, 0, 0, 0)
        return Rectangle(
            int(coords[0]), int(coords[1]),
            int(coords[2]), int(coords[3])
        )
    
    @staticmethod
    def parse_node(node: ET.Element) -> UiElement:
        """Parse XML node to UiElement"""
        bounds = UiHierarchyParser.parse_bounds(node.get('bounds', '[0,0][0,0]'))
        
        element = UiElement(
            element_id=node.get('resource-id', ''),
            element_type=node.get('class', ''),
            text=node.get('text', ''),
            content_desc=node.get('content-desc', ''),
            bounds=bounds,
            clickable=node.get('clickable', 'false') == 'true',
            enabled=node.get('enabled', 'false') == 'true',
            focused=node.get('focused', 'false') == 'true',
            scrollable=node.get('scrollable', 'false') == 'true',
            children=[]
        )
        
        # Parse children
        for child_node in node.findall('node'):
            element.children.append(UiHierarchyParser.parse_node(child_node))
        
        return element
    
    @staticmethod
    def parse_xml_file(xml_path: str) -> UiElement:
        """Parse XML file and return root element"""
        tree = ET.parse(xml_path)
        root = tree.getroot()
        
        # Find first node (root of hierarchy)
        first_node = root.find('node')
        if first_node is None:
            raise ValueError("No nodes found in XML")
        
        return UiHierarchyParser.parse_node(first_node)


class UiIssueDetector:
    """Detect UI layout issues"""
    
    # Constants (assuming 3x density ~420dpi)
    MIN_TOUCH_TARGET = 144  # 48dp in pixels
    CHAR_WIDTH_ESTIMATE = 20  # Average character width in pixels
    
    def __init__(self, density: float = 3.0):
        self.density = density
        self.min_touch_target = int(48 * density)  # 48dp to pixels
    
    def analyze_hierarchy(self, root: UiElement) -> List[UiIssue]:
        """Analyze entire hierarchy for issues"""
        issues = []
        self._traverse_and_check(root, issues)
        return issues
    
    def _traverse_and_check(self, element: UiElement, issues: List[UiIssue]):
        """Recursively check element and children"""
        
        # 1. Check text truncation
        if element.text and len(element.text) > 0:
            estimated_width = len(element.text) * self.CHAR_WIDTH_ESTIMATE
            if estimated_width > element.bounds.width * 0.9:
                element.is_likely_truncated = True
                issues.append(UiIssue(
                    severity='HIGH',
                    issue_type='TEXT_TRUNCATED',
                    element=element,
                    description=f"Text '{element.text}' appears truncated. "
                               f"Available width: {element.bounds.width}px, "
                               f"estimated need: {estimated_width}px",
                    suggestion="Increase container width or reduce text length",
                    confidence=0.85
                ))
        
        # 2. Check touch target size
        if element.clickable:
            if (element.bounds.width < self.min_touch_target or 
                element.bounds.height < self.min_touch_target):
                element.touch_target_too_small = True
                issues.append(UiIssue(
                    severity='MEDIUM',
                    issue_type='TOUCH_TARGET_TOO_SMALL',
                    element=element,
                    description=f"Clickable element too small: "
                               f"{element.bounds.width}x{element.bounds.height}px. "
                               f"Minimum recommended: {self.min_touch_target}px "
                               f"(48dp at {self.density}x density)",
                    suggestion=f"Increase size to at least 48dp ({self.min_touch_target}px)",
                    confidence=1.0
                ))
        
        # 3. Check button text wrapping
        if 'Button' in element.element_type and element.children:
            text_children = [c for c in element.children if 'TextView' in c.element_type]
            if text_children:
                text_child = text_children[0]
                # Text taking up more than 50% of button height suggests wrapping
                if text_child.bounds.height > element.bounds.height * 0.5:
                    element.has_text_wrapping = True
                    issues.append(UiIssue(
                        severity='MEDIUM',
                        issue_type='TEXT_WRAPPED',
                        element=element,
                        description=f"Button text appears to wrap to multiple lines. "
                                   f"Text height ({text_child.bounds.height}px) is "
                                   f"{(text_child.bounds.height/element.bounds.height*100):.0f}% "
                                   f"of button height",
                        suggestion="Use shorter text or increase button width",
                        confidence=0.9
                    ))
        
        # 4. Check for missing accessibility labels
        if element.clickable and not element.content_desc and not element.text:
            issues.append(UiIssue(
                severity='MEDIUM',
                issue_type='MISSING_CONTENT_DESCRIPTION',
                element=element,
                description="Clickable element has no text or content description",
                suggestion="Add contentDescription for accessibility",
                confidence=1.0
            ))
        
        # 5. Check for overlapping clickable elements
        if element.clickable:
            for sibling in element.children:
                if sibling.clickable and element.bounds.overlaps(sibling.bounds):
                    element.has_overlapping_elements = True
                    issues.append(UiIssue(
                        severity='HIGH',
                        issue_type='OVERLAPPING_ELEMENTS',
                        element=element,
                        description=f"Clickable element overlaps with another: "
                                   f"{sibling.text or sibling.element_type}",
                        suggestion="Adjust layout to prevent overlapping touch targets",
                        confidence=1.0
                    ))
        
        # Recurse into children
        for child in element.children:
            self._traverse_and_check(child, issues)


class ReportGenerator:
    """Generate analysis reports"""
    
    @staticmethod
    def generate_json_report(
        root: UiElement,
        issues: List[UiIssue],
        output_path: str,
        metadata: Optional[Dict] = None
    ):
        """Generate JSON report"""
        report = {
            'metadata': metadata or {},
            'summary': {
                'totalIssues': len(issues),
                'critical': sum(1 for i in issues if i.severity == 'CRITICAL'),
                'high': sum(1 for i in issues if i.severity == 'HIGH'),
                'medium': sum(1 for i in issues if i.severity == 'MEDIUM'),
                'low': sum(1 for i in issues if i.severity == 'LOW')
            },
            'issues': [issue.to_dict() for issue in issues],
            'hierarchy': root.to_dict()
        }
        
        with open(output_path, 'w') as f:
            json.dump(report, f, indent=2)
    
    @staticmethod
    def generate_ai_prompt(
        issues: List[UiIssue],
        screenshot_path: Optional[str] = None
    ) -> str:
        """Generate prompt for AI analysis"""
        
        prompt = f"""# UI Analysis Request

I've detected {len(issues)} potential UI issues in my Android app. 
Please review and provide additional insights.

## Detected Issues

"""
        for i, issue in enumerate(issues, 1):
            prompt += f"""
### Issue {i}: {issue.issue_type}
- **Severity**: {issue.severity}
- **Element**: {issue.element.element_type}
- **Text**: "{issue.element.text}"
- **Bounds**: {issue.element.bounds.width}x{issue.element.bounds.height}px at ({issue.element.bounds.left}, {issue.element.bounds.top})
- **Description**: {issue.description}
- **Suggested Fix**: {issue.suggestion}
- **Confidence**: {issue.confidence:.0%}

"""
        
        prompt += """
## Questions for You

1. Are these issues correctly identified?
2. What is the recommended fix priority?
3. Are there any issues I might have missed?
4. Can you suggest specific code changes?

"""
        
        if screenshot_path:
            prompt += f"""
## Visual Reference

Screenshot available at: {screenshot_path}

Please analyze the screenshot to:
- Confirm the detected issues
- Identify any visual problems not caught by structural analysis
- Suggest improvements to visual design
"""
        
        return prompt
    
    @staticmethod
    def generate_html_report(
        root: UiElement,
        issues: List[UiIssue],
        output_path: str,
        screenshot_path: Optional[str] = None
    ):
        """Generate HTML report with visualization"""
        
        # Read screenshot as base64 if available
        screenshot_data = ""
        if screenshot_path and Path(screenshot_path).exists():
            with open(screenshot_path, 'rb') as f:
                screenshot_data = base64.b64encode(f.read()).decode()
        
        html = f"""<!DOCTYPE html>
<html>
<head>
    <title>UI Analysis Report</title>
    <style>
        body {{ font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', sans-serif; margin: 20px; }}
        .summary {{ background: #f5f5f5; padding: 20px; border-radius: 8px; margin-bottom: 20px; }}
        .issue {{ border-left: 4px solid #ccc; padding: 15px; margin: 10px 0; background: white; }}
        .issue.critical {{ border-color: #d32f2f; background: #ffebee; }}
        .issue.high {{ border-color: #f57c00; background: #fff3e0; }}
        .issue.medium {{ border-color: #fbc02d; background: #fffde7; }}
        .issue.low {{ border-color: #388e3c; background: #e8f5e9; }}
        .element-info {{ font-family: monospace; font-size: 12px; background: #f5f5f5; padding: 10px; border-radius: 4px; }}
        .screenshot {{ max-width: 400px; border: 1px solid #ddd; margin: 20px 0; }}
        h1 {{ color: #1976d2; }}
        h2 {{ color: #424242; }}
        .badge {{ padding: 4px 8px; border-radius: 4px; font-size: 12px; font-weight: bold; }}
        .badge.critical {{ background: #d32f2f; color: white; }}
        .badge.high {{ background: #f57c00; color: white; }}
        .badge.medium {{ background: #fbc02d; color: black; }}
        .badge.low {{ background: #388e3c; color: white; }}
    </style>
</head>
<body>
    <h1>🔍 UI Analysis Report</h1>
    
    <div class="summary">
        <h2>Summary</h2>
        <p><strong>Total Issues:</strong> {len(issues)}</p>
        <p>
            <span class="badge critical">CRITICAL: {sum(1 for i in issues if i.severity == 'CRITICAL')}</span>
            <span class="badge high">HIGH: {sum(1 for i in issues if i.severity == 'HIGH')}</span>
            <span class="badge medium">MEDIUM: {sum(1 for i in issues if i.severity == 'MEDIUM')}</span>
            <span class="badge low">LOW: {sum(1 for i in issues if i.severity == 'LOW')}</span>
        </p>
    </div>
"""
        
        if screenshot_data:
            html += f"""
    <div>
        <h2>Screenshot</h2>
        <img src="data:image/png;base64,{screenshot_data}" class="screenshot" alt="UI Screenshot" />
    </div>
"""
        
        html += """
    <h2>Detected Issues</h2>
"""
        
        for i, issue in enumerate(issues, 1):
            html += f"""
    <div class="issue {issue.severity.lower()}">
        <h3>{i}. <span class="badge {issue.severity.lower()}">{issue.severity}</span> {issue.issue_type.replace('_', ' ').title()}</h3>
        <p><strong>Description:</strong> {issue.description}</p>
        <p><strong>Suggestion:</strong> {issue.suggestion}</p>
        <p><strong>Confidence:</strong> {issue.confidence:.0%}</p>
        <div class="element-info">
            <strong>Element:</strong><br/>
            Type: {issue.element.element_type}<br/>
            Text: "{issue.element.text}"<br/>
            Bounds: {issue.element.bounds.width}x{issue.element.bounds.height}px at ({issue.element.bounds.left}, {issue.element.bounds.top})<br/>
            Clickable: {issue.element.clickable}, Enabled: {issue.element.enabled}
        </div>
    </div>
"""
        
        html += """
</body>
</html>
"""
        
        with open(output_path, 'w') as f:
            f.write(html)


def main():
    """Main entry point"""
    import argparse
    
    parser = argparse.ArgumentParser(description='Analyze Android UI dumps for layout issues')
    parser.add_argument('xml_file', help='Path to UI Automator XML dump')
    parser.add_argument('--screenshot', help='Path to screenshot PNG', default=None)
    parser.add_argument('--output', help='Output directory', default='.')
    parser.add_argument('--density', type=float, default=3.0, help='Screen density (default: 3.0)')
    
    args = parser.parse_args()
    
    print(f"📱 Analyzing UI dump: {args.xml_file}")
    
    # Parse hierarchy
    root = UiHierarchyParser.parse_xml_file(args.xml_file)
    print(f"✅ Parsed UI hierarchy")
    
    # Detect issues
    detector = UiIssueDetector(density=args.density)
    issues = detector.analyze_hierarchy(root)
    print(f"🔍 Found {len(issues)} issues")
    
    # Generate reports
    output_dir = Path(args.output)
    output_dir.mkdir(exist_ok=True)
    
    # JSON report
    json_path = output_dir / 'ui-analysis.json'
    ReportGenerator.generate_json_report(
        root, issues, str(json_path),
        metadata={
            'sourceFile': args.xml_file,
            'screenshot': args.screenshot,
            'density': args.density
        }
    )
    print(f"📄 JSON report: {json_path}")
    
    # HTML report
    html_path = output_dir / 'ui-analysis.html'
    ReportGenerator.generate_html_report(root, issues, str(html_path), args.screenshot)
    print(f"📊 HTML report: {html_path}")
    
    # AI prompt
    prompt_path = output_dir / 'ai-prompt.txt'
    prompt = ReportGenerator.generate_ai_prompt(issues, args.screenshot)
    with open(prompt_path, 'w') as f:
        f.write(prompt)
    print(f"🤖 AI prompt: {prompt_path}")
    
    # Summary
    print(f"\n📋 Summary:")
    print(f"   Total Issues: {len(issues)}")
    print(f"   Critical: {sum(1 for i in issues if i.severity == 'CRITICAL')}")
    print(f"   High: {sum(1 for i in issues if i.severity == 'HIGH')}")
    print(f"   Medium: {sum(1 for i in issues if i.severity == 'MEDIUM')}")
    print(f"   Low: {sum(1 for i in issues if i.severity == 'LOW')}")
    
    if issues:
        print(f"\n⚠️  Top Issues:")
        for issue in sorted(issues, key=lambda i: ('CRITICAL','HIGH','MEDIUM','LOW').index(i.severity))[:5]:
            print(f"   • [{issue.severity}] {issue.issue_type}: {issue.description[:80]}...")


if __name__ == '__main__':
    main()
