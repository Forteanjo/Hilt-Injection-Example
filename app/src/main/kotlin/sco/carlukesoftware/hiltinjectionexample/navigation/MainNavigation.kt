package sco.carlukesoftware.hiltinjectionexample.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import sco.carlukesoftware.hiltinjectionexample.ui.screens.ButtonsScreen
import sco.carlukesoftware.hiltinjectionexample.ui.screens.LogsScreen
import sco.carlukesoftware.hiltinjectionexample.viewmodels.LoggerViewModel

@Composable
fun MainNavigation(
    loggerViewModel: LoggerViewModel,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = MainRoute.Buttons.name
    ) {
        composable(
            route = MainRoute.Buttons.name
        ) {
            ButtonsScreen(
                navController = navController,
                onAddLogEntry = { logEntry ->
                    loggerViewModel
                        .addLogEntry(logEntry)
                },
                onDeleteAllLogs = {
                    loggerViewModel
                        .deleteAllLogEntries()
                }
            )
        }
        composable(
            route = MainRoute.LogsList.name
        ) {
            LogsScreen(
                logEntriesList = loggerViewModel.logs.invoke().collectAsState(initial = emptyList()).value,
                navController = navController
            )
        }
    }
}