package com.example.doit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.doit.databinding.ActivityUpdateTaskBinding

class UpdateTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateTaskBinding
    private lateinit var db : TasksDatabaseHelper
    private var taskId : Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = TasksDatabaseHelper(this)

        taskId = intent.getIntExtra("task_id",-1)
        if(taskId == -1){
            finish()
            return
        }

        val task = db.getTaskById(taskId)
        binding.UpdateTitleEditText.setText(task.title)
        binding.UpdateDescription.setText(task.content)

        binding.UpdateSaveButton.setOnClickListener{
            val newTitle = binding.UpdateTitleEditText.text.toString()
            val newDescription = binding.UpdateDescription.text.toString()
            val updatedTask = Task(taskId , newTitle,newDescription)
            db.updateTask(updatedTask)

            finish()
            Toast.makeText(this,"Updated successfully", Toast.LENGTH_SHORT).show()
        }


    }
}