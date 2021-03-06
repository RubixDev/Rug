# Rug Mod for Fabric

[![GitHub all releases](https://img.shields.io/github/downloads/RubixDev/fabric-rug/total)](https://github.com/RubixDev/fabric-rug/releases)
[![GitHub release (latest by date)](https://img.shields.io/github/downloads/RubixDev/fabric-rug/latest/total)](https://github.com/RubixDev/fabric-rug/releases/latest)
[![License](https://img.shields.io/github/license/RubixDev/fabric-rug)](https://github.com/RubixDev/fabric-rug/blob/main/LICENSE)
[![GitHub issues](https://img.shields.io/github/issues/RubixDev/fabric-rug)](https://github.com/RubixDev/fabric-rug/issues)
[![GitHub closed issues](https://img.shields.io/github/issues-closed/RubixDev/fabric-rug)](https://github.com/RubixDev/fabric-rug/issues?q=is%3Aissue+is%3Aclosed)  
[![CurseForge Total Downloads](http://cf.way2muchnoise.eu/full_449938_downloads.svg)](https://www.curseforge.com/minecraft/mc-mods/fabric-rug/files)
[![CurseForge All Versions](http://cf.way2muchnoise.eu/versions/For%20MC_449938_all.svg)](https://www.curseforge.com/minecraft/mc-mods/fabric-rug)

Extension Mod for [gnembon's fabric-carpet](https://github.com/gnembon/fabric-carpet) with some more features

## Lists of Categories
- [`BUGFIX`](markdown/BUGFIX_Category.md)
- [`CLIENT`](markdown/CLIENT_Category.md)
- [`CRAFTING`](markdown/CRAFTING_Category.md)
- [`CREATIVE`](markdown/CREATIVE_Category.md)
- [`EXPERIMENTAL`](markdown/EXPERIMENTAL_Category.md)
- [`FEATURE`](markdown/FEATURE_Category.md)
- [`SURVIVAL`](markdown/SURVIVAL_Category.md)

## Index
Count: 64
- [anvilledBlueIce](#anvilledblueice)
- [anvilledIce](#anvilledice)
- [anvilledPackedIce](#anvilledpackedice)
- [cactusFurnaceXp](#cactusfurnacexp)
- [campSleeping](#campsleeping)
- [concreteConvertOnCauldron](#concreteconvertoncauldron)
- [craftableCobwebs](#craftablecobwebs)
- [craftableHorseArmor](#craftablehorsearmor)
- [craftableNameTags](#craftablenametags)
- [craftableNotchApple](#craftablenotchapple)
- [dragonDrops](#dragondrops)
- [dragonEggConvertsCobbleToEndstone](#dragoneggconvertscobbletoendstone)
- [dragonXpDrop](#dragonxpdrop)
- [easyBlueIceCrafting](#easyblueicecrafting)
- [easyBoneBlockCrafting](#easyboneblockcrafting)
- [easyChestCrafting](#easychestcrafting)
- [easyDispenserCrafting](#easydispensercrafting)
- [easyHarvesting](#easyharvesting)
- [easyMinecartsCrafting](#easyminecartscrafting)
- [easyRepeaterCrafting](#easyrepeatercrafting)
- [easyStickCrafting](#easystickcrafting)
- [easyTrappedChestCrafting](#easytrappedchestcrafting)
- [edibleGoldIngots](#ediblegoldingots)
- [edibleMagmaCream](#ediblemagmacream)
- [edibleNetheriteScraps](#ediblenetheritescraps)
- [edibleSlimeBalls](#edibleslimeballs)
- [eggWaterDrag](#eggwaterdrag)
- [enderPearlDamage](#enderpearldamage)
- [enderPearlWaterDrag](#enderpearlwaterdrag)
- [foodInstantHeal](#foodinstantheal)
- [honeyCombStickiness](#honeycombstickiness)
- [infinityNeedsArrow](#infinityneedsarrow)
- [kelpBlockHardness](#kelpblockhardness)
- [lilyPadsOnCauldron](#lilypadsoncauldron)
- [longerRepeaters](#longerrepeaters)
- [moreBarkCrafting](#morebarkcrafting)
- [moreFortressSpawningBlocks](#morefortressspawningblocks)
- [newShulkerBehavior](#newshulkerbehavior)
- [noCreeperGriefing](#nocreepergriefing)
- [noEndermanGriefing](#noendermangriefing)
- [noGhastGriefing](#noghastgriefing)
- [oldFishingLoot](#oldfishingloot)
- [peacefulHunger](#peacefulhunger)
- [playerHeadDrops](#playerheaddrops)
- [powderToGlassSmelting](#powdertoglasssmelting)
- [reachDistance](#reachdistance)
- [redstoneLampTurnOffDelay](#redstonelampturnoffdelay)
- [shapelessCrafting](#shapelesscrafting)
- [silenceMobs](#silencemobs)
- [silkTouchFarmland](#silktouchfarmland)
- [silkTouchPathBlocks](#silktouchpathblocks)
- [silkTouchSpawners](#silktouchspawners)
- [slimeChunkPercentage](#slimechunkpercentage)
- [snowballWaterDrag](#snowballwaterdrag)
- [stonecutterDamage](#stonecutterdamage)
- [strictShulkerShells](#strictshulkershells)
- [universalDyeing](#universaldyeing)
- [unpackableIce](#unpackableice)
- [unpackableNetherWart](#unpackablenetherwart)
- [unpackableQuartz](#unpackablequartz)
- [unpackableWool](#unpackablewool)
- [woodcutting](#woodcutting)
- [zeroTickPlants](#zerotickplants)
- [zombifiedPiglinsSpawningInPortals](#zombifiedpiglinsspawninginportals)

## Implemented Rules

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

### cactusFurnaceXp
Amount of XP a Cactus smelted in a furnace gives  
1 XP per Cactus seems to be a bug, as in Bedrock Edition it's only 0.2, which fits more in line with other items  
- Type: `double`
- Default value: `1`
- Suggested options: `0.1`, `0.2`, `0.5`, `1`
- Categories: `BUGFIX`, `RUG`, `SURVIVAL`
- Additional notes:
  - You must choose a value from 0 to 1

### campSleeping
Allows players to sleep in a Bed without setting their spawn point by entering while sneaking    
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `EXPERIMENTAL`, `FEATURE`, `RUG`, `SURVIVAL`

### concreteConvertOnCauldron
Concrete powder converts to concrete blocks when on top of a filled cauldron    
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `FEATURE`, `RUG`

### craftableCobwebs
Cobwebs can be crafted with 5 Strings in a cross pattern or with a 3x3 full area  
Expect a lag spike when changing the value  
- Type: `String`
- Default value: `off`
- Required options: `off`, `cross`, `full`
- Categories: `CRAFTING`, `RUG`, `SURVIVAL`

### craftableHorseArmor
Horse Armor can be crafted in vanilla_style (H-shape), with_saddle (like in VanillaTweaks) or with armor_pieces  
Expect a lag spike when changing the value  
- Type: `String`
- Default value: `off`
- Required options: `off`, `vanilla_style`, `with_saddle`, `armor_pieces`
- Categories: `CRAFTING`, `RUG`, `SURVIVAL`

### craftableNameTags
Name Tags can be crafted with Paper and Iron or String or both  
Expect a lag spike when changing the value  
- Type: `String`
- Default value: `off`
- Required options: `off`, `with_iron`, `with_string`, `with_both`
- Categories: `CRAFTING`, `RUG`, `SURVIVAL`

### craftableNotchApple
Enchanted Golden Apples can be crafted with 8 Gold Blocks again  
Expect a lag spike when changing the value  
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `CRAFTING`, `RUG`, `SURVIVAL`

### dragonDrops
Ender Dragon drops an Elytra when killed    
- Type: `String`
- Default value: `none`
- Required options: `none`, `dragon_egg`, `elytra`, `both`
- Categories: `FEATURE`, `RUG`, `SURVIVAL`
- Additional notes:
  - Idea from [VanillaTweaks](https://vanillatweaks.net/picker/datapacks/)

### dragonEggConvertsCobbleToEndstone
Dragon Eggs will convert Cobble under them to Endstone either on set event    
- Type: `String`
- Default value: `off`
- Required options: `off`, `on_teleport`, `on_landing`, `both`
- Categories: `EXPERIMENTAL`, `FEATURE`, `RUG`

### dragonXpDrop
Amount of XP dropped by later Dragons. The first Dragon always drops 12000    
- Type: `int`
- Default value: `500`
- Suggested options: `500`, `1200`, `12000`
- Categories: `CLIENT`, `EXPERIMENTAL`, `RUG`
- Additional notes:
  - You must choose a value from 0 to 12000
  - [Idea from Neubulae](https://github.com/gnembon/carpet-extra/issues/171)

### easyBlueIceCrafting
Blue Ice can be crafted from Ice and Blue Dye  
Expect a lag spike when changing the value  
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `CRAFTING`, `RUG`, `SURVIVAL`

### easyBoneBlockCrafting
Bone Blocks can be crafted out of Bones  
Expect a lag spike when changing the value  
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `CRAFTING`, `RUG`, `SURVIVAL`

### easyChestCrafting
Chests can be crafted out of logs  
Expect a lag spike when changing the value  
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `CRAFTING`, `RUG`, `SURVIVAL`

### easyDispenserCrafting
Dispensers can be crafted in full stacks with a dropper plus the raw materials of a bow  
Expect a lag spike when changing the value  
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `CRAFTING`, `RUG`, `SURVIVAL`
- Additional notes:
  - Idea from [VanillaTweaks](https://vanillatweaks.net/picker/crafting-tweaks/)

### easyHarvesting
Right clicking on fully grown crops harvests and immediately replants it  
Works on: Wheat, Potatoes, Carrots, Beetroots, Nether Warts and Cocoa Beans  
- Type: `String`
- Default value: `off`
- Required options: `off`, `normal`, `require_hoe`
- Categories: `EXPERIMENTAL`, `FEATURE`, `RUG`, `SURVIVAL`

### easyMinecartsCrafting
The special Minecarts can be crafted like normal Minecarts with the matching Block in the middle  
Expect a lag spike when changing the value  
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `CRAFTING`, `RUG`, `SURVIVAL`

### easyRepeaterCrafting
Repeaters can be crafted with the raw materials for Redstone Torches  
Expect a lag spike when changing the value  
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `CRAFTING`, `RUG`, `SURVIVAL`

### easyStickCrafting
Sticks can be crafted out of logs  
Expect a lag spike when changing the value  
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `CRAFTING`, `RUG`, `SURVIVAL`

### easyTrappedChestCrafting
Trapped Chests can be crafted out of logs and a Tripwire Hook  
Expect a lag spike when changing the value  
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `CRAFTING`, `RUG`, `SURVIVAL`

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

### eggWaterDrag
How fast thrown Eggs can travel under water. 0.99 is the default for above water and for Tridents  
Thrown Egg will stutter on client when mod is only on server  
- Type: `double`
- Default value: `0.8`
- Suggested options: `0.8`, `0.9`, `0.99`
- Categories: `CLIENT`, `EXPERIMENTAL`, `RUG`
- Additional notes:
  - You must choose a value from 0.5 to 0.99

### enderPearlDamage
Amount of damage dealt by Ender Pearls    
- Type: `int`
- Default value: `5`
- Suggested options: `0`, `2`, `3`, `5`
- Categories: `FEATURE`, `RUG`, `SURVIVAL`
- Additional notes:
  - You must choose a value from 0 to 10

### enderPearlWaterDrag
How fast thrown Ender Pearls can travel under water. 0.99 is the default for above water and for Tridents  
Thrown Pearl will stutter on client when mod is only on server  
- Type: `double`
- Default value: `0.8`
- Suggested options: `0.8`, `0.9`, `0.99`
- Categories: `CLIENT`, `EXPERIMENTAL`, `RUG`
- Additional notes:
  - You must choose a value from 0.5 to 0.99

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
  - [Idea from DragonEggBedrockBreaking#0034](https://discord.com/channels/211786369951989762/573613501164159016/816793720011358208) on the [SciCraft Discord](https://discord.gg/scicraft)

### infinityNeedsArrow
A Bow enchanted with Infinity needs the player to have an arrow in his inventory    
- Type: `boolean`
- Default value: `true`
- Required options: `true`, `false`
- Categories: `BUGFIX`, `EXPERIMENTAL`, `RUG`, `SURVIVAL`

### kelpBlockHardness
How long Kelp Blocks take to mine in survival  
Any value other than 0 will behave like 0.5 for clients without this mod  
- Type: `double`
- Default value: `0.5`
- Suggested options: `0`, `0.25`, `0.5`
- Categories: `CLIENT`, `EXPERIMENTAL`, `RUG`, `SURVIVAL`
- Additional notes:
  - You must choose a value from 0 to 0.5

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

### moreBarkCrafting
Crafting Logs to Wood gives 4 instead of 3  
Expect a lag spike when changing the value  
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `CRAFTING`, `RUG`, `SURVIVAL`

### moreFortressSpawningBlocks
What blocks Fortress mobs can spawn on inside the bigger Bounding Box  
o  
- Type: `String`
- Default value: `off`
- Required options: `off`, `more`, `all`
- Categories: `EXPERIMENTAL`, `FEATURE`, `RUG`
- Additional notes:
  - [Idea from DragonEggBedrockBreaking](https://github.com/gnembon/carpet-extra/issues/182)

### newShulkerBehavior
Makes shulkers behave like in the current 1.17 snapshots  
Shulkers hit by a shulker bullet have a chance to spawn a new shulker and teleport  
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `EXPERIMENTAL`, `FEATURE`, `RUG`

### noCreeperGriefing
Prevents Creepers from destroying blocks    
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `RUG`, `SURVIVAL`

### noEndermanGriefing
Prevents Endermen from picking up and placing blocks    
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `RUG`, `SURVIVAL`

### noGhastGriefing
Prevents Ghasts from destroying blocks    
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `RUG`, `SURVIVAL`

### oldFishingLoot
Reverts the fishing loot to how it was before 1.16    
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `RUG`, `SURVIVAL`

### peacefulHunger
Players do not lose any hunger like in peaceful mode    
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `EXPERIMENTAL`, `FEATURE`, `RUG`, `SURVIVAL`
- Additional notes:
  - Suggestion by [real_zockerhopper](https://www.curseforge.com/members/real_zockerhopper)

### playerHeadDrops
Players drop their head    
- Type: `String`
- Default value: `off`
- Required options: `off`, `on_death`, `on_killed_by_player`
- Categories: `FEATURE`, `RUG`, `SURVIVAL`
- Additional notes:
  - Idea from [VanillaTweaks](https://vanillatweaks.net/picker/datapacks/)

### powderToGlassSmelting
Concrete Powder can be smelted to Glass of the corresponding color  
Expect a lag spike when changing the value  
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `CRAFTING`, `RUG`, `SURVIVAL`
- Additional notes:
  - Idea from [VanillaTweaks](https://vanillatweaks.net/picker/crafting-tweaks/)

### reachDistance
Reach in which you can place and break blocks. Value will be 0.5 higher in creative  
Mod needed on server and client for this feature to work  
- Type: `double`
- Default value: `4.5`
- Suggested options: `0`, `4.5`, `5`, `10`
- Categories: `CLIENT`, `CREATIVE`, `EXPERIMENTAL`, `RUG`
- Additional notes:
  - You must choose a value from 0 to 100

### redstoneLampTurnOffDelay
Delay in game ticks for Redstone Lamps to turn off    
- Type: `int`
- Default value: `4`
- Suggested options: `0`, `4`, `8`
- Categories: `RUG`
- Additional notes:
  - You must choose a value from 0 to 8

### shapelessCrafting
Bread, Paper and Shulker Boxes can be crafted in a 2x2 field  
Expect a lag spike when changing the value  
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `CRAFTING`, `RUG`, `SURVIVAL`
- Additional notes:
  - Idea from [VanillaTweaks](https://vanillatweaks.net/picker/crafting-tweaks/)

### silenceMobs
Mobs named with 'silence_me' stop making noise    
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `FEATURE`, `RUG`, `SURVIVAL`
- Additional notes:
  - Idea from [VanillaTweaks](https://vanillatweaks.net/picker/datapacks/)

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

### slimeChunkPercentage
The percentage of chunks that are Slime chinks    
- Type: `int`
- Default value: `10`
- Suggested options: `0`, `10`, `50`, `100`
- Categories: `EXPERIMENTAL`, `RUG`
- Additional notes:
  - You must choose a value from 0 to 100 that is a multiple of 10
  - [Idea from Philipp766](https://github.com/gnembon/carpet-extra/issues/161)

### snowballWaterDrag
How fast thrown Snowballs can travel under water. 0.99 is the default for above water and for Tridents  
Thrown Snowball will stutter on client when mod is only on server  
- Type: `double`
- Default value: `0.8`
- Suggested options: `0.8`, `0.9`, `0.99`
- Categories: `CLIENT`, `EXPERIMENTAL`, `RUG`
- Additional notes:
  - You must choose a value from 0.5 to 0.99

### stonecutterDamage
How much damage Stonecutters deal when stepping on them    
- Type: `int`
- Default value: `0`
- Suggested options: `0`, `3`, `4`, `5`
- Categories: `BUGFIX`, `RUG`, `SURVIVAL`
- Additional notes:
  - You must choose a value from 0 to 10

### strictShulkerShells
Shulkers always drop a given amount of shulker shells when killed    
- Type: `int`
- Default value: `0`
- Suggested options: `0`, `1`, `2`
- Categories: `FEATURE`, `RUG`, `SURVIVAL`
- Additional notes:
  - You must choose a value from 0 to 4
  - Idea from [VanillaTweaks](https://vanillatweaks.net/picker/datapacks/)

### universalDyeing
Allows to dye already dyed things and clean Glass, Glass Panes and Terracotta with a Water Bucket in the Crafting Table  
Expect a lag spike when changing the value  
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `CRAFTING`, `RUG`, `SURVIVAL`
- Additional notes:
  - Idea from [VanillaTweaks](https://vanillatweaks.net/picker/crafting-tweaks/)

### unpackableIce
Blue Ice and Packed Ice can be crafted into set amount of Packed Ice and Normal Ice accordingly  
Expect a lag spike when changing the value  
- Type: `int`
- Default value: `0`
- Suggested options: `0`, `3`, `4`, `9`
- Categories: `CRAFTING`, `RUG`, `SURVIVAL`
- Additional notes:
  - You must choose a value from 0 to 9

### unpackableNetherWart
Nether Wart Blocks can be crafted into set amount of Nether Warts  
Expect a lag spike when changing the value  
- Type: `int`
- Default value: `0`
- Suggested options: `0`, `9`
- Categories: `CRAFTING`, `RUG`, `SURVIVAL`
- Additional notes:
  - You must choose a value from 0 to 9

### unpackableQuartz
Quartz Blocks can be crafted into set amount of Quartz  
Expect a lag spike when changing the value  
- Type: `int`
- Default value: `0`
- Suggested options: `0`, `4`
- Categories: `CRAFTING`, `RUG`, `SURVIVAL`
- Additional notes:
  - You must choose a value from 0 to 9

### unpackableWool
White Wool Blocks can be crafted into set amount of String  
Expect a lag spike when changing the value  
- Type: `int`
- Default value: `0`
- Suggested options: `0`, `4`
- Categories: `CRAFTING`, `RUG`, `SURVIVAL`
- Additional notes:
  - You must choose a value from 0 to 9

### woodcutting
The Stonecutter can be used for all wood types  
Expect a lag spike when changing the value  
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `CRAFTING`, `RUG`, `SURVIVAL`

### zeroTickPlants
Brings back the ability to force grow certain plants using 0-ticks    
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `EXPERIMENTAL`, `RUG`

### zombifiedPiglinsSpawningInPortals
Allows Zombified Piglins to spawn inside Nether Portals    
- Type: `boolean`
- Default value: `true`
- Required options: `true`, `false`
- Categories: `RUG`, `SURVIVAL`
