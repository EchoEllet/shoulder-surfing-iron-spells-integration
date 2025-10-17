# Shoulder Surfing: Iron's Spells Integration

A simple client-side mod that integrates [Shoulder Surfing Reloaded](https://modrinth.com/mod/shoulder-surfing-reloaded)
and [Iron's Spells 'n Spellbooks](https://modrinth.com/mod/irons-spells-n-spellbooks), making the camera automatically
follow your crosshair whenever you cast a spell—via spellbooks, scroll items, or quick cast. Compatible with controller
mods.

[//]: # (Hosted in: https://github.com/EchoEllet/shoulder-surfing-iron-spells-integration/discussions/1)
![Shoulder Surfing Camera Decoupled](https://github.com/user-attachments/assets/92e3af27-b409-4beb-ade4-cabb70325115)

![Shoulder Surfing Camera Coupled](https://github.com/user-attachments/assets/1aed31a7-2348-4c43-828f-6ff6042b731e)

## Compatibility

Tested with the following mods and works as expected:

* [Epic Fight](https://modrinth.com/mod/epic-fight)
* [Controlify](https://modrinth.com/mod/controlify)
* [Controllable](https://www.curseforge.com/minecraft/mc-mods/controllable)

## Bug reports

This isn't an official Shoulder Surfing Reloaded addon, so any issues should be reported
to [this mod issue tracker](https://github.com/EchoEllet/shoulder-surfing-iron-spells-integration/issues)
rather than to the Shoulder Surfing Reloaded team.

## Technical Implementation

**This mod fixes issues you may encounter when using the Shoulder Surfing Perspective.**  
The fixes are universal and apply **regardless of whether the camera is decoupled or not**.

Here are the issues that this mod fix:

### Continuous Spell Casting

This mod uses the [`IAdaptiveItemCallback`](https://github.com/Exopandora/ShoulderSurfing/wiki/API-Documentation-Callbacks#iadaptiveitemcallback)  
callback provided by the Shoulder Surfing Reloaded mod so that it **identifies as if we're aiming at the crosshair target**,  
ensuring the **player looks at the crosshair target** regardless of the spell cast source (e.g., spell book, scroll item)  
and whether the camera is decoupled or not.

This fix is effective even when the camera is coupled, because without it, casting a continuous spell in Shoulder Surfing
causes the player to look straight ahead instead of at the crosshair.

### Look at Crosshair Target When Casting via Spell Book

When casting a spell using a scroll item by right-clicking with the mouse, the Shoulder Surfing mod will automatically
look at the crosshair target **if the camera is decoupled**.
This is not the case when casting spells via spell books, which is a common method.

Mixins into the constructors of [
`CastPacket`](https://github.com/iron431/irons-spells-n-spellbooks/blob/aa90a5b2826da07c1ed5a6e65e178c35e23c73ec/src/main/java/io/redspace/ironsspellbooks/network/casting/CastPacket.java#L17-L18)
and [
`QuickCastPacket`](https://github.com/iron431/irons-spells-n-spellbooks/blob/aa90a5b2826da07c1ed5a6e65e178c35e23c73ec/src/main/java/io/redspace/ironsspellbooks/network/casting/QuickCastPacket.java#L19-L21),
since these are only constructed from the client side and only when the player is casting a spell from a keybind. This
ensures that the mod stays compatible with any controller mod and remains client-side without sending packets to the
network.

The [official API provided by the Iron Spells mod](https://iron.wiki/developers/#api-vs-full-mod-dependency) is
currently a bit limited, and all events are called on the server side only. This makes it difficult to keep this mod
client-side only while still supporting controller mods and avoiding dependency on their internal APIs.

### Always Look at Crosshair Target When Using a Scroll Item

We call `ShoulderSurfingImpl.lookAtCrosshairTarget` before [
`Scroll.use`](https://github.com/iron431/irons-spells-n-spellbooks/blob/4326ce5c42bc87b59260a9ff8f10dcfb90ad7f31/src/main/java/io/redspace/ironsspellbooks/item/Scroll.java#L59-L82)
is called to fix two issues:

* The Shoulder Surfing mod automatically calls [
  `ShoulderSurfingImpl.lookAtCrosshairTarget`](https://github.com/Exopandora/ShoulderSurfing/blob/7f0df83beb4f7158810e188150eb7e9812981529/common/src/main/java/com/github/exopandora/shouldersurfing/client/ShoulderSurfingImpl.java#L125-L129)
  when you use any item **if the camera is decoupled**. However, when using a scroll item via controller,
  Shoulder Surfing does not recognize it.
* This fix is useful **even if you don't use a controller**, because when the camera is coupled,
`ShoulderSurfingImpl.lookAtCrosshairTarget` is not called automatically. Without this fix,
spells will be cast straight ahead of the player instead of aiming at the crosshair target.

The player will **always** look at the crosshair target, **regardless of whether the camera is coupled or decoupled**,
and regardless of the input method—controller or keyboard.

## Disclaimer

> **This mod is NOT AN OFFICIAL MINECRAFT PRODUCT.  
> It is NOT APPROVED BY OR ASSOCIATED WITH MOJANG OR MICROSOFT.**
>
> **This mod is not affiliated with the [Shoulder Surfing Reloaded Team](https://github.com/Exopandora/ShoulderSurfing)
or [Iron's Spells 'n Spellbooks](https://github.com/iron431/Irons-Spells-n-Spellbooks).**

## License

[MIT](https://github.com/EchoEllet/shoulder-surfing-iron-spells-integration/blob/main/LICENSE)

You can also find the [source code on GitHub](https://github.com/EchoEllet/shoulder-surfing-iron-spells-integration).
