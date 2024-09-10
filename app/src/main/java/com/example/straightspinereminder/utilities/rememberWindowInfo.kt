package com.example.straightspinereminder.utilities

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
//todo: use dependency injection
@Composable
fun rememberWindowInfo(): WindowInfo {
    val configuration= LocalConfiguration.current
    return WindowInfo(
        screenWidthInfo = when{
            configuration.screenWidthDp<600-> WindowInfo.WindowType.Compact
            configuration.screenWidthDp<840-> WindowInfo.WindowType.Medium
            else-> WindowInfo.WindowType.Expanded
        },
        screenHeightInfo = when{
            configuration.screenHeightDp<480-> WindowInfo.WindowType.Compact
            configuration.screenHeightDp<900-> WindowInfo.WindowType.Medium
            else-> WindowInfo.WindowType.Expanded
        },
        screenWidth = configuration.screenWidthDp.dp,
        screenHeight = configuration.screenHeightDp.dp
    )
}
@Composable
fun getFontSizeBody(): Int{
    val windowInfo= rememberWindowInfo()
    return when(windowInfo.screenWidthInfo){
        WindowInfo.WindowType.Compact ->MaterialTheme.typography.bodySmall.fontSize.value.toInt()
        WindowInfo.WindowType.Medium ->MaterialTheme.typography.bodyMedium.fontSize.value.toInt()
        WindowInfo.WindowType.Expanded ->MaterialTheme.typography.bodyLarge.fontSize.value.toInt()
    }
}
@Composable
fun getFontSizeTitle(): Int{
    val windowInfo= rememberWindowInfo()
    return when(windowInfo.screenWidthInfo){
        WindowInfo.WindowType.Compact ->MaterialTheme.typography.titleSmall.fontSize.value.toInt()
        WindowInfo.WindowType.Medium ->MaterialTheme.typography.titleMedium.fontSize.value.toInt()
        WindowInfo.WindowType.Expanded ->MaterialTheme.typography.titleLarge.fontSize.value.toInt()
    }
}


data class WindowInfo(
    val screenWidthInfo: WindowType,
    val screenHeightInfo: WindowType,
    val screenWidth: Dp,
    val screenHeight: Dp
){
    sealed class WindowType{
        object Compact: WindowType()
        object Medium: WindowType()
        object Expanded: WindowType()
    }
}