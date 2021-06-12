# List of Rules in the CLIENT Category

For a list of all implemented Rules go [here](../README.md)
## Index
Count: 11
- [edibleGoldIngots](#ediblegoldingots)
- [edibleMagmaCream](#ediblemagmacream)
- [edibleNetheriteScraps](#ediblenetheritescraps)
- [edibleSlimeBalls](#edibleslimeballs)
- [eggWaterDrag](#eggwaterdrag)
- [enderPearlWaterDrag](#enderpearlwaterdrag)
- [honeyCombStickiness](#honeycombstickiness)
- [kelpBlockHardness](#kelpblockhardness)
- [maxBannerLayers](#maxbannerlayers)
- [reachDistance](#reachdistance)
- [snowballWaterDrag](#snowballwaterdrag)

## Rules in CLIENT Category

### edibleGoldIngots
Butter is finally edible. Keep in mind 250g of pure butter are not that healthy  
Works server side only, but eating animation is only rendered if the mod is on the client too  
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `CLIENT`, `EXPERIMENTAL`, `FEATURE`, `RUG`, `SURVIVAL`
- Additional notes:
  - [AppleSkin](https://www.curseforge.com/minecraft/mc-mods/appleskin) always shows this item as edible with Rug installed on client, as described in [#19](https://github.com/RubixDev/fabric-rug/issues/19)

### edibleMagmaCream
Magma Cream is edible and gives 10 seconds of Fire Resistance  
Works server side only, but eating animation is only rendered if the mod is on the client too  
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `CLIENT`, `EXPERIMENTAL`, `FEATURE`, `RUG`, `SURVIVAL`
- Additional notes:
  - [AppleSkin](https://www.curseforge.com/minecraft/mc-mods/appleskin) always shows this item as edible with Rug installed on client, as described in [#19](https://github.com/RubixDev/fabric-rug/issues/19)

### edibleNetheriteScraps
Makes Netherite Scraps edible, because, let's be honest, they kinda look like chocolate  
Works server side only, but eating animation is only rendered if the mod is on the client too  
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `CLIENT`, `EXPERIMENTAL`, `FEATURE`, `RUG`, `SURVIVAL`
- Additional notes:
  - [AppleSkin](https://www.curseforge.com/minecraft/mc-mods/appleskin) always shows this item as edible with Rug installed on client, as described in [#19](https://github.com/RubixDev/fabric-rug/issues/19)

### edibleSlimeBalls
Slime Balls are edible and give Jump Boost and Slowness  
Works server side only, but eating animation is only rendered if the mod is on the client too  
- Type: `boolean`
- Default value: `false`
- Required options: `true`, `false`
- Categories: `CLIENT`, `EXPERIMENTAL`, `FEATURE`, `RUG`, `SURVIVAL`
- Additional notes:
  - [AppleSkin](https://www.curseforge.com/minecraft/mc-mods/appleskin) always shows this item as edible with Rug installed on client, as described in [#19](https://github.com/RubixDev/fabric-rug/issues/19)

### eggWaterDrag
How fast thrown Eggs can travel under water. 0.99 is the default for above water and for Tridents  
Thrown Egg will stutter on client when mod is only on server  
- Type: `double`
- Default value: `0.8`
- Suggested options: `0.8`, `0.9`, `0.99`
- Categories: `CLIENT`, `EXPERIMENTAL`, `RUG`
- Additional notes:
  - You must choose a value from 0.5 to 0.99

### enderPearlWaterDrag
How fast thrown Ender Pearls can travel under water. 0.99 is the default for above water and for Tridents  
Thrown Pearl will stutter on client when mod is only on server  
- Type: `double`
- Default value: `0.8`
- Suggested options: `0.8`, `0.9`, `0.99`
- Categories: `CLIENT`, `EXPERIMENTAL`, `RUG`
- Additional notes:
  - You must choose a value from 0.5 to 0.99

### honeyCombStickiness
Honey Comb only sticks to selected Blocks  
Will render Ghost Blocks on the Client when mod is only Server Side  
- Type: `String`
- Default value: `both`
- Required options: `both`, `honey`, `slime`, `none`
- Categories: `CLIENT`, `EXPERIMENTAL`, `FEATURE`, `RUG`
- Additional notes:
  - [Idea from DragonEggBedrockBreaking#0034](https://discord.com/channels/211786369951989762/573613501164159016/816793720011358208) on the [SciCraft Discord](https://discord.gg/scicraft)

### kelpBlockHardness
How long Kelp Blocks take to mine in survival  
Any value other than 0 will behave like 0.5 for clients without this mod  
- Type: `double`
- Default value: `0.5`
- Suggested options: `0`, `0.25`, `0.5`
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

### reachDistance
Reach in which you can place and break blocks. Value will be 0.5 higher in creative  
Mod needed on server and client for this feature to work  
- Type: `double`
- Default value: `4.5`
- Suggested options: `0`, `4.5`, `5`, `10`
- Categories: `CLIENT`, `CREATIVE`, `EXPERIMENTAL`, `RUG`
- Additional notes:
  - You must choose a value from 0 to 100
  - Is disabled when [reach-entity-attributes](https://github.com/JamiesWhiteShirt/reach-entity-attributes) is installed

### snowballWaterDrag
How fast thrown Snowballs can travel under water. 0.99 is the default for above water and for Tridents  
Thrown Snowball will stutter on client when mod is only on server  
- Type: `double`
- Default value: `0.8`
- Suggested options: `0.8`, `0.9`, `0.99`
- Categories: `CLIENT`, `EXPERIMENTAL`, `RUG`
- Additional notes:
  - You must choose a value from 0.5 to 0.99
