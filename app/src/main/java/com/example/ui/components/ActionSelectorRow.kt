package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.CyanAccent
import com.example.ui.theme.EmeraldAccent
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.NavyBorder
import com.example.ui.theme.NavyCard
import com.example.ui.theme.NavyCardElevated
import com.example.ui.theme.PurpleAccent
import com.example.ui.viewmodel.SalesActionTab

@Composable
fun ActionSelectorRow(
    selectedTab: SalesActionTab,
    onTabSelected: (SalesActionTab) -> Unit,
    modifier: Modifier = Modifier
) {
    // 2x2 grid or 4-pill layout for clear finger tapping on retail counter
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ActionTile(
                tab = SalesActionTab.EXPLAIN,
                isSelected = selectedTab == SalesActionTab.EXPLAIN,
                activeColor = CyanAccent,
                onClick = { onTabSelected(SalesActionTab.EXPLAIN) },
                modifier = Modifier.weight(1f)
            )
            ActionTile(
                tab = SalesActionTab.QUESTIONS,
                isSelected = selectedTab == SalesActionTab.QUESTIONS,
                activeColor = EmeraldAccent,
                onClick = { onTabSelected(SalesActionTab.QUESTIONS) },
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ActionTile(
                tab = SalesActionTab.PRICE,
                isSelected = selectedTab == SalesActionTab.PRICE,
                activeColor = GoldPrimary,
                onClick = { onTabSelected(SalesActionTab.PRICE) },
                modifier = Modifier.weight(1f)
            )
            ActionTile(
                tab = SalesActionTab.PRACTICE,
                isSelected = selectedTab == SalesActionTab.PRACTICE,
                activeColor = PurpleAccent,
                onClick = { onTabSelected(SalesActionTab.PRACTICE) },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun ActionTile(
    tab: SalesActionTab,
    isSelected: Boolean,
    activeColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(14.dp),
        color = if (isSelected) activeColor.copy(alpha = 0.22f) else NavyCard,
        border = BorderStroke(
            width = if (isSelected) 2.dp else 1.dp,
            color = if (isSelected) activeColor else NavyBorder
        ),
        modifier = modifier
            .testTag("action_tab_${tab.name.lowercase()}")
            .clip(RoundedCornerShape(14.dp))
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 11.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(text = tab.emoji, fontSize = 20.sp)
            Spacer(modifier = Modifier.padding(horizontal = 4.dp))
            Column {
                Text(
                    text = tab.titleAr,
                    fontSize = 13.sp,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.SemiBold,
                    color = if (isSelected) activeColor else MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = when (tab) {
                        SalesActionTab.EXPLAIN -> "جملة تقديم مقنعة"
                        SalesActionTab.QUESTIONS -> "إجابات حقائق مؤكدة"
                        SalesActionTab.PRICE -> "رد على غلاء السعر"
                        SalesActionTab.PRACTICE -> "تدريب ونطق الكلمات"
                    },
                    fontSize = 10.sp,
                    color = if (isSelected) activeColor.copy(alpha = 0.85f) else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
