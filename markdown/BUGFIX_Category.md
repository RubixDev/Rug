# List of Rules in the BUGFIX Category

For a list of all implemented Rules go [here](../README.md)
## Index
Count: 5
- [cactusFurnaceXp](#cactusfurnacexp)
- [infinityNeedsArrow](#infinityneedsarrow)
- [itemFramesActivatePressurePlates](#itemframesactivatepressureplates)
- [missingCobbleRecipes](#missingcobblerecipes)
- [stonecutterDamage](#stonecutterdamage)

## Rules in BUGFIX Category

### cactusFurnaceXp
Amount of XP a Cactus smelted in a furnace gives

1 XP per Cactus seems to be a bug, as in Bedrock Edition it's only 0.2, which fits more in line with other items
- Type: `double`
- Default value: `1.0`
- Suggested options: `0.1`, `0.2`, `0.5`, `1.0`
- Categories: `BUGFIX`, `RUG`, `SURVIVAL`
- Additional notes:
  - You must choose a value from 0 to 1

### infinityNeedsArrow
A Bow enchanted with Infinity needs the player to have an arrow in their inventory
- Type: `boolean`
- Default value: `true`
- Required options: `true`, `false`
- Categories: `BUGFIX`, `EXPERIMENTAL`, `RUG`, `SURVIVAL`

### itemFramesActivatePressurePlates
Item Frames inside wooden pressure plates hold them down
- Type: `boolean`
- Default value: `true`
- Required options: `true`, `false`
- Categories: `BUGFIX`, `RUG`

### missingCobbleRecipes
Adds missing recipes for allowing blackstone and cobbled deepslate in place of cobblestone

Expect a lag spike when changing the value
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `BUGFIX`, `CRAFTING`, `RUG`, `SURVIVAL`

### stonecutterDamage
How much damage Stonecutters deal when stepping on them
- Type: `int`
- Default value: `0`
- Suggested options: `0`, `3`, `4`, `5`
- Categories: `BUGFIX`, `RUG`, `SURVIVAL`
- Additional notes:
  - You must choose a value from 0 to 10
