package com.siddhesh.pixelpdf.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.siddhesh.pixelpdf.data.FileType
import com.siddhesh.pixelpdf.ui.reader.composables.EpubViewer
import com.siddhesh.pixelpdf.ui.reader.composables.PdfViewer
import com.siddhesh.pixelpdf.ui.reader.composables.ReaderControls
import com.siddhesh.pixelpdf.ui.viewmodels.ReaderViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReaderViewScreen(navController: NavController, viewModel: ReaderViewModel = hiltViewModel()) {
    val book by viewModel.book.collectAsState()
    var controlsVisible by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            AnimatedVisibility(visible = controlsVisible) {
                TopAppBar(
                    title = { Text(text = book?.title ?: "") },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    },
                    actions = {
                        IconButton(onClick = { navController.navigate("settings") }) {
                            Icon(Icons.Default.Settings, contentDescription = "Settings")
                        }
                    }
                )
            }
        },
        bottomBar = {
            AnimatedVisibility(visible = controlsVisible) {
                ReaderControls()
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = { controlsVisible = !controlsVisible }
                    )
                }
        ) {
            Column {
                book?.let {
                    when (FileType.valueOf(it.fileType)) {
                        FileType.PDF -> PdfViewer(book = it)
                        FileType.EPUB -> EpubViewer(book = it)
                    }
                }
            }
        }
    }
}
