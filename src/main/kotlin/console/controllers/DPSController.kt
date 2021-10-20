package console.controllers
import console.models.DamageOverTimeModel
import console.models.DirectDamageModel
import console.views.DPSView

class DPSController {

    val dpsView = DPSView()


    fun menu() :Int { return dpsView.menu() }

    fun addNewDamageSource() {

    }

    fun updateDamageSource(){

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