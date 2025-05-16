package com.leedsbeckett.todo_application

import TaskAdapter
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.leedsbeckett.todo_application.data.Datasource
import com.leedsbeckett.todo_application.model.Task
import kotlin.math.log

class MainActivity : AppCompatActivity(), TaskAdapter.OnTaskClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //ask for a list of tasks and store them in dataset variable
        val dataset = Datasource(this).loadTasks()

        //get a reference to the RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        //recyclerview should utilise the adapter class.
        //TaskAdapter object will be stored in recyclerView's adapter property
        recyclerView.adapter = TaskAdapter(dataset, this)

        //layout wont be affected by addition of new tasks
        recyclerView.setHasFixedSize(true)

        //listen for click event
        //launch new activity when clicked
        val addTaskButton: FloatingActionButton = findViewById(R.id.add_button)
        addTaskButton.setOnClickListener {
            val intent = Intent(applicationContext, AddTaskActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onItemClick(task: Task) {
        //create intent to open EditTaskActivity
        val intent = Intent(this, EditTaskActivity::class.java)

        //put task title as an extra to the intent
        intent.putExtra("title", task.taskTitle)
        intent.putExtra("details", task.taskDetails)
        intent.putExtra("index", task.taskId)

        //start the activity with the intent
        startActivity(intent)
    }




}
