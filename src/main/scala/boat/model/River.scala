package boat.model
import scala.collection.mutable.ArrayBuffer
import scala.util.Random
import scala.util.control.Breaks.{break, breakable}

class River() {
    // properties
    val riverList: Array[Item] = new Array[Item](100)

    // automatically create river when constructor is called
    createRiver()

    // method to fill river with random number of currents and traps
    def createRiver(): Unit = {
        var riverItems: ArrayBuffer[Item] = new ArrayBuffer[Item]()
        var random = new Random()
        
        // create 15-20 currents 
        var currentNum: Int = 15 + random.nextInt(6)
        for (i <- 1 to currentNum) {
            var current: Current = new Current()
            var validLocation: Boolean = false
            while (!validLocation) {
                var counter: Int = 0
                breakable {
                    for (item <- riverItems) {
                        if (current.location != item.location) {
                            counter += 1
                        } else { // create new current if item already exists in location
                            current = new Current()
                            break
                        }
                    }
                }
                

                if (counter == riverItems.size) { // add current to riverItems only when end is reached
                    riverItems += current
                    validLocation = true
                }
            }
        }

        // create 15-20 traps
        var trapNum: Int = 15 + random.nextInt(6)
        for (i <- 1 to trapNum) {
            var trap: Trap = new Trap()
            var validLocation: Boolean = false

            while (!validLocation) {
                var counter: Int = 0
                breakable {
                    for (item <- riverItems) {
                        if (trap.location != item.location) {
                            counter += 1
                        } else { // create new trap if item already exists in location
                            trap = new Trap()
                            break
                        }
                    }
                }

                if (counter == riverItems.size) { // add trap to riverItems only when end of riverItems is reached
                    riverItems += trap
                    validLocation = true
                }
            }
        }


        // initialize riverList with blank items
        for (i <- 0 to 99) {
            riverList(i) = new Blank()
        }

        // insert currents and traps into riverList
        for (item <- riverItems) {
            riverList(item.location) = item
        }

        // create new river if too many traps are next to each other -> might cause player to never move forward
        while (!validRiver()) {
            createRiver()
        }
    }

    // function to check if there are too many traps next to each other
    def validRiver(): Boolean = {
        var count: Int = 0
        for (i <- 0 to 98) {
            if (riverList(i).isInstanceOf[Trap] && riverList(i + 1).isInstanceOf[Trap]) {
                count += 1
            } else {
                count = 0
            }
        }

        if (count < 5) {
            true
        } else {
            false
        }
    }
}