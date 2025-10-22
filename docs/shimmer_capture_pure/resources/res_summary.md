# `resources/res`

- Full Android resource tree (layouts, drawables, colors, strings) combining stock AppCompat assets with PURE-specific
  screens:
    - `layout/fragment_plot.xml`, `fragment_signals_to_plot.xml`, etc. underpin the fragments documented earlier.
    - `values/strings.xml`, `arrays.xml`, and `styles.xml` provide labels, sample lists, and the app theme.
- Use as reference when migrating UI into Buccancs; most resources map 1:1 to the fragment responsibilities described in
  the documentation.
- Check `values/integers.xml` and `values/bools.xml` for configuration constants (plot durations, feature toggles).
  These often match defaults used in the service or fragments.
