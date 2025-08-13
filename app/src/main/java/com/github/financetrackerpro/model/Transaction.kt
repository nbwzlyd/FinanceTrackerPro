package com.github.financetrackerpro.model

import java.time.LocalDateTime
import java.util.UUID

data class Transaction(
    val id: String = UUID.randomUUID().toString(),
    val amount: Double,
    val category: Category,
    val description: String,
    val date: LocalDateTime,
    val isExpense: Boolean
)

// 示例交易数据
val sampleTransactions = listOf(
    Transaction(
        amount = 45.50,
        category = getCategoryByType(CategoryType.FOOD),
        description = "麦当劳",
        date = LocalDateTime.now().minusHours(2),
        isExpense = true
    ),
    Transaction(
        amount = 35.00,
        category = getCategoryByType(CategoryType.TRANSPORT),
        description = "滴滴出行",
        date = LocalDateTime.now().minusDays(1),
        isExpense = true
    ),
    Transaction(
        amount = 150.00,
        category = getCategoryByType(CategoryType.SHOPPING),
        description = "京东商城",
        date = LocalDateTime.now().minusDays(2),
        isExpense = true
    ),
    Transaction(
        amount = 8000.00,
        category = getCategoryByType(CategoryType.INCOME),
        description = "工资收入",
        date = LocalDateTime.now().minusDays(5),
        isExpense = false
    )
)
