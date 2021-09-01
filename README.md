# ExoPlayerSlider

This project is a small demo for exoplayer shown in a vertical viewpager. 

Challenges:
Reusing a single player to avoid memory issues, effectively creating efficient code, able to show many video items with minimal memory footprint.

Tests:
At some point Ill add some tests.

Structure:
2 main layers: Repository (lower) + ui (uppper)

Repository module: Consists of an interface Repository that provides entities to the upper layer. The upper layer does not need to know about the source of the provided 
data. This way we can later extend it to be the network, a database, or any other source.
For this demo, all data comes from a couple of files in the assets directory. 

UI module: This module depends on the repository module. It will ask for the required data to the other module. This way using this architectural pattern we can reduce cohesion.
Inside this module we have two main sections: 
- Home: Shows the video feed
- Profile: Shows basic information about the user
