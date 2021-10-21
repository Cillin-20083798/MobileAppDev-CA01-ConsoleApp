package console.main


import console.models.DamageOverTimeModel
import console.models.DirectDamageModel
import console.controllers.DPSController

    val controller = DPSController()

    fun main(args: Array<String>) {

        println("Placemark Kotlin App Version 1.0")

        var input: Int

        do {1
            input = controller.menu()
            when(input) {
                1 -> controller.addNewDOTSource()
                2 -> controller.addNewDDSource()
                3 -> controller.updateDamageOverTime()
                4 -> controller.updateDirectDamage()
                5 -> controller.listDOT()
                6 -> controller.listDD()
                7 -> controller.searchDamageSource()
                8 -> controller.searchDamageSource()
                9 -> controller.deleteDot()
                10 -> controller.deleteDD()
                -1 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        println("Goodbye!")
    }

    fun GoodInput(lowVal: Int, highVal: Int) : Int {
        var goodInput = false

        return 1

    }


