package com.gnacoding.eplapp.presentation.screen.profile

import android.content.Intent
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gnacoding.eplapp.R
import com.gnacoding.eplapp.presentation.ui.theme.EPLAppTheme

@Composable
fun ProfileScreen() {
    val context = LocalContext.current
    val onGitHubClicked = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse("https://www.github.com/abghany")
    }
    val onLinkedInClicked = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse("https://linkedin.com/in/abghany/")
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(R.drawable.profile),
                contentDescription = null,
                modifier = Modifier
                    .size(180.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Abdul Ghany At-Tirmidzi",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "aghany08@gmail.com",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.alpha(0.5f)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    context.startActivity(onGitHubClicked)
                },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.width(210.dp)
            ) {
                Text(text = "Follow on GitHub")
            }
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedButton(
                onClick = {
                    context.startActivity(onLinkedInClicked)
                },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.width(210.dp)
            ) {
                Text(text = "Connect on LinkedIn")
            }
        }
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
fun ProfileScreenLightPreview() {
    EPLAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            ProfileScreen()
        }
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ProfileScreenNightPreview() {
    EPLAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            ProfileScreen()
        }
    }
}