# List of Rules in the BUGFIX Category

For a list of all implemented Rules go [here](../README.md)
## Index
Count: 3
- [cactusFurnaceXP](#cactusfurnacexp)
- [infinityNeedsArrow](#infinityneedsarrow)
- [stonecutterDamage](#stonecutterdamage)

## Rules in BUGFIX Category

### cactusFurnaceXP
Amount of XP a Cactus smelted in a furnace gives  
1 XP per Cactus seems to be a bug, as in Bedrock Edition it's only 0.2, which fits more in line with other items  
- Type: `double`
- Default value: `1`
- Suggested options: `0.1`, `0.2`, `0.5`, `1`
- Categories: `BUGFIX`, `SURVIVAL`, `RUG`
- Additional notes:
  - You must choose a value from 0 to 1

### infinityNeedsArrow
A Bow enchanted with Infinity needs the player to have an arrow in his inventory    
- Type: `boolean`
- Default value: `true`
- Required options: `true`, `false`
- Categories: `EXPERIMENTAL`, `BUGFIX`, `SURVIVAL`, `RUG`

### stonecutterDamage
How much damage Stonecutters deal when stepping on them    
- Type: `int`
- Default value: `0`
- Suggested options: `0`, `3`, `4`, `5`
- Categories: `BUGFIX`, `SURVIVAL`, `RUG`
- Additional notes:
  - You must choose a value from 0 to 10
