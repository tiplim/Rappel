package com.example.tachesetha

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.example.tachesetha.database.RappelDAO
import com.example.tachesetha.database.RappelDatabase
import com.example.tachesetha.pages.ManageRappel
import com.example.tachesetha.ui.theme.TachesethaTheme
import com.example.tachesetha.ui.theme.YellowBestial

class MainActivity : ComponentActivity() {

    private lateinit var rappelDatabase: RappelDatabase
    private lateinit var rappelDao: RappelDAO

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        rappelDatabase = RappelDatabase.getDatabase(applicationContext)
        rappelDao = rappelDatabase.rappelDao()

        setContent {
            TachesethaTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            modifier = Modifier.shadow(elevation = 8.dp),
                            title = { Text("Alarmes") },
                            colors = TopAppBarDefaults.topAppBarColors(containerColor = YellowBestial)
                        )
                    },
                ) { innerPadding ->
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)) {
                        ManageRappel(rappelDao, applicationContext)
                    }
                }
            }
        }
    }
}
