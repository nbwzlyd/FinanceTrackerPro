package com.github.financetrackerpro.model

import androidx.compose.ui.graphics.Color
import com.github.fiancetrackerpro.R
import com.github.financetrackerpro.ui.theme.Green
import com.github.financetrackerpro.ui.theme.Orange
import com.github.financetrackerpro.ui.theme.PurpleShopping
import com.github.financetrackerpro.ui.theme.TransportBlue

enum class CategoryType {
    FOOD, TRANSPORT, SHOPPING, ENTERTAINMENT, UTILITIES, HOUSING, HEALTH, EDUCATION, INCOME, OTHER, INVESTMENT, SALARY, OTHER_INCOME
}

data class Category(
    val type: CategoryType,
    val name: String,
    val iconRes: Int,
    val color: Color
)

val categories = listOf(
    Category(CategoryType.FOOD, "餐饮", R.drawable.ic_launcher_background, Orange),
    Category(CategoryType.TRANSPORT, "交通", R.drawable.ic_launcher_background, TransportBlue),
    Category(CategoryType.SHOPPING, "购物", R.drawable.ic_launcher_background, PurpleShopping),
    Category(CategoryType.ENTERTAINMENT, "娱乐", R.drawable.ic_launcher_background, Color(0xFF1ABC9C)),
    Category(CategoryType.UTILITIES, "水电", R.drawable.ic_launcher_background, Color(0xFFE67E22)),
    Category(CategoryType.HOUSING, "住房", R.drawable.ic_launcher_background, Color(0xFF3498DB)),
    Category(CategoryType.HEALTH, "医疗", R.drawable.ic_launcher_background, Color(0xFFE74C3C)),
    Category(CategoryType.EDUCATION, "教育", R.drawable.ic_launcher_background, Color(0xFF9B59B6)),
    Category(CategoryType.INCOME, "收入", R.drawable.ic_launcher_background, Green),
    Category(CategoryType.SALARY, "工资", R.drawable.ic_launcher_background, Orange),
    Category(CategoryType.INVESTMENT, "收入", R.drawable.ic_launcher_background, Green),
    Category(CategoryType.OTHER_INCOME, "其他收入", R.drawable.ic_launcher_background, Color(0xFF9B59B6)),
    Category(CategoryType.INCOME, "收入", R.drawable.ic_launcher_background, Green),
    Category(CategoryType.OTHER, "其他", R.drawable.ic_launcher_background, Color(0xFF7F8C8D))
)

fun getCategoryByType(type: CategoryType): Category {
    return categories.first { it.type == type }
}
