package com.siddhesh.pixelpdf.ui.screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ViewModule
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.siddhesh.pixelpdf.db.BookEntity
import com.siddhesh.pixelpdf.ui.composables.BookGridItem
import com.siddhesh.pixelpdf.ui.composables.BookListItem
import com.siddhesh.pixelpdf.ui.viewmodels.LibraryViewModel

enum class ViewMode { GRID, LIST }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryHomeScreen(navController: NavController, viewModel: LibraryViewModel = hiltViewModel()) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Recent", "All Files", "PDFs", "EPUBs")
    val showCurrentlyReading = selectedTabIndex == 0 // Only show on "Recent" tab
    val books by viewModel.books.collectAsState()
    var viewMode by remember { mutableStateOf(ViewMode.GRID) }
    val context = LocalContext.current

    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri ->
        uri?.let { viewModel.importBook(it, context) }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("eBook Reader") },
                navigationIcon = {
                    IconButton(onClick = { /* Handle menu click */ }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { filePickerLauncher.launch(arrayOf("application/pdf", "application/epub+zip")) }) {
                Icon(Icons.Default.Add, contentDescription = "Add Book")
            }
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            TextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Search books...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clip(RoundedCornerShape(50)),
                colors = TextFieldDefaults.colors(
                    disabledTextColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )

            TabRow(selectedTabIndex = selectedTabIndex) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = { Text(text = title) }
                    )
                }
            }

            Row(modifier = Modifier.align(Alignment.End)) {
                IconToggleButton(checked = viewMode == ViewMode.GRID, onCheckedChange = { viewMode = ViewMode.GRID }) {
                    Icon(Icons.Default.ViewModule, contentDescription = "Grid View")
                }
                IconToggleButton(checked = viewMode == ViewMode.LIST, onCheckedChange = { viewMode = ViewMode.LIST }) {
                    Icon(Icons.Default.List, contentDescription = "List View")
                }
            }

            if (showCurrentlyReading) {
                CurrentlyReadingCard()
            }

            when (viewMode) {
                ViewMode.GRID -> {
                    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                        items(books) { book ->
                            BookGridItemWrapper(book = book) {
                                navController.navigate("reader/${book.id}")
                            }
                        }
                    }
                }
                ViewMode.LIST -> {
                    LazyColumn {
                        items(books) { book ->
                            BookListItemWrapper(book = book) {
                                navController.navigate("reader/${book.id}")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BookGridItemWrapper(book: BookEntity, onClick: () -> Unit) {
    Column(modifier = Modifier.clickable(onClick = onClick)) {
        BookGridItem(book = book)
    }
}

@Composable
fun BookListItemWrapper(book: BookEntity, onClick: () -> Unit) {
    Column(modifier = Modifier.clickable(onClick = onClick)) {
        BookListItem(book = book)
    }
}

@Composable
fun CurrentlyReadingCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Currently Reading", style = MaterialTheme.typography.titleLarge)
            // Add book details here later
            Text(text = "Book title")
        }
    }
}
