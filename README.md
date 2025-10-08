# Experimentalist

This mod removes the "Worlds using Experimental Settings are not supported" screen and allows you to enable experimental features globally. Those features will then be enabled by default on world creation and hidden from the experimental features screen. Worlds using those features will not be flagged as experimental and will not show the "Experimental" flavor text in the world selection screen.

This mod has no dependencies (not even Fabric API).

## Use cases

This mod can be useful for modpack authors who want to enable the experimental features available in Minecraft in their modpack in a way that requires no user interaction and is invisible to the user, or just for convenience if you like playing with experimental features.

## Configuration

By default, none of the experimental features are enabled globally. To make an experimental feature global, open the `experimentalist.json` config file in the mod config folder and change the value for the desired feature from `false` to `true`. This config file will be created after launching the game for the first time after installing the mod, and the changes you make will apply after restarting the game.