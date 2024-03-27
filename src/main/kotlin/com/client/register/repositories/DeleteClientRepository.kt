package com.client.register.repositories

import com.client.register.entities.ClientEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DeleteClientRepository: JpaRepository<ClientEntity, Int>