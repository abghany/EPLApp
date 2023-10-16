package com.gnacoding.eplapp.presentation.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.gnacoding.eplapp.R
import com.gnacoding.eplapp.presentation.navigation.NavigationItem
import com.gnacoding.eplapp.presentation.navigation.Screen
import com.gnacoding.eplapp.presentation.ui.theme.EPLAppTheme

@Composable
fun BottomBar(
    navController: NavController,
    currentRoute: String?
) {
    val navigationItems = listOf(
        NavigationItem(
            title = "Home",
            icon = painterResource(id = R.drawable.ic_home),
            screen = Screen.Home,
            contentDescription = "home_page"
        ),
        NavigationItem(
            title = "Favorite",
            icon = painterResource(id = R.drawable.ic_favorite),
            screen = Screen.Favorite,
            contentDescription = "favorite_page"
        ),
        NavigationItem(
            title = "Profile",
            icon = painterResource(id = R.drawable.ic_profile),
            screen = Screen.Profile,
            contentDescription = "about_page"
        ),
    )

    NavigationBar {
        navigationItems.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(painter = item.icon, contentDescription = item.contentDescription)
                },
                label = { Text(text = item.title) },
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
fun BottomBarLightPreview() {
    val navController = rememberNavController()
    EPLAppTheme {
        BottomBar(navController = navController, currentRoute = "Home")
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun BottomBarDarkPreview() {
    val navController = rememberNavController()
    EPLAppTheme {
        BottomBar(navController = navController, currentRoute = "Home")
    }
}