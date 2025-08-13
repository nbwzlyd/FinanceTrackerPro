package com.github.financetracker.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.github.fiancetrackerpro.R
import com.github.financetrackerpro.model.Category
import com.github.financetrackerpro.ui.theme.Blue
import com.github.financetrackerpro.ui.theme.DarkGray
import com.github.financetrackerpro.ui.theme.Green
import com.github.financetrackerpro.ui.theme.LightBlue
import com.github.financetrackerpro.ui.theme.LightGray
import com.github.financetrackerpro.ui.theme.Red
import com.github.financetrackerpro.ui.theme.White

@Composable
fun AppCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        content()
    }
}

@Composable
fun ProgressIndicator(
    modifier: Modifier=Modifier,
    progress: Float,
    color: Color = Blue,
    backgroundColor: Color = LightGray,
) {
    // 确保进度值在有效范围内
    val safeProgress = progress.coerceIn(0f, 1f)

    Box(
        modifier = modifier
            .height(6.dp)
            .clip(RoundedCornerShape(3.dp))
            .background(backgroundColor)
    ) {
        if (safeProgress > 0f) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(safeProgress)
                    .clip(RoundedCornerShape(3.dp))
                    .background(color)
            )
        }
    }
}

@Composable
fun CategoryIcon(
    modifier: Modifier = Modifier,
    category: Category,
    isSelected: Boolean = false,
    tint: Color = category.color
) {
    val backgroundColor = if (isSelected) Blue else White
    val iconColor = tint

    Box(
        modifier = modifier
            .size(36.dp)
            .clip(RoundedCornerShape(50))
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = category.iconRes),
            contentDescription = category.name,
            tint = iconColor,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun AmountText(
    amount: Double,
    isExpense: Boolean,
    style: TextStyle = MaterialTheme.typography.bodyLarge,
    fontWeight: FontWeight = FontWeight.SemiBold
) {
    val color = if (isExpense) Red else Green
    val prefix = if (isExpense) "-" else "+"

    Text(
        text = "$prefix¥${String.format("%.2f", amount)}",
        style = style,
        fontWeight = fontWeight,
        color = color
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionItem(
    merchant: String,
    time: String,
    amount: Double,
    isExpense: Boolean,
    category: Category,
    onClick: () -> Unit = {}
) {
    Surface(
        onClick = onClick,
        color = White
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 左侧分类图标
            CategoryIcon(
                category = category,
                modifier = Modifier.size(40.dp)
            )

            // 中间区域：商户名称和时间
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 12.dp)
            ) {
                Text(
                    text = merchant,
                    style = MaterialTheme.typography.bodyLarge,
                    color = DarkGray
                )
                Text(
                    text = time,
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.Gray
                )
            }

            // 右侧金额
            AmountText(
                amount = amount,
                isExpense = isExpense,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun AIReminderCard(message: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(LightBlue)
            .border(1.dp, Color.LightGray.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "AI提醒",
                tint = Blue,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                color = DarkGray
            )
        }
    }
}

@Composable
fun BudgetCard(
    title: String,
    totalAmount: Double,
    spentAmount: Double,
    progress: Float,
    isOverBudget: Boolean = false,
    modifier: Modifier = Modifier
) {
    val safeProgress = progress.coerceIn(0f, 1f)

    AppCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "已用 ¥${String.format("%.2f", spentAmount)}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (isOverBudget) Red else DarkGray
                )

                Text(
                    text = "剩余 ¥${String.format("%.2f", totalAmount - spentAmount)}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (isOverBudget) Red else DarkGray
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            ProgressIndicator(
                progress = safeProgress,
                color = if (isOverBudget) Red else Blue,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
