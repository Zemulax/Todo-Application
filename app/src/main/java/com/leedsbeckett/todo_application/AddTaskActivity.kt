package com.leedsbeckett.todo_application

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.leedsbeckett.todo_application.database.Database

class AddTaskActivity() : AppCompatActivity() {

    private lateinit var database: Database
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        //button for adding new task

        database = Database(this)

        //return an sqlitedatabase object to interact with the database
        database.writableDatabase
        val addTaskButton: Button = findViewById(R.id.add_task)
        addTaskButton.setOnClickListener {
            val taskTitle: TextView = findViewById(R.id.enter_activity_title)
            val taskDetails: TextView = findViewById(R.id.enter_activity_details)

            //we will insert these values in the database
            //set multiple object properties in a single block
            val newTask = ContentValues().apply {
                put("Task_Title", taskTitle.text.toString())
                put("Task_Details", taskDetails.text.toString())
            }
            //call addTask method and pass in the contentValues
            val newRecord = database.addTask(newTask)
            //val intent = Intent(this, MainActivity::class.java)
            //startActivity(intent)
            finish()
        }
    }

}