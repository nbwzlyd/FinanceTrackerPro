package com.github.financetrackerpro.model

data class Budget(
    val category: Category,
    val amount: Double,
    val spent: Double
) {
    val remaining: Double
        get() = amount - spent
    
    val progress: Float
        get() = (spent / amount).toFloat().coerceIn(0f, 1f)
        
    val isOverBudget: Boolean
        get() = spent > amount
}

// 示例预算数据
val sampleBudgets = listOf(
    Budget(
        category = getCategoryByType(CategoryType.FOOD),
        amount = 1500.0,
        spent = 1200.0
    ),
    Budget(
        category = getCategoryByType(CategoryType.TRANSPORT),
        amount = 800.0,
        spent = 650.0
    ),
    Budget(
        category = getCategoryByType(CategoryType.SHOPPING),
        amount = 1000.0,
        spent = 1100.0
    ),
    Budget(
        category = getCategoryByType(CategoryType.ENTERTAINMENT),
        amount = 500.0,
        spent = 300.0
    ),
    Budget(
        category = getCategoryByType(CategoryType.UTILITIES),
        amount = 400.0,
        spent = 350.0
    )
)

// 总预算
val totalBudget = Budget(
    category = getCategoryByType(CategoryType.OTHER),
    amount = 8000.0,
    spent = 3241.35
)
