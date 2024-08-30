package com.cb.edgettoedge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.navigation.BottomSheetNavigator
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.cb.edgettoedge.ui.theme.JetpackComposeEdgeToEdgeSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeEdgeToEdgeSampleTheme {
                MyContent()
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyContent() {
    androidx.compose.material.ModalBottomSheetLayout(
        sheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Expanded),
        sheetContent = {
            Column(
                Modifier
                    .background(Color.Gray)
                    .imePadding()
                    .verticalScroll(rememberScrollState())
            ) {
                var text by remember {
                    mutableStateOf(TextFieldValue(""))
                }
                Text(text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting")

                TextField(text, onValueChange = {
                    text = it
                })
                Spacer(
                    modifier = Modifier.size(16.dp)
                )
                Text(text = "Text under the text field, should be fully visible when textfield is focused, under it there should be no margin")
                Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.systemBars))
            }

        },
        sheetBackgroundColor = Color.Transparent
    ) {
        Scaffold(
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
            bottomBar = {
                Row(
                    Modifier
                        .padding(WindowInsets.systemBars.asPaddingValues())
                        .background(Color.Gray)
                ) {
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
            content = { outerPadding ->
                Scaffold(
                    topBar = {
                        TopAppBar(title = { Text(text = "title") })
                    },
                    content = { padding ->
                        Column(
                            modifier = Modifier
                                .padding(padding)
                                .fillMaxSize()
                                .background(Color.Black)
                        ) {
                            Text(
                                text = "Mylist should be 100 item long",
                                color = Color.White
                            )

                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.Black)

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
                )
            }
        )
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