package com.axesoft.viewmodelexcerciseapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.axesoft.viewmodelexcerciseapp.ui.core.composable.Screen
import com.axesoft.viewmodelexcerciseapp.ui.viewmodels.NextPageViewModel

@Composable
fun NextPageScreen(
    viewModel: NextPageViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    Screen(viewModel) { viewState ->
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp)
        ) {
            Text(text = viewState.message)
            Spacer(modifier = Modifier.height(16.dp))
            ElevatedButton(onClick = onBack) {
                Text("Go back")
            }
        }
    }
}
