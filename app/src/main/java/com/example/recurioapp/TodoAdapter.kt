package com.example.recurioapp

import android.app.AlertDialog
import android.widget.EditText
import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recurioapp.databinding.ItemTodoBinding

class TodoAdapter(
    private val todos: MutableList<Todo>,
) : RecyclerView.Adapter<TodoAdapter.HabitViewHolder>() {

    class HabitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        return HabitViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    fun addHabit(todo: Todo) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun deleteDoneTodos() {
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvHabitTitle: TextView, isChecked: Boolean) {
        if(isChecked) {
            tvHabitTitle.paintFlags = tvHabitTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvHabitTitle.paintFlags = tvHabitTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        val curTodo = todos[position]
        holder.itemView.apply {
            val tvHabitTitle : TextView = findViewById(R.id.tvHabitTitle)
            val cbDone: CheckBox = this.findViewById(R.id.cbDone)
            tvHabitTitle.text = curTodo.title
            cbDone.isChecked = curTodo.isChecked
            toggleStrikeThrough(tvHabitTitle, curTodo.isChecked)
            cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvHabitTitle, isChecked)
                curTodo.isChecked = !curTodo.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}
