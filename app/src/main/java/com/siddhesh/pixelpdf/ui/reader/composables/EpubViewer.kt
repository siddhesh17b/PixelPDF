package com.siddhesh.pixelpdf.ui.reader.composables

import android.net.Uri
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.siddhesh.pixelpdf.db.BookEntity

@Composable
fun EpubViewer(book: BookEntity) {
    AndroidView(factory = { context ->
        WebView(context).apply {
            webViewClient = WebViewClient()
            loadUrl(book.fileUri)
        }
    })
}
