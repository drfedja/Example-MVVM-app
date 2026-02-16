package com.axesoft.viewmodelexcerciseapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import com.axesoft.viewmodelexcerciseapp.ui.core.composable.AppWrapper
import com.axesoft.viewmodelexcerciseapp.ui.navgraph.NavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme(
                content = {
                    AppWrapper {
                        NavHost()
                    }
                }
            )
        }
    }
}
