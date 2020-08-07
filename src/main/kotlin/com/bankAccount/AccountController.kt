package com.bankAccount

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import sun.jvm.hotspot.oops.CellTypeState.value
import java.lang.RuntimeException
import javax.swing.text.AbstractDocument

@RestController
@RequestMapping( value: "accounts" )
class AccountController(val repository: AccountRepository) {

//    CREATE
    @PostMapping
//    RequestBody para fazer o parse do corpo da requisição
    fun create(@RequestBody account: Account): ResponseEntity<Account> {
        val accountSaved = repository.save(account)
        return ResponseEntity.ok(accountSaved)
    }

//    READ
    @GetMapping
    fun read(): ResponseEntity<(Mutable)List<Account>> =  ResponseEntity.ok(repository.findAll())

//    UPDATE
    @PostMapping(value: "{document}")
    fun update(@PathVariable document: String, @RequestBody account: Account): ResponseEntity<Account> {
        val accountDBOptional = repository.findByDocument(document)
//        caso não exista, lançar uma exceção
        val accountDB = accountDBOptional.orElseThrow { RuntimeException("Account Document: $document não encontrado") }
        val saved = repository.save( accountDB.copy(name = account.name, balance = account.balance) )
        return  ResponseEntity.ok(saved)
    }

//    DELETE
    @DeleteMapping(value: "{document}")
    fun delete(@PathVariable document: String) = repository
            .findByDocument(document)
            .ifPresent { repository.delete(it) }
}


