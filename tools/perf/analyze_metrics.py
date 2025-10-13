#!/usr/bin/env python3
import argparse
import json
import statistics
from pathlib import Path
from typing import List


def parse_args() -> argparse.Namespace:
    parser = argparse.ArgumentParser(description="Summarise performance_metrics.jsonl samples.")
    parser.add_argument("--input", required=True, help="Path to performance_metrics.jsonl")
    parser.add_argument("--label", default="device", help="Label used in summary output")
    return parser.parse_args()


def load_samples(path: Path) -> List[dict]:
    samples = []
    with path.open("r", encoding="utf-8") as handle:
        for line in handle:
            line = line.strip()
            if not line:
                continue
            try:
                samples.append(json.loads(line))
            except json.JSONDecodeError as exc:
                raise RuntimeError(f"Invalid JSON on line {len(samples) + 1}: {exc}") from exc
    return samples


def compute_summary(samples: List[dict]) -> str:
    if not samples:
        return "No samples captured."

    cpu = [s.get("cpuPercent", 0.0) for s in samples]
    pss = [s.get("memoryPssMb", 0.0) for s in samples]
    heap = [s.get("javaHeapMb", 0.0) for s in samples]
    storage = [s.get("availableStorageMb", 0.0) for s in samples]
    battery = [s.get("batteryTempC") for s in samples if s.get("batteryTempC") is not None]

    def stats(values: List[float]):
        return {
            "avg": statistics.fmean(values) if values else 0.0,
            "p95": statistics.quantiles(values, n=20)[18] if len(values) >= 20 else max(values) if values else 0.0,
            "max": max(values) if values else 0.0,
        }

    cpu_stats = stats(cpu)
    pss_stats = stats(pss)
    heap_stats = stats(heap)
    storage_min = min(storage) if storage else 0.0
    battery_stats = stats(battery) if battery else None

    timestamps = [s.get("timestampEpochMs", 0) for s in samples if s.get("timestampEpochMs") is not None]
    duration_sec = 0.0
    if timestamps:
        duration_sec = max(timestamps) / 1000.0 - min(timestamps) / 1000.0

    lines = [
        f"samples: {len(samples)} over {duration_sec:.1f}s",
        f"cpu: avg {cpu_stats['avg']:.1f}% · p95 {cpu_stats['p95']:.1f}% · max {cpu_stats['max']:.1f}%",
        f"pss: avg {pss_stats['avg']:.1f} MB · p95 {pss_stats['p95']:.1f} MB · max {pss_stats['max']:.1f} MB",
        f"java heap: avg {heap_stats['avg']:.1f} MB · max {heap_stats['max']:.1f} MB",
        f"available storage minimum: {storage_min:.1f} MB",
    ]
    if battery_stats:
        lines.append(
            f"battery temp: avg {battery_stats['avg']:.1f} °C · max {battery_stats['max']:.1f} °C"
        )
    return "\n".join(lines)


def main() -> None:
    args = parse_args()
    path = Path(args.input)
    if not path.exists():
        raise FileNotFoundError(f"Metrics file not found: {path}")
    samples = load_samples(path)
    summary = compute_summary(samples)
    print(f"Summary for {args.label}")
    print(summary)


if __name__ == "__main__":
    main()
