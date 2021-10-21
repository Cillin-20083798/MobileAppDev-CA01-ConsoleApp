import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import console.models.DirectDamageModel
import mu.KotlinLogging
import console.helpers.*
import console.models.DDStore
import console.models.DamageOverTimeModel
import java.util.*

private val logger = KotlinLogging.logger {}

val JSON_FILE_DD = "dd.json"
val gsonBuilderDD = GsonBuilder().setPrettyPrinting().create()
val listTypeDD = object : TypeToken<java.util.ArrayList<DirectDamageModel>>() {}.type



class DirectDamageJSONStore : DDStore {

    private fun generateRandomId(): Long {
        return Random().nextLong()
    }

    var dds = mutableListOf<DirectDamageModel>()

    init {
        if (exists(JSON_FILE_DD)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<DirectDamageModel> {
        return dds
    }

    override fun findOne(id: Long) : DirectDamageModel? {
        var foundDot: DirectDamageModel? = dds.find { p -> p.id == id }
        return foundDot
    }

    override fun create(directDamage: DirectDamageModel) {
        directDamage.id = generateRandomId()
        dds.add(directDamage)
        serialize()
    }

    override fun update(directDamage: DirectDamageModel) {
        var foundDD = findOne(directDamage.id!!)
        if (foundDD != null) {
            foundDD.name = directDamage.name
            foundDD.damagePerHit = directDamage.damagePerHit
            foundDD.timeBetweenAttacks = directDamage.timeBetweenAttacks
            foundDD.numberOfProjectiles = directDamage.numberOfProjectiles
            foundDD.reloadSpeed = directDamage.reloadSpeed
            foundDD.magSize = directDamage.magSize
        }
        serialize()
    }

    internal fun logAll() {
        dds.forEach {
            print("ID : ")
            print(it.id)
            print("\nName : ")
            print(it.name)
            print(", Damage Per Hit : ")
            print(it.damagePerHit)
            print(", Time Between Attacks : ")
            print(it.timeBetweenAttacks)
            print(", Number Of Projectiles : ")
            print(it.numberOfProjectiles)
            print(", Reload Speed : ")
            print(it.reloadSpeed)
            print("\nMagazine Size : ")
            print(it.magSize)
            print(", DPS 60 seconds : ")
            print(it.dps60)
            print(", DPS 5 seconds : ")
            print(it.dps5)
            println()
        }
    }

    private fun serialize() {
        val jsonString = gsonBuilderDD.toJson(dds, listTypeDD)
        write(JSON_FILE_DD, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE_DD)
        dds = Gson().fromJson(jsonString, listTypeDD)
    }

    override fun delete(directDamage: DirectDamageModel) {
        dds.remove(directDamage)
        serialize()
    }

    fun searchDDByName(name: String) : ArrayList<DirectDamageModel>?{
        var resultArray = arrayListOf<DirectDamageModel>()
        dds.forEach {
            if (it.name.contains(name)) {
                resultArray.add(it)
            }
        }
        return resultArray
    }

    fun searchDDForHighDPS(value : Float) : ArrayList<DirectDamageModel> {
        var highestDPSArray = arrayListOf<DirectDamageModel>()

        dds.forEach {
            if (it.dps60!! >= value) {
                highestDPSArray.add(it)
            }
        }
        return highestDPSArray
    }
}
