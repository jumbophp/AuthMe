# Changelog

## [8.0.0+1.20.5](https://github.com/jumbophp/AuthMe/compare/v8.0.0+1.20.5...v8.0.0+1.20.5) (2024-06-03)


### ⚠ BREAKING CHANGES

* Mojang (or legacy) authentication is no longer available, see https://aka.ms/MinecraftPostMigrationFAQ
* upgrade to Minecraft 1.20 ([#82](https://github.com/jumbophp/AuthMe/issues/82))
* upgrade to Minecraft 1.19.4 ([#78](https://github.com/jumbophp/AuthMe/issues/78))
* Drops support for Minecraft <= 1.19.2
* upgrade Minecraft (1.18.2 -> 1.19) ([#48](https://github.com/jumbophp/AuthMe/issues/48))
* use `googleapis/release-please` for releases
* no longer works on Minecraft 1.18.1
* rewrite the mod to support multiple login methods

### Features

* add a warning screen when custom Microsoft auth URLs are in use ([#55](https://github.com/jumbophp/AuthMe/issues/55)) ([563b8e7](https://github.com/jumbophp/AuthMe/commit/563b8e721923727e1621ae191d90430fcced4013))
* add Chinese translation ([738f822](https://github.com/jumbophp/AuthMe/commit/738f822b2235baaad00e92dba04ee3bb073a8125))
* add error message for when the account has not purchased Minecraft (closes [#101](https://github.com/jumbophp/AuthMe/issues/101)) ([#104](https://github.com/jumbophp/AuthMe/issues/104)) ([a6b34e9](https://github.com/jumbophp/AuthMe/commit/a6b34e94f4a21452a319673497cce0097de2f367))
* add Finnish translation ([#45](https://github.com/jumbophp/AuthMe/issues/45)) ([0924997](https://github.com/jumbophp/AuthMe/commit/092499713c03526f50f5c20c360443c0fa679acb))
* add French translation ([#37](https://github.com/jumbophp/AuthMe/issues/37)) ([d64353b](https://github.com/jumbophp/AuthMe/commit/d64353bd757b550b865365a6486c033bcb4ca536))
* add German translation ([f3725e6](https://github.com/jumbophp/AuthMe/commit/f3725e656d7249b48068698b7692ef3eb5698b75))
* add greeting tooltip to auth button ([cb72cdb](https://github.com/jumbophp/AuthMe/commit/cb72cdb6e37a01cd20a581a107b3fbe96ffd9c70))
* add login success toast ([cb72cdb](https://github.com/jumbophp/AuthMe/commit/cb72cdb6e37a01cd20a581a107b3fbe96ffd9c70))
* add Microsoft login ([cb72cdb](https://github.com/jumbophp/AuthMe/commit/cb72cdb6e37a01cd20a581a107b3fbe96ffd9c70))
* add Polish translation ([#36](https://github.com/jumbophp/AuthMe/issues/36)) ([00fa1bc](https://github.com/jumbophp/AuthMe/commit/00fa1bc7be2b3d195e912d6569acf1ce24d8f10e))
* add traditional Chinese translation ([#52](https://github.com/jumbophp/AuthMe/issues/52)) ([423c495](https://github.com/jumbophp/AuthMe/commit/423c4955c4394fba4f0840cc93bff6103c348b5d))
* **microsoft:** `ctrl + click` the Microsoft button to choose account (closes [#46](https://github.com/jumbophp/AuthMe/issues/46)) ([#84](https://github.com/jumbophp/AuthMe/issues/84)) ([d63c490](https://github.com/jumbophp/AuthMe/commit/d63c490cdde4151f2508036c5cff2599dba199ae))
* rewrite ([cb72cdb](https://github.com/jumbophp/AuthMe/commit/cb72cdb6e37a01cd20a581a107b3fbe96ffd9c70))
* show 're-login' button for more disconnection reasons ([cb72cdb](https://github.com/jumbophp/AuthMe/commit/cb72cdb6e37a01cd20a581a107b3fbe96ffd9c70))
* upgrade `cloth-config-fabric` (7 -&gt; 8) ([2d9e00a](https://github.com/jumbophp/AuthMe/commit/2d9e00a3bfaf251a6cce3b432580b805ea0f0d6d))
* upgrade Minecraft (1.18.1 -&gt; 1.18.2) ([#41](https://github.com/jumbophp/AuthMe/issues/41)) ([717c24f](https://github.com/jumbophp/AuthMe/commit/717c24ff2d47362b32bead6f38bf003820149b8a))
* upgrade Minecraft (1.18.2 -&gt; 1.19) ([#48](https://github.com/jumbophp/AuthMe/issues/48)) ([3f6541a](https://github.com/jumbophp/AuthMe/commit/3f6541ae38eb44f07d9193d2cc1ee24da8701216))
* upgrade to Minecraft 1.18 ([4f01859](https://github.com/jumbophp/AuthMe/commit/4f018596d547cd1255d43219367461fa7996621e))
* upgrade to Minecraft 1.19.3 (fixes [#70](https://github.com/jumbophp/AuthMe/issues/70)) ([#71](https://github.com/jumbophp/AuthMe/issues/71)) ([9753f1a](https://github.com/jumbophp/AuthMe/commit/9753f1ac8b5de8a0adec7b369e69845a699e56a6))
* upgrade to Minecraft 1.20 ([#82](https://github.com/jumbophp/AuthMe/issues/82)) ([d960644](https://github.com/jumbophp/AuthMe/commit/d960644cabf45a08c9bdbbc927154a5d9ec939de))
* upgrade to Minecraft 1.20.2 ([#102](https://github.com/jumbophp/AuthMe/issues/102)) ([c1adb99](https://github.com/jumbophp/AuthMe/commit/c1adb99e758bb1a12f61d1e8dadba3ce0c10ea7b))


### Bug Fixes

* 'IS YOU' splash text should reflect the new username (fixes [#65](https://github.com/jumbophp/AuthMe/issues/65)) ([#67](https://github.com/jumbophp/AuthMe/issues/67)) ([2fc0ab2](https://github.com/jumbophp/AuthMe/commit/2fc0ab2c060dc91814814d0dcef1839e4dcd2531))
* disconnected screen crashes the game when adding the re-login button (fixes [#87](https://github.com/jumbophp/AuthMe/issues/87)) ([#88](https://github.com/jumbophp/AuthMe/issues/88)) ([e740900](https://github.com/jumbophp/AuthMe/commit/e740900ee8a8d75dd21ec6663b7cefbbbd0d6568))
* do not rerun the Microsoft login task when resizing the window ([#38](https://github.com/jumbophp/AuthMe/issues/38)) ([0fb8842](https://github.com/jumbophp/AuthMe/commit/0fb8842e59312864994d50e9007e5d9adc5a6a7e))
* **lang:** update Traditional Chinese ([#92](https://github.com/jumbophp/AuthMe/issues/92)) ([5d7218c](https://github.com/jumbophp/AuthMe/commit/5d7218c2dcf4c735fe6ba79e148c24c2159c60cf))
* **microsoft:** 'Chat disabled due to missing profile public key' error ([#91](https://github.com/jumbophp/AuthMe/issues/91)) ([3fcdb51](https://github.com/jumbophp/AuthMe/commit/3fcdb51a65d2bdf5476f7c5e4be6aa566f452858))
* **microsoft:** update Microsoft OAuth2 token endpoints (fixes [#57](https://github.com/jumbophp/AuthMe/issues/57)) ([#83](https://github.com/jumbophp/AuthMe/issues/83)) ([e606c2a](https://github.com/jumbophp/AuthMe/commit/e606c2ae7cbab004d90d1b06a2b6f81675454f27))
* re-login button should appear on the disconnected screen (fixes [#63](https://github.com/jumbophp/AuthMe/issues/63)) ([#64](https://github.com/jumbophp/AuthMe/issues/64)) ([f0bf6e2](https://github.com/jumbophp/AuthMe/commit/f0bf6e28564cbdf834123be502f05972de9059c3))
* Realms screen ([c804805](https://github.com/jumbophp/AuthMe/commit/c8048056a414ca817ff7450e6ea4cf3d860049ea))
* unable to join servers with chat verification turned on (fixes [#60](https://github.com/jumbophp/AuthMe/issues/60)) ([#66](https://github.com/jumbophp/AuthMe/issues/66)) ([d8ee98d](https://github.com/jumbophp/AuthMe/commit/d8ee98d081754f864735544390b7c773c31723df))


### Miscellaneous Chores

* release as v7.0.1+1.20 ([9ab8526](https://github.com/jumbophp/AuthMe/commit/9ab85267ffc02bab9c93b49c1376c38f95d55ffe))
* release as v8.0.0+1.20.2 ([8736a6a](https://github.com/jumbophp/AuthMe/commit/8736a6a281c94f53bddf1133e6abff6d42820762))
* upgrade to Minecraft 1.19.4 ([#78](https://github.com/jumbophp/AuthMe/issues/78)) ([97c0a1a](https://github.com/jumbophp/AuthMe/commit/97c0a1ad6a547a0718575bb7ba2f21956524e99c))


### Build System

* add support for Minecraft 1.20.3+ ([#107](https://github.com/jumbophp/AuthMe/issues/107)) ([11a6d70](https://github.com/jumbophp/AuthMe/commit/11a6d704d44e943a85aad01f8b089790e7ad364c))
* add support for Minecraft 1.20.5+ ([#113](https://github.com/jumbophp/AuthMe/issues/113)) ([456a273](https://github.com/jumbophp/AuthMe/commit/456a273edb90b6076f8f376f294166119f0dc3b9))


### Continuous Integration

* use `googleapis/release-please` for releases ([717c24f](https://github.com/jumbophp/AuthMe/commit/717c24ff2d47362b32bead6f38bf003820149b8a))

## [8.0.0+1.20.5](https://github.com/axieum/authme/compare/v8.0.0+1.20.4...v8.0.0+1.20.5) (2024-04-28)


### Build System

* add support for Minecraft 1.20.5+ ([#113](https://github.com/axieum/authme/issues/113)) ([456a273](https://github.com/axieum/authme/commit/456a273edb90b6076f8f376f294166119f0dc3b9))

## [8.0.0+1.20.4](https://github.com/axieum/authme/compare/v8.0.0+1.20.2...v8.0.0+1.20.4) (2023-12-10)


### Build System

* add support for Minecraft 1.20.3+ ([#107](https://github.com/axieum/authme/issues/107)) ([11a6d70](https://github.com/axieum/authme/commit/11a6d704d44e943a85aad01f8b089790e7ad364c))

## [8.0.0+1.20.2](https://github.com/axieum/authme/compare/v7.0.2+1.20...v8.0.0+1.20.2) (2023-10-15)


### ⚠ BREAKING CHANGES

* Mojang (or legacy) authentication is no longer available, see https://aka.ms/MinecraftPostMigrationFAQ

### Features

* add error message for when the account has not purchased Minecraft (closes [#101](https://github.com/axieum/authme/issues/101)) ([#104](https://github.com/axieum/authme/issues/104)) ([a6b34e9](https://github.com/axieum/authme/commit/a6b34e94f4a21452a319673497cce0097de2f367))
* upgrade to Minecraft 1.20.2 ([#102](https://github.com/axieum/authme/issues/102)) ([c1adb99](https://github.com/axieum/authme/commit/c1adb99e758bb1a12f61d1e8dadba3ce0c10ea7b))


### Miscellaneous Chores

* release as v8.0.0+1.20.2 ([8736a6a](https://github.com/axieum/authme/commit/8736a6a281c94f53bddf1133e6abff6d42820762))

## [7.0.2+1.20](https://github.com/axieum/authme/compare/v7.0.1+1.20...v7.0.2+1.20) (2023-07-07)


### Bug Fixes

* **lang:** update Traditional Chinese ([#92](https://github.com/axieum/authme/issues/92)) ([5d7218c](https://github.com/axieum/authme/commit/5d7218c2dcf4c735fe6ba79e148c24c2159c60cf))
* **microsoft:** 'Chat disabled due to missing profile public key' error ([#91](https://github.com/axieum/authme/issues/91)) ([3fcdb51](https://github.com/axieum/authme/commit/3fcdb51a65d2bdf5476f7c5e4be6aa566f452858))

## [7.0.1+1.20](https://github.com/axieum/authme/compare/v7.0.0...v7.0.1+1.20) (2023-06-10)


### Bug Fixes

* disconnected screen crashes the game when adding the re-login button (fixes [#87](https://github.com/axieum/authme/issues/87)) ([#88](https://github.com/axieum/authme/issues/88)) ([e740900](https://github.com/axieum/authme/commit/e740900ee8a8d75dd21ec6663b7cefbbbd0d6568))


### Miscellaneous Chores

* release as v7.0.1+1.20 ([9ab8526](https://github.com/axieum/authme/commit/9ab85267ffc02bab9c93b49c1376c38f95d55ffe))

## [7.0.0](https://github.com/axieum/authme/compare/v6.1.0...v7.0.0) (2023-06-08)


### ⚠ BREAKING CHANGES

* upgrade to Minecraft 1.20 ([#82](https://github.com/axieum/authme/issues/82))

### Features

* upgrade to Minecraft 1.20 ([#82](https://github.com/axieum/authme/issues/82)) ([d960644](https://github.com/axieum/authme/commit/d960644cabf45a08c9bdbbc927154a5d9ec939de))

## [6.1.0](https://github.com/axieum/authme/compare/v6.0.0...v6.1.0) (2023-06-07)


### Features

* **microsoft:** `ctrl + click` the Microsoft button to choose account (closes [#46](https://github.com/axieum/authme/issues/46)) ([#84](https://github.com/axieum/authme/issues/84)) ([d63c490](https://github.com/axieum/authme/commit/d63c490cdde4151f2508036c5cff2599dba199ae))


### Bug Fixes

* **microsoft:** update Microsoft OAuth2 token endpoints (fixes [#57](https://github.com/axieum/authme/issues/57)) ([#83](https://github.com/axieum/authme/issues/83)) ([e606c2a](https://github.com/axieum/authme/commit/e606c2ae7cbab004d90d1b06a2b6f81675454f27))

## [6.0.0](https://github.com/axieum/authme/compare/v5.0.0...v6.0.0) (2023-03-28)


### ⚠ BREAKING CHANGES

* upgrade to Minecraft 1.19.4 ([#78](https://github.com/axieum/authme/issues/78))

### Miscellaneous Chores

* upgrade to Minecraft 1.19.4 ([#78](https://github.com/axieum/authme/issues/78)) ([97c0a1a](https://github.com/axieum/authme/commit/97c0a1ad6a547a0718575bb7ba2f21956524e99c))

## [5.0.0](https://github.com/axieum/authme/compare/v4.2.0...v5.0.0) (2022-12-14)


### ⚠ BREAKING CHANGES

* Drops support for Minecraft <= 1.19.2

### Features

* upgrade to Minecraft 1.19.3 (fixes [#70](https://github.com/axieum/authme/issues/70)) ([#71](https://github.com/axieum/authme/issues/71)) ([9753f1a](https://github.com/axieum/authme/commit/9753f1ac8b5de8a0adec7b369e69845a699e56a6))

## [4.2.0](https://github.com/axieum/authme/compare/v4.1.0...v4.2.0) (2022-10-08)


### Features

* upgrade `cloth-config-fabric` (7 -> 8) ([2d9e00a](https://github.com/axieum/authme/commit/2d9e00a3bfaf251a6cce3b432580b805ea0f0d6d))


### Bug Fixes

* 'IS YOU' splash text should reflect the new username (fixes [#65](https://github.com/axieum/authme/issues/65)) ([#67](https://github.com/axieum/authme/issues/67)) ([2fc0ab2](https://github.com/axieum/authme/commit/2fc0ab2c060dc91814814d0dcef1839e4dcd2531))
* re-login button should appear on the disconnected screen (fixes [#63](https://github.com/axieum/authme/issues/63)) ([#64](https://github.com/axieum/authme/issues/64)) ([f0bf6e2](https://github.com/axieum/authme/commit/f0bf6e28564cbdf834123be502f05972de9059c3))
* unable to join servers with chat verification turned on (fixes [#60](https://github.com/axieum/authme/issues/60)) ([#66](https://github.com/axieum/authme/issues/66)) ([d8ee98d](https://github.com/axieum/authme/commit/d8ee98d081754f864735544390b7c773c31723df))

## [4.1.0](https://github.com/axieum/authme/compare/v4.0.0...v4.1.0) (2022-07-26)


### Features

* add a warning screen when custom Microsoft auth URLs are in use ([#55](https://github.com/axieum/authme/issues/55)) ([563b8e7](https://github.com/axieum/authme/commit/563b8e721923727e1621ae191d90430fcced4013))
* add traditional Chinese translation ([#52](https://github.com/axieum/authme/issues/52)) ([423c495](https://github.com/axieum/authme/commit/423c4955c4394fba4f0840cc93bff6103c348b5d))

## [4.0.0](https://github.com/axieum/authme/compare/v3.1.0...v4.0.0) (2022-06-08)


### ⚠ BREAKING CHANGES

* upgrade Minecraft (1.18.2 -> 1.19) (#48)

### Features

* upgrade Minecraft (1.18.2 -> 1.19) ([#48](https://github.com/axieum/authme/issues/48)) ([3f6541a](https://github.com/axieum/authme/commit/3f6541ae38eb44f07d9193d2cc1ee24da8701216))

## [3.1.0](https://github.com/axieum/authme/compare/v3.0.0...v3.1.0) (2022-04-17)


### Features

* add Finnish translation ([#45](https://github.com/axieum/authme/issues/45)) ([0924997](https://github.com/axieum/authme/commit/092499713c03526f50f5c20c360443c0fa679acb))

## [3.0.0](https://github.com/axieum/authme/compare/v2.2.0...v3.0.0) (2022-03-31)


### ⚠ BREAKING CHANGES

* use `googleapis/release-please` for releases
* no longer works on Minecraft 1.18.1

### Features

* add French translation ([#37](https://github.com/axieum/authme/issues/37)) ([d64353b](https://github.com/axieum/authme/commit/d64353bd757b550b865365a6486c033bcb4ca536))
* add Polish translation ([#36](https://github.com/axieum/authme/issues/36)) ([00fa1bc](https://github.com/axieum/authme/commit/00fa1bc7be2b3d195e912d6569acf1ce24d8f10e))
* upgrade Minecraft (1.18.1 -> 1.18.2) ([#41](https://github.com/axieum/authme/issues/41)) ([717c24f](https://github.com/axieum/authme/commit/717c24ff2d47362b32bead6f38bf003820149b8a))


### Bug Fixes

* do not rerun the Microsoft login task when resizing the window ([#38](https://github.com/axieum/authme/issues/38)) ([0fb8842](https://github.com/axieum/authme/commit/0fb8842e59312864994d50e9007e5d9adc5a6a7e))


### Continuous Integration

* use `googleapis/release-please` for releases ([717c24f](https://github.com/axieum/authme/commit/717c24ff2d47362b32bead6f38bf003820149b8a))
