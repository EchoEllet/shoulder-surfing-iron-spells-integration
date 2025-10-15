# Changelog

All notable changes to this project will be documented in this file.

## [0.0.4] - 2025-10-15

* Updated the mod to use official Shoulder Surfing Reloaded API to force camera coupling when casting a continuous
  spell.
  This uses [
  `ICameraCouplingCallback`](https://github.com/Exopandora/ShoulderSurfing/wiki/API-Documentation-Callbacks#icameracouplingcallback)
  rather than injecting [`ShoulderSurfingImpl.shouldEntityAimAtTargetInternal`](https://github.com/Exopandora/ShoulderSurfing/blob/7f0df83beb4f7158810e188150eb7e9812981529/common/src/main/java/com/github/exopandora/shouldersurfing/client/ShoulderSurfingImpl.java#L158-L164).

## [0.0.3] - 2025-10-15

* Modified Shoulder Surfing mod internals instead of using the `ShoulderSurfingImpl.toggleShoulderSurfingCameraCoupling`
  workaround, which requires running on every client player tick event.

## [0.0.2] - 2025-10-14

* Fixed a minor bug; avoid looking at crosshair target when in first person by applying the fix when only in Shoulder
  Surfing perspective with camera decoupled.

## [0.0.1] - 2025-10-13

* Initial release
