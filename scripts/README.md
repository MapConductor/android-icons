# Icon pack generator

The SVG files and `artwork/manifest.json` in each Android icon repository are
the source of truth for all three SDKs.

```bash
node android-icons/scripts/generate-icon-packs.mjs
node android-icons/scripts/generate-icon-packs.mjs --check
node android-icons/scripts/generate-icon-packs.mjs --pack=weather
```

SVG paths must use a `0 0 24 24` viewBox and normalized absolute `M`, `L`, `C`
and `Z` commands. The generator rejects duplicate IDs, invalid namespaces,
unsupported path commands and missing platform metadata.
