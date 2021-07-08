# List of Rules in the COMMAND Category

For a list of all implemented Rules go [here](../README.md)
## Index
Count: 6
- [commandFrame](#commandframe)
- [commandMaxEffect](#commandmaxeffect)
- [commandPeek](#commandpeek)
- [commandSkull](#commandskull)
- [commandSlimeChunk](#commandslimechunk)
- [commandSudo](#commandsudo)

## Rules in COMMAND Category

### commandFrame
A command that makes the nearest item frame in a 5 Block radius around the player, that holds an item, invisible or visible
- Type: `String`
- Default value: `ops`
- Required options: `true`, `false`, `ops`
- Categories: `COMMAND`, `RUG`

### commandMaxEffect
A command to quickly give the player the specified effect for infinite time and at max level

This is basically just an alias for: `/effect give @s <effect> 999999 255 true`
- Type: `String`
- Default value: `ops`
- Required options: `true`, `false`, `ops`
- Categories: `COMMAND`, `RUG`

### commandPeek
A command that shows the Inventory or Ender Chest of the specified player
- Type: `String`
- Default value: `ops`
- Required options: `true`, `false`, `ops`
- Categories: `COMMAND`, `RUG`

### commandSkull
A command that gives the executing Player the Player Head of the selected Player
- Type: `String`
- Default value: `ops`
- Required options: `true`, `false`, `ops`
- Categories: `COMMAND`, `RUG`

### commandSlimeChunk
A command that shows if the current chunk is a slime chunk based on the set slimeChunkPercentage
- Type: `String`
- Default value: `ops`
- Required options: `true`, `false`, `ops`
- Categories: `COMMAND`, `RUG`

### commandSudo
A command that executes the exact input as the selected Player

This is not just an alias for /execute, because inputs without '/' will be sent as a chat message by the selected Player
- Type: `String`
- Default value: `ops`
- Required options: `true`, `false`, `ops`
- Categories: `COMMAND`, `RUG`
