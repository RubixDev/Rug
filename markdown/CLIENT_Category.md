# List of Rules in the CLIENT Category

For a list of all implemented Rules go [here](../README.md)
## Index
Count: 6
- [edibleGoldIngots](#ediblegoldingots)
- [edibleMagmaCream](#ediblemagmacream)
- [edibleNetheriteScraps](#ediblenetheritescraps)
- [edibleSlimeBalls](#edibleslimeballs)
- [honeyCombStickiness](#honeycombstickiness)
- [reachDistance](#reachdistance)

## Rules in CLIENT Category

### edibleGoldIngots
Butter is finally edible. Keep in mind 250g of pure butter are not that healthy  
Works server side only, but eating animation is only rendered if the mod is on the client too  
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `CLIENT`, `EXPERIMENTAL`, `FEATURE`, `RUG`, `SURVIVAL`

### edibleMagmaCream
Magma Cream is edible and gives 10 seconds of Fire Resistance  
Works server side only, but eating animation is only rendered if the mod is on the client too  
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `CLIENT`, `EXPERIMENTAL`, `FEATURE`, `RUG`, `SURVIVAL`

### edibleNetheriteScraps
Makes Netherite Scraps edible, because, let's be honest, they kinda look like chocolate  
Works server side only, but eating animation is only rendered if the mod is on the client too  
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `CLIENT`, `EXPERIMENTAL`, `FEATURE`, `RUG`, `SURVIVAL`

### edibleSlimeBalls
Slime Balls are edible and give Jump Boost and Slowness  
Works server side only, but eating animation is only rendered if the mod is on the client too  
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `CLIENT`, `EXPERIMENTAL`, `FEATURE`, `RUG`, `SURVIVAL`

### honeyCombStickiness
Honey Comb only sticks to selected Blocks  
Will render Ghost Blocks on the Client when mod is only Server Side  
- Type: `String`
- Default value: `both`
- Required options: `both`, `honey`, `slime`, `none`
- Categories: `CLIENT`, `EXPERIMENTAL`, `FEATURE`, `RUG`
- Additional notes:
  - Suggestion by DragonEggBedrockBreaking#0034

### reachDistance
Reach in which you can place and break blocks. Value will be 0.5 higher in creative  
Mod needed on server and client for this feature to work  
- Type: `double`
- Default value: `4.5`
- Suggested options: `0`, `4.5`, `5`, `10`
- Categories: `CLIENT`, `CREATIVE`, `EXPERIMENTAL`, `RUG`
- Additional notes:
  - You must choose a value from 0 to 100