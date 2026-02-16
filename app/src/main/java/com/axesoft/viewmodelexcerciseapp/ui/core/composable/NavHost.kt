package com.axesoft.viewmodelexcerciseapp.ui.core.composable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.axesoft.viewmodelexcerciseapp.ui.navgraph.NavGraph
import com.axesoft.viewmodelexcerciseapp.ui.screens.MainScreen
import com.axesoft.viewmodelexcerciseapp.ui.screens.NextPageScreen

val LocalNavController = staticCompositionLocalOf<NavController> { error("No AppState provided") }

@Composable
fun NavHost() {
    val navController = rememberNavController()

    CompositionLocalProvider(LocalNavController provides navController) {
        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            navController = navController,
            startDestination = NavGraph.Home.route
        ) {
            composable(
                route = NavGraph.Home.route
            ) {
                MainScreen(
                    onNavigate = {
                        navController.navigate(NavGraph.NextPage.route)
                    }
                )
            }

            composable(
                route = NavGraph.NextPage.route
            ) {
                NextPageScreen(onBack = {
                    navController.popBackStack()
                })
            }
        }
    }
}
