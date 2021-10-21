package console.models

data class DamageOverTimeModel(var id: Long = 0,
                               var name: String = "UNKWN",
                               var tickTime: Float = 0f,
                               var damagePerTick: Float = 0f,
                               var initialDamage: Float = 0f,
                               var duration: Float = 0f,
                               var percentIncreasePerTick: Float = 0f,
                               var dps60: Float? = 0f,
                               var dps5: Float? = 0f)  {

}