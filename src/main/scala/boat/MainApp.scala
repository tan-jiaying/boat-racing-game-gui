package boat
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.Includes._
import scalafxml.core.{NoDependencyResolver, FXMLLoader}
import javafx.{scene => jfxs}
import scalafx.scene.image.Image

object MainApp extends JFXApp {
    // load root layout
    val rootResource = getClass.getResourceAsStream("view/RootLayout.fxml")
    val loader = new FXMLLoader(null, NoDependencyResolver)
    loader.load(rootResource)
    val roots = loader.getRoot[jfxs.layout.BorderPane] 

    // initalize stage
    stage = new PrimaryStage {
        title = "BoatRacingGame"
        scene = new Scene {
            root = roots
        }
        width  = 900
        height = 600

        icons += new Image(
            getClass().getResourceAsStream("/image/gameLogo.png")
        )
    }

    // function to load main menu
    def showMainMenu(): Unit = {
        val resource = getClass.getResourceAsStream("view/MainMenu.fxml")
        val loader = new FXMLLoader(null, NoDependencyResolver)
        loader.load(resource) 
        val roots = loader.getRoot[jfxs.layout.AnchorPane]
        this.roots.setCenter(roots)
    }

    // function to load instructions page 
    def showInstructions(): Unit = {
        val resource = getClass.getResourceAsStream("view/Instructions.fxml")
        val loader = new FXMLLoader(null, NoDependencyResolver)
        loader.load(resource)
        val roots = loader.getRoot[jfxs.layout.AnchorPane]
        this.roots.setCenter(roots)
    }

    // function to load game 
    def showGame(): Unit = {
        val resource = getClass.getResource("view/Game.fxml")
        val loader = new FXMLLoader(resource, NoDependencyResolver)
        loader.load() 
        val roots = loader.getRoot[jfxs.layout.AnchorPane]
        this.roots.setCenter(roots)
    }

    // show main menu when app starts 
    showMainMenu()
}