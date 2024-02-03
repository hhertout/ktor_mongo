package api.service

interface ServiceInterface<T> {
    fun findAll(): List<T>

    fun findById(id: String): T?
    fun save(entity: T): T
}