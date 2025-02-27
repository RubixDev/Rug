# List of Rules in the CLIENT Category

For a list of all implemented Rules go [here](../README.md)
## Index
Count: 6
- [eggWaterDrag](#eggwaterdrag)
- [enderPearlWaterDrag](#enderpearlwaterdrag)
- [honeyCombStickiness](#honeycombstickiness)
- [kelpBlockHardness](#kelpblockhardness)
- [maxBannerLayers](#maxbannerlayers)
- [snowballWaterDrag](#snowballwaterdrag)

## Rules in CLIENT Category

### eggWaterDrag
How fast thrown Eggs can travel underwater. 0.99 is the default for above water and for Tridents

Thrown Egg will stutter on client when mod is only on server
- Type: `double`
- Default value: `0.8`
- Suggested options: `0.8`, `0.9`, `0.99`
- Categories: `CLIENT`, `EXPERIMENTAL`, `RUG`
- Additional notes:
  - You must choose a value from 0.5 to 0.99

### enderPearlWaterDrag
How fast thrown Ender Pearls can travel underwater. 0.99 is the default for above water and for Tridents

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
- Suggested options: `0.0`, `0.25`, `0.5`
- Categories: `CLIENT`, `EXPERIMENTAL`, `RUG`, `SURVIVAL`
- Additional notes:
  - You must choose a value from 0 to 0.5

### maxBannerLayers
Maximum number of layers, that can be applied to a banner

This only works for clients with this mod installed and the Banner tooltips never show more than 6 layers  
Is disabled when [Infinite Banner Patterns](https://modrinth.com/mod/infinite-banner-patterns) is installed
- Type: `int`
- Default value: `6`
- Suggested options: `3`, `6`, `10`, `12`
- Categories: `CLIENT`, `CRAFTING`, `EXPERIMENTAL`, `RUG`, `SURVIVAL`
- Additional notes:
  - You must choose a value from 3 to 16
  - [Idea from SouthernPixel](https://github.com/gnembon/carpet-extra/issues/111)

### snowballWaterDrag
How fast thrown Snowballs can travel underwater. 0.99 is the default for above water and for Tridents

Thrown Snowball will stutter on client when mod is only on server
- Type: `double`
- Default value: `0.8`
- Suggested options: `0.8`, `0.9`, `0.99`
- Categories: `CLIENT`, `EXPERIMENTAL`, `RUG`
- Additional notes:
  - You must choose a value from 0.5 to 0.99
