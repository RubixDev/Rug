# List of Rules in the FEATURE Category

For a list of all implemented Rules go [here](../README.md)
## Index
Count: 23
- [anvilledBlueIce](#anvilledblueice)
- [anvilledIce](#anvilledice)
- [anvilledPackedIce](#anvilledpackedice)
- [concreteConvertOnCauldron](#concreteconvertoncauldron)
- [dragonDropsElytra](#dragondropselytra)
- [easyHarvesting](#easyharvesting)
- [easyHarvestingRequireHoe](#easyharvestingrequirehoe)
- [edibleGoldIngots](#ediblegoldingots)
- [edibleMagmaCream](#ediblemagmacream)
- [edibleNetheriteScraps](#ediblenetheritescraps)
- [edibleSlimeBalls](#edibleslimeballs)
- [enderPearlDamage](#enderpearldamage)
- [foodInstantHeal](#foodinstantheal)
- [lilyPadsOnCauldron](#lilypadsoncauldron)
- [longerRepeaters](#longerrepeaters)
- [newShulkerBehavior](#newshulkerbehavior)
- [peacefulHunger](#peacefulhunger)
- [playerHeadDrops](#playerheaddrops)
- [silenceMobs](#silencemobs)
- [silkTouchFarmland](#silktouchfarmland)
- [silkTouchPathBlocks](#silktouchpathblocks)
- [silkTouchSpawners](#silktouchspawners)
- [strictShulkerShells](#strictshulkershells)

## Rules in FEATURE Category

### anvilledBlueIce
Custom amount of packed ice crushed by falling anvils make one blue ice.    
- Type: `int`
- Default value: `0`
- Suggested options: `0`, `4`, `9`
- Categories: `FEATURE`, `EXPERIMENTAL`, `RUG`
- Additional notes:
  - You must choose a value from 0 to 32
  - From [QuickCarpet](https://github.com/DeadlyMC/QuickCarpet114)

### anvilledIce
Custom amount of frosted ice crushed by falling anvils make one ice. Allows for new type of ice farm.    
- Type: `int`
- Default value: `0`
- Suggested options: `0`, `4`, `9`
- Categories: `FEATURE`, `EXPERIMENTAL`, `RUG`
- Additional notes:
  - You must choose a value from 0 to 32
  - From [QuickCarpet](https://github.com/DeadlyMC/QuickCarpet114)

### anvilledPackedIce
Custom amount of ice crushed by falling anvils make one packed ice.    
- Type: `int`
- Default value: `0`
- Suggested options: `0`, `4`, `9`
- Categories: `FEATURE`, `EXPERIMENTAL`, `RUG`
- Additional notes:
  - You must choose a value from 0 to 32
  - From [QuickCarpet](https://github.com/DeadlyMC/QuickCarpet114)

### concreteConvertOnCauldron
Concrete powder converts to concrete blocks when on top of a filled cauldron    
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `FEATURE`, `RUG`

### dragonDropsElytra
Ender Dragon drops an Elytra when killed    
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `FEATURE`, `SURVIVAL`, `RUG`
- Additional notes:
  - Idea from [VanillaTweaks](https://vanillatweaks.net/picker/datapacks/)

### easyHarvesting
Right clicking on fully grown crops harvests and immediately replants it  
Works on: Wheat, Potatoes, Carrots, Beetroots, Nether Warts and Cocoa Beans  
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `EXPERIMENTAL`, `FEATURE`, `SURVIVAL`, `RUG`

### easyHarvestingRequireHoe
The easyHarvesting feature requires the player to hold a hoe in his main hand  
Requires easyHarvesting to be enabled  
- Type: `boolean`
- Default value: `true`
- Required options: `true`, `false`
- Categories: `EXPERIMENTAL`, `FEATURE`, `SURVIVAL`, `RUG`

### edibleGoldIngots
Butter is finally edible. Keep in mind 250g of pure butter are not that healthy  
Works server side only, but eating animation is only rendered if the mod is on the client too  
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `EXPERIMENTAL`, `FEATURE`, `SURVIVAL`, `CLIENT`, `RUG`

### edibleMagmaCream
Magma Cream is edible and gives 10 seconds of Fire Resistance  
Works server side only, but eating animation is only rendered if the mod is on the client too  
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `EXPERIMENTAL`, `FEATURE`, `SURVIVAL`, `CLIENT`, `RUG`

### edibleNetheriteScraps
Makes Netherite Scraps edible, because, let's be honest, they kinda look like chocolate  
Works server side only, but eating animation is only rendered if the mod is on the client too  
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `EXPERIMENTAL`, `FEATURE`, `SURVIVAL`, `CLIENT`, `RUG`

### edibleSlimeBalls
Slime Balls are edible and give Jump Boost and Slowness  
Works server side only, but eating animation is only rendered if the mod is on the client too  
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `EXPERIMENTAL`, `FEATURE`, `SURVIVAL`, `CLIENT`, `RUG`

### enderPearlDamage
Amount of damage dealt by Ender Pearls    
- Type: `int`
- Default value: `5`
- Suggested options: `0`, `2`, `3`, `5`
- Categories: `FEATURE`, `SURVIVAL`, `RUG`
- Additional notes:
  - You must choose a value from 0 to 10

### foodInstantHeal
Food heals hearts not hunger like in the first MC versions and naturalRegeneration is off  
Recommended using with peacefulHunger  
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `EXPERIMENTAL`, `FEATURE`, `SURVIVAL`, `RUG`
- Additional notes:
  - Suggestion by [real_zockerhopper](https://www.curseforge.com/members/real_zockerhopper)

### lilyPadsOnCauldron
Lily Pads can be placed on Cauldrons    
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `FEATURE`, `RUG`

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
- Categories: `EXPERIMENTAL`, `FEATURE`, `SURVIVAL`, `RUG`
- Additional notes:
  - Suggestion by [real_zockerhopper](https://www.curseforge.com/members/real_zockerhopper)

### playerHeadDrops
Players drop their head when killed by a player    
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `FEATURE`, `SURVIVAL`, `RUG`
- Additional notes:
  - Idea from [VanillaTweaks](https://vanillatweaks.net/picker/datapacks/)

### silenceMobs
Mobs named with 'silence_me' stop making noise    
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `FEATURE`, `SURVIVAL`, `RUG`
- Additional notes:
  - Idea from [VanillaTweaks](https://vanillatweaks.net/picker/datapacks/)

### silkTouchFarmland
Mining Farmland with a Silk Touch tool will drop itself    
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `EXPERIMENTAL`, `FEATURE`, `SURVIVAL`, `RUG`

### silkTouchPathBlocks
Mining Path Blocks with a Silk Touch tool will drop itself    
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `EXPERIMENTAL`, `FEATURE`, `SURVIVAL`, `RUG`

### silkTouchSpawners
Mining Spawners with a Silk Touch tool will drop itself    
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `EXPERIMENTAL`, `FEATURE`, `SURVIVAL`, `RUG`

### strictShulkerShells
Shulkers always drop a given amount of shulker shells when killed    
- Type: `int`
- Default value: `0`
- Suggested options: `0`, `1`, `2`
- Categories: `FEATURE`, `SURVIVAL`, `RUG`
- Additional notes:
  - You must choose a value from 0 to 4
  - Idea from [VanillaTweaks](https://vanillatweaks.net/picker/datapacks/)
