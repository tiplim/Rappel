package com.example.tachesetha.pages

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tachesetha.components.RappelDialog
import com.example.tachesetha.database.RappelDAO
import com.example.tachesetha.entities.Rappel
import com.example.tachesetha.ui.theme.RedBestial
import com.example.tachesetha.ui.theme.WhiteBestial
import com.example.tachesetha.ui.theme.YellowBestial
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Calendar

@Composable
fun ManageRappel(rappelDao: RappelDAO, context: Context) {

    var rappels by remember { mutableStateOf<List<Rappel>>(emptyList()) }
    var rappelIdToDelete by remember { mutableStateOf<Int?>(null) }
    var selectedDate by remember { mutableStateOf(Calendar.getInstance().time) }
    var description by remember { mutableStateOf(TextFieldValue("")) }
    var showDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        rappelDao.getAllRappels().collect {
            rappels = it
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        rappels.forEach {
            ElevatedCard(elevation = CardDefaults.cardElevation(6.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        val dateSplit = it.date.split(" ")
                        Text(dateSplit[1], style = TextStyle(fontSize = 28.sp))
                        Text(it.description)
                        Text(dateSplit[0], style = TextStyle(fontSize = 10.sp))
                    }
                    ElevatedButton(
                        colors = ButtonDefaults.elevatedButtonColors(
                            containerColor = RedBestial,
                            contentColor = WhiteBestial
                        ),
                        onClick = {
                            rappelIdToDelete = it.id
                        }) {
                        Icon(Icons.Filled.Delete, null)
                    }
                    rappelIdToDelete?.let { rappelId ->
                        LaunchedEffect(rappelId) {
                            withContext(Dispatchers.IO) {
                                val rappelToDelete = rappelDao.getRappelById(rappelId)
                                if (rappelToDelete != null) {
                                    rappelDao.delete(rappelToDelete)
                                }
                            }
                            withContext(Dispatchers.Main) {
                                Toast.makeText(context, "Rappel supprimé ;)", Toast.LENGTH_SHORT)
                                    .show()
                            }

                        }
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp) // optional padding from edges
        ) {
            FloatingActionButton(
                containerColor = YellowBestial,
                onClick = { showDialog = true },
                modifier = Modifier.align(Alignment.BottomEnd)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        }
    }



    if (showDialog) {
        RappelDialog(
            onCancel = { showDialog = false },
            onSave = {
                // Handle Save action here (e.g., add the reminder to the list)
                showDialog = false
            },
            selectedDate = selectedDate,
            onDateSelected = { selectedDate = it },
            description = description,
            onDescriptionChange = { description = it }
        )
    }


}
