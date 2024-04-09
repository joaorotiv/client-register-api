package com.client.register.services.address

import com.client.register.exceptions.ZipcodeNotFoundException
import com.client.register.mappers.dummyAddressEntity
import com.client.register.mappers.dummyClientEntity
import com.client.register.mappers.toDTO
import com.client.register.repositories.AddressRepository
import com.client.register.repositories.ClientRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import java.util.*

class AddressGetterServiceTest {

    @Mock
    lateinit var repository: AddressRepository

    @Mock
    lateinit var clientRepository: ClientRepository

    @InjectMocks
    lateinit var service: AddressGetterService

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `should successfully get address by id`() {
        val addressId = 1
        val address = dummyAddressEntity()

        `when`(repository.findById(addressId)).thenReturn(Optional.of(address))
        val result = service.findAddressById(addressId)

        verify(repository, times(1)).findById(addressId)
        assertEquals("14927-51", result.zipcode)
    }

    @Test
    fun `should successfully get address by zipcode`() {
        val clientId = 1
        val zipcode = "14927-55"
        val address = dummyAddressEntity()

        `when`(repository.findAddressByZipcode(zipcode, clientId)).thenReturn(listOf(address))
        service.findAddressByZipcode(zipcode, clientId)

        verify(repository, times(1)).findAddressByZipcode(zipcode, clientId)

    }

    @Test
    fun `should throw ZipcodeNotFoundException while trying to get non-existent address by zipcode`() {
        val zipcode = ""
        val clientId = 1

        `when`(repository.findAddressByZipcode(zipcode, clientId)).thenReturn(emptyList())

        assertThrows(ZipcodeNotFoundException::class.java) {
            service.findAddressByZipcode(zipcode, clientId)
        }

        verify(repository, times(1)).findAddressByZipcode(zipcode, clientId)
    }

    @Test
    fun `should throw NotFoundException while trying to get non-existent address by Id`() {
        val addressId = 999

        `when`(repository.findById(addressId)).thenReturn(Optional.empty())

        assertThrows(NotFoundException::class.java) {
            service.findAddressById(addressId)
        }

        verify(repository, times(1)).findById(addressId)
    }

    @Test
    fun `should successfully get a list of all addresses from client`() {
        val clientId = 1
        val client = dummyClientEntity()

        `when`(clientRepository.findById(clientId)).thenReturn(Optional.of(client))

        val result = client.addresses!!.map { it.toDTO() }

        assertEquals(2, result.size)
        assertEquals("11111-11", result[0].zipcode)
        assertEquals("22222-22", result[1].zipcode)
    }
}