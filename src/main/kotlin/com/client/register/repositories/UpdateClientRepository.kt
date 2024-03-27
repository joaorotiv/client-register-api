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
interface UpdateClientRepository: JpaRepository<ClientEntity, Int> {

    fun save (savedClient : ClientEntity): ClientEntity

    @Modifying(clearAutomatically = true)
    @Query("UPDATE ClientEntity m SET m.purchasePermission = :purchasePermission WHERE m.clientId = :clientId")
    fun updateClientPermissionById(@Param("clientId") clientId: Int,
                                    @Param("purchasePermission") purchasePermission: Boolean)
}