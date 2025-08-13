package com.github.financetrackerpro.ui.screens


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.github.financetrackerpro.ui.theme.Blue
import com.github.financetrackerpro.ui.theme.DarkGray
import com.github.financetrackerpro.ui.theme.LightGray

@Composable
fun SettingsScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            SettingsHeader()
        }

        item {
            SettingsGroup(title = "账户设置") {
                SettingsItem(
                    title = "个人信息",
                    onClick = { }
                )

                SettingsItem(
                    title = "支付方式",
                    onClick = { }
                )

                SettingsItem(
                    title = "银行账户",
                    onClick = { }
                )
            }
        }

        item {
            SettingsGroup(title = "应用设置") {
                SettingsItem(
                    title = "语言",
                    value = "简体中文",
                    onClick = { }
                )

                SettingsItem(
                    title = "货币",
                    value = "人民币 (¥)",
                    onClick = { }
                )

                SettingsItem(
                    title = "主题",
                    value = "浅色",
                    onClick = { }
                )

                SettingsToggleItem(
                    title = "指纹登录",
                    isChecked = true,
                    onCheckedChange = { }
                )
            }
        }

        item {
            SettingsGroup(title = "通知") {
                SettingsToggleItem(
                    title = "预算提醒",
                    isChecked = true,
                    onCheckedChange = { }
                )

                SettingsToggleItem(
                    title = "大额消费提醒",
                    isChecked = true,
                    onCheckedChange = { }
                )

                SettingsToggleItem(
                    title = "每周报告",
                    isChecked = false,
                    onCheckedChange = { }
                )
            }
        }

        item {
            SettingsGroup(title = "数据") {
                SettingsItem(
                    title = "导出数据",
                    onClick = { }
                )

                SettingsItem(
                    title = "备份与恢复",
                    onClick = { }
                )

                SettingsItem(
                    title = "清除数据",
                    textColor = Color.Red,
                    onClick = { }
                )
            }
        }

        item {
            SettingsGroup(title = "关于") {
                SettingsItem(
                    title = "版本",
                    value = "1.0.0",
                    onClick = { }
                )

                SettingsItem(
                    title = "隐私政策",
                    onClick = { }
                )

                SettingsItem(
                    title = "用户协议",
                    onClick = { }
                )

                SettingsItem(
                    title = "反馈问题",
                    onClick = { }
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red.copy(alpha = 0.8f)
                )
            ) {
                Text(
                    text = "退出登录",
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun SettingsHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 用户头像
        Surface(
            modifier = Modifier.size(80.dp),
            shape = MaterialTheme.shapes.medium,
            color = Blue
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(
                    text = "ZL",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "派大星",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "派大星@example.com",
            style = MaterialTheme.typography.bodyMedium,
            color = DarkGray
        )
    }
}

@Composable
fun SettingsGroup(
    title: String,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelLarge,
            color = Blue,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            content()
        }
    }
}

@Composable
fun SettingsItem(
    title: String,
    value: String? = null,
    textColor: Color = Color.Unspecified,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            color = textColor,
            modifier = Modifier.weight(1f)
        )

        if (value != null) {
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                color = DarkGray
            )

            Spacer(modifier = Modifier.width(8.dp))
        }

        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = null,
            tint = LightGray
        )
    }

    Divider(
        color = LightGray.copy(alpha = 0.5f),
        thickness = 1.dp,
        modifier = Modifier.padding(start = 16.dp)
    )
}

@Composable
fun SettingsToggleItem(
    title: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )

        Switch(
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Blue,
                checkedTrackColor = Blue.copy(alpha = 0.5f)
            )
        )
    }

    Divider(
        color = LightGray.copy(alpha = 0.5f),
        thickness = 1.dp,
        modifier = Modifier.padding(start = 16.dp)
    )
}
