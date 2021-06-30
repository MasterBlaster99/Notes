package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), NotesAdapter.Listener {
    lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager=LinearLayoutManager(this)
        val adapter = NotesAdapter(this, this)

        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this, Observer {
            adapter.update(it)
        })


        recyclerView.adapter = adapter
    }

    override fun onItemClicked(note: Note) {
        viewModel.deleteNote(note)
    }

    fun submit(view: View) {
        val noteText = findViewById<EditText>(R.id.editText).text.toString()
        if(noteText.isNotEmpty()){
            viewModel.insertNote(Note(noteText))
        }
    }
}