# List of Rules in the EXPERIMENTAL Category

For a list of all implemented Rules go [here](../README.md)
## Index
Count: 20
- [anvilledBlueIce](#anvilledblueice)
- [anvilledIce](#anvilledice)
- [anvilledPackedIce](#anvilledpackedice)
- [easyHarvesting](#easyharvesting)
- [easyHarvestingRequireHoe](#easyharvestingrequirehoe)
- [edibleGoldIngots](#ediblegoldingots)
- [edibleMagmaCream](#ediblemagmacream)
- [edibleNetheriteScraps](#ediblenetheritescraps)
- [edibleSlimeBalls](#edibleslimeballs)
- [foodInstantHeal](#foodinstantheal)
- [honeyCombStickiness](#honeycombstickiness)
- [infinityNeedsArrow](#infinityneedsarrow)
- [longerRepeaters](#longerrepeaters)
- [newShulkerBehavior](#newshulkerbehavior)
- [peacefulHunger](#peacefulhunger)
- [reachDistance](#reachdistance)
- [silkTouchFarmland](#silktouchfarmland)
- [silkTouchPathBlocks](#silktouchpathblocks)
- [silkTouchSpawners](#silktouchspawners)
- [zeroTickPlants](#zerotickplants)

## Rules in EXPERIMENTAL Category

### anvilledBlueIce
Custom amount of packed ice crushed by falling anvils make one blue ice.    
- Type: `int`
- Default value: `0`
- Suggested options: `0`, `4`, `9`
- Categories: `EXPERIMENTAL`, `FEATURE`, `RUG`
- Additional notes:
  - You must choose a value from 0 to 32
  - From [QuickCarpet](https://github.com/DeadlyMC/QuickCarpet114)

### anvilledIce
Custom amount of frosted ice crushed by falling anvils make one ice. Allows for new type of ice farm.    
- Type: `int`
- Default value: `0`
- Suggested options: `0`, `4`, `9`
- Categories: `EXPERIMENTAL`, `FEATURE`, `RUG`
- Additional notes:
  - You must choose a value from 0 to 32
  - From [QuickCarpet](https://github.com/DeadlyMC/QuickCarpet114)

### anvilledPackedIce
Custom amount of ice crushed by falling anvils make one packed ice.    
- Type: `int`
- Default value: `0`
- Suggested options: `0`, `4`, `9`
- Categories: `EXPERIMENTAL`, `FEATURE`, `RUG`
- Additional notes:
  - You must choose a value from 0 to 32
  - From [QuickCarpet](https://github.com/DeadlyMC/QuickCarpet114)

### easyHarvesting
Right clicking on fully grown crops harvests and immediately replants it  
Works on: Wheat, Potatoes, Carrots, Beetroots, Nether Warts and Cocoa Beans  
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `EXPERIMENTAL`, `FEATURE`, `RUG`, `SURVIVAL`

### easyHarvestingRequireHoe
The easyHarvesting feature requires the player to hold a hoe in his main hand  
Requires easyHarvesting to be enabled  
- Type: `boolean`
- Default value: `true`
- Required options: `true`, `false`
- Categories: `EXPERIMENTAL`, `FEATURE`, `RUG`, `SURVIVAL`

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

### foodInstantHeal
Food heals hearts not hunger like in the first MC versions and naturalRegeneration is off  
Recommended using with peacefulHunger  
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `EXPERIMENTAL`, `FEATURE`, `RUG`, `SURVIVAL`
- Additional notes:
  - Suggestion by [real_zockerhopper](https://www.curseforge.com/members/real_zockerhopper)

### honeyCombStickiness
Honey Comb only sticks to selected Blocks  
Will render Ghost Blocks on the Client when mod is only Server Side  
- Type: `String`
- Default value: `both`
- Required options: `both`, `honey`, `slime`, `none`
- Categories: `CLIENT`, `EXPERIMENTAL`, `FEATURE`, `RUG`
- Additional notes:
  - Suggestion by DragonEggBedrockBreaking#0034

### infinityNeedsArrow
A Bow enchanted with Infinity needs the player to have an arrow in his inventory    
- Type: `boolean`
- Default value: `true`
- Required options: `true`, `false`
- Categories: `BUGFIX`, `EXPERIMENTAL`, `RUG`, `SURVIVAL`

### longerRepeaters
Repeaters on top of Redstone BLocks multiply their delay by set amount    
- Type: `int`
- Default value: `1`
- Required options: `1`, `2`, `3`, `4`
- Categories: `EXPERIMENTAL`, `FEATURE`, `RUG`

### newShulkerBehavior
Makes shulkers behave like in the current 1.17 snapshots  
Shulkers hit by a shulker bullet have a chance to spawn a new shulker and teleport  
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `EXPERIMENTAL`, `FEATURE`, `RUG`

### peacefulHunger
Players do not lose any hunger like in peaceful mode    
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `EXPERIMENTAL`, `FEATURE`, `RUG`, `SURVIVAL`
- Additional notes:
  - Suggestion by [real_zockerhopper](https://www.curseforge.com/members/real_zockerhopper)

### reachDistance
Reach in which you can place and break blocks. Value will be 0.5 higher in creative  
Mod needed on server and client for this feature to work  
- Type: `double`
- Default value: `4.5`
- Suggested options: `0`, `4.5`, `5`, `10`
- Categories: `CLIENT`, `CREATIVE`, `EXPERIMENTAL`, `RUG`
- Additional notes:
  - You must choose a value from 0 to 100

### silkTouchFarmland
Mining Farmland with a Silk Touch tool will drop itself    
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `EXPERIMENTAL`, `FEATURE`, `RUG`, `SURVIVAL`

### silkTouchPathBlocks
Mining Path Blocks with a Silk Touch tool will drop itself    
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `EXPERIMENTAL`, `FEATURE`, `RUG`, `SURVIVAL`

### silkTouchSpawners
Mining Spawners with a Silk Touch tool will drop itself    
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `EXPERIMENTAL`, `FEATURE`, `RUG`, `SURVIVAL`

### zeroTickPlants
Brings back the ability to force grow certain plants using 0-ticks    
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `EXPERIMENTAL`, `RUG`