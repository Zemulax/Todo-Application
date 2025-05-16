package com.leedsbeckett.todo_application

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.leedsbeckett.todo_application.database.Database

class EditTaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_task)

        val database = Database(this)

        //populate the layout with appropriate task details
        val taskTitle = intent?.extras?.getString("title").toString()
        val taskDetails = intent?.extras?.getString("details").toString()
        val index = intent?.extras?.getInt("index")


        val setTaskTitle: TextView = findViewById(R.id.change_task_title)
        val setTaskDetails: TextView = findViewById(R.id.enter_activity_details)

        setTaskTitle.text = taskTitle
        setTaskDetails.text = taskDetails

        val saveButton: Button = findViewById(R.id.edit_task)
        saveButton.setOnClickListener {
            if (index != null) {
                database.updateTask(
                    index,
                    setTaskTitle.text.toString(),
                    setTaskDetails.text.toString()
                )
                Toast.makeText(this, "TASK UPDATED!", Toast.LENGTH_LONG).show()
                //val intent = Intent(this, MainActivity::class.java)
                //startActivity(intent)
                finish()
            }
        }
        //cancel button to undo changes and return to main activity
        val cancelButton: Button = findViewById(R.id.cancel_changes)
        cancelButton.setOnClickListener {
            //val intent = Intent(this, MainActivity::class.java)
            //startActivity(intent)
            this.finish()
        }

        //delete element at specific index
        val deleteButton: Button = findViewById(R.id.delete_task)
        deleteButton.setOnClickListener {
            if (index != null) {
                database.deleteTask(index)
                Toast.makeText(this, "TASK DELETED!", Toast.LENGTH_LONG).show()
                //val intent = Intent(this, MainActivity::class.java)
                //startActivity(intent)
                finish()
            }
        }

    }
}