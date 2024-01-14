package sco.carlukesoftware.hiltinjectionexample.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import sco.carlukesoftware.hiltinjectionexample.domain.entity.LogEntry
import sco.carlukesoftware.hiltinjectionexample.navigation.MainRoute
import sco.carlukesoftware.hiltinjectionexample.ui.theme.HiltInjectionExampleTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ButtonsScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onAddLogEntry: (LogEntry) -> Unit = { },
    onDeleteAllLogs: () -> Unit = { }
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Hilt Injection Example"
                    )
                }
            )
        }
    ) { paddingValues ->
        ConstraintLayout(
            modifier = Modifier
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
        ) {
            // Name our elements and create our refs
            val(greetingMessage, button1, button2, button3, viewLogsBtn, deleteLogsBtn) = createRefs()

            // Set up our guidelines
            val topGuideline = createGuidelineFromTop(64.dp)
            val bottomGuideLine = createGuidelineFromBottom(128.dp)
            val centreGuideline = createGuidelineFromAbsoluteLeft(0.5f)
            val startGuideline = createGuidelineFromStart(16.dp)
            val endGuideLine = createGuidelineFromEnd(16.dp)

            Text(
                text = "Please interact with the buttons, \n we'll track your clicks!",
                modifier = Modifier
                    .constrainAs(greetingMessage) {
                        top.linkTo(topGuideline)
                        start.linkTo(startGuideline)
                        end.linkTo(endGuideLine)
                    },
                fontWeight = FontWeight.Bold,
                softWrap = true,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )

            //createVerticalChain(greetingMessage, button1, button2, button3, viewLogsBtn, deleteLogsBtn, chainStyle = ChainStyle.SpreadInside)

            Button(
                onClick = {
                    onAddLogEntry(
                        LogEntry("Interaction with 'Button 1'")
                    )
                },
                modifier = Modifier
                    .constrainAs(button1) {
                        top.linkTo(
                            anchor = greetingMessage.bottom,
                            margin = 32.dp
                        )
                        start.linkTo(startGuideline)
                        end.linkTo(endGuideLine)
                    }
                    .padding(16.dp)
            ) {
                Text(text = "Button 1")
            }

            Button(
                onClick = {
                    onAddLogEntry(
                        LogEntry("Interaction with 'Button 2'")
                    )
                },
                modifier = Modifier
                    .constrainAs(button2) {
                        top.linkTo(
                            anchor = button1.bottom,
                            margin = 16.dp
                        )
                        start.linkTo(startGuideline)
                        end.linkTo(endGuideLine)
                    }
                    .padding(16.dp)
            ) {
                Text(text = "Button 2")
            }

            Button(
                onClick = {
                    onAddLogEntry(
                        LogEntry("Interaction with 'Button 3'")
                    )
                },
                modifier = Modifier
                    .constrainAs(button3) {
                        top.linkTo(
                            anchor = button2.bottom,
                            margin = 16.dp
                        )
                        start.linkTo(startGuideline)
                        end.linkTo(endGuideLine)
                    }
                    .padding(16.dp)
            ) {
                Text(text = "Button 3")
            }

            Button(
                onClick = {
                    navController.navigate(MainRoute.LogsList.name)
                },
                modifier = Modifier
                    .constrainAs(viewLogsBtn) {
                        top.linkTo(
                            anchor = button3.bottom,
                            margin = 16.dp
                        )
                        start.linkTo(startGuideline)
                        end.linkTo(endGuideLine)
                    }
                    .padding(16.dp)
            ) {
                Text(text = "See all logs")
            }

            Button(
                onClick = { onDeleteAllLogs() },
                modifier = Modifier
                    .constrainAs(deleteLogsBtn) {
                        top.linkTo(
                            anchor = viewLogsBtn.bottom,
                            margin = 16.dp
                        )
                        start.linkTo(startGuideline)
                        end.linkTo(endGuideLine)
                    }
                    .padding(16.dp)
            ) {
                Text(text = "Delete logs")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewButtonsScreen() {
    HiltInjectionExampleTheme {
        ButtonsScreen(
            navController = rememberNavController()
        )
    }
}
