package console.models

interface DOTStore {
    fun findAll(): List<DamageOverTimeModel>
    fun findOne(id: Long): DamageOverTimeModel?
    fun create(damageOverTime: DamageOverTimeModel)
    fun update(damageOverTimeModel: DamageOverTimeModel)
    fun delete(damageOverTimeModel: DamageOverTimeModel)
}