package com.siddhesh.pixelpdf.ui.reader.composables

import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.net.Uri
import android.os.ParcelFileDescriptor
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.siddhesh.pixelpdf.db.BookEntity

@Composable
fun PdfViewer(book: BookEntity) {
    val context = LocalContext.current
    val renderer = remember(book.fileUri) {
        val uri = Uri.parse(book.fileUri)
        val input = context.contentResolver.openFileDescriptor(uri, "r")
        input?.let { PdfRenderer(it) }
    }

    if (renderer != null) {
        LazyColumn {
            items(renderer.pageCount) { index ->
                val page = renderer.openPage(index)
                val bitmap = Bitmap.createBitmap(page.width, page.height, Bitmap.Config.ARGB_8888)
                page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
                page.close()
                Image(
                    bitmap = bitmap.asImageBitmap(),
                    contentDescription = "Page ${index + 1}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                )
            }
        }

        DisposableEffect(Unit) {
            onDispose {
                renderer.close()
            }
        }
    }
}
