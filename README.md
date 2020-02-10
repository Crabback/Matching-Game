# -Matching-Game

This assignment involves developing a graphical application that represents a simple matching
game with cards. Our game is a one player memory game in which 12 cards are initially laid
face down on a surface. Each turn, the player selects two face down cards, which are then
flipped over. If the two cards match, they remain face up. Otherwise, they are both flipped
face down. The player wins the game when he succeeds to turn over all pairs of matching cards.
Fig.1 illustrates an example of what this program might look like when it is done.

We have prepared a jar file that contains the processing library, along with a few extra object
types to help you build this and future assignments. Download this CS300MatchingGame.jar
file and copy it into the project folder that you just created. Then, right-click on this file in the
“Package Explorer” within Eclipse, choose “Build Path” and then “Add to Build Path” from
the menu. Note: If the .jar file is not immediately visible within Eclipse’s Package Explorer,
try right-clicking on your project folder and selecting “Refresh”.
(Note that for Chrome users on MAC, Chrome may block the the jar file and incorrectly
reports it as a malicious file. To be able to copy the downloaded jar file, Go to “chrome://downloads/”
and click on “Show in folder” to open the folder where your jar file is located.)
If the “Build Path” entry is missing when you right click on the jar file in the “Package
Explorer”, follow the next set of instructions to add the jar file to the build path:

1. Right-click on the project and choose “properties”.
2. Click on the “Java Build Path” option in the left side menu.
3. From the Java Build Path window, click on the “Libraries” Tab.
4. You can add the “CS300MatchingGame.jar” file located in your project folder by clicking
“Add JARs...” from the right side menu.
5. Click on “Apply” button.

# 2.2 Check your project setup
Now, to test that the CS300MatchingGame.jar file library is added appropriately to the build
path of your project, try running your program with the following method being called from
main() method .
Utility.runApplication(); // starts the application
If everything is working correctly, you should see a blank window that appears with the title,
“Matching Cards Game” as depicted in Fig. 2. Please consult piazza or one of the consultants,
if you have any problems with this setup before proceeding.
