package com.client.register.services

import com.client.register.mappers.dummyClientEntity
import com.client.register.repositories.DeleteClientRepository
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import java.util.*

class ClientDeleterServiceTest {
    @Mock
    private lateinit var repository: DeleteClientRepository

    @InjectMocks
    private lateinit var service: ClientDeleterService

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `should successfully delete client by id`() {
        val savedClient = dummyClientEntity()
        val clientId = 1

        `when`(repository.findById(clientId)).thenReturn(Optional.of(savedClient))

        service.delete(clientId)

        verify(repository, times(1)).deleteById(savedClient.clientId!!)
        verify(repository, times(1)).findById(clientId)
    }

    @Test
    fun `should throw NoFoundException while trying to delete non-existent client`() {
        val clientId = 1

        `when`(repository.findById(clientId)).thenReturn(Optional.empty())

        assertThrows(
            NotFoundException::class.java
        ) { service.delete(clientId) }

        verify(repository, times(1)).findById(clientId)
        verify(repository, never()).deleteById(clientId)
    }
}
