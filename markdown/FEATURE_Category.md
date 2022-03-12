# List of Rules in the FEATURE Category

For a list of all implemented Rules go [here](../README.md)
## Index
Count: 30
- [anvilledBlueIce](#anvilledblueice)
- [anvilledIce](#anvilledice)
- [anvilledPackedIce](#anvilledpackedice)
- [basaltToBlackstoneConversion](#basalttoblackstoneconversion)
- [basaltToLavaConversion](#basalttolavaconversion)
- [campSleeping](#campsleeping)
- [cobbleCrushing](#cobblecrushing)
- [concreteConvertOnCauldron](#concreteconvertoncauldron)
- [concreteMixing](#concretemixing)
- [dragonDrops](#dragondrops)
- [dragonEggConvertsCobbleToEndstone](#dragoneggconvertscobbletoendstone)
- [easyHarvesting](#easyharvesting)
- [enderPearlDamage](#enderpearldamage)
- [foodInstantHeal](#foodinstantheal)
- [gravelCrushing](#gravelcrushing)
- [honeyCombStickiness](#honeycombstickiness)
- [lilyPadsOnCauldron](#lilypadsoncauldron)
- [longerRepeaters](#longerrepeaters)
- [moreFortressSpawningBlocks](#morefortressspawningblocks)
- [netherrackGeneration](#netherrackgeneration)
- [peacefulHunger](#peacefulhunger)
- [playerHeadDrops](#playerheaddrops)
- [silenceMobs](#silencemobs)
- [silkTouchBuddingAmethysts](#silktouchbuddingamethysts)
- [silkTouchFarmland](#silktouchfarmland)
- [silkTouchPathBlocks](#silktouchpathblocks)
- [silkTouchSpawners](#silktouchspawners)
- [stalagmiteSteppingDamage](#stalagmitesteppingdamage)
- [strictShulkerShells](#strictshulkershells)
- [villagersDropEmeralds](#villagersdropemeralds)

## Rules in FEATURE Category

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

### campSleeping
Allows players to sleep in a Bed without setting their spawn point by entering while sneaking. Requires the main hand to be empty
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `EXPERIMENTAL`, `FEATURE`, `RUG`, `SURVIVAL`

### cobbleCrushing
Cobblestone crushed by a falling Anvil will convert into Gravel

Carpet-Extra's renewableSand is prioritized over this
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `FEATURE`, `RENEWABLE`, `RUG`, `SURVIVAL`

### concreteConvertOnCauldron
Concrete powder converts to concrete blocks when on top of a filled cauldron
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `FEATURE`, `RUG`

### concreteMixing
Sand falling on Gravel (or the other way around) with a Concrete Block below will blend to Concrete Powder colored like the Concrete Block below

In combination with cobbleCrushing and gravelCrushing allows for an automatic Concrete generator
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `EXPERIMENTAL`, `FEATURE`, `RUG`, `SURVIVAL`

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

### easyHarvesting
Right clicking on fully grown crops harvests and immediately replants it

Works on: Wheat, Potatoes, Carrots, Beetroots, Nether Warts and Cocoa Beans
- Type: `String`
- Default value: `off`
- Required options: `off`, `normal`, `require_hoe`
- Categories: `EXPERIMENTAL`, `FEATURE`, `RUG`, `SURVIVAL`

### enderPearlDamage
Amount of damage dealt by Ender Pearls
- Type: `int`
- Default value: `5`
- Suggested options: `0`, `2`, `3`, `5`
- Categories: `FEATURE`, `RUG`, `SURVIVAL`
- Additional notes:
  - You must choose a value from 0 to 10

### foodInstantHeal
Food heals hearts not hunger like in the first MC versions and naturalRegeneration is off

Recommended using with peacefulHunger
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `EXPERIMENTAL`, `FEATURE`, `RUG`, `SURVIVAL`
- Additional notes:
  - Suggestion by [real_zockerhopper](https://www.curseforge.com/members/real_zockerhopper)

### gravelCrushing
Gravel crushed by a falling Anvil will convert into Sand
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `FEATURE`, `RENEWABLE`, `RUG`, `SURVIVAL`

### honeyCombStickiness
Honey Comb only sticks to selected Blocks

Will render Ghost Blocks on the Client when mod is only Server Side
- Type: `String`
- Default value: `both`
- Required options: `both`, `honey`, `slime`, `none`
- Categories: `CLIENT`, `EXPERIMENTAL`, `FEATURE`, `RUG`
- Additional notes:
  - [Idea from DragonEggBedrockBreaking#0034](https://discord.com/channels/211786369951989762/573613501164159016/816793720011358208) on the [SciCraft Discord](https://discord.gg/scicraft)

### lilyPadsOnCauldron
Lily Pads can be placed on Cauldrons
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `FEATURE`, `RUG`

### longerRepeaters
Repeaters on top of Redstone Blocks multiply their delay by set amount
- Type: `int`
- Default value: `1`
- Required options: `1`, `2`, `3`, `4`
- Categories: `EXPERIMENTAL`, `FEATURE`, `RUG`

### moreFortressSpawningBlocks
What blocks Fortress mobs can spawn on inside the bigger Bounding Box

off: nether bricks only / more: (red) nether bricks, netherrack, soul sand/soil, packed/blue ice, gravel, magma blocks / all: all
- Type: `String`
- Default value: `off`
- Required options: `off`, `more`, `all`
- Categories: `EXPERIMENTAL`, `FEATURE`, `RUG`
- Additional notes:
  - [Idea from DragonEggBedrockBreaking](https://github.com/gnembon/carpet-extra/issues/182)

### netherrackGeneration
Netherrack is generated instead of Cobblestone if a Magma Block is below
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `EXPERIMENTAL`, `FEATURE`, `RENEWABLE`, `RUG`, `SURVIVAL`

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

### silenceMobs
Mobs named with 'silence_me' stop making noise
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `FEATURE`, `RUG`, `SURVIVAL`
- Additional notes:
  - Idea from [VanillaTweaks](https://vanillatweaks.net/picker/datapacks/)

### silkTouchBuddingAmethysts
Mining Budding Amethysts with a Silk Touch tool will drop itself
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `EXPERIMENTAL`, `FEATURE`, `RUG`, `SURVIVAL`

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

### stalagmiteSteppingDamage
Pointed Dripstones (stalagmites) deal damage when standing on them. Sneaking prevents this
- Type: `int`
- Default value: `0`
- Suggested options: `0`, `2`, `4`
- Categories: `FEATURE`, `RUG`, `SURVIVAL`
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

### villagersDropEmeralds
Villagers drop between 1 and x Emeralds on death, where x is the given number
- Type: `int`
- Default value: `0`
- Suggested options: `0`, `1`, `3`
- Categories: `FEATURE`, `RUG`, `SURVIVAL`
- Additional notes:
  - You must choose a value from 0 to 5
