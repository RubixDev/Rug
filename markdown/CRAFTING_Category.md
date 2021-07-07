# List of Rules in the CRAFTING Category

For a list of all implemented Rules go [here](../README.md)
## Index
Count: 30
- [blastFurnaceGlass](#blastfurnaceglass)
- [blastFurnaceGlazedTerracotta](#blastfurnaceglazedterracotta)
- [blastFurnaceNetherBrick](#blastfurnacenetherbrick)
- [blastFurnaceSmoothQuartz](#blastfurnacesmoothquartz)
- [blastFurnaceSmoothSandStone](#blastfurnacesmoothsandstone)
- [blastFurnaceStone](#blastfurnacestone)
- [craftableCobwebs](#craftablecobwebs)
- [craftableHorseArmor](#craftablehorsearmor)
- [craftableNameTags](#craftablenametags)
- [craftableNotchApple](#craftablenotchapple)
- [easyBlueIceCrafting](#easyblueicecrafting)
- [easyBoneBlockCrafting](#easyboneblockcrafting)
- [easyChestCrafting](#easychestcrafting)
- [easyDispenserCrafting](#easydispensercrafting)
- [easyMinecartsCrafting](#easyminecartscrafting)
- [easyRepeaterCrafting](#easyrepeatercrafting)
- [easyStickCrafting](#easystickcrafting)
- [easyTrappedChestCrafting](#easytrappedchestcrafting)
- [maxBannerLayers](#maxbannerlayers)
- [moreBarkCrafting](#morebarkcrafting)
- [powderToGlassSmelting](#powdertoglasssmelting)
- [shapelessCrafting](#shapelesscrafting)
- [smokerGreenDye](#smokergreendye)
- [smokerLimeDye](#smokerlimedye)
- [universalDyeing](#universaldyeing)
- [unpackableIce](#unpackableice)
- [unpackableNetherWart](#unpackablenetherwart)
- [unpackableQuartz](#unpackablequartz)
- [unpackableWool](#unpackablewool)
- [woodcutting](#woodcutting)

## Rules in CRAFTING Category

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
