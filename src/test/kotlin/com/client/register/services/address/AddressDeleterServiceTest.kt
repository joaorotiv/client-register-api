package com.client.register.services.address

import com.client.register.exceptions.DeleteFavoriteAddressException
import com.client.register.mappers.dummyAddressEntity
import com.client.register.repositories.AddressRepository
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import java.util.*

class AddressDeleterServiceTest {

    @Mock
    private lateinit var repository: AddressRepository

    @InjectMocks
    private lateinit var service: AddressDeleterService

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `should successfully delete address by id`() {
        val address = dummyAddressEntity()
        val addressId = 1

        `when`(repository.findById(addressId)).thenReturn(Optional.of(address))

        address.main = false
        service.deleteAddressById(addressId)

        verify(repository, times(1)).deleteById(addressId)
        verify(repository, times(1)).findById(addressId)
    }

    @Test
    fun `should throw NotFoundException while trying to delete non-existent address`() {
        val addressId = 1

        `when`(repository.findById(addressId)).thenReturn(Optional.empty())

        assertThrows(
            NotFoundException::class.java
        ) { service.deleteAddressById(addressId ) }

        verify(repository, times(1)).findById(addressId)
        verify(repository, never()).deleteById(addressId)
    }

    @Test
    fun `should throw DeleteFavoriteAddressException while trying to delete favorite address`() {
        val addressId = 1
        val address = dummyAddressEntity()

        `when`(repository.findById(addressId)).thenReturn(Optional.of(address))

        assertThrows(
            DeleteFavoriteAddressException::class.java
        ) { service.deleteAddressById(addressId ) }

        verify(repository, times(1)).findById(addressId)
        verify(repository, never()).deleteById(addressId)
    }
}
