package console.controllers
import console.models.DamageOverTimeModel
import console.models.DirectDamageModel
import console.views.DPSView
import DamageOverTimeJSONStore
import DirectDamageJSONStore
import mu.KotlinLogging
import kotlin.math.roundToInt

class DPSController {

    val dpsView = DPSView()
    var dotStore = DamageOverTimeJSONStore()
    var ddStore = DirectDamageJSONStore()
    var logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching DPS Calculator App" }
        println("Dps Kotlin App")
    }

    fun menu() :Int { return dpsView.menu() }

    fun addNewDOTSource() {
        var newDot = DamageOverTimeModel()

        if(dpsView.addDamageOverTime(newDot)){
            dotStore.create(newDot)
        }else{
            logger.info("Failed to add, try again")
        }
    }

    fun addNewDDSource() {
        var newDD = DirectDamageModel()

        if(dpsView.addDirectDamage(newDD)){

            ddStore.create(newDD)
        }else{
            logger.info("Failed to add, try again")
        }
    }

    fun listDOT(){
        dpsView.listAllDamageOverTime(dotStore)
    }

    fun listDD(){
        dpsView.listAllDirectDamage(ddStore)
    }

    fun updateDirectDamage(){
        dpsView.listAllDirectDamage(ddStore)
        var searchId = dpsView.getId()
        val aDD = searchDD(searchId)

        if (aDD != null){
            if (dpsView.updateDirectDamage(aDD)){
                ddStore.update(aDD)
                dpsView.showDirectDamage(aDD)

                logger.info(" Direct Damaged Updated : [ $aDD ]")
            }else
                logger.info("Failed to update, please try again")
        }else
            println("Damage not found")

    }

    fun searchDD() {
        val aDD = searchDD(dpsView.getId())!!
        dpsView.showDirectDamage(aDD)
    }

    fun searchDD(id: Long) : DirectDamageModel? {
        var foundDD = ddStore.findOne(id)
        return foundDD
    }

    fun updateDamageOverTime(){
        dpsView.listAllDamageOverTime(dotStore)
        var searchId = dpsView.getId()
        val aDOT = searchDOT(searchId)

        if (aDOT != null){
            if (dpsView.updateDamageOverTime(aDOT)){
                dotStore.update(aDOT)
                dpsView.showDamageOverTime(aDOT)
                logger.info(" Damage Over Time Updated : [ $aDOT ]")
            }else
                logger.info("Failed to update, please try again")
        }else
            println("Damage not found")

    }

    fun searchDOT() {
        val aDOT = searchDOT(dpsView.getId())!!
        dpsView.showDamageOverTime(aDOT)
    }

    fun searchDOT(id: Long) : DamageOverTimeModel? {

        var foundDot = dotStore.findOne(id)
        return foundDot
    }

    fun deleteDot() : Boolean {
        dpsView.listAllDamageOverTime(dotStore)
        var searchId = dpsView.getId()
        val aDOT = searchDOT(searchId)

        if(aDOT != null){
            dotStore.delete(aDOT)
            println("Successfully deleted")
            return true
        }else {
            println("failed to delete please try again")
            return false
        }
    }

    fun deleteDD() : Boolean {
        dpsView.listAllDirectDamage(ddStore)
        var searchId = dpsView.getId()
        val aDD = searchDD(searchId)

        if(aDD != null){
            ddStore.delete(aDD)
            println("Successfully deleted")
            return true
        }else {
            println("failed to delete please try again")
            return false
        }
    }

    fun searchDotByName(){
        var nameToSearch = dpsView.getStringInput()
        var foundDot = dotStore.searchDOTByName(nameToSearch.lowercase())
        if(!foundDot!!.isEmpty()){
            foundDot.forEach { println(it) }
        }else{

            println("No Match")
        }
    }

    fun searchDDByName(){
        var nameToSearch = dpsView.getStringInput()
        var foundDot = ddStore.searchDDByName(nameToSearch.lowercase())
        if(!foundDot!!.isEmpty()){
            foundDot.forEach { println(it) }
        }else{

            println("No Match")
        }
    }

    fun searchDOTForDPSVal(){
        var valueToSearch = dpsView.getFloatInput()
        var foundVals = dotStore.searchDOTForHighDPS(valueToSearch)
        if(!foundVals.isEmpty()){
            foundVals.forEach { println(it) }
        }else
            println("No Match")
    }

    fun searchDDForDPSVal(){
        var valueToSearch = dpsView.getFloatInput()
        var foundVals = ddStore.searchDDForHighDPS(valueToSearch)
        if(!foundVals.isEmpty()){
            foundVals.forEach { println(it) }
        }else
            println("No Match")
    }



    /**
    println(" 2. Update a Damage Source")
    println(" 3. List All Damage Sources")
    println(" 4. Search For Damage Source")
    **/
}