package sco.carlukesoftware.hiltinjectionexample.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import sco.carlukesoftware.hiltinjectionexample.domain.entity.LogEntriesList
import sco.carlukesoftware.hiltinjectionexample.domain.entity.LogEntry
import sco.carlukesoftware.hiltinjectionexample.ui.theme.HiltInjectionExampleTheme
import sco.carlukesoftware.hiltinjectionexample.utils.DateFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogsScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    logEntriesList: LogEntriesList
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Logs List"
                    )
                }
            )
        }
    ) { paddingValues ->
        Row(
            modifier = modifier
                .fillMaxSize()
        ) {
            if (logEntriesList.isEmpty()) {
                Text(
                    text = "No logs recorded"
                )
            } else {
                LazyColumn(
                    modifier = modifier
                        .padding(paddingValues)
                ) {
                    items(logEntriesList) { logEntry ->
                        LogEntryItem(modifier = Modifier, logEntry = logEntry)
                    }
                }
            }
        }
    }
}

@Composable
fun LogEntryItem(
    modifier: Modifier,
    logEntry: LogEntry
) {
    Box(
        modifier = Modifier
            .padding(2.dp)
            .border(
                width = 1.dp,
                color = Color.Gray
            )
    ) {
        Column(
            modifier = modifier
                .then(modifier.fillMaxWidth())
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp),
                text = logEntry.msg,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Left
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp),
                text = DateFormatter.formatDate(logEntry.timestamp),
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Right
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LogEntryItemPreview() {
    HiltInjectionExampleTheme {
        LogEntryItem(
            modifier = Modifier,
            logEntry = LogEntry(
                msg = "Sample Message",
                timestamp = System.currentTimeMillis()
            )
        )
    }

}

@Preview(showBackground = true)
@Composable
fun LogScreenPreview() {
    HiltInjectionExampleTheme {
        LogsScreen(
            navController = rememberNavController(),
            logEntriesList = arrayListOf(
                LogEntry(msg = "Log Entry One"),
                LogEntry(msg = "Log Entry Two"),
                LogEntry(msg = "Log Entry Three"),
                LogEntry(msg = "Log Entry Four"),
                LogEntry(msg = "Log Entry Five"),
                LogEntry(msg = "Log Entry Six"),
                LogEntry(msg = "Log Entry Seven"),
                LogEntry(msg = "Log Entry Eight"),
                LogEntry(msg = "Log Entry Nine"),
                LogEntry(msg = "Log Entry Ten"),
            )
        )
    }
    
}