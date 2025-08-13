package com.github.financetrackerpro.ui.screens


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.financetrackerpro.model.Category
import com.github.financetrackerpro.model.CategoryType
import com.github.financetrackerpro.model.categories
import com.github.financetracker.ui.components.CategoryIcon
import com.github.financetrackerpro.ui.theme.Blue
import com.github.financetrackerpro.ui.theme.DarkGray
import com.github.financetrackerpro.ui.theme.LightGray
import com.github.financetrackerpro.ui.theme.White

@Composable
fun ReportScreen() {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("支出分析", "收入分析", "趋势图")

    Column(modifier = Modifier.fillMaxSize()) {
        // 选项卡
        TabRow(
            selectedTabIndex = selectedTab,
            containerColor = White,
            contentColor = Blue
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = { Text(title) }
                )
            }
        }

        // 内容区
        when (selectedTab) {
            0 -> ExpenseAnalysisContent()
            1 -> IncomeAnalysisContent()
            2 -> TrendChartContent()
        }
    }
}

@Composable
fun ExpenseAnalysisContent() {
    val expenseData = listOf(
        Pair(categories.first { it.type == CategoryType.FOOD }, 35f),
        Pair(categories.first { it.type == CategoryType.TRANSPORT }, 25f),
        Pair(categories.first { it.type == CategoryType.SHOPPING }, 20f),
        Pair(categories.first { it.type == CategoryType.ENTERTAINMENT }, 10f),
        Pair(categories.first { it.type == CategoryType.OTHER }, 10f)
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                text = "本月支出分类占比",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }

        item {
            PieChart(
                data = expenseData,
                "总支出",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(16.dp)
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = White)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "支出分析",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Medium
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "本月餐饮支出最高，占总支出35%",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Text(
                        text = "相比上月，交通支出增长了15%",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Text(
                        text = "娱乐支出降低了5%，表现良好",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))

            ExpenseCategoryList(expenseData)
        }
    }
}

@Composable
fun IncomeAnalysisContent() {
    val incomeData = listOf(
        Pair(categories.first { it.type == CategoryType.SALARY }, 70f),
        Pair(categories.first { it.type == CategoryType.INVESTMENT }, 20f),
        Pair(categories.first { it.type == CategoryType.OTHER_INCOME }, 10f)
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                text = "本月收入分类占比",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }

        item {
            PieChart(
                data = incomeData,
                "总收入",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(16.dp)
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = White)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "收入分析",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Medium
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "工资收入是主要来源，占总收入70%",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Text(
                        text = "投资收入相比上月增长了5%",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))

            IncomeCategoryList(incomeData)
        }
    }
}

@Composable
fun TrendChartContent() {
    val monthlyData = listOf(
        Pair("1月", 3200f),
        Pair("2月", 3500f),
        Pair("3月", 2800f),
        Pair("4月", 3200f),
        Pair("5月", 3800f),
        Pair("6月", 3500f)
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Text(
                text = "支出趋势 (最近6个月)",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }

        item {
            LineChart(
                data = monthlyData,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(16.dp)
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = White)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "趋势分析",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Medium
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "最近6个月平均支出：¥3333",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Text(
                        text = "支出最高的月份：5月 (¥3800)",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Text(
                        text = "支出最低的月份：3月 (¥2800)",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Text(
                        text = "整体趋势：支出逐月增加，建议控制开支",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Composable
fun ExpenseCategoryList(data: List<Pair<Category, Float>>) {
    Column {
        Text(
            text = "支出分类明细",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(8.dp))

        data.forEach { (category, percentage) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CategoryIcon(
                    category = category,
                    modifier = Modifier.size(32.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = category.name,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    LinearProgressIndicator(
                        progress = percentage / 100f,
                        modifier = Modifier.fillMaxWidth(),
                        color = category.color,
                        trackColor = LightGray
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "${percentage.toInt()}%",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun IncomeCategoryList(data: List<Pair<Category, Float>>) {
    Column {
        Text(
            text = "收入分类明细",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(8.dp))

        data.forEach { (category, percentage) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CategoryIcon(
                    category = category,
                    modifier = Modifier.size(32.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = category.name,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    LinearProgressIndicator(
                        progress = percentage / 100f,
                        modifier = Modifier.fillMaxWidth(),
                        color = category.color,
                        trackColor = LightGray
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "${percentage.toInt()}%",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun PieChart(
    data: List<Pair<Category, Float>>,
    text:String,
    modifier: Modifier = Modifier
) {
    val total = data.sumOf { it.second.toDouble() }
    val colors = data.map { it.first.color }

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val radius = size.minDimension / 2.5f
            val center = Offset(size.width / 2, size.height / 2)

            var startAngle = 0f

            data.forEachIndexed { index, (_, value) ->
                val sweepAngle = 360f * (value / total.toFloat())

                drawArc(
                    color = colors[index],
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                    useCenter = true,
                    topLeft = Offset(center.x - radius, center.y - radius),
                    size = Size(radius * 2, radius * 2)
                )

                // 画边框
                drawArc(
                    color = Color.White,
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                    useCenter = true,
                    topLeft = Offset(center.x - radius, center.y - radius),
                    size = Size(radius * 2, radius * 2),
                    style = Stroke(width = 2f)
                )

                startAngle += sweepAngle
            }

            // 内圆
            drawCircle(
                color = White,
                radius = radius * 0.6f,
                center = center
            )
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                color = DarkGray
            )
            Text(
                text = "¥3,241.35",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun LineChart(
    data: List<Pair<String, Float>>,
    modifier: Modifier = Modifier
) {
    val maxValue = data.maxOf { it.second }

    Box(modifier = modifier) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height
            val padding = 50f

            val chartWidth = width - padding * 2
            val chartHeight = height - padding * 2

            // 画X轴
            drawLine(
                color = Color.Gray,
                start = Offset(padding, height - padding),
                end = Offset(width - padding, height - padding),
                strokeWidth = 2f
            )

            // 画Y轴
            drawLine(
                color = Color.Gray,
                start = Offset(padding, height - padding),
                end = Offset(padding, padding),
                strokeWidth = 2f
            )

            // 画数据点和连线
            val pointDistance = chartWidth / (data.size - 1)
            val points = data.mapIndexed { index, (_, value) ->
                val x = padding + index * pointDistance
                val y = height - padding - (value / maxValue) * chartHeight
                Offset(x, y)
            }

            // 画折线
            for (i in 0 until points.size - 1) {
                drawLine(
                    color = Blue,
                    start = points[i],
                    end = points[i + 1],
                    strokeWidth = 3f
                )
            }

            // 画数据点
            points.forEach { point ->
                drawCircle(
                    color = Blue,
                    radius = 6f,
                    center = point
                )

                drawCircle(
                    color = White,
                    radius = 3f,
                    center = point
                )
            }

            // 画X轴标签
            data.forEachIndexed { index, (label, _) ->
                val x = padding + index * pointDistance
                drawContext.canvas.nativeCanvas.drawText(
                    label,
                    x,
                    height - padding + 30,
                    android.graphics.Paint().apply {
                        textSize = 12.sp.toPx()
                        color = android.graphics.Color.GRAY
                        textAlign = android.graphics.Paint.Align.CENTER
                    }
                )
            }
        }
    }
}
