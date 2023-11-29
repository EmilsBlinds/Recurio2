package com.example.recurioapp

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoAdapter = TodoAdapter(mutableListOf())

        val rvHabitItems: RecyclerView = findViewById(R.id.rvHabitItems)
        rvHabitItems.adapter = todoAdapter
        rvHabitItems.layoutManager = LinearLayoutManager(this)

        val btnAddHabit: Button = findViewById(R.id.btnAddHabit)
        val etHabitTitle: EditText = findViewById(R.id.etHabitTitle)
        btnAddHabit.setOnClickListener {
            val habitTitle = etHabitTitle.text.toString()
            if (habitTitle.isNotEmpty()) {
                val todo = Todo(habitTitle)
                todoAdapter.addHabit(todo)
                etHabitTitle.text.clear()
            }
        }

//        val btnNew: Button = findViewById(R.id.btnNew)
//        btnNew.setOnClickListener {
//            showInputDialog()
//        }

        val btnDeleteDoneTools: Button = findViewById(R.id.btnDeleteDoneHabits)
        btnDeleteDoneTools.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }
    }

    private fun showInputDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Enter Text")

        // Set up the input
        val input = EditText(this)
        builder.setView(input)

        // Set up the buttons
        builder.setPositiveButton("OK") { dialog, which ->
            val enteredText = input.text.toString()
            val tvHabitTitle: ViewText = findViewById(R.id.tvHabitTitle)

            // Update the tvHabitTitle with the entered text
            tvHabitTitle.text = enteredText

            dialog.dismiss()
        }

        builder.setNegativeButton("Cancel") { dialog, which ->
            dialog.cancel()
        }

        // Create and show the dialog
        val alertDialog = builder.create()
        alertDialog.show()
    }
}
