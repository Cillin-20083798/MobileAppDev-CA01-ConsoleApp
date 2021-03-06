package console.models

data class DirectDamageModel(var id: Long = 0,
                             var name: String = "UNKWN",
                             var damagePerHit: Float = 0f,
                             var timeBetweenAttacks: Float = 0f,
                             var numberOfProjectiles: Float = 0f,
                             var reloadSpeed: Float = 0f,
                             var magSize: Int = 0,
                             var dps60: Float? = 0f,
                             var dps5: Float? = 0f){

}