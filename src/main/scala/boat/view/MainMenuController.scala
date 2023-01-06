package boat.view 
import scalafxml.core.macros.sfxml
import boat.MainApp 
import scalafx.scene.image.{ImageView, Image}
import scalafx.event.ActionEvent

@sfxml
class MainMenuController(private val gameLogo: ImageView, private val mainMenuBg: ImageView) {
    // display images 
    gameLogo.image = new Image("/image/gameLogo.png")
    mainMenuBg.image = new Image("/image/mainMenuBg.jpg", 900, 600, false, false)

    // function to handle Start button 
    def handleStart (action: ActionEvent): Unit = {
        // load game
        MainApp.showGame()
    }

    // function to handle Exit button 
    def handleExit (action: ActionEvent): Unit = {
        // close app
        System.exit(0)
    }

    // function to handle How to Play button 
    def handleInstructions (action: ActionEvent): Unit = {
        // load instructions page 
        MainApp.showInstructions()
    }
}