# Introducing Mineral Game by Dαsher! <img src="https://github.com/dasherwithalpha/Mineral-Game/blob/main/src/Resources/Textures/quarry-grayite-1000px.png" alt="Grayite Quarry Image" width="36"/>
An incremental game where the goal is to get as much as money you can.
Place quarries and gather minerals from underground and sell them.
Each quarry you place will have their own cycle of gathering minerals.
## Game has 4 different minerals:

- Brownite
- Grayite
- Whiteoxide
- Bluntric

Each one has a different value.
Brownite and Graynite has the lowest value, so it will make less money when sold. Whiteoxide has more, and Bluntric has the most value.

## You can also upgrade your quarries' attributes:
- Power, determines how many kilograms of minerals a quarry gathers in it's cycle.
- Frequency, determines how fast a quarry finish it's cycle.

### Extra notes:
Game is still in alpha, so a lot of things are missing.
Such as saving your progress, additional menus and start screen etc.

Game uses the NES controller layout. For the moment,
- Enter, acts as A
- Apostrophe (İ for TRQ keyboard), acts as B
- K, acts as Select
- L, acts as Start
- Arrow buttons, acts as D-Pad

To navigate in game, firstly press K to go into the menu mode, then press the right or left arrow keys while holding down apostrophe. To select an option from the 3rd screen, just use the arrow keys by themselves.

To place a quarry, go to the kilograms screen(the first screen you see in the game) and make sure you are in tile mode. A white border square will be seen on the world grid when you are at it. To switch between Tile and Menu modes, press K.

To run the game, open a command line terminal in the directory of where the mineralgame.jar is located and type "java -jar mineralgame.jar" and make sure Resources folder is in the same directory as mineralgame.jar.

I made it in Java 27 so probably it's required. Haven't tested in previous versions lol.