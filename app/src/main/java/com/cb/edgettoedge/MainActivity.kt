package com.cb.edgettoedge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.navigation.ModalBottomSheetLayout
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.navigation.BottomSheetNavigator
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cb.edgettoedge.ui.theme.JetpackComposeEdgeToEdgeSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeEdgeToEdgeSampleTheme {
                Scaffold(

                    bottomBar = {
                        Row {
                            val items = listOf(
                                "Test1", "Test2"
                            )
                            items.forEach { screen ->
                                NavigationBarItem(
                                    icon = {
                                        Icon(
                                            Icons.Filled.Favorite,
                                            contentDescription = null
                                        )
                                    },
                                    label = { Text(screen) },
                                    selected = true,
                                    onClick = {

                                    }
                                )
                            }
                        }
                    },
                    content = {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = Color.Green)
                        ) {

                            val bottomSheetNavigator = rememberBottomSheetNavigator(true)
                            ModalBottomSheetLayout(
                                modifier = Modifier.padding(it),
                                bottomSheetNavigator = bottomSheetNavigator,
                                sheetBackgroundColor = Color.Transparent
                            ) {
                                Scaffold(
                                    topBar = {
                                        TopAppBar(title = { Text(text = "title") })
                                    },
                                    content = { padding ->
                                        Box(
                                            modifier = Modifier
                                                .padding(padding)
                                                .fillMaxSize()
                                        ) {
                                            Image(
                                                modifier = Modifier.fillMaxSize(),
                                                painter = painterResource(id = R.drawable.main_bg),
                                                contentDescription = "",
                                                contentScale = ContentScale.Crop
                                            )
                                            Column(
                                                modifier = Modifier
                                                    .background(color = Color.Black)

                                            ) {
                                                LazyColumn(
                                                    modifier = Modifier
                                                        .fillMaxSize()
                                                        .weight(1f)
                                                ) {
                                                    items(100) {
                                                        Text(
                                                            text = "${(it + 1)} . ENABLED EDGE TO EDGE DEMO",
                                                            color = Color.White,
                                                            modifier = Modifier.padding(10.dp)
                                                        )
                                                    }
                                                }
                                            }
                                        }
                                    }
                                )
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun rememberBottomSheetNavigator(skipHalfExpanded: Boolean = false): BottomSheetNavigator {
    val sheetState = rememberModalBottomSheetState(
        ModalBottomSheetValue.Hidden,
        skipHalfExpanded = skipHalfExpanded
    )
    return remember { BottomSheetNavigator(sheetState) }
}