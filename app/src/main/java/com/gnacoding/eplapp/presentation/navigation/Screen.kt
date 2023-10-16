package com.gnacoding.eplapp.presentation.navigation

sealed class Screen(val route: String) {
    object Home : Screen(route = "home")
    object Favorite : Screen(route = "favorite")
    object Profile : Screen(route = "profile")
    object ClubDetail : Screen(route = "home/{clubId}") {
        fun createRoute(clubId: Int?) = "home/$clubId"
    }
}