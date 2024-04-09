package com.client.register.repositories

import com.client.register.entities.AddressEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Transactional
@Repository
interface AddressRepository : JpaRepository<AddressEntity, Int>{

    fun save(savedAddress: AddressEntity): AddressEntity

    fun findAllByClientId(clientId: Int): List<AddressEntity>

    @Modifying(clearAutomatically = true)
    @Query("SELECT a from AddressEntity a WHERE a.zipcode like %:zipcode% AND a.client.id = :clientId")
    fun findAddressByZipcode(@Param("zipcode") zipcode: String,
                             @Param("clientId") clientId: Int ): List<AddressEntity>
}