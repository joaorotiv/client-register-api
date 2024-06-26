package com.client.register.exceptions

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.apache.coyote.BadRequestException
import org.postgresql.util.PSQLException
import org.springframework.http.*
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import org.springframework.web.servlet.resource.NoResourceFoundException
import java.sql.SQLIntegrityConstraintViolationException

@RestControllerAdvice
class ApplicationExceptionHandler : ResponseEntityExceptionHandler(){

    val badRequestMessage = "Falha ao ler a requisição, consulte qual a maneira correta de realizar essa ação."
    val argumentAlreadyExistsMessage =  "CPF/CNPJ ou email já cadastrado em nosso sistema."

    @ExceptionHandler(BadRequestException::class)
    fun handleExceptionBadRequest(
        exception: Exception
    ): ResponseEntity<ApiError> {
        val error = ApiError(exception.message, status = HttpStatus.BAD_REQUEST)
        return ResponseEntity(error, error.status)
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException::class)
    fun handleSQLIntegrityConstraintViolationException(
        exception: SQLIntegrityConstraintViolationException
    ): ResponseEntity<ApiError> {
        val error = ApiError(argumentAlreadyExistsMessage, status = HttpStatus.UNAUTHORIZED)
        return ResponseEntity(error, error.status)
    }

    @ExceptionHandler(PSQLException::class)
    fun handlePSQLException(
        exception: PSQLException,
    ): ResponseEntity<ApiError> {
        val error = ApiError(argumentAlreadyExistsMessage, status = HttpStatus.UNAUTHORIZED)
        return ResponseEntity(error, error.status)
    }

    @ExceptionHandler(EmptyListException::class)
    fun handleEmptyListException(
        exception: EmptyListException,
    ): ResponseEntity<ApiError> {
        val error = ApiError("Não possui clientes cadastrados no sistema.", status = HttpStatus.NOT_FOUND)
        return ResponseEntity(error, error.status)
    }

    @ExceptionHandler(ZipcodeNotFoundException::class)
    fun handleZipcodeNotFoundException(
        exception: ZipcodeNotFoundException,
    ): ResponseEntity<ApiError> {
        val error = ApiError("Não possui endereços cadastrados com este CEP.", status = HttpStatus.NOT_FOUND)
        return ResponseEntity(error, error.status)
    }

    @ExceptionHandler(AddressMustBeCommercialException::class)
    fun handleAddressMustBeCommercialException(
        exception: AddressMustBeCommercialException,
    ): ResponseEntity<ApiError> {
        val error = ApiError("Para clientes jurídicos, é necessário informar um endereço comercial.", status = HttpStatus.UNAUTHORIZED)
        return ResponseEntity(error, error.status)
    }

    @ExceptionHandler(DeleteFavoriteAddressException::class)
    fun handleDeleteFavoriteAddressException(
        exception: DeleteFavoriteAddressException,
    ): ResponseEntity<ApiError> {
        val error = ApiError("Você não pode deletar um endereço principal, por favor selecione outro endereço para ser seu principal.", status = HttpStatus.UNAUTHORIZED)
        return ResponseEntity(error, error.status)
    }

    @ExceptionHandler(ExceedMaxNumberOfAddresses::class)
    fun handleMaxNumberOfAddressesException(
        exception: ExceedMaxNumberOfAddresses,
    ): ResponseEntity<ApiError> {
        val error = ApiError("Você só pode cadastrar 3 endereços. Para adicionar um novo endereço é preciso remover um da sua lista de endereços cadastrados", status = HttpStatus.UNAUTHORIZED)
        return ResponseEntity(error, error.status)
    }

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(
        exception: NotFoundException
    ): ResponseEntity<ApiError> {
        val error = ApiError("Identificador informado não corresponde a nenhum cliente.", status = HttpStatus.NOT_FOUND)
        return ResponseEntity(error, error.status)
    }

     override fun handleNoResourceFoundException(
        ex: NoResourceFoundException, headers: HttpHeaders, status: HttpStatusCode, request: WebRequest
    ): ResponseEntity<Any> {
         val error = ApiError(badRequestMessage, status = HttpStatus.BAD_REQUEST)
         return ResponseEntity(error, error.status)
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleMethodArgumentTypeMismatchException(
        exception: MethodArgumentTypeMismatchException
    ): ResponseEntity<ApiError> {
        val error = ApiError(badRequestMessage, status = HttpStatus.BAD_REQUEST)
        return ResponseEntity(error, error.status)
    }

    override fun handleHttpMessageNotReadable(
        ex: HttpMessageNotReadableException, headers: HttpHeaders, status: HttpStatusCode, request: WebRequest
    ): ResponseEntity<Any> {
        val error = ApiError(badRequestMessage, status = HttpStatus.BAD_REQUEST)
        return ResponseEntity(error, error.status)
    }

    override fun handleHttpRequestMethodNotSupported(
        ex: HttpRequestMethodNotSupportedException,headers: HttpHeaders, status: HttpStatusCode, request: WebRequest
    ): ResponseEntity<Any> {
        val error = ApiError(badRequestMessage, status = HttpStatus.BAD_REQUEST)
        return ResponseEntity(error, error.status)
    }
}