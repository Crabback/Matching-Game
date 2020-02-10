import java.io.File;
import java.util.Random;
import processing.core.PApplet;
import processing.core.PImage;

//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P02 Matching Gaming
// Files: MatchingGame.java
// Course: CS 300
//
// Author: Zhengjia Mao
// Email: zmao27@wisc.edu
// Lecturer's Name: Gary DAHL
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Bailong Huang
// Partner Email: bhuang67@wisc.edu
// Partner Lecturer's Name: Gary DAHL
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// _YES__ Write-up states that pair programming is allowed for this assignment.
// _YES__ We have both read and understand the course Pair Programming Policy.
// _YES__ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: Jiayu Li
// Online Sources: NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

public class MatchingGame {

  // Congratulations message
  private final static String CONGRA_MSG = "CONGRATULATIONS! YOU WON!";
  // Cards not matched message
  private final static String NOT_MATCHED = "CARDS NOT MATCHED. Try again!";
  // Cards matched message
  private final static String MATCHED = "CARDS MATCHED! Good Job!";
  // 2D-array which stores cards coordinates on the window display
  private final static float[][] CARDS_COORDINATES =
      new float[][] {{170, 170}, {324, 170}, {478, 170}, {632, 170}, {170, 324}, {324, 324},
          {478, 324}, {632, 324}, {170, 478}, {324, 478}, {478, 478}, {632, 478}};
  // Array that stores the card images filenames
  private final static String[] CARD_IMAGES_NAMES = new String[] {"apple.png", "ball.png",
      "peach.png", "redFlower.png", "shark.png", "yellowFlower.png"};

  private static PApplet processing; // PApplet object that represents
  // the graphic display window
  private static Card[] cards; // one dimensional array of cards
  private static PImage[] images; // array of images of the different cards
  private static Random randGen; // generator of random numbers
  private static Card selectedCard1; // First selected card
  private static Card selectedCard2; // Second selected card
  private static boolean winner; // boolean evaluated true if the game is won,
  // and false otherwise
  private static int matchedCardsCount; // number of cards matched so far
  // in one session of the game
  private static String message; // Displayed message to the display window

  /**
   * Defines the initial environment properties of this game as the program starts
   */
  public static void setup(PApplet processing) {
    System.out.println("start");
    MatchingGame.processing = processing;
    images = new PImage[CARD_IMAGES_NAMES.length];
    for (int i = 0; i < CARD_IMAGES_NAMES.length; i++) {
      images[i] = processing.loadImage("images" + File.separator + CARD_IMAGES_NAMES[i]);
    }
    initGame();

  }

  /**
   * Initializes the Game
   */
  public static void initGame() {
    randGen = new Random(Utility.getSeed());
    selectedCard1 = null;
    selectedCard2 = null;
    matchedCardsCount = 0;
    winner = false;
    message = " ";

    cards = new Card[CARD_IMAGES_NAMES.length * 2];
    
    for (int i = 0; i < cards.length; i++) {
      boolean probe = false; 
      do { // place a card
        int rand = randGen.nextInt(CARD_IMAGES_NAMES.length * 2);
        // check whether the position has been occupied
        if (cards[rand] != null) {
          continue; //if occupied, go back
        } else {
          if (i < images.length) { // place one card of each image (first round)
            cards[rand] =
                new Card(images[i], CARDS_COORDINATES[rand][0], CARDS_COORDINATES[rand][1]);
            probe = true;

          } else { // place the second card of each image (second round)
            cards[rand] =
                new Card(images[i - 6], CARDS_COORDINATES[rand][0], CARDS_COORDINATES[rand][1]);
            probe = true;
          }
        }
      } while (!probe);
    }

  }

  /**
   * Callback method called each time the user presses a key
   */
  public static void keyPressed() {
    char det = processing.key;
    //if detects N or n, shuffle the cards
    if (det == 'N' || det == 'n') {
      initGame();
    }
  }

