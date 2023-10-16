package com.gnacoding.eplapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gnacoding.eplapp.presentation.navigation.Screen
import com.gnacoding.eplapp.presentation.screen.club_detail.ClubDetailScreen
import com.gnacoding.eplapp.presentation.screen.favorite.FavoriteScreen
import com.gnacoding.eplapp.presentation.screen.home.HomeScreen
import com.gnacoding.eplapp.presentation.screen.profile.ProfileScreen
import com.gnacoding.eplapp.presentation.ui.components.BottomBar
import com.gnacoding.eplapp.presentation.ui.theme.EPLAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            EPLAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.ClubDetail.route) {
                BottomBar(navController = navController, currentRoute = currentRoute)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { clubId ->
                        navController.navigate(Screen.ClubDetail.createRoute(clubId))
                    }
                )
            }
            composable(
                route = Screen.ClubDetail.route,
                arguments = listOf(
                    navArgument("clubId") { type = NavType.IntType }
                )
            ) {
                val clubId = it.arguments?.getInt("clubId") ?: 0
                ClubDetailScreen(
                    clubId = clubId,
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
            composable(Screen.Favorite.route) {
                FavoriteScreen(
                    navigateToDetail = { clubId ->
                        navController.navigate(Screen.ClubDetail.createRoute(clubId))
                    }
                )
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
        }
    }
}