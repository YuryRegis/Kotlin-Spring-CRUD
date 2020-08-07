package com.bankAccount

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AccountRepository: MongoRepository<Account, String> {
    run findByDocument(document: String) : Optional<Account>
}