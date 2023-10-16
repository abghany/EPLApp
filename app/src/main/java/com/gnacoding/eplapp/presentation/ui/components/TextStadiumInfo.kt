package com.gnacoding.eplapp.presentation.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.gnacoding.eplapp.presentation.ui.theme.EPLAppTheme
import com.gnacoding.eplapp.presentation.ui.theme.Typography

@Composable
fun TextStadiumInfo(
    title: String,
    info: String,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.fillMaxWidth()) {
        Text(
            text = title,
            style = Typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = info,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = Typography.bodyMedium,
            modifier = Modifier
                .weight(1f)
                .alpha(0.5f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TextStadiumInfoPreview() {
    EPLAppTheme {
        TextStadiumInfo(title = "Capacity", info = "60,704")
    }
}