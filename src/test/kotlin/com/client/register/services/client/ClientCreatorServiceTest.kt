package com.client.register.services.client

import com.client.register.dtos.ClientDTO
import com.client.register.mappers.dummyClientDTO
import com.client.register.mappers.dummyClientEntity
import com.client.register.mappers.toEntity
import com.client.register.repositories.ClientRepository
import com.client.register.validators.Validator
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class ClientCreatorServiceTest {

    @Mock
    lateinit var repository: ClientRepository

    @Mock
    lateinit var validator: Validator<ClientDTO>

    @InjectMocks
    lateinit var service: ClientCreatorService

    @Test
    fun `should successfully create client`() {
        val clientDTO = dummyClientDTO()
        val savedEntity = dummyClientEntity()

        `when`(validator.validate(clientDTO)).thenReturn(true)
        `when`(repository.save(clientDTO.toEntity())).thenReturn(savedEntity)
        service.create(clientDTO)

        verify(repository, times(1)).save(clientDTO.toEntity())
    }
}