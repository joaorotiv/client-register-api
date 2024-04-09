package com.client.register.services.client

import com.client.register.entities.AddressEntity
import com.client.register.entities.ClientEntity
import com.client.register.enums.AddressType
import com.client.register.enums.ClientDocumentType
import com.client.register.enums.ClientType
import com.client.register.exceptions.EmptyListException
import com.client.register.mappers.dummyClientEntity
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
import org.springframework.data.domain.*
import java.util.*

class ClientGetterServiceTest {

    @Mock
    lateinit var repository: ClientRepository

    @InjectMocks
    lateinit var service: ClientGetterService

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `should successfully get client by id`() {
        val clientId = 1
        val clientEntity = dummyClientEntity()

        `when`(repository.findById(clientId)).thenReturn(Optional.of(clientEntity))
        val result = service.findClientById(clientId)

        verify(repository, times(1)).findById(clientId)
        assertEquals("name", result.name)
    }

    @Test
    fun `should successfully get a list of filtered clients`() {
        val clients = listOf(
            ClientEntity(1, "Jorge Alves", "jorgealves@example.com", ClientType.FISICO, ClientDocumentType.CPF,"123.456.789-01", listOf(
                AddressEntity(
                    addressType = AddressType.RESIDENCIAL,
                    publicArea = "Rua 1-A",
                    residenceNumber = "S/N",
                    complement = "Qd 13, Lt 4",
                    neighborhood = "Bairro das Garças",
                    city = "São Paulo",
                    state = "SP",
                    country = "Brazil",
                    zipcode = "82956-10",
                    pointOfReference = "próximo a um hospital",
                    main = true
                )), true, pendingRegistration = false),
            ClientEntity(2, "Joana Maria", "joanamaria@example.com",  ClientType.FISICO, ClientDocumentType.CPF, "987.654.321-09", listOf(
                AddressEntity(
                    addressType = AddressType.RESIDENCIAL,
                    publicArea = "Rua 1-A",
                    residenceNumber = "S/N",
                    complement = "Qd 13, Lt 4",
                    neighborhood = "Bairro das Garças",
                    city = "São Paulo",
                    state = "SP",
                    country = "Brazil",
                    zipcode = "82956-10",
                    pointOfReference = "próximo a um hospital",
                    main = true
                )), true, pendingRegistration = false)
        )
        val pageable = PageRequest.of(0, 3, Sort.Direction.ASC, "name")
        val page: Page<ClientEntity> = PageImpl(clients)

        `when`(repository.findAll(pageable)).thenReturn(page)

        val result = service.findFilteredClients(pageable.pageNumber ,pageable.pageSize, "name", "ASC")

        assertEquals(2, result.content.size)
        assertEquals("Jorge Alves", result.content[0].name)
        assertEquals("Joana Maria", result.content[1].name)
    }

    @Test
    fun `should successfully get client by name`() {
        val name = "name"
        val clientEntity = dummyClientEntity()

        `when`(repository.findClientByName(name)).thenReturn(listOf(clientEntity))
        service.findClientByName(name)

        verify(repository, times(1)).findClientByName(name)
    }

    @Test
    fun `should throw NotFoundException while trying to get non-existent client by Id`() {
        val clientId = 999

        `when`(repository.findById(clientId)).thenReturn(Optional.empty())

        assertThrows(NotFoundException::class.java) {
            service.findClientById(clientId)
        }

        verify(repository, times(1)).findById(clientId)
    }

    @Test
    fun `should throw EmptyListException while trying to get non-existent client by Name`() {
        val name = ""

        `when`(repository.findClientByName(name)).thenReturn(emptyList())

        assertThrows(EmptyListException::class.java) {
            service.findClientByName(name)
        }

        verify(repository, times(1)).findClientByName(name)
    }
}
