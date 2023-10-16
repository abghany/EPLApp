package com.gnacoding.eplapp.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gnacoding.eplapp.presentation.ui.theme.EPLAppTheme
import com.gnacoding.eplapp.presentation.ui.theme.Typography

@Composable
fun CardClubInfo(
    title: String,
    info: String,
    modifier: Modifier = Modifier
) {
    OutlinedCard(modifier = modifier) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = Typography.bodyMedium,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Start,
                modifier = Modifier.alpha(0.8f)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = info,
                style = Typography.headlineSmall,
                fontWeight = FontWeight.Black,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardClubInfoPreview() {
    EPLAppTheme {
        CardClubInfo(title = "Matches played", info = "1,199")
    }
}