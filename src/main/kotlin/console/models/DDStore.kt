package console.models

interface DDStore {
    fun findAll(): List<DirectDamageModel>
    fun findOne(id: Long): DirectDamageModel?
    fun create(directDamageModel: DirectDamageModel)
    fun update(directDamageModel: DirectDamageModel)
    fun delete(directDamageModel: DirectDamageModel)
}