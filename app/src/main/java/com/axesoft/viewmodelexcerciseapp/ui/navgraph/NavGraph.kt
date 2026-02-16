package com.axesoft.viewmodelexcerciseapp.ui.navgraph

sealed interface NavGraph {
    val route: String

    data object Home: NavGraph {
        override val route = "/"
    }
    data object NextPage: NavGraph {
        override val route = "/nextPage"
    }
}