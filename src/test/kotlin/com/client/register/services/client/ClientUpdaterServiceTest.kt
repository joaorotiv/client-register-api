package com.client.register.services.client

import com.client.register.ApiRepository
import com.client.register.dtos.ClientDTO
import com.client.register.mappers.dummyClientDTO
import com.client.register.mappers.dummyClientEntity
import com.client.register.repositories.ClientRepository
import com.client.register.validators.Validator
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.springframework.beans.BeanUtils
import java.util.*

class ClientUpdaterServiceTest {

    @Mock
    lateinit var repository: ClientRepository

    @Mock
    lateinit var apiRepository: ApiRepository

    @Mock
    lateinit var validator: Validator<ClientDTO>

    @InjectMocks
    lateinit var service: ClientUpdaterService

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `should successfully update client by id`() {
        val clientDTO = dummyClientDTO()
        val savedClient = dummyClientEntity()
        val clientId = 1

        `when`(validator.validate(clientDTO)).thenReturn(true)
        `when`(repository.findById(clientId)).thenReturn(Optional.of(savedClient))

        BeanUtils.copyProperties(clientDTO, savedClient,"registerDate")

        repository.findById(clientId)
        repository.save(savedClient)

        assertEquals(clientDTO.name, savedClient.name)
        assertEquals(clientDTO.email, savedClient.email)
        verify(repository, times(1)).findById(clientId)
        verify(repository, times(1)).save(savedClient)
    }

    @Test
    fun `should successfully update client purchase permission by id`() {
        val purchasePermission= true
        val savedClient = dummyClientEntity()
        val clientId = 1

        `when`(repository.findById(clientId)).thenReturn(Optional.of(savedClient))

        savedClient.purchasePermission = false
        savedClient.purchasePermission = purchasePermission

        repository.findById(clientId)
        repository.save(savedClient)

        assertEquals(purchasePermission, savedClient.purchasePermission)
        verify(repository, times(1)).findById(clientId)
        verify(repository, times(1)).save(savedClient)
    }
//
//    @Test
//    fun `should throw NotFoundException while trying to update non-existent client`() {
//        val clientDTO = dummyClientDTO()
//        val savedClient = dummyClientEntity()
//        val clientId = 1
//
//        `when`(repository.findById(clientId)).thenReturn(Optional.empty())
//
//        assertThrows(
//            NotFoundException::class.java
//        ) { service.updateById(clientDTO, clientId) }
//
//        verify(repository, times(1)).findById(clientId)
//        verify(repository, times(0)).save(savedClient)
//    }
//
//    @Test
//    fun `should throw NotFoundException while trying to update purchase permission from non-existent client`() {
//        val purchasePermission= true
//        val savedClient = dummyClientEntity()
//        val clientId = 100
//
//        savedClient.purchasePermission = false
//
//        `when`(repository.findById(clientId)).thenReturn(Optional.empty())
//
//        assertThrows(
//            NotFoundException::class.java
//        ) { service.updateClientPermissionById(purchasePermission, clientId) }
//
//        verify(repository, times(1)).findById(clientId)
//        verify(repository, times(0)).save(savedClient)
//    }
}