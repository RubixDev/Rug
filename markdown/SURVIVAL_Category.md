# List of Rules in the SURVIVAL Category

For a list of all implemented Rules go [here](../README.md)
## Index
Count: 66
- [basaltToBlackstoneConversion](#basalttoblackstoneconversion)
- [basaltToLavaConversion](#basalttolavaconversion)
- [blastFurnaceGlass](#blastfurnaceglass)
- [blastFurnaceGlazedTerracotta](#blastfurnaceglazedterracotta)
- [blastFurnaceNetherBrick](#blastfurnacenetherbrick)
- [blastFurnaceSmoothQuartz](#blastfurnacesmoothquartz)
- [blastFurnaceSmoothSandStone](#blastfurnacesmoothsandstone)
- [blastFurnaceStone](#blastfurnacestone)
- [cactusFurnaceXp](#cactusfurnacexp)
- [campSleeping](#campsleeping)
- [cobbleCrushing](#cobblecrushing)
- [concreteMixing](#concretemixing)
- [craftableCobwebs](#craftablecobwebs)
- [craftableHorseArmor](#craftablehorsearmor)
- [craftableNameTags](#craftablenametags)
- [craftableNotchApple](#craftablenotchapple)
- [dragonDrops](#dragondrops)
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
- [enderEyeDropChance](#endereyedropchance)
- [enderPearlDamage](#enderpearldamage)
- [foodInstantHeal](#foodinstantheal)
- [gravelCrushing](#gravelcrushing)
- [infiniteLavaSources](#infinitelavasources)
- [infinityNeedsArrow](#infinityneedsarrow)
- [kelpBlockHardness](#kelpblockhardness)
- [maxBannerLayers](#maxbannerlayers)
- [moreBarkCrafting](#morebarkcrafting)
- [netherrackGeneration](#netherrackgeneration)
- [noCreeperGriefing](#nocreepergriefing)
- [noEndermanGriefing](#noendermangriefing)
- [noGhastGriefing](#noghastgriefing)
- [oldFishingLoot](#oldfishingloot)
- [peacefulHunger](#peacefulhunger)
- [playerHeadDrops](#playerheaddrops)
- [powderToGlassSmelting](#powdertoglasssmelting)
- [shapelessCrafting](#shapelesscrafting)
- [silenceMobs](#silencemobs)
- [silkTouchBuddingAmethysts](#silktouchbuddingamethysts)
- [silkTouchFarmland](#silktouchfarmland)
- [silkTouchPathBlocks](#silktouchpathblocks)
- [silkTouchSpawners](#silktouchspawners)
- [smokerGreenDye](#smokergreendye)
- [smokerLimeDye](#smokerlimedye)
- [stalagmiteSteppingDamage](#stalagmitesteppingdamage)
- [stonecutterDamage](#stonecutterdamage)
- [strictShulkerShells](#strictshulkershells)
- [tameCatsWithCookedFish](#tamecatswithcookedfish)
- [universalDyeing](#universaldyeing)
- [unpackableAmethysts](#unpackableamethysts)
- [unpackableIce](#unpackableice)
- [unpackableNetherWart](#unpackablenetherwart)
- [unpackableQuartz](#unpackablequartz)
- [unpackableWool](#unpackablewool)
- [villagersAlwaysConvert](#villagersalwaysconvert)
- [villagersDropEmeralds](#villagersdropemeralds)
- [woodcutting](#woodcutting)
- [zombifiedPiglinsSpawningInPortals](#zombifiedpiglinsspawninginportals)

## Rules in SURVIVAL Category

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

### blastFurnaceGlass
Sand and Red Sand can be smelted to Glass Blocks in a Blast Furnace

Expect a lag spike when changing the value
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `CRAFTING`, `RUG`, `SURVIVAL`

### blastFurnaceGlazedTerracotta
Dyed Terracotta can be smelted to the corresponding Glazed Terracotta in a Blast Furnace

Expect a lag spike when changing the value
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `CRAFTING`, `RUG`, `SURVIVAL`

### blastFurnaceNetherBrick
Netherrack can be smelted to Nether Brick in a Blast Furnace

Expect a lag spike when changing the value
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `CRAFTING`, `RUG`, `SURVIVAL`

### blastFurnaceSmoothQuartz
Quartz Blocks can be smelted to Smooth Quartz in a Blast Furnace

Expect a lag spike when changing the value
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `CRAFTING`, `RUG`, `SURVIVAL`

### blastFurnaceSmoothSandStone
Sandstone and Red Sandstone can be smelted to the smooth variant in a Blast Furnace

Expect a lag spike when changing the value
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `CRAFTING`, `RUG`, `SURVIVAL`

### blastFurnaceStone
Cobblestone to Stone to Smooth Stone can be smelted in a Blast Furnace

Expect a lag spike when changing the value
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `CRAFTING`, `RUG`, `SURVIVAL`
- Additional notes:
  - [Suggestion from Philipp766](https://github.com/RubixDev/Rug/issues/15)

### cactusFurnaceXp
Amount of XP a Cactus smelted in a furnace gives

1 XP per Cactus seems to be a bug, as in Bedrock Edition it's only 0.2, which fits more in line with other items
- Type: `double`
- Default value: `1.0`
- Suggested options: `0.1`, `0.2`, `0.5`, `1.0`
- Categories: `BUGFIX`, `RUG`, `SURVIVAL`
- Additional notes:
  - You must choose a value from 0 to 1

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

### concreteMixing
Sand falling on Gravel (or the other way around) with a Concrete Block below will blend to Concrete Powder colored like the Concrete Block below

In combination with cobbleCrushing and gravelCrushing allows for an automatic Concrete generator
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `EXPERIMENTAL`, `FEATURE`, `RUG`, `SURVIVAL`

### craftableCobwebs
Cobwebs can be crafted with 5 Strings in a cross pattern or with a 3x3 full area

Expect a lag spike when changing the value
- Type: `String`
- Default value: `off`
- Required options: `off`, `cross`, `full`
- Categories: `CRAFTING`, `RENEWABLE`, `RUG`, `SURVIVAL`

### craftableHorseArmor
Horse Armor can be crafted in vanilla_style (H-shape), with_saddle (like in VanillaTweaks) or with armor_pieces

Expect a lag spike when changing the value
- Type: `String`
- Default value: `off`
- Required options: `off`, `vanilla_style`, `with_saddle`, `armor_pieces`
- Categories: `CRAFTING`, `RENEWABLE`, `RUG`, `SURVIVAL`

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
- Categories: `CRAFTING`, `RENEWABLE`, `RUG`, `SURVIVAL`

### dragonDrops
Ender Dragon drops selected item(s) when killed
- Type: `String`
- Default value: `none`
- Required options: `none`, `dragon_egg`, `elytra`, `dragon_head`, `dragon_egg,elytra`, `dragon_egg,dragon_head`, `elytra,dragon_head`, `all`
- Categories: `FEATURE`, `RENEWABLE`, `RUG`, `SURVIVAL`
- Additional notes:
  - Idea from [VanillaTweaks](https://vanillatweaks.net/picker/datapacks/)

### dragonXpDrop
Amount of XP dropped by later Dragons. The first Dragon always drops 12000
- Type: `int`
- Default value: `500`
- Suggested options: `500`, `1200`, `12000`
- Categories: `EXPERIMENTAL`, `RUG`, `SURVIVAL`
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

### enderEyeDropChance
The chance of Ender Eyes dropping when thrown
- Type: `int`
- Default value: `80`
- Suggested options: `0`, `50`, `80`, `100`
- Categories: `RUG`, `SURVIVAL`
- Additional notes:
  - You must choose a value from 0 to 100

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

### infiniteLavaSources
Lava creates infinite sources like Water
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `RENEWABLE`, `RUG`, `SURVIVAL`

### infinityNeedsArrow
A Bow enchanted with Infinity needs the player to have an arrow in their inventory
- Type: `boolean`
- Default value: `true`
- Required options: `true`, `false`
- Categories: `BUGFIX`, `EXPERIMENTAL`, `RUG`, `SURVIVAL`

### kelpBlockHardness
How long Kelp Blocks take to mine in survival

Any value other than 0 will behave like 0.5 for clients without this mod
- Type: `double`
- Default value: `0.5`
- Suggested options: `0.0`, `0.25`, `0.5`
- Categories: `CLIENT`, `EXPERIMENTAL`, `RUG`, `SURVIVAL`
- Additional notes:
  - You must choose a value from 0 to 0.5

### maxBannerLayers
Maximum number of layers, that can be applied to a banner

This only works for clients with this mod installed and the Banner tooltips never show more than 6 layers
- Type: `int`
- Default value: `6`
- Suggested options: `3`, `6`, `10`, `12`
- Categories: `CLIENT`, `CRAFTING`, `EXPERIMENTAL`, `RUG`, `SURVIVAL`
- Additional notes:
  - You must choose a value from 3 to 16
  - [Idea from SouthernPixel](https://github.com/gnembon/carpet-extra/issues/111)

### moreBarkCrafting
Crafting Logs to Wood gives 4 instead of 3

Expect a lag spike when changing the value
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `CRAFTING`, `RUG`, `SURVIVAL`

### netherrackGeneration
Netherrack is generated instead of Cobblestone if a Magma Block is below
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `EXPERIMENTAL`, `FEATURE`, `RENEWABLE`, `RUG`, `SURVIVAL`

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

### smokerGreenDye
Cacti can be cooked into Green Dye in a Smoker. XP override of cactusFurnaceXp still applies

Expect a lag spike when changing the value
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `CRAFTING`, `RUG`, `SURVIVAL`

### smokerLimeDye
Sea Pickles can be cooked into Lime Dye in a Smoker

Expect a lag spike when changing the value
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `CRAFTING`, `RUG`, `SURVIVAL`

### stalagmiteSteppingDamage
Pointed Dripstones (stalagmites) deal damage when standing on them. Sneaking prevents this
- Type: `int`
- Default value: `0`
- Suggested options: `0`, `2`, `4`
- Categories: `FEATURE`, `RUG`, `SURVIVAL`
- Additional notes:
  - You must choose a value from 0 to 10

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

### tameCatsWithCookedFish
Cats can also be tamed with cooked Cod and Salmon, not just with raw
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `RUG`, `SURVIVAL`

### universalDyeing
Allows to dye already dyed things and clean Glass, Glass Panes and Terracotta with a Water Bucket in the Crafting Table

Expect a lag spike when changing the value
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `CRAFTING`, `RUG`, `SURVIVAL`
- Additional notes:
  - Idea from [VanillaTweaks](https://vanillatweaks.net/picker/crafting-tweaks/)

### unpackableAmethysts
Amethyst Blocks can be crafted into set amount of Amethyst Shards

Expect a lag spike when changing the value
- Type: `int`
- Default value: `0`
- Suggested options: `0`, `4`
- Categories: `CRAFTING`, `RUG`, `SURVIVAL`
- Additional notes:
  - You must choose a value from 0 to 9
  - Idea from [NAPPUS](https://github.com/MultiCoreNetwork/carpet-redcraft-addons/issues/79)

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

### villagersAlwaysConvert
Villagers killed by Zombies will always convert to Zombie Villagers regardless of difficulty
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `RUG`, `SURVIVAL`

### villagersDropEmeralds
Villagers drop between 1 and x Emeralds on death, where x is the given number
- Type: `int`
- Default value: `0`
- Suggested options: `0`, `1`, `3`
- Categories: `FEATURE`, `RUG`, `SURVIVAL`
- Additional notes:
  - You must choose a value from 0 to 5

### woodcutting
The Stonecutter can be used for all wood types

Expect a lag spike when changing the value
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `CRAFTING`, `RUG`, `SURVIVAL`

### zombifiedPiglinsSpawningInPortals
Allows Zombified Piglins to spawn inside Nether Portals
- Type: `boolean`
- Default value: `true`
- Required options: `true`, `false`
- Categories: `RUG`, `SURVIVAL`
