package id.flabiraya.mytodolist

import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import java.util.ArrayList

class TodoAdapter(var todoList: List<ToDoItem>? = ArrayList<ToDoItem>()) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    private var onTodoItemClickedListener: OnTodoItemClickedListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val layout = if (itemCount == 0) R.layout.empty_view else R.layout.todo_item_view
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return TodoViewHolder(view, todoList!!)
    }

    override fun getItemCount(): Int {
        return if (todoList!!.isEmpty()) 0 else todoList!!.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.view.setOnClickListener { onTodoItemClickedListener!!.onTodoItemClicked(todoList!!.get(position)) }
        holder.onBindViews(position)
    }

    inner class TodoViewHolder(val view: View, val todoList: List<ToDoItem>) : RecyclerView.ViewHolder(view) {
        fun onBindViews(position: Int) {
            if (itemCount != 0 && !TextUtils.isEmpty(todoList.get(position).itemText)) {
                view.findViewById<TextView>(R.id.title).text = todoList.get(position).itemText
                view.findViewById<TextView>(R.id.first_letter).text = todoList.get(position).itemText!!.first().toUpperCase().toString()
            }
        }
    }

    fun setTodoItemClickedListener(onTodoItemClickedListener: OnTodoItemClickedListener) {
        this.onTodoItemClickedListener = onTodoItemClickedListener
    }

    interface OnTodoItemClickedListener {
        fun onTodoItemClicked(todo: ToDoItem)
    }
}