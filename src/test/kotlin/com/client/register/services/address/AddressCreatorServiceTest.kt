package com.client.register.services.address

import com.client.register.ApiRepository
import com.client.register.dtos.AddressDTO
import com.client.register.mappers.*
import com.client.register.repositories.AddressRepository
import com.client.register.validators.Validator
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class AddressCreatorServiceTest {

    @Mock
    lateinit var repository: AddressRepository

    @Mock
    lateinit var apiRepository: ApiRepository

    @Mock
    lateinit var validator: Validator<AddressDTO>

    @InjectMocks
    lateinit var service: AddressCreatorService

    @Test
    fun `should successfully create address`() {
        val address = dummyAddressDTO()
        val savedAddress = dummyAddressEntity()
        val clientId = 1
        val client = dummyClientEntity()

        `when`(validator.validate(address)).thenReturn(true)
        `when`(apiRepository.findClientOrThrow(clientId)).thenReturn(client)
        `when`(repository.save(address.toEntity(client))).thenReturn(savedAddress)

        service.execute(address, clientId)

        verify(repository, times(1)).save(address.toEntity(client))
    }
}