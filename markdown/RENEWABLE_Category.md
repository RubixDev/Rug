# List of Rules in the RENEWABLE Category

For a list of all implemented Rules go [here](../README.md)
## Index
Count: 11
- [basaltToBlackstoneConversion](#basalttoblackstoneconversion)
- [basaltToLavaConversion](#basalttolavaconversion)
- [cobbleCrushing](#cobblecrushing)
- [craftableCobwebs](#craftablecobwebs)
- [craftableHorseArmor](#craftablehorsearmor)
- [craftableNotchApple](#craftablenotchapple)
- [craftableTuff](#craftabletuff)
- [dragonDrops](#dragondrops)
- [dragonEggConvertsCobbleToEndstone](#dragoneggconvertscobbletoendstone)
- [gravelCrushing](#gravelcrushing)
- [netherrackGeneration](#netherrackgeneration)

## Rules in RENEWABLE Category

### basaltToBlackstoneConversion
Basalt converts to Blackstone if next to both Lava and Water
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `EXPERIMENTAL`, `FEATURE`, `RENEWABLE`, `RUG`, `SURVIVAL`

### basaltToLavaConversion
Basalt converts to a Lava source when placed (or moved) next to a Lava Source, Flowing Lava and a Magma Block
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `FEATURE`, `RENEWABLE`, `RUG`, `SURVIVAL`

### cobbleCrushing
Cobblestone crushed by a falling Anvil will convert into Gravel

Carpet-Extra's renewableSand is prioritized over this
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `FEATURE`, `RENEWABLE`, `RUG`, `SURVIVAL`

### craftableCobwebs
Cobwebs can be crafted with 5 Strings in a cross pattern or with a 3x3 full area

Expect a lag spike when changing the value
- Type: `String`
- Default value: `off`
- Required options: `off`, `cross`, `full`
- Categories: `CRAFTING`, `RENEWABLE`, `RUG`, `SURVIVAL`

### craftableHorseArmor
Horse Armor can be crafted in vanilla_style (H-shape), with_saddle (like in VanillaTweaks), with armor_pieces, or with wool (like in 13w16a)

Expect a lag spike when changing the value
- Type: `String`
- Default value: `off`
- Required options: `off`, `vanilla_style`, `with_saddle`, `armor_pieces`, `with_wool`
- Categories: `CRAFTING`, `RENEWABLE`, `RUG`, `SURVIVAL`

### craftableNotchApple
Enchanted Golden Apples can be crafted with 8 Gold Blocks again

Expect a lag spike when changing the value
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `CRAFTING`, `RENEWABLE`, `RUG`, `SURVIVAL`

### craftableTuff
Craft tuff from andesite and cobblestone

Expect a lag spike when changing the value
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `CRAFTING`, `RENEWABLE`, `RUG`, `SURVIVAL`

### dragonDrops
Ender Dragon drops selected item(s) when killed
- Type: `String`
- Default value: `none`
- Required options: `none`, `dragon_egg`, `elytra`, `dragon_head`, `dragon_egg,elytra`, `dragon_egg,dragon_head`, `elytra,dragon_head`, `all`
- Categories: `FEATURE`, `RENEWABLE`, `RUG`, `SURVIVAL`
- Additional notes:
  - Idea from [VanillaTweaks](https://vanillatweaks.net/picker/datapacks/)

### dragonEggConvertsCobbleToEndstone
Dragon Eggs will convert Cobble under them to Endstone either on set event
- Type: `String`
- Default value: `off`
- Required options: `off`, `on_teleport`, `on_landing`, `both`
- Categories: `EXPERIMENTAL`, `FEATURE`, `RENEWABLE`, `RUG`

### gravelCrushing
Gravel crushed by a falling Anvil will convert into Sand
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `FEATURE`, `RENEWABLE`, `RUG`, `SURVIVAL`

### netherrackGeneration
Netherrack is generated instead of Cobblestone if a Magma Block is below
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `EXPERIMENTAL`, `FEATURE`, `RENEWABLE`, `RUG`, `SURVIVAL`
