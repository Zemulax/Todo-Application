import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.leedsbeckett.todo_application.R
import com.leedsbeckett.todo_application.model.Task

/**
 * Adapter for the recyclerview found in main activity
 * this will facilitate display of task objects
 */
class TaskAdapter(
    private val dataset: List<Task>,
    private val clickListener: OnTaskClickListener
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    // define the interface for handling task click events
    interface OnTaskClickListener {
        fun onItemClick(task: Task)
    }

    /**
     * For each data item, a reference to its views should be provided
     * Since complex data items may require multiple views,
     * a view holder should be used to provide access to all of them
     * each data item is simply a task
     */
    inner class TaskViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById(R.id.task_title)

        fun bind(task: Task) {
            titleTextView.text = task.taskTitle
            view.setOnClickListener { clickListener.onItemClick(task) }
        }
    }

    /**
     * create new views for displaying the tasks
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.task_list, parent, false)
        return TaskViewHolder(adapterLayout)
    }

    /**
     * this method will replace contents of a view if need be
     */
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = dataset[position]
        holder.titleTextView.text = task.taskTitle
        holder.bind(task)
    }

    /**
     * get the size of items to be displayed(dataset)
     */
    override fun getItemCount(): Int {
        return dataset.size
    }


}
