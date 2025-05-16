package com.leedsbeckett.todo_application.database

import TaskAdapter
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.leedsbeckett.todo_application.model.Task

/**
 * Database class
 * to handle creation of a database
 */
class Database(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    //companion object to store constants
    companion object {
        private const val DATABASE_NAME = "TodoAppDatabase.db"
        private const val DATABASE_VERSION = 1
    }

    private val database = writableDatabase

    /**
     * this method will check if the database exists
     * and act accordingly
     * it will also make sure a table tasks is present with fields
     */
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE TASKS(_id INTEGER PRIMARY KEY AUTOINCREMENT,Task_Title TEXT, Task_Details TEXT);")

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    /**
     * method for deleting a task from the database
     * takes Int parameter to determine which row to delete
     * close the database after the action id done
     */
    fun deleteTask(taskId: Int) {
        database.delete("TASKS", "_Id = ?", arrayOf(taskId.toString()))
        database.close()
    }

    /**
     * method for updating values of the database
     * takes parameter o taskID to determine which task to update
     * takes string parameter to use for updating
     */
    fun updateTask(taskId: Int, taskTitle: String, taskDetails: String) {
        val taskUpdate = ContentValues().apply {
            put("Task_Details", taskDetails)
            put("Task_Title", taskTitle)
        }
        database.update("TASKS", taskUpdate, "_Id = ?", arrayOf(taskId.toString()))
        database.close()
    }

    /**
     * This method is used to add a task to the database
     * takes parameters of ContentValues from the calling class
     */
    fun addTask(contentValues: ContentValues) {
        database.insert("TASKS", "null", contentValues)
        database.close()
    }
}