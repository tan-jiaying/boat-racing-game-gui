package boat.view 
import scalafxml.core.macros.sfxml
import boat.MainApp 
import boat.model.Game
import boat.model.Item
import boat.model.Current 
import boat.model.Trap 
import boat.model.Blank
import boat.model.Player
import scalafx.scene.image.{ImageView, Image}
import scalafx.event.ActionEvent
import scalafx.Includes._
import scalafx.scene.shape.{Rectangle, Circle}
import scalafx.scene.text.Text
import scalafx.scene.control.Button
import scalafx.scene.paint.Color 
import scalafx.scene.text.Font
import javafx.scene.text.FontWeight
import javafx.scene.{text => jfxst}

@sfxml 
class GameController(
    private val player1Up: Button, private val player1Down: Button, private val player1Left: Button, private val player1Right: Button, private val player1Dice: Button, private val player1Boat: Rectangle, private val player1Text: Text, 
    private val player2Up: Button, private val player2Down: Button, private val player2Left: Button, private val player2Right: Button, private val player2Dice: Button, private val player2Boat: Rectangle, private val player2Text: Text, 
    private val leftPanelBg: ImageView, private val rightPanelBg: ImageView, private val messageText: Text, private val backButton: Button, private val item0: Circle, private val item1: Circle, private val item2: Circle, 
    private val item3: Circle, private val item4: Circle, private val item5: Circle, private val item6: Circle, private val item7: Circle, private val item8: Circle, private val item9: Circle, private val item10: Circle, private val item11: Circle, 
    private val item12: Circle, private val item13: Circle, private val item14: Circle, private val item15: Circle, private val item16: Circle, private val item17: Circle, private val item18: Circle, private val item19: Circle, private val item20: Circle, 
    private val item21: Circle, private val item22: Circle, private val item23: Circle, private val item24: Circle, private val item25: Circle, private val item26: Circle, private val item27: Circle, private val item28: Circle, private val item29: Circle, 
    private val item30: Circle, private val item31: Circle, private val item32: Circle, private val item33: Circle, private val item34: Circle, private val item35: Circle, private val item36: Circle, private val item37: Circle, private val item38: Circle, 
    private val item39: Circle, private val item40: Circle, private val item41: Circle, private val item42: Circle, private val item43: Circle, private val item44: Circle, private val item45: Circle, private val item46: Circle, private val item47: Circle, 
    private val item48: Circle, private val item49: Circle, private val item50: Circle, private val item51: Circle, private val item52: Circle, private val item53: Circle, private val item54: Circle, private val item55: Circle, private val item56: Circle, 
    private val item57: Circle, private val item58: Circle, private val item59: Circle, private val item60: Circle, private val item61: Circle, private val item62: Circle, private val item63: Circle, private val item64: Circle, private val item65: Circle, 
    private val item66: Circle, private val item67: Circle, private val item68: Circle, private val item69: Circle, private val item70: Circle, private val item71: Circle, private val item72: Circle, private val item73: Circle, private val item74: Circle, 
    private val item75: Circle, private val item76: Circle, private val item77: Circle, private val item78: Circle, private val item79: Circle, private val item80: Circle, private val item81: Circle, private val item82: Circle, private val item83: Circle, 
    private val item84: Circle, private val item85: Circle, private val item86: Circle, private val item87: Circle, private val item88: Circle, private val item89: Circle, private val item90: Circle, private val item91: Circle, private val item92: Circle, 
    private val item93: Circle, private val item94: Circle, private val item95: Circle, private val item96: Circle, private val item97: Circle, private val item98: Circle, private val item99: Circle
) {
    // display background image 
    leftPanelBg.image = new Image("/image/leftPanelBg.jpg", 218, 600, false, false)
    rightPanelBg.image = new Image("/image/rightPanelBg.jpg", 684, 600, false, false)

    // initialize new game 
    var game = new Game() 
    var diceNum: Int = 1
    var desiredLocation: Int = 0
    
    // store all items in a list
    val items = List(item0, item1, item2, item3, item4, item5, item6, item7, item8, item9, item10, item11, item12, item13, item14, item15, item16, item17, item18, item19, item20, 
                    item21, item22, item23, item24, item25, item26, item27, item28, item29, item30, item31, item32, item33, item34, item35, item36, item37, item38, item39, item40,
                    item41, item42, item43, item44, item45, item46, item47, item48, item49, item50, item51, item52, item53, item54, item55, item56, item57, item58, item59, item60,
                    item61, item62, item63, item64, item65, item66, item67, item68, item69, item70, item71, item72, item73, item74, item75, item76, item77, item78, item79, item80, 
                    item81, item82, item83, item84, item85, item86, item87, item88, item89, item90, item91, item92, item93, item94, item95, item96, item97, item98, item99)

    // change current and trap colours
    for (item <- game.river.riverList) {
        var index: Int = game.river.riverList.indexOf(item)
        if (item.isInstanceOf[Current]) {
            items(index).fill = Color.Green

        } else if (item.isInstanceOf[Trap]) {
            items(index).fill = Color.Red
        }
    }

    // disable roll dice button and all move buttons 
    player1Dice.setDisable(true)
    player1Up.setDisable(true)
    player1Down.setDisable(true)
    player1Left.setDisable(true)
    player1Right.setDisable(true)

    player2Dice.setDisable(true)
    player2Up.setDisable(true)
    player2Down.setDisable(true)
    player2Left.setDisable(true)
    player2Right.setDisable(true)

    newRound(game.player1.location, game.player2.location) 

    // function to start a new round
    def newRound(player1Location: Int, player2Location: Int): Unit = {
        if (game.player1.location != 99 && game.player2.location != 99) {
            // unbold Player 2
            player2Text.setFont(new Font(jfxst.Font.font("Arial", FontWeight.NORMAL, 16)))
        
            // bold Player 1
            player1Text.setFont(new Font(jfxst.Font.font("Arial", FontWeight.BOLD, 16)))

            // enable player 1 roll dice button
            player1Dice.setDisable(false)

            // display roll dice message to prompt player 1
            messageText.setText("Player 1, \nplease roll the dice")

            // roll dice when player 1 clicks roll dice button
            player1Dice.onAction = (event: ActionEvent) => {
                diceNum = game.player1.rollDice()
                
                // disable player 1 roll dice button
                player1Dice.setDisable(true)

                // display number rolled 
                messageText.setText("Player 1 has rolled a " + diceNum + ", \nmove forward " + diceNum + " step(s)")
                desiredLocation = game.player1.location + diceNum

                if (desiredLocation > 99) {
                    desiredLocation = 99
                }
                
                // enable appropriate move buttons to allow player to move
                enableMoveButtons(game.player1.location, desiredLocation, 1)
            }   

            // roll dice when player 2 clicks roll dice button
            player2Dice.onAction = (event: ActionEvent) => {
                diceNum = game.player2.rollDice()
                
                // disable player 2 roll dice button
                player2Dice.setDisable(true)

                // display number rolled 
                messageText.setText("Player 2 has rolled a " + diceNum + ", \nmove forward " + diceNum + " step(s)")
                desiredLocation = game.player2.location + diceNum

                if (desiredLocation > 99) {
                    desiredLocation = 99
                }
                
                // enable appropriate move buttons to allow player to move
                enableMoveButtons(game.player2.location, desiredLocation, 2)
            } 
        }
    }

    // function to enable appropriate move buttons
    def enableMoveButtons(playerLocation: Int, desiredLocation: Int, playerNum: Int) : Unit = {
        // enable buttons only if player has not reached desired location
        if (playerLocation != desiredLocation) {
            if (playerLocation == 9 || playerLocation == 29 || playerLocation == 49 || playerLocation == 69 || playerLocation == 89) {
                if (playerNum == 1) {
                    player1Left.setDisable(false)
                    player1Up.setDisable(false)

                    player1Right.setDisable(true)
                    player1Down.setDisable(true)
                } else {
                    player2Left.setDisable(false)
                    player2Up.setDisable(false)

                    player2Right.setDisable(true)
                    player2Down.setDisable(true)
                }
            } else if (playerLocation == 10 || playerLocation == 30 ||playerLocation == 50 || playerLocation == 70 || playerLocation == 90) {
                if (playerNum == 1) {
                    player1Left.setDisable(false)
                    player1Down.setDisable(false)

                    player1Right.setDisable(true)
                    player1Up.setDisable(true)
                } else {
                    player2Left.setDisable(false)
                    player2Down.setDisable(false)

                    player2Right.setDisable(true)
                    player2Up.setDisable(true)
                }
            } else if (playerLocation == 19 || playerLocation == 39 || playerLocation == 59 || playerLocation == 79) {
                if (playerNum == 1) {
                    player1Right.setDisable(false)
                    player1Up.setDisable(false)
                    
                    player1Left.setDisable(true)
                    player1Down.setDisable(true)
                } else {
                    player2Right.setDisable(false)
                    player2Up.setDisable(false)
                    
                    player2Left.setDisable(true)
                    player2Down.setDisable(true)
                }
            } else if (playerLocation == 20 || playerLocation == 40 || playerLocation == 60 || playerLocation == 80) {
                if (playerNum == 1) {
                    player1Right.setDisable(false)
                    player1Down.setDisable(false)

                    player1Left.setDisable(true)
                    player1Up.setDisable(true)
                } else {
                    player2Right.setDisable(false)
                    player2Down.setDisable(false)

                    player2Left.setDisable(true)
                    player2Up.setDisable(true)
                }
            } else if (playerLocation == 0) {
                if (playerNum == 1) {
                    player1Right.setDisable(false)

                    player1Left.setDisable(true)
                    player1Up.setDisable(true)
                    player1Down.setDisable(true)
                } else {
                    player2Right.setDisable(false)

                    player2Left.setDisable(true)
                    player2Up.setDisable(true)
                    player2Down.setDisable(true)
                }
            } else { // middle items 
                if (playerNum == 1) {
                    player1Left.setDisable(false)
                    player1Right.setDisable(false)

                    player1Up.setDisable(true)
                    player1Down.setDisable(true)
                } else {
                    player2Left.setDisable(false)
                    player2Right.setDisable(false)

                    player2Up.setDisable(true)
                    player2Down.setDisable(true)
                }
            }
        } else if (desiredLocation == 99) {
            if (playerNum == 1) { // player 1 won
                messageText.setText("Game over. \nCongratulations, \nPlayer 1 has won!")  // display player 1 won message 

                // disable move buttons
                player1Left.setDisable(true)
                player1Right.setDisable(true)
            } else { // player 2 won
                messageText.setText("Game over. \nCongratulations, \nPlayer 2 has won!")  // display player 2 won message 

                // disable move buttons
                player2Left.setDisable(true)
                player2Right.setDisable(true)
            }
        } else { // disable all move buttons when desired location is reached
            if (playerNum == 1) {
                player1Left.setDisable(true)
                player1Right.setDisable(true)
                player1Up.setDisable(true)
                player1Down.setDisable(true)
            } else {
                player2Left.setDisable(true)
                player2Right.setDisable(true)
                player2Up.setDisable(true)
                player2Down.setDisable(true)
            }

            // check if current or trap is encountered
            if (playerNum == 1) {
                checkPlayerLocation(playerLocation, 1)
            } else {
                checkPlayerLocation(playerLocation, 2)
            }
        }
    }

    // function to check if player has encountered current or trap 
    def checkPlayerLocation(playerLocation: Int, playerNum: Int): Unit = {
        if (!(game.river.riverList(playerLocation).isInstanceOf[Blank])) {
            if (playerNum == 1) {
                desiredLocation = encounterItem(game.river.riverList(playerLocation), playerLocation, 1)

                // enable appropriate move buttons to allow player to move
                enableMoveButtons(playerLocation, desiredLocation, 1)
                game.player1.location = playerLocation
                
            } else {
                desiredLocation = encounterItem(game.river.riverList(playerLocation), playerLocation, 2)

                // enable appropriate move buttons to allow player to move
                enableMoveButtons(playerLocation, desiredLocation, 2)
                game.player2.location = playerLocation
            }
        } else {
            if (playerNum == 1 && game.player1.location != 99) { // switch to player 2
                // enable player 2 roll dice button
                player2Dice.setDisable(false)

                // unbold Player 1
                player1Text.setFont(new Font(jfxst.Font.font("Arial", FontWeight.NORMAL, 16)))
            
                // bold Player 2
                player2Text.setFont(new Font(jfxst.Font.font("Arial", FontWeight.BOLD, 16)))

                // enable player 2 roll dice button
                player2Dice.setDisable(false)

                // display roll dice message to prompt player 2
                messageText.setText("Player 2, \nplease roll the dice")
            } else if (playerNum == 2 & game.player2.location != 99) { // switch back to player 1
                newRound(game.player1.location, game.player2.location)
            }
        }
    }

    // function to randomly generate magnitude of current or trap
    def encounterItem(item: Item, location: Int, playerNum: Int): Int = {
        if (item.isInstanceOf[Current]) {
            (item.asInstanceOf[Current]).generateMagnitude()

            // display magnitude of current 
            if (playerNum == 1) {
                messageText.setText("Player 1 has encountered \na current of magnitude " + item.magnitude + ", \nmove forward " + item.magnitude + " step(s)")
            } else {
                messageText.setText("Player 2 has encountered \na current of magnitude " + item.magnitude + ", \nmove forward " + item.magnitude + " step(s)")
            }
            
            // set desired locaiton 
            desiredLocation = location + item.magnitude
            if (desiredLocation > 99) {
                desiredLocation = 99
            }

            // return desired location 
            desiredLocation
        } else {
            (item.asInstanceOf[Trap]).generateMagnitude() 

            // display magnitude of trap 
            if (playerNum == 1) {
                messageText.setText("Player 1 has encountered \na trap of magnitude " + item.magnitude + ", \nmove backward " + item.magnitude + " step(s)")
            } else {
                messageText.setText("Player 2 has encountered \na trap of magnitude " + item.magnitude + ", \nmove backward " + item.magnitude + " step(s)")
            }
        
            // set desired location
            desiredLocation = location - item.magnitude 
            if (desiredLocation < 0) {
                desiredLocation = 0
            }
        
            // return desired location
            desiredLocation
        } 
    }

    // function to handle player 1 move left button
    def handlePlayer1Left (action: ActionEvent) : Unit = {
        player1Boat.setLayoutX(player1Boat.getLayoutX - 60)
        if ((game.player1.location >= 10 && game.player1.location< 19) || (game.player1.location >= 30 && game.player1.location < 39) 
            || (game.player1.location >= 50 && game.player1.location < 59) || (game.player1.location >= 70 && game.player1.location < 79)
            || (game.player1.location >= 90 && game.player1.location < 99)) { 
                game.player1.location = game.player1.location + 1
        } else {
            game.player1.location = game.player1.location - 1
        }
        enableMoveButtons(game.player1.location, desiredLocation, 1)
    }

    // function to handle player 1 move right button
    def handlePlayer1Right (action: ActionEvent) : Unit = {
        player1Boat.setLayoutX(player1Boat.getLayoutX + 60)
        if ((game.player1.location > 10 && game.player1.location <= 19) || (game.player1.location > 30 && game.player1.location <= 39) 
            || (game.player1.location > 50 && game.player1.location <= 59) || (game.player1.location > 70 && game.player1.location <= 79)
            || (game.player1.location > 90 && game.player1.location <= 99)) {
                game.player1.location = game.player1.location - 1
        } else {
            game.player1.location = game.player1.location + 1
        }
        enableMoveButtons(game.player1.location, desiredLocation, 1)
    }

    // function to handle player 1 move up button
    def handlePlayer1Up (action: ActionEvent) : Unit = {
        player1Boat.setLayoutY(player1Boat.getLayoutY - 50)
        game.player1.location = game.player1.location + 1
        enableMoveButtons(game.player1.location, desiredLocation, 1)
    }

    // function to handle player 1 move down button
    def handlePlayer1Down (action: ActionEvent) : Unit = {
        player1Boat.setLayoutY(player1Boat.getLayoutY + 50)
        game.player1.location = game.player1.location - 1
        enableMoveButtons(game.player1.location, desiredLocation, 1)
    }

    // function to handle player 2 move left button
    def handlePlayer2Left (action: ActionEvent) : Unit = {
        player2Boat.setLayoutX(player2Boat.getLayoutX - 60)
        if ((game.player2.location >= 10 && game.player2.location < 19) || (game.player2.location >= 30 && game.player2.location < 39) 
            || (game.player2.location >= 50 && game.player2.location < 59) || (game.player2.location >= 70 && game.player2.location < 79)
            || (game.player2.location >= 90 && game.player2.location < 99)) { 
                game.player2.location = game.player2.location + 1
        } else {
            game.player2.location = game.player2.location - 1
        }
        enableMoveButtons(game.player2.location, desiredLocation, 2)
    }

    // function to handle player 2 move right button
    def handlePlayer2Right (action: ActionEvent) : Unit = {
        player2Boat.setLayoutX(player2Boat.getLayoutX + 60)
        if ((game.player2.location > 10 && game.player2.location <= 19) || (game.player2.location > 30 && game.player2.location <= 39) 
            || (game.player2.location > 50 && game.player2.location <= 59) || (game.player2.location > 70 && game.player2.location <= 79)
            || (game.player2.location > 90 && game.player2.location <= 99)) {
                game.player2.location = game.player2.location - 1
        } else {
            game.player2.location = game.player2.location + 1
        }
        enableMoveButtons(game.player2.location, desiredLocation, 2)
    }

    // function to handle player 2 move up button
    def handlePlayer2Up (action: ActionEvent) : Unit = {
        player2Boat.setLayoutY(player2Boat.getLayoutY - 50)
        game.player2.location = game.player2.location + 1
        enableMoveButtons(game.player2.location, desiredLocation, 2)
    }

    // function to handle player 2 move down button
    def handlePlayer2Down (action: ActionEvent) : Unit = {
        player2Boat.setLayoutY(player2Boat.getLayoutY + 50)
        game.player2.location = game.player2.location - 1
        enableMoveButtons(game.player2.location, desiredLocation, 2)
    }

    // funciton to handle Back button 
    def handleBack (action: ActionEvent): Unit = {
        MainApp.showMainMenu()
    }
}