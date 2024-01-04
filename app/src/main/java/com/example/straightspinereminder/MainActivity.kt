package com.example.straightspinereminder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EditNotifications
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material.icons.filled.SportsGymnastics
import androidx.compose.material.icons.outlined.EditNotifications
import androidx.compose.material.icons.outlined.ElectricBolt
import androidx.compose.material.icons.outlined.SportsGymnastics
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.straightspinereminder.ui.theme.StraightSpineReminderTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tabItems = listOf(
            TabItem(
                title = "Causes",
                unselectedIcon = Icons.Outlined.ElectricBolt,
                selectedIcon = Icons.Filled.ElectricBolt
            ),
            TabItem(
                title = "Exercises",
                unselectedIcon = Icons.Outlined.SportsGymnastics,
                selectedIcon = Icons.Filled.SportsGymnastics
            ),
            TabItem(
                title = "Notification",
                unselectedIcon = Icons.Outlined.EditNotifications,
                selectedIcon = Icons.Filled.EditNotifications
            )
        )
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



        setContent {
            StraightSpineReminderTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var selectedTabIndex by remember {
                        mutableIntStateOf(0)
                    }
                    var pagerState = rememberPagerState {
                        tabItems.size
                    }
                    var pagerStateCauses = rememberPagerState {
                        causesItems.size
                    }
                    LaunchedEffect(selectedTabIndex) {
                        pagerState.animateScrollToPage(selectedTabIndex)
                    }
                    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
                        if (!pagerState.isScrollInProgress) {
                            selectedTabIndex = pagerState.currentPage
                        }
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        TabRow(selectedTabIndex = selectedTabIndex) {
                            tabItems.forEachIndexed { index, item ->
                                Tab(
                                    selected = index == selectedTabIndex,
                                    onClick = { selectedTabIndex = index },
                                    text = { Text(text = item.title) },
                                    icon = {
                                        Icon(
                                            imageVector =
                                            if (index == selectedTabIndex) {
                                                item.selectedIcon
                                            } else item.unselectedIcon,
                                            contentDescription = item.title
                                        )
                                    }
                                )
                            }
                        }

                        HorizontalPager(
                            state = pagerState,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                        )
                        { index ->
                            if (index == 0) {
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
//                                        Text(text = tabItems[index].title)
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
                                            showImage(id = causesItems[page].causeIcon, 100)
                                        }

                                    }


                                }


                                LaunchedEffect(key1 = pagerStateCauses.initialPage) {
                                    while (true) {
                                        delay(5000)
                                        with(pagerStateCauses) {
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
                    }
                }

            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

//@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StraightSpineReminderTheme {
        Greeting("Android")
    }
}


data class TabItem(
    val title: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector
)

data class PagerContentItem(
    val causeTitle: Int,
    val causeDescription: Int,
    val causeIcon: Int
)



@Composable
fun showImage(id: Int, size: Int) {
    Image(
        painter = painterResource(id = id),
        contentDescription = "",
        modifier = Modifier.size(size.dp),
        colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.primary)
    )
}