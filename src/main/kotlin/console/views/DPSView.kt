package console.views

import console.models.DirectDamageModel
import DirectDamageJSONStore
import DamageOverTimeJSONStore
import console.models.DamageOverTimeModel
import kotlin.math.roundToInt
import kotlin.math.floor

class DPSView {

    fun menu() : Int {

        var option : Int
        var input: String?

        println("MAIN MENU")
        println(" 1. Add New Damage Over Time Source")
        println(" 2. Add New Direct Damage Source")
        println(" 3. Update a Damage Over Time Source")
        println(" 4. Update a Direct Damage Source")
        println(" 5. List All Damage Over Time Sources")
        println(" 6. List All Direct Damage Sources")
        println(" 7. Search For Damage Over Time Source By Name")
        println(" 8. Search For Direct Damage Source By Name")
        println(" 9. Search For Damage Over Time Source By DPS")
        println(" 10. Search For Direct Damage Source By DPS")
        println(" 11. Delete a Damage Over Time Source")
        println(" 12. Delete a Direct Damage Source")
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
    /** 1 -> controller.addNewDOTSource()
    2 -> controller.addNewDDSource()
    3 -> controller.updateDamageOverTime()
    4 -> controller.updateDirectDamage()
    5 -> controller.listDOT()
    6 -> controller.listDD()
    7 -> controller.searchDotByName()
    8 -> controller.searchDDByName()
    9 -> controller.deleteDot()
    10 -> controller.deleteDD()**/

    fun listAllDirectDamage(dds: DirectDamageJSONStore) {
        println("Listing All Direct Damage")
        println()
        dds.logAll()
        println()
    }

    fun listAllDamageOverTime(dots: DamageOverTimeJSONStore) {
        println("Listing All Damage Over Time")
        println()
        dots.logAll()
        println()
    }

    fun showDirectDamage(directDamage : DirectDamageModel) {
        if(directDamage != null)
            println("Damage Details [ $directDamage ]")
        else
            println("Not Found...")
    }

    fun showDamageOverTime(damageOverTime : DamageOverTimeModel) {
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
        directDamage.magSize = readLine()!!.toInt()

        return directDamage.name.isNotEmpty()
                && !directDamage.damagePerHit.isNaN()
                && !directDamage.timeBetweenAttacks.isNaN()
                && !directDamage.numberOfProjectiles.isNaN()
                && !directDamage.reloadSpeed.isNaN()
                && directDamage.magSize != null
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

    fun updateDirectDamage(directDamage: DirectDamageModel) : Boolean {

        var tempName: String?
        var tempDamagePerHit: Float?
        var tempTimeBetweenAttacks: Float?
        var tempNumberOfProjectiles: Float?
        var tempReloadSpeed: Float?
        var tempMagSize: Int?

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
            tempMagSize = readLine()!!.toIntOrNull()

            //if (!tempName.isNullOrEmpty() && tempDamagePerHit != null && tempTimeBetweenAttacks != null && tempNumberOfProjectiles != null && tempReloadSpeed != null && tempMagSize != null) {
            if (!tempName.isNullOrEmpty())
                directDamage.name = tempName

            if(tempDamagePerHit != null)
                directDamage.damagePerHit = tempDamagePerHit

            if(tempTimeBetweenAttacks != null)
                directDamage.timeBetweenAttacks = tempTimeBetweenAttacks

            if(tempNumberOfProjectiles != null)
                directDamage.numberOfProjectiles = tempNumberOfProjectiles

            if(tempReloadSpeed != null)
                directDamage.reloadSpeed = tempReloadSpeed

            if(tempMagSize != null)
                directDamage.magSize = tempMagSize

            directDamage.dps60 = calculateDPS(directDamage, 60f)
            directDamage.dps5 = calculateDPS(directDamage, 5f)
            //    return true
            //}
        }
        return true
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
            print("Enter a new initial damage value, was [ " + damageOverTime.initialDamage + " ] : ")
            tempInitialDamage = readLine()!!.toFloatOrNull()
            print("Enter a new duration value, was [ " + damageOverTime.duration + " ] : ")
            tempDuration = readLine()!!.toFloatOrNull()
            print("Enter a new percent increase per tick (0 if none 1 if doubles every tick), was [ " + damageOverTime.percentIncreasePerTick + " ] : ")
            tempPercentIncrease = readLine()!!.toFloatOrNull()


            //if (!tempName.isNullOrEmpty() && tempTickTime != null && tempDamagePerTick != null && tempInitialDamage != null && tempDuration != null && tempPercentIncrease != null) {
            if(!tempName.isNullOrEmpty())
                damageOverTime.name = tempName

            if(tempTickTime != null)
                damageOverTime.tickTime = tempTickTime

            if(tempDamagePerTick != null)
                damageOverTime.damagePerTick = tempDamagePerTick

            if(tempInitialDamage != null)
                damageOverTime.initialDamage = tempInitialDamage

            if(tempDuration != null)
                damageOverTime.duration = tempDuration

            if(tempPercentIncrease != null)
                damageOverTime.percentIncreasePerTick = tempPercentIncrease

            damageOverTime.dps60 = calculateDPS(damageOverTime, 60f)
            damageOverTime.dps5 = calculateDPS(damageOverTime, 5f)
            damageOverTime.dpsDuration = calculateDPS(damageOverTime, damageOverTime.duration)
                //return true
            //}
        }
        return true
    }

