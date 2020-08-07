package com.bankAccount

data class Account (
        val id: String? = null,
        val name: String,
        val document: String,
        val balance: Long? = 0
) {}
