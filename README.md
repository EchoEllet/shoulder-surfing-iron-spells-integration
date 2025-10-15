# Shoulder Surfing: Iron's Spells Integration

A simple client-side mod that integrates [Shoulder Surfing Reloaded](https://modrinth.com/mod/shoulder-surfing-reloaded)
and [Iron's Spells 'n Spellbooks](https://modrinth.com/mod/irons-spells-n-spellbooks), making the camera automatically
follow your crosshair whenever you cast a spell—via spellbooks, normal spells, or quick cast. Compatible with controller
mods.

[//]: # (Hosted in: https://github.com/EchoEllet/shoulder-surfing-iron-spells-integration/discussions/1)
![output](https://github.com/user-attachments/assets/92e3af27-b409-4beb-ade4-cabb70325115)

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

This mod fixes issues that you may encounter when the Shoulder Surfing camera is decoupled.

Here are the issues that this mod fix:

### Fixing Continuous Spell Casting

This mod uses the [
`ICameraCouplingCallback`](https://github.com/Exopandora/ShoulderSurfing/wiki/API-Documentation-Callbacks#icameracouplingcallback)
callback provided by the Shoulder Surfing Reloaded mod to force camera coupling whenever the player is casting a
continuous spell, regardless of the cast source (e.g., spell book, spell item).

### Look at Crosshair Target When Casting via Spell Book

When casting a spell using a spell item by right-clicking with the mouse, the Shoulder Surfing mod will automatically
look at the crosshair target. This is not the case when casting spells via spell books, which is a common method.

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

### Always Look at Crosshair Target When Using a Spell Item

The Shoulder Surfing mod automatically calls [
`ShoulderSurfingImpl.lookAtCrosshairTarget`](https://github.com/Exopandora/ShoulderSurfing/blob/7f0df83beb4f7158810e188150eb7e9812981529/common/src/main/java/com/github/exopandora/shouldersurfing/client/ShoulderSurfingImpl.java#L125-L129)
when you use any item. However, when using a spell item via controller, Shoulder Surfing does not recognize it. To work
around this, we call `ShoulderSurfingImpl.lookAtCrosshairTarget` before [
`Scroll.use`](https://github.com/iron431/irons-spells-n-spellbooks/blob/4326ce5c42bc87b59260a9ff8f10dcfb90ad7f31/src/main/java/io/redspace/ironsspellbooks/item/Scroll.java#L59-L82)
is called.

This fixes the issue for Shoulder Surfing and Iron Spells mods, but the core issue remains unresolved for other mods;
Shoulder Surfing does not automatically look at the crosshair target when using an item via controller rather than a
vanilla mouse right-click.

## Disclaimer

> **This mod is NOT AN OFFICIAL MINECRAFT PRODUCT.  
> It is NOT APPROVED BY OR ASSOCIATED WITH MOJANG OR MICROSOFT.**
>
> **This mod is not affiliated with the [Shoulder Surfing Reloaded Team](https://github.com/Exopandora/ShoulderSurfing)
or [Iron's Spells 'n Spellbooks](https://github.com/iron431/Irons-Spells-n-Spellbooks).**

## License

[MIT](https://github.com/EchoEllet/shoulder-surfing-iron-spells-integration/blob/main/LICENSE)

You can also find the [source code on GitHub](https://github.com/EchoEllet/shoulder-surfing-iron-spells-integration).
