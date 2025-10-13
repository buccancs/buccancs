# British English Spelling Reference

**Last Modified:** 2025-10-13 20:19 UTC  
**Modified By:** GitHub Copilot CLI  
**Document Type:** Reference Guide

## Required Spelling Conversions

This document provides a reference for American to British English conversions required in all project documentation.

### Common -ize/-ise Words

| American English | British English | Usage Example |
|------------------|-----------------|---------------|
| synchronize | synchronise | Time synchronisation across devices |
| optimize | optimise | Performance optimisation |
| organize | organise | Session organisation |
| initialize | initialise | Sensor initialisation |
| prioritize | prioritise | Task prioritisation |
| authorize | authorise | User authorisation |
| realize | realise | Realise the requirement |
| finalize | finalise | Finalise the session |
| analyze | analyse | Analyse the data |
| visualize | visualise | Visualise the metrics |
| utilize | utilise | Utilise resources |

### Common Spelling Differences

| American English | British English | Notes |
|------------------|-----------------|-------|
| color | colour | CSS properties remain as 'color' |
| behavior | behaviour | Class names may use 'behavior' |
| center | centre | Gravity.CENTER remains unchanged |
| meter | metre | Unit of measurement |
| fiber | fibre | |
| defense | defence | |
| offense | offence | |
| license | licence | (noun); license (verb) |
| practice | practise | (verb); practice (noun) |
| program | programme | (TV/event); program (computer) |

### API and Code Exceptions

The following remain in American English as they are part of established APIs:

- Android API methods: setBackgroundColor, Gravity.CENTER, TextView.setText()
- Kotlin/Java keywords and standard library
- Third-party library identifiers: Shimmer, Topdon, OpenCV APIs
- Protocol Buffer field names
- JSON property names in established schemas
- Variable and function names following established conventions

### In Comments and Documentation

Even when discussing API methods, use British English prose:

`kotlin
// Initialise the colour palette for synchronised rendering
setBackgroundColor(defaultColour) // API method uses American spelling
`

### Commit Messages

Use British English:
- Good: "Optimise synchronisation logic for better behaviour"
- Avoid: "Optimize synchronization logic for better behavior"

### Academic Writing

British English is required for all thesis content:
- Use -ise endings consistently
- Use British spellings: colour, behaviour, centre, analyse
- Follow British punctuation conventions
- Use British date format: DD/MM/YYYY or YYYY-MM-DD

## Tools and Validation

### VS Code Settings

Add to .vscode/settings.json:
`json
{
  "cSpell.language": "en-GB"
}
`

### Regex Patterns for Conversion

Search for American patterns:
`egex
\b(synchroniz|optimiz|organiz|analyz|color|behavior|center)\b
`

Replace with British equivalents.

## Common Mistakes

1. **Color vs Colour**: Remember that API methods like setBackgroundColor() keep American spelling
2. **-ize vs -ise**: British English uses -ise for most words (synchronise, optimise)
3. **Program vs Programme**: Use "programme" for events/TV, "program" for software code
4. **Practice vs Practise**: "Practice" is noun, "practise" is verb in British English

## References

- Oxford English Dictionary
- Cambridge Dictionary
- British National Corpus
- IEEE UK Style Guide
