package com.client.register.services

import com.client.register.mappers.dummyClientDTO
import com.client.register.mappers.dummyClientEntity
import com.client.register.mappers.toEntity
import com.client.register.repositories.CreateClientRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class ClientCreatorServiceTest {

    @Mock
    lateinit var repository: CreateClientRepository

    @InjectMocks
    lateinit var service: ClientCreatorService

    @Test
    fun `should successfully create client`() {
        val clientDTO = dummyClientDTO()
        val savedEntity = dummyClientEntity()

        `when`(repository.save(clientDTO.toEntity())).thenReturn(savedEntity)
        service.execute(clientDTO)

        verify(repository, times(1)).save(clientDTO.toEntity())
    }
}