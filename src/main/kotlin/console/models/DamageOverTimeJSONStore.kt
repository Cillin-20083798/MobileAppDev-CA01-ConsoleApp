import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import console.models.DamageOverTimeModel
import mu.KotlinLogging
import console.helpers.*
import console.models.DOTStore
import java.util.*

private val logger = KotlinLogging.logger {}

val JSON_FILE_DOT = "dots.json"
val gsonBuilderDot = GsonBuilder().setPrettyPrinting().create()
val listTypeDot = object : TypeToken<java.util.ArrayList<DamageOverTimeModel>>() {}.type

class DamageOverTimeJSONStore : DOTStore {

    private fun generateRandomId(): Long {
        return Random().nextLong()
    }

    var dots = mutableListOf<DamageOverTimeModel>()

    init {
        if (exists(JSON_FILE_DOT)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<DamageOverTimeModel> {
        return dots
    }

    override fun findOne(id: Long) : DamageOverTimeModel? {
        var foundDot: DamageOverTimeModel? = dots.find { p -> p.id == id }
        return foundDot
    }

    override fun create(damageOverTime: DamageOverTimeModel) {
        damageOverTime.id = generateRandomId()
        dots.add(damageOverTime)
        serialize()
    }

    override fun update(damageOverTime: DamageOverTimeModel) {
        var foundDOT = findOne(damageOverTime.id!!)
        if (foundDOT != null) {
            foundDOT.name = damageOverTime.name
            foundDOT.damagePerTick = damageOverTime.damagePerTick
            foundDOT.initialDamage = damageOverTime.initialDamage
            foundDOT.duration = damageOverTime.duration
            foundDOT.percentIncreasePerTick = damageOverTime.percentIncreasePerTick
        }
        serialize()
    }

    internal fun logAll() {
        dots.forEach { println("${it}") }
    }

    private fun serialize() {
        val jsonString = gsonBuilderDot.toJson(dots, listTypeDot)
        write(JSON_FILE_DOT, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE_DOT)
        dots = Gson().fromJson(jsonString, listTypeDot)
    }

    override fun delete(damageOverTime: DamageOverTimeModel) {
        dots.remove(damageOverTime)
        serialize()
    }
}