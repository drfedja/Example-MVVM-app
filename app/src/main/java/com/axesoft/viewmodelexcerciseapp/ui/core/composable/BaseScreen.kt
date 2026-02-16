package com.axesoft.viewmodelexcerciseapp.ui.core.composable

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.axesoft.viewmodelexcerciseapp.ui.core.BaseViewModel
import com.axesoft.viewmodelexcerciseapp.ui.navgraph.LocalNavController
import kotlinx.coroutines.flow.filter

@SuppressLint("RestrictedApi")
@Composable
fun <State : Any> Screen(
    viewModel: BaseViewModel<State>,
    content: @Composable (State) -> Unit
) {
    val viewState by viewModel.state.collectAsState()
    val context = LocalContext.current
    val navController = LocalNavController.current

    LaunchedEffect(context) {
        navController.currentBackStackEntryFlow
            .filter {
                it.maxLifecycle == Lifecycle.State.RESUMED &&
                        isDestinationSame(navController, viewModel)
            }
            .collect { backStack ->
                viewModel.checkForScreenResult(backStack.savedStateHandle)
            }
    }

    content(viewState)
}

private fun <State : Any> isDestinationSame(
    homeNavController: NavController,
    viewModel: BaseViewModel<State>
) = homeNavController.currentBackStackEntry?.destination?.id == viewModel.navGraphDestinationId