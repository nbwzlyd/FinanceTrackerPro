package com.github.financetrackerpro.ui.screens


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.github.financetrackerpro.model.CategoryType
import com.github.financetrackerpro.model.categories
import com.github.financetrackerpro.model.Category
import com.github.financetrackerpro.model.TransactionType
import com.github.financetracker.ui.components.CategoryIcon
import com.github.financetrackerpro.ui.theme.Blue
import com.github.financetrackerpro.ui.theme.Green
import com.github.financetrackerpro.ui.theme.LightGray
import com.github.financetrackerpro.ui.theme.Red
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecordScreen() {
    var transactionType by remember { mutableStateOf(TransactionType.EXPENSE) }
    var amount by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf<Category?>(null) }
    var note by remember { mutableStateOf("") }
    var date by remember { mutableStateOf(Calendar.getInstance()) }
    var paymentMethod by remember { mutableStateOf("现金") }
    var showDatePicker by remember { mutableStateOf(false) }
    var showPaymentMethodDialog by remember { mutableStateOf(false) }

    val dateFormatter = SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault())
    val formattedDate = dateFormatter.format(date.time)

    val filteredCategories = categories.filter {
        when (transactionType) {
            TransactionType.EXPENSE -> it.type != CategoryType.SALARY &&
                    it.type != CategoryType.INVESTMENT &&
                    it.type != CategoryType.OTHER_INCOME
            TransactionType.INCOME -> it.type == CategoryType.SALARY ||
                    it.type == CategoryType.INVESTMENT ||
                    it.type == CategoryType.OTHER_INCOME
        }
    }

    val paymentMethods = listOf("现金", "支付宝", "微信", "银行卡", "信用卡")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // 交易类型选择器
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            TransactionTypeTab(
                type = TransactionType.EXPENSE,
                selected = transactionType == TransactionType.EXPENSE,
                onClick = { transactionType = TransactionType.EXPENSE }
            )

            TransactionTypeTab(
                type = TransactionType.INCOME,
                selected = transactionType == TransactionType.INCOME,
                onClick = { transactionType = TransactionType.INCOME }
            )
        }

        // 金额输入
        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("金额") },
            prefix = { Text("¥") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            singleLine = true,
            trailingIcon = {
                if (amount.isNotEmpty()) {
                    IconButton(onClick = { amount = "" }) {
                        Icon(Icons.Default.Clear, contentDescription = "清除")
                    }
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 分类选择
        Text(
            text = "选择分类",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier.height(200.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(filteredCategories) { category ->
                CategoryItem(
                    category = category,
                    selected = selectedCategory == category,
                    onClick = { selectedCategory = category }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 备注输入
        OutlinedTextField(
            value = note,
            onValueChange = { note = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("备注") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 日期选择
        OutlinedTextField(
            value = formattedDate,
            onValueChange = { },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("日期") },
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { showDatePicker = true }) {
                    Icon(Icons.Default.DateRange, contentDescription = "选择日期")
                }
            }
        )

        if (showDatePicker) {
            DatePickerDialog(
                onDismissRequest = { showDatePicker = false },
                confirmButton = {
                    TextButton(onClick = { showDatePicker = false }) {
                        Text("确定")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDatePicker = false }) {
                        Text("取消")
                    }
                }
            ) {
                DatePicker(
                    state = rememberDatePickerState(
                        initialSelectedDateMillis = date.timeInMillis
                    ),
//                    dateValidator = { timestamp ->
//                        // 只允许选择今天及以前的日期
//                        timestamp <= System.currentTimeMillis()
//                    },
                    title = { Text("选择日期") }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 支付方式选择
        OutlinedTextField(
            value = paymentMethod,
            onValueChange = { },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("支付方式") },
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { showPaymentMethodDialog = true }) {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "选择支付方式")
                }
            }
        )

        if (showPaymentMethodDialog) {
            AlertDialog(
                onDismissRequest = { showPaymentMethodDialog = false },
                title = { Text("选择支付方式") },
                text = {
                    LazyColumn {
                        items(paymentMethods) { method ->
                            Text(
                                text = method,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        paymentMethod = method
                                        showPaymentMethodDialog = false
                                    }
                                    .padding(vertical = 12.dp)
                            )

                            if (method != paymentMethods.last()) {
                                Divider()
                            }
                        }
                    }
                },
                confirmButton = { }
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // 保存按钮
        Button(
            onClick = { /* 保存交易记录 */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Blue
            )
        ) {
            Text("保存")
        }
    }
}

@Composable
fun TransactionTypeTab(
    type: TransactionType,
    selected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = when {
        selected -> when (type) {
            TransactionType.EXPENSE -> Red.copy(alpha = 0.1f)
            TransactionType.INCOME -> Green.copy(alpha = 0.1f)
        }
        else -> Color.Transparent
    }

    val textColor = when {
        selected -> when (type) {
            TransactionType.EXPENSE -> Red
            TransactionType.INCOME -> Green
        }
        else -> Color.Gray
    }

    val borderColor = when {
        selected -> when (type) {
            TransactionType.EXPENSE -> Red
            TransactionType.INCOME -> Green
        }
        else -> Color.Gray.copy(alpha = 0.5f)
    }

    val text = when (type) {
        TransactionType.EXPENSE -> "支出"
        TransactionType.INCOME -> "收入"
    }

    Surface(
        modifier = Modifier
            .height(48.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(8.dp),
        color = backgroundColor,
        border = BorderStroke(1.dp, borderColor)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge,
                color = textColor,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun CategoryItem(
    category: Category,
    selected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(
                    if (selected) category.color.copy(alpha = 0.2f)
                    else LightGray
                )
                .padding(12.dp)
        ) {
            CategoryIcon(
                category = category,
                tint = if (selected) category.color else Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = category.name,
            style = MaterialTheme.typography.bodySmall,
            color = if (selected) category.color else Color.Gray
        )
    }
}
