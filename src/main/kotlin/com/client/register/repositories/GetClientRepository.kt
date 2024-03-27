package com.client.register.repositories

import com.client.register.entities.ClientEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
interface GetClientRepository : JpaRepository<ClientEntity, Int> {

    @Modifying(clearAutomatically = true)
    @Query("SELECT c from ClientEntity c WHERE c.name like %?1%")
    fun findClientByName(@Param("name") name: String): List<ClientEntity>
}