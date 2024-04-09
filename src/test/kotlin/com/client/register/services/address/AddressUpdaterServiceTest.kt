package com.client.register.services.address

import com.client.register.dtos.AddressDTO
import com.client.register.mappers.dummyAddressDTO
import com.client.register.mappers.dummyAddressEntity
import com.client.register.mappers.dummyClientDTO
import com.client.register.mappers.dummyClientEntity
import com.client.register.repositories.AddressRepository
import com.client.register.validators.Validator
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.springframework.beans.BeanUtils
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import java.util.*

class AddressUpdaterServiceTest {

    @Mock
    lateinit var repository: AddressRepository

    @Mock
    lateinit var validator: Validator<AddressDTO>

    @InjectMocks
    lateinit var service: AddressUpdaterService

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `should successfully update address by id`() {
        val addressDTO = dummyAddressDTO()
        val address = dummyAddressEntity()
        val addressId = 1

        `when`(validator.validate(addressDTO)).thenReturn(true)
        `when`(repository.findById(addressId)).thenReturn(Optional.of(address))
        `when`(repository.save(address)).thenReturn(address)

        BeanUtils.copyProperties(addressDTO, address,"main", "addressType")

        service.updateById(addressDTO, addressId)

        assertEquals(addressDTO.zipcode, address.zipcode)
        assertEquals(addressDTO.complement, address.complement)
        verify(repository, times(1)).findById(addressId)
        verify(repository, times(1)).save(address)
    }

    @Test
    fun `should successfully update favorite address`() {
        val clientId = 1
        val addresses = dummyClientEntity().addresses
        val addressesDTO = dummyClientDTO().addresses

        `when`(repository.findAllByClientId(clientId)).thenReturn(addresses)

        addressesDTO?.get(0)?.main = true
        repository.findAllByClientId(clientId)
        repository.saveAll(addresses!!)

        assertEquals(addressesDTO?.get(0)?.main, addresses[0].main)
        verify(repository, times(1)).findAllByClientId(clientId)
        verify(repository, times(1)).saveAll(addresses)
    }

    @Test
    fun `should throw NotFoundException while trying to update non-existent address`() {
        val addressDTO = dummyAddressDTO()
        val address = dummyAddressEntity()
        val addressId = 999

        `when`(validator.validate(addressDTO)).thenReturn(true)
        `when`(repository.findById(addressId)).thenReturn(Optional.empty())

        assertThrows(
            NotFoundException::class.java
        ) { service.updateById(addressDTO, addressId)}

        verify(repository, times(1)).findById(addressId)
        verify(repository, times(0)).save(address)
    }
}