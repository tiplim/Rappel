package com.example.tachesetha.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import java.util.Calendar
import java.util.Date

@Composable
fun RappelDialog(
    onCancel: () -> Unit,
    onSave: () -> Unit,
    selectedDate: Date,
    onDateSelected: (Date) -> Unit,
    description: TextFieldValue,
    onDescriptionChange: (TextFieldValue) -> Unit
) {
    Dialog(
        onDismissRequest = { onCancel() },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Add Reminder")

            Spacer(modifier = Modifier.height(8.dp))

            // Date Picker
            Text("Selected Date: ${selectedDate.toString()}")
            Spacer(modifier = Modifier.height(4.dp))
            ElevatedButton(onClick = {
                // This is where you would show a Date Picker dialog
                // For now, simulate a date change using the current date
                onDateSelected(Calendar.getInstance().time)
            }) {
                Text("Select Date")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Description field
            Text("Description")
            TextField(
                value = description,
                onValueChange = { onDescriptionChange(it) },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Enter Description") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Buttons: Cancel and Save
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(onClick = { onCancel() }) {
                    Text("Cancel")
                }
                Spacer(modifier = Modifier.width(8.dp))
                ElevatedButton(onClick = { onSave() }) {
                    Text("Save")
                }
            }
        }
    }
}
