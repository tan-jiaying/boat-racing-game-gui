package boat.view 
import scalafxml.core.macros.sfxml
import boat.MainApp 
import scalafx.scene.image.{ImageView, Image}
import scalafx.event.ActionEvent

@sfxml 
class InstructionsController(private val instructionsBg: ImageView) {
    // display background image
    instructionsBg.image = new Image("/image/instructionsBg.jpg", 900, 600, false, false)

    // function to handle Back button 
    def handleBack (action: ActionEvent): Unit = {
        MainApp.showMainMenu()
    }
}