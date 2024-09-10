package com.example.straightspinereminder

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

class ExercisesTabLayout {
    @Composable
    fun ExercisesTab() {
        val items = listOf(
            //embedded video link //todo load from internet
            "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/sU9NBiH8r0I?si=NhCWVGutqQhHp9mC\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>",
            "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/fDrTbLXHKu8?si=9F_FQ5Yw02UyV16b\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>",
            "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/sU9NBiH8r0I?si=NhCWVGutqQhHp9mC\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>",
            "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/fDrTbLXHKu8?si=9F_FQ5Yw02UyV16b\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>"
        )
        LazyColumn {
            items(items) { item ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(30.dp, 0.dp, 30.dp, 0.dp)
                ) {
                    WebViewCompose(url = item)
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(30.dp, 0.dp, 30.dp, 30.dp)
                ) {
                    var isSelected by remember { mutableStateOf(false) }
                    IconButton(
                        onClick = { isSelected = !isSelected },
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .size(45.dp)
                    ) {
                        Icon(
                            imageVector = if (isSelected) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                            contentDescription = "Favorite",
                            tint = Color.Red,
                            modifier = Modifier.size(45.dp),
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun WebViewCompose(url: String) {
        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        500,
                    )
                    settings.javaScriptEnabled = true
                    // Set WebViewClient to handle URL loading within WebView
                    webViewClient = WebViewClient()
                    // Load data (HTML content) into the WebView
                    loadData(url, "text/html", "utf-8")
                }
            },
        )
    }
}
