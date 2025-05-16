package com.leedsbeckett.todo_application.data

import android.content.Context
import com.leedsbeckett.todo_application.database.Database
import com.leedsbeckett.todo_application.model.Task

/**
 * Datasource object is the source of Task objects
 */
class Datasource(private val context: Context) {

    //this function will return a list of tasks from the database
    fun loadTasks(): List<Task> {
        val tasks = mutableListOf<Task>()
        val cursor = Database(context).readableDatabase.rawQuery("SELECT * FROM TASKS", null)
        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"))
            val taskTitle = cursor.getString(cursor.getColumnIndexOrThrow("Task_Title"))
            val taskDetails = cursor.getString((cursor.getColumnIndexOrThrow("Task_Details")))
            tasks.add(Task(id, taskTitle, taskDetails))
        }
        cursor.close()
        return tasks
    }
}
