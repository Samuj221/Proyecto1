package com.samupro.proyecto1.navigation


import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import androidx.navigation.compose.composable
import com.samupro.proyecto1.ui.screens.*


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavGraph(nav: NavHostController) {
    val duration = 250
    AnimatedNavHost(
        navController = nav,
        startDestination = Dest.Home.route,
        enterTransition = { fadeIn(animationSpec = tween(duration)) + slideInHorizontally({ it/6 }, tween(duration)) },
        exitTransition = { fadeOut(animationSpec = tween(duration)) + slideOutHorizontally({ -it/6 }, tween(duration)) },
        popEnterTransition = { fadeIn(tween(duration)) + slideInHorizontally({ -it/6 }, tween(duration)) },
        popExitTransition = { fadeOut(tween(duration)) + slideOutHorizontally({ it/6 }, tween(duration)) }
    ) {
        composable(Dest.Home.route) { HomeScreen(onOpen = { nav.navigate(it) }) }
        composable(Dest.Explore.route) { ExploreScreen(onOpen = { nav.navigate(it) }) }
        composable(Dest.Profile.route) { ProfileScreen(onOpen = { nav.navigate(it) }) }
        composable(Dest.Settings.route) { SettingsScreen(onBack = { nav.popBackStack() }) }
    }
}