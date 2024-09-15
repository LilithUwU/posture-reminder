package com.example.straightspinereminder

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.straightspinereminder.utilities.WindowInfo
import com.example.straightspinereminder.utilities.getFontSizeBody
import com.example.straightspinereminder.utilities.getFontSizeTitle
import com.example.straightspinereminder.utilities.rememberWindowInfo
import kotlinx.coroutines.delay


class CausesTabLayout {
    val causesItems = listOf(
        PagerContentItem(
            causeTitle = R.string.causesTab_title1,
            causeDescription = R.string.causesTab_text1,
            causeIcon = R.drawable.posture_computer
        ),
        PagerContentItem(
            causeTitle = R.string.causesTab_title2,
            causeDescription = R.string.causesTab_text2,
            causeIcon = R.drawable.posture_injury
        ),
        PagerContentItem(
            causeTitle = R.string.causesTab_title3,
            causeDescription = R.string.causesTab_text3,
            causeIcon = R.drawable.posture_shoes
        ),
        PagerContentItem(
            causeTitle = R.string.causesTab_title4,
            causeDescription = R.string.causesTab_text4,
            causeIcon = R.drawable.posture_stress
        ),
        PagerContentItem(
            causeTitle = R.string.causesTab_title5,
            causeDescription = R.string.causesTab_text5,
            causeIcon = R.drawable.posture_weak_muscle
        )
    )

    @Composable
    fun CausesTab(pagerState: PagerState) {
        val fsTitle= getFontSizeTitle()
        val fsBody= getFontSizeBody()
        val winInfo= rememberWindowInfo()
        /** card padding size*/
        val cp= when (winInfo.screenWidthInfo) {
            WindowInfo.WindowType.Compact -> 50.dp
            WindowInfo.WindowType.Medium -> 100.dp
            else -> 300.dp
        }
        /**title padding size*/
        val tlp= when (winInfo.screenWidthInfo) {
            WindowInfo.WindowType.Compact -> 20.dp
            WindowInfo.WindowType.Medium -> 40.dp
            else -> 60.dp
        }

        /**body padding size*/
        val bp= when (winInfo.screenWidthInfo) {
            WindowInfo.WindowType.Compact -> 25.dp
            WindowInfo.WindowType.Medium -> 35.dp
            else -> 40.dp
        }

        /**image size*/
        val imgSize= when (winInfo.screenWidthInfo) {
            WindowInfo.WindowType.Compact -> 50
            WindowInfo.WindowType.Medium -> 100
            else -> 150
        }

        Card(
            Modifier
                .fillMaxSize()
                .padding(100.dp, 100.dp, 100.dp, 200.dp)
                .graphicsLayer {
                    alpha = 0.5f
                }
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                VerticalPager(
                    state = pagerStateCauses,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                ) { page ->
                    Text(
                        text = stringResource(id = causesItems[page].causeTitle),
                        Modifier.padding(40.dp, 60.dp, 40.dp, 20.dp),
                        fontFamily = FontFamily.SansSerif,
                        fontStyle = FontStyle.Italic,
                        fontSize = 30.sp,
                    )
                    Text(
                        text = stringResource(id = causesItems[page].causeDescription),
                        Modifier.padding(50.dp, 20.dp, 50.dp, 50.dp),
                        fontStyle = FontStyle.Italic
                    )
                    ShowImage(id = causesItems[page].causeIcon, 100)
                }

            }


        }
//Scroll animation
        LaunchedEffect(key1 = pagerState.settledPage) {
            while (true) {
                delay(5000)
                with(pagerState) {
                    val target =
                        if (currentPage < pageCount - 1) currentPage + 1 else 0
                    animateScrollToPage(
                        page = target,
                        animationSpec = tween(
                            durationMillis = 5000,
                            easing = FastOutSlowInEasing
                        )
                    )
                }
            }
        }
    }

}

@Composable
fun ShowImage(id: Int, size: Int) {
    Image(
        painter = painterResource(id = id),
        contentDescription = "",
        modifier = Modifier.size(size.dp),
        colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.primary)
    )
}