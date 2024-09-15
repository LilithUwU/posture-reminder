package com.example.straightspinereminder

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
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
                .wrapContentSize()
                .padding(cp, cp, cp, cp)
                .graphicsLayer {
                    alpha = 0.5f
                }
        ) {
            Box(
                modifier = Modifier.heightIn(min = 200.dp, max = 400.dp),
                contentAlignment = Alignment.Center
            ) {
                VerticalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(),
                ) { page ->
                    Text(//Title
                        text = stringResource(id = causesItems[page].causeTitle),
                        Modifier.padding(tlp, tlp, tlp, 0.dp),
                        fontFamily = FontFamily.SansSerif,
                        fontStyle = FontStyle.Italic,
                        fontSize = fsTitle.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
//Body
                        text = stringResource(id = causesItems[page].causeDescription),
                        Modifier.padding(bp, 0.dp, bp, 0.dp),
                        fontStyle = FontStyle.Italic,
                        fontSize = fsBody.sp,
                    )
                    ShowImage(id = causesItems[page].causeIcon, imgSize)
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