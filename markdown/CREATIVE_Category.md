# List of Rules in the CREATIVE Category

For a list of all implemented Rules go [here](../README.md)
## Index
Count: 3
- [reachDistance](#reachdistance)
- [snowMelting](#snowmelting)
- [waterInNether](#waterinnether)

## Rules in CREATIVE Category

### reachDistance
Reach in which you can place and break blocks. Value will be 0.5 higher in creative

Mod needed on server and client for this feature to work
- Type: `double`
- Default value: `4.5`
- Suggested options: `0.0`, `4.5`, `5.0`, `10.0`
- Categories: `CLIENT`, `CREATIVE`, `EXPERIMENTAL`, `RUG`
- Additional notes:
  - You must choose a value from 0 to 100
  - Is disabled when [reach-entity-attributes](https://github.com/JamiesWhiteShirt/reach-entity-attributes) or [Pehkui](https://www.curseforge.com/minecraft/mc-mods/pehkui) is installed

### snowMelting
Whether snow can melt
- Type: `boolean`
- Default value: `true`
- Required options: `true`, `false`
- Categories: `CREATIVE`, `RUG`, `SURVIVAL`

### waterInNether
Allow water placing and ice melting in ultrawarm dimensions
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `CREATIVE`, `RUG`
