package com.example.straightspinereminder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.straightspinereminder.ui.theme.StraightSpineReminderTheme

class MainActivity : ComponentActivity() {

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
                title = "Notifications",
                unselectedIcon = Icons.Outlined.EditNotifications,
                selectedIcon = Icons.Filled.EditNotifications
            )
        )
        val causesTabLayout = CausesTabLayout()
        val exercisesTab = ExercisesTabLayout()
//  todo
//   val notificationsTabLayout = notificationsTabLayout()


        setContent {
            StraightSpineReminderTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var selectedTabIndex by remember {
                        mutableIntStateOf(0)
                    }
                    val pagerState = rememberPagerState {
                        tabItems.size
                    }
                    val pagerStateCauses = rememberPagerState {
                        causesTabLayout.causesItems.size
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
                            when (index) {
                                0 -> causesTabLayout.CausesTab(pagerState = pagerStateCauses)
                                1 -> exercisesTab.ExercisesTab()
                                //todo
                                // 2 -> notificationsTab.notificationsTab(pagerStateNotifications)
                            }
                        }
                    }
                }
            }
        }
    }
}


/*@Composable
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
}*/


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