  /**
   * Callback method draws continuously this application window display
   */
  public static void draw() {
    // Set the color used for the background of the Processing window
    processing.background(245, 255, 250); // Mint cream color
    for (int i = 0; i < cards.length; i++) {
      // cards[i].setVisible(true);
      cards[i].draw();
    }
    displayMessage(message);
  }

  /**
   * Displays a given message to the display window
   * 
   * @param message to be displayed to the display window
   */
  public static void displayMessage(String message) {
    processing.fill(0);
    processing.textSize(20);
    processing.text(message, processing.width / 2, 50);
    processing.textSize(12);
  }

  /**
   * Checks whether the mouse is over a given Card
   * 
   * @return true if the mouse is over the storage list, false otherwise
   */
  public static boolean isMouseOver(Card card) {

    float cardX = card.getX();
    float cardY = card.getY();
    int cardW = card.getWidth();
    int cardH = card.getHeight();
    if (Math.abs(processing.mouseX - cardX) < cardW / 2
        && Math.abs(processing.mouseY - cardY) < cardH / 2) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Callback method called each time the user presses the mouse
   */

  public static void mousePressed() {
    // first check if the two selected cards are empty
    // if not empty but matched, leave visible but deselect and reset the two cards, then assign cards
    if (selectedCard1 != null && selectedCard2 != null
        && matchingCards(selectedCard1, selectedCard2) == true) {
      selectedCard1.deselect();
      selectedCard2.deselect();
      selectedCard1 = null;
      selectedCard2 = null;
      for (int i = 0; i < 12; i++) {
        if (isMouseOver(cards[i]) && cards[i].isVisible() == false) {
          cards[i].setVisible(true);
          cards[i].select();
          if (selectedCard1 == null) {
            selectedCard1 = cards[i];
          } else {
            selectedCard2 = cards[i];
          }
        }
      }
      // if not empty and not matched, flip back and reset the two cards, then assign cards
    } else if (selectedCard1 != null && selectedCard2 != null
        && matchingCards(selectedCard1, selectedCard2) == false) {
      selectedCard1.setVisible(false);
      selectedCard2.setVisible(false);
      selectedCard1.deselect();
      selectedCard2.deselect();
      selectedCard1 = null;
      selectedCard2 = null;
      for (int i = 0; i < 12; i++) {
        if (isMouseOver(cards[i]) && cards[i].isVisible() == false) {
          cards[i].setVisible(true);
          cards[i].select();
          if (selectedCard1 == null) {
            selectedCard1 = cards[i];
          } else {
            selectedCard2 = cards[i];
          }
        }
      }
    }
    // if empty, assign cards
    else {
      for (int i = 0; i < 12; i++) {
        if (isMouseOver(cards[i]) && cards[i].isVisible() == false) {
          cards[i].setVisible(true);
          cards[i].select();
          if (selectedCard1 == null) {
            selectedCard1 = cards[i];
          } else {
            selectedCard2 = cards[i];
          }
        }
      }
    }
      // if matched, print matched messages; if not, print wrong messages
      if (selectedCard1 != null && selectedCard2 != null) {
        if (matchingCards(selectedCard1, selectedCard2)) {
          message = MATCHED;
          matchedCardsCount += 2;
          if (matchedCardsCount == 12) {
            winner = true;
            selectedCard1.deselect();
            selectedCard2.deselect();
            message = CONGRA_MSG;
          }
        } else {

          message = NOT_MATCHED;
        }
      }
    
  }

  /**
   * Checks whether two cards match or not
   * 
   * @param card1 reference to the first card
   * @param card2 reference to the second card
   * @return true if card1 and card2 image references are the same, false otherwise
   */
  public static boolean matchingCards(Card card1, Card card2) {
    if (card1.getImage() == card2.getImage()) {
      return true;
    } else {
      return false;
    }
  }


  public static void main(String[] args) {

    Utility.runApplication();
  }

}
