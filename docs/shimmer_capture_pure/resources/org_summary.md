# `resources/org`

- Properties bundles for third-party libraries (Apache HttpClient, BouncyCastle, Conscrypt, etc.).
- Keep in sync with the corresponding Java packages under `sources/org`.
- Some libraries (e.g., Conscrypt) read these `.properties` files to determine build identifiers or enable optional
  features. If you replace the libraries with official Maven artifacts, these resource files will be provided
  automatically.
