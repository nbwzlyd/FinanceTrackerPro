package com.github.financetrackerpro.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.github.financetrackerpro.model.Category
import com.github.financetrackerpro.model.sampleBudgets
import com.github.financetrackerpro.model.totalBudget
import com.github.financetracker.ui.components.CategoryIcon
import com.github.financetracker.ui.components.ProgressIndicator
import com.github.financetrackerpro.ui.theme.Blue
import com.github.financetrackerpro.ui.theme.DarkGray
import com.github.financetrackerpro.ui.theme.Green
import com.github.financetrackerpro.ui.theme.Red
import com.github.financetrackerpro.ui.theme.White

@Composable
fun BudgetScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // 总预算展示
        item {
            TotalBudgetCard(
                totalAmount = totalBudget.amount,
                spentAmount = totalBudget.spent,
                remainingAmount = totalBudget.remaining,
                progress = totalBudget.progress
            )
        }
        
        // 分类预算标题
        item {
            Text(
                text = "分类预算",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }
        
        // 分类预算列表
        items(sampleBudgets) { budget ->
            CategoryBudgetItem(
                categoryName = budget.category.name,
                category = budget.category,
                totalAmount = budget.amount,
                spentAmount = budget.spent,
                remainingAmount = budget.remaining,
                progress = budget.progress,
                isOverBudget = budget.isOverBudget
            )
            
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun TotalBudgetCard(
    totalAmount: Double,
    spentAmount: Double,
    remainingAmount: Double,
    progress: Float
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = White)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "本月总预算",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Medium
            )
            
            Spacer(modifier = Modifier.height(4.dp))
            
            Text(
                text = "¥${String.format("%.2f", totalAmount)}",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "已用 ¥${String.format("%.2f", spentAmount)}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = DarkGray
                )
                
                Text(
                    text = "剩余 ¥${String.format("%.2f", remainingAmount)}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = DarkGray
                )
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            ProgressIndicator(
                progress = progress,
            )
        }
    }
}

@Composable
fun CategoryBudgetItem(
    categoryName: String,
    category: Category,
    totalAmount: Double,
    spentAmount: Double,
    remainingAmount: Double,
    progress: Float,
    isOverBudget: Boolean
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 分类图标
            CategoryIcon(
                category = category,
                modifier = Modifier.size(40.dp)
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "$categoryName ¥${String.format("%.0f", totalAmount)}/月",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Medium
                    )
                    
                    Text(
                        text = if (isOverBudget) "超支" else "正常",
                        style = MaterialTheme.typography.labelMedium,
                        color = if (isOverBudget) Red else Green
                    )
                }
                
                Spacer(modifier = Modifier.height(8.dp))
                
                ProgressIndicator(
                    progress = progress,
                    color = if (isOverBudget) Red else Blue,
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "已用¥${String.format("%.0f", spentAmount)}",
                        style = MaterialTheme.typography.labelMedium,
                        color = if (isOverBudget) Red else DarkGray
                    )
                    
                    Text(
                        text = "剩余¥${String.format("%.0f", remainingAmount)}",
                        style = MaterialTheme.typography.labelMedium,
                        color = if (isOverBudget) Red else DarkGray
                    )
                }
            }
        }
    }
}
