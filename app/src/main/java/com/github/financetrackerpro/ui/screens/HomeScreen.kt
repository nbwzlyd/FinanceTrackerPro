package com.github.financetrackerpro.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.financetrackerpro.model.sampleTransactions
import com.github.financetracker.ui.components.AIReminderCard
import com.github.financetracker.ui.components.AppCard
import com.github.financetracker.ui.components.TransactionItem
import com.github.financetrackerpro.ui.theme.Blue
import com.github.financetrackerpro.ui.theme.DarkGray
import com.github.financetrackerpro.ui.theme.Green
import com.github.financetrackerpro.ui.theme.LightGray
import com.github.financetrackerpro.ui.theme.Red
import com.github.financetrackerpro.ui.theme.White
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun HomeScreen(
    onAddTransaction: () -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddTransaction,
                containerColor = Blue
            ) {
                Icon(
                    imageVector = androidx.compose.material.icons.Icons.Default.Add,
                    contentDescription = "添加交易",
                    tint = White
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // 余额卡片
            item {
                BalanceCard(
                    balance = 12458.65,
                    income = 8000.00,
                    expense = 3241.35
                )
            }
            
            // AI提醒
            item {
                AIReminderCard(message = "最近有笔餐饮支出占比超了15%，建议控制一下餐饮开支，预计可节约¥800")
            }
            
            // 最近交易标题
            item {
                Text(
                    text = "最近交易",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(16.dp)
                )
            }
            
            // 交易列表
            items(sampleTransactions) { transaction ->
                TransactionItem(
                    merchant = transaction.description,
                    time = formatDateTime(transaction.date),
                    amount = transaction.amount,
                    isExpense = transaction.isExpense,
                    category = transaction.category
                )
                
                Divider(
                    color = LightGray,
                    thickness = 1.dp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }
}

@Composable
fun BalanceCard(
    balance: Double,
    income: Double,
    expense: Double
) {
    AppCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "本月总余额",
                style = MaterialTheme.typography.bodyMedium,
                color = DarkGray
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = "¥${String.format("%.2f", balance)}",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = DarkGray
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "收入",
                        style = MaterialTheme.typography.labelMedium,
                        color = DarkGray
                    )
                    Text(
                        text = "¥${String.format("%.2f", income)}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Green,
                        fontWeight = FontWeight.Medium
                    )
                }
                
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "支出",
                        style = MaterialTheme.typography.labelMedium,
                        color = DarkGray
                    )
                    Text(
                        text = "¥${String.format("%.2f", expense)}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Red,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

// 格式化日期时间
fun formatDateTime(dateTime: LocalDateTime): String {
    val now = LocalDateTime.now()
    
    return when {
        dateTime.toLocalDate() == now.toLocalDate() -> {
            "今天 ${dateTime.format(DateTimeFormatter.ofPattern("HH:mm"))}"
        }
        dateTime.toLocalDate() == now.toLocalDate().minusDays(1) -> {
            "昨天 ${dateTime.format(DateTimeFormatter.ofPattern("HH:mm"))}"
        }
        else -> {
            dateTime.format(DateTimeFormatter.ofPattern("MM-dd HH:mm"))
        }
    }
}
