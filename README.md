# About The Project
As a senior at Massachusetts Academy of Math and Science in Worcester, I was tasked with finding a senior project I would have to do for an entire 100 hours by the end of the year. As a result, I decided to have the optimistic idea to build my own chess AI (Artificial Intelligence)! However, it came dawned on my rather quickly (or maybe after 20 hours into coding and banging my head against the wall) that my computer science and general coding skills lacked the depth needed to meet my intended goal. In lieu, I changed the course of the project to instead be a more achievable goal, build my own chess software entirely from scratch. 

As a way to make the code actually usuable to other people that somehow happen to find this page while scrolling through the vast expanse of GitHub and accidentally finding this page, I decided to break up the software into neat, orderly packages instead of the tempting "one class that somehow holds 30,000 lines of pure spaghetti code." Hopefully someone can find them useful down the road... 

## Current Status

## Progress Over the Project

The following journey will guide through the horrible trainwreck of the development process just to reach version 1.0

Format: _Version #  (Robustness/Stability) (Hours Used to reach Development)_ 

### Version 0.1 (Stable) (3 Hours)
A singular Piece object was made, housing all of the data necessary for all different types of pieces. This was a major structural blunder, especially since I was unaware of what abstract classes, super classes, interfaces, or even enumerations were. Thus, the overall design was utter garbage. Every class-scope data type was encapsulated with both getters and setters, in essence entirely defeating the purpose of encapsulation in the first place. On top of that, most the data was in the form of strings since this singular piece object had to adapt into any of the six varieties of chess pieces, as well as be either a black or a white piece. Even worse, I wasted an enormous amount of time typing up almost every possible configuration of constructor for some reason despite the need for only one, arguably two, constructors necessary in hindsight. 

Also, a Board object was made, with only two constructors and a 2-dimensional array of string objects, and the string objects mapped to to an index of an arrayList of Piece objects. Looking back I remember I thought Strings and ArrayLists we also primitive data-types, and also 2-dimensional arrays could only be type-safed with a primitive data type (hence why I used the String object and not the Piece object directly). The god-awful abstraction of the board class brought upon so much tedious work to fully flesh out the Board class in Version 0.4

### Version 0.4 (Stable) (10 Hours)
In this version, 

### Version 0.8 (Almost Stable) (20 hours) 

### Version 0.8.5 (Horribly Broken) (10 Hours)

### Version 1.0 (20 Hours)



## Questions
If you have any questions, comments, concerns, rebuttles, exclamations, accusations, or epic roasts regarding the code or the project in general, feel free to email me at _jcdeoliveira@wpi.edu_.   
