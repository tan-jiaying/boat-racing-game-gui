package boat.model
import scala.util.Random

class Player() {
    // properties
    var location: Int = 0

    // methods
    def rollDice(): Int = {
        var random = new Random()
        1 + random.nextInt(6)
    }
}

