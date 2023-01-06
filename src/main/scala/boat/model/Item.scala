package boat.model
import scala.util.Random

abstract class Item() {
    // abstract properties
    var magnitude: Int 
    var location: Int
  
    // method
    def generateMagnitude(): Unit = { 
        // generate random number from 1 to 4
        var random = new Random()
        magnitude = 1 + random.nextInt(4)
    }
}

class Current extends Item {
    // properties 
    var magnitude = 0
    var location = 1 + new Random().nextInt(98)  
}

class Trap extends Item {
    // properties 
    var magnitude = 0
    var location = 1 + new Random().nextInt(98)   
}

class Blank extends Item {
    // properties
    var magnitude = 0
    var location = 1 + new Random().nextInt(98)
}
