package console.controllers
import console.models.DamageOverTimeModel
import console.models.DirectDamageModel
import console.views.DPSView
import DamageOverTimeJSONStore
import DirectDamageJSONStore
import mu.KotlinLogging

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

    }

    fun searchDD() {
        val aDD = searchDD(dpsView.getId())!!
        dpsView.showDirectDamage(aDD)
    }

    fun searchDD(id: Long) : DirectDamageModel? {
        var foundDD = ddStore.findOne(id)
        return foundDD
    }

    fun listDamageSource(){

    }

    fun searchDamageSource(){

    }

    fun CalculateDPS (dot: DamageOverTimeModel, time: Float) {


    }

    fun CalculateDPS (dd: DirectDamageModel, time: Float) {


    }

    /**
    println(" 2. Update a Damage Source")
    println(" 3. List All Damage Sources")
    println(" 4. Search For Damage Source")
    **/
}