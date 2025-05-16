package com.leedsbeckett.todo_application.model

/**
 * Task represents single Task object
 */
data class Task(val taskId:Int,val taskTitle:String, var taskDetails: String)