package com.axesoft.viewmodelexcerciseapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.axesoft.viewmodelexcerciseapp.ui.core.composable.Screen
import com.axesoft.viewmodelexcerciseapp.ui.viewmodels.MainViewModel

@Composable
fun MainScreen(
    onNavigate: ()-> Unit
) {
    Screen(
        viewModel = hiltViewModel<MainViewModel>(),
    ) { viewState ->
        MainScreenContent(
            someData = viewState.goToNextPageText,
            someList = viewState.someList,
            onNavigate = onNavigate
        )
    }
}

@Composable
fun MainScreenContent(
    someData: String,
    someList: List<String>,
    onNavigate: () -> Unit
) {
    Column {
        ElevatedButton(
            modifier = Modifier.padding(10.dp),
            onClick = onNavigate
        ) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = someData
            )
        }
        LazyColumn{
            items(someList) { listItem ->
                ListItem(listItem)
            }
        }
    }
}

@Composable
fun ListItem(listItem: String) {
    Box {
        Text(
            modifier = Modifier.padding(16.dp),
            text = listItem
        )
    }
}