    fun getId() : Long {
        var strId : String?
        var searchId: Long

        print("Enter id to Search/Update : ")
        strId = readLine()!!
        searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toLong()
        else
            -9
        return searchId
    }

    fun calculateDPS (dot: DamageOverTimeModel, time: Float) : Float{

        /**var tickTime: Float = 0f,
        var damagePerTick: Float = 0f,
        var initialDamage: Float = 0f,
        var duration: Float = 0f,
        var percentIncreasePerTick: Float = 0f,**/
        var totalDamage = 0f;
        var currentTickDamage = dot.damagePerTick
        var totalDotDamage = 0f
        var totalTicksPerDuration = floor((1 / dot.tickTime) * dot.duration)
        var iterateCount = 0f
        var totalTicksLeft = 0f

        if(time >= dot.duration) {
            //Gets the total amount of times the dot will cycle
            iterateCount = floor(time / dot.duration) // floor this //6

            //Calc each dot value
            for (i in 1..totalTicksPerDuration.roundToInt()) {
                currentTickDamage += currentTickDamage * dot.percentIncreasePerTick
                totalDotDamage += currentTickDamage // 2000
            }

            totalDamage = (totalDotDamage + dot.initialDamage) * iterateCount //13200
        }

        //Finding the remainder ticks
        currentTickDamage = dot.damagePerTick
        totalTicksLeft = ((time / dot.duration) - iterateCount) * totalTicksPerDuration
        if (totalTicksLeft == 0f) return totalDamage / time

        //Calc each tick
        for (i in 1 .. totalTicksLeft.roundToInt()) {
            println(i)
            currentTickDamage += currentTickDamage * dot.percentIncreasePerTick
            totalDotDamage += currentTickDamage
        }

        return (totalDamage + totalDotDamage + dot.initialDamage) / time


    }

    fun calculateDPS (dd: DirectDamageModel, time: Float) : Float {
        /**var damagePerHit: Float = 0f,
        var timeBetweenAttacks: Float = 0f,
        var numberOfProjectiles: Float = 0f,
        var reloadSpeed: Float = 0f,
        var magSize: Float = 0f,**/


        if(dd.magSize == 0){
            var totalAttacks = floor(time / dd.timeBetweenAttacks) //floor this
            println(totalAttacks)
            return totalAttacks * (dd.damagePerHit * dd.numberOfProjectiles) / time
        }else{
            var timeToShootMag = dd.magSize * dd.timeBetweenAttacks

            var totalBullets = floor(time/dd.timeBetweenAttacks)

            var reloads = totalBullets / dd.magSize


            var timeReloading = timeToShootMag * reloads

            var totalAttacks = time / dd.timeBetweenAttacks - timeReloading

            return totalAttacks * (dd.damagePerHit * dd.numberOfProjectiles) / time
        }

    }

    fun getStringInput() : String{
        var str : String?

        print("Enter string to Search/Update : ")
        str = readLine()!!
        return str
    }

    fun getFloatInput() : Float{
        var valToSearch : Float

        print("Enter string to Search/Update : ")
        valToSearch = readLine()!!.toFloat()

        return valToSearch
    }

    /**
    fun addDamageSource(){
        var input: Int?
        print("Would you like to add a 1) DOT or 2) Direct Damage?")
        input = readLine()!!.toIntOrNull()

        if(input == 1){
            var newDot = DamageOverTimeModel()

            if(addDamageOverTime(newDot)){

            }

        }else if (input ==2){

        }
    }
    **/

}