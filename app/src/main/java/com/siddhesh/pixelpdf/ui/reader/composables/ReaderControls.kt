package com.siddhesh.pixelpdf.ui.reader.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ReaderControls(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { /* Handle previous chapter */ }) {
            Icon(Icons.Default.SkipPrevious, contentDescription = "Previous Chapter")
        }
        Slider(value = 0.5f, onValueChange = {}, modifier = Modifier.weight(1f))
        IconButton(onClick = { /* Handle next chapter */ }) {
            Icon(Icons.Default.SkipNext, contentDescription = "Next Chapter")
        }
        Switch(checked = false, onCheckedChange = {})
        Text("Scroll")
    }
}
