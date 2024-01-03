package com.example.straightspinereminder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.straightspinereminder.ui.theme.StraightSpineReminderTheme

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
                causeTitle = "Use of technology",
                causeDescription = "If you text on your phone often, you may develop “text neck” — a condition caused by flexing your neck forward too often. " +
                        " If you sit on the couch watching television for many hours each day, you may develop stooped shoulders and lower back problems. " +
                        " If you often sit in a chair without ergonomic support, your posture may eventually worsen. ",
                causeIcon = R.drawable.posture_computer
            ),
            PagerContentItem(
                causeTitle = "Injury",
                causeDescription = "If you have suffered from an injury, some of the muscles around the injury site may spasm to protect " +
                        "the part of your body that is vulnerable.  This is a normal physiological response designed to keep the injured part of your body stable.  " +
                        "Unfortunately, the muscles that are spasming frequently can eventually weaken, which results in a muscular imbalance that may affect your posture. ",
                causeIcon = R.drawable.posture_injury

            ),
            PagerContentItem(
                causeTitle = "Poor footwear",
                causeDescription = "If you wear shoes that are poorly fitted, your gait may be adversely affected. " +
                        " It may cause you to walk in an unusual way, placing undue strain on your ankles, hips, or knees.  " +
                        "This can eventually lead to poor posture.",
                causeIcon = R.drawable.posture_shoes
            ),

            PagerContentItem(
                causeTitle = "Stress and anxiety",
                causeDescription = "Researchers have discovered that being in a stressed or anxious state can change how a person uses their body.  Stressed and anxious people tend to take shallower breaths and have more contracted muscles.  Unfortunately this can lead to poor posture.",
                causeIcon = R.drawable.posture_stress
            ),

            PagerContentItem(
                causeTitle = "Muscle tension or muscle weakness",
                causeDescription = " If you have certain muscles that are much stronger or much weaker than others, it can encourage poor posture.  For example, if your abdominal muscles are weak from an insufficient amount of exercise, you may come to rely on back muscles for stability, leading to back pain.  The solution to this common problem is a robust exercise regime that works out all of the major muscle groups. ",
                causeIcon = R.drawable.posture_weak_muscle
            )
        )









        setContent {
            StraightSpineReminderTheme {
                // A surface container using the 'background' color from the theme
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
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = tabItems[index].title)
                                if (index == 0) {
                                    VerticalPager(

                                        state = pagerStateCauses,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .fillMaxHeight()
                                    ) { page ->
                                        Text(
                                            text = causesItems[page].causeTitle,
                                            Modifier.padding(40.dp, 100.dp, 40.dp, 20.dp),
                                        )
                                        Text(
                                            text = causesItems[page].causeDescription,
                                            Modifier.padding(50.dp, 20.dp, 50.dp, 50.dp)
                                        )
                                        showImage(id = causesItems[page].causeIcon, 100)
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
    val causeTitle: String,
    val causeDescription: String,
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