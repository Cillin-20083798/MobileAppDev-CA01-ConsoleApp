package console.views

import console.models.DirectDamageModel
import DirectDamageJSONStore
import DamageOverTimeJSONStore
import console.models.DamageOverTimeModel

class DPSView {

    fun menu() : Int {

        var option : Int
        var input: String?

        println("MAIN MENU")
        println(" 1. Add New Damage Source")
        println(" 2. Update a Damage Source")
        println(" 3. List All Damage Sources")
        println(" 4. Search For Damage Source")
        println("-1. Exit")
        println()
        print("Enter Option : ")
        input = readLine()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -9
        return option
    }

    fun listAllDirectDamage(dds: DirectDamageJSONStore) {
        println("Listing All Direct Damage")
        println()
        dds.logAll()
        println()
    }

    fun listAllDamageOverTime(dots: DamageOverTimeJSONStore) {
        println("Listing All Direct Damage")
        println()
        dots.logAll()
        println()
    }

    fun showDirectDamage(directDamage : DirectDamageJSONStore) {
        if(directDamage != null)
            println("Damage Details [ $directDamage ]")
        else
            println("Not Found...")
    }

    fun showDamageOverTime(damageOverTime : DamageOverTimeJSONStore) {
        if(damageOverTime != null)
            println("Damage Details [ $damageOverTime ]")
        else
            println("Not Found...")
    }


    fun addDirectDamage(directDamage : DirectDamageModel) : Boolean {

        println()
        print("Enter a name : ")
        directDamage.name = readLine()!!
        print("Enter the damage per Hit : ")
        directDamage.damagePerHit = readLine()!!.toFloat()
        print("Enter the time between attacks : ")
        directDamage.timeBetweenAttacks = readLine()!!.toFloat()
        print("Enter the number of projectiles : ")
        directDamage.numberOfProjectiles = readLine()!!.toFloat()
        print("Enter the reload speed if applicable: ")
        directDamage.reloadSpeed = readLine()!!.toFloat()
        print("Enter a mag size if applicable: ")
        directDamage.magSize = readLine()!!.toFloat()

        return directDamage.name.isNotEmpty()
                && !directDamage.damagePerHit.isNaN()
                && !directDamage.timeBetweenAttacks.isNaN()
                && !directDamage.numberOfProjectiles.isNaN()
                && !directDamage.reloadSpeed.isNaN()
                && !directDamage.magSize.isNaN()
    }

    fun addDamageOverTime(damageOverTime: DamageOverTimeModel) : Boolean {

        println()
        print("Enter a name : ")
        damageOverTime.name = readLine()!!
        print("Enter the time per tick : ")
        damageOverTime.tickTime = readLine()!!.toFloat()
        print("Enter the damage per tick : ")
        damageOverTime.damagePerTick = readLine()!!.toFloat()
        print("Enter the initial damage if applicable : ")
        damageOverTime.initialDamage = readLine()!!.toFloat()
        print("Enter the duration of the dot : ")
        damageOverTime.duration = readLine()!!.toFloat()
        print("Enter the percent increase per tick (0 if none 1 if doubles every tick): ")
        damageOverTime.percentIncreasePerTick = readLine()!!.toFloat()

        return damageOverTime.name.isNotEmpty()
                && !damageOverTime.tickTime.isNaN()
                && !damageOverTime.damagePerTick.isNaN()
                && !damageOverTime.initialDamage.isNaN()
                && !damageOverTime.duration.isNaN()
                && !damageOverTime.percentIncreasePerTick.isNaN()

    }

    fun updateDirectDamageData(directDamage: DirectDamageModel) : Boolean {

        var tempName: String?
        var tempDamagePerHit: Float?
        var tempTimeBetweenAttacks: Float?
        var tempNumberOfProjectiles: Float?
        var tempReloadSpeed: Float?
        var tempMagSize: Float?

        if (directDamage != null) {
            print("Enter a new name for [ " + directDamage.name + " ] : ")
            tempName = readLine()!!
            print("Enter a new damage per hit value, was [ " + directDamage.damagePerHit + " ] : ")
            tempDamagePerHit = readLine()!!.toFloatOrNull()
            print("Enter a new time between attacks value, was [ " + directDamage.timeBetweenAttacks + " ] : ")
            tempTimeBetweenAttacks = readLine()!!.toFloatOrNull()
            print("Enter a new number of projectiles value, was [ " + directDamage.numberOfProjectiles + " ] : ")
            tempNumberOfProjectiles = readLine()!!.toFloatOrNull()
            print("Enter a new reload speed value if applicable else set 0, was [ " + directDamage.reloadSpeed + " ] : ")
            tempReloadSpeed = readLine()!!.toFloatOrNull()
            print("Enter a new mag size value if applicable else set 0, was [ " + directDamage.magSize + " ] : ")
            tempMagSize = readLine()!!.toFloatOrNull()

            if (!tempName.isNullOrEmpty() && tempDamagePerHit != null && tempTimeBetweenAttacks != null && tempNumberOfProjectiles != null && tempReloadSpeed != null && tempMagSize != null) {
                directDamage.name = tempName
                directDamage.damagePerHit = tempDamagePerHit
                directDamage.timeBetweenAttacks = tempTimeBetweenAttacks
                directDamage.numberOfProjectiles = tempNumberOfProjectiles
                directDamage.reloadSpeed = tempReloadSpeed
                directDamage.magSize = tempMagSize

                return true
            }
        }
        return false
    }


    fun updateDamageOverTime(damageOverTime: DamageOverTimeModel) : Boolean {

        var tempName: String?
        var tempTickTime: Float?
        var tempDamagePerTick: Float?
        var tempInitialDamage: Float?
        var tempDuration: Float?
        var tempPercentIncrease: Float?


        if (damageOverTime != null) {
            print("Enter a new name for [ " + damageOverTime.name + " ] : ")
            tempName = readLine()!!
            print("Enter a new tick time value, was [ " + damageOverTime.tickTime + " ] : ")
            tempTickTime = readLine()!!.toFloatOrNull()
            print("Enter a new damage per tick value, was [ " + damageOverTime.damagePerTick + " ] : ")
            tempDamagePerTick = readLine()!!.toFloatOrNull()
            print("Enter a new time between attacks value, was [ " + damageOverTime.initialDamage + " ] : ")
            tempInitialDamage = readLine()!!.toFloatOrNull()
            print("Enter a new number of projectiles value, was [ " + damageOverTime.duration + " ] : ")
            tempDuration = readLine()!!.toFloatOrNull()
            print("Enter a new reload speed value if applicable else set 0, was [ " + damageOverTime.percentIncreasePerTick + " ] : ")
            tempPercentIncrease = readLine()!!.toFloatOrNull()


            if (!tempName.isNullOrEmpty() && tempTickTime != null && tempDamagePerTick != null && tempInitialDamage != null && tempDuration != null && tempPercentIncrease != null) {
                damageOverTime.name = tempName
                damageOverTime.tickTime = tempTickTime
                damageOverTime.damagePerTick = tempDamagePerTick
                damageOverTime.initialDamage = tempInitialDamage
                damageOverTime.duration = tempDuration
                damageOverTime.percentIncreasePerTick = tempPercentIncrease


                return true
            }
        }
        return false
    }

}