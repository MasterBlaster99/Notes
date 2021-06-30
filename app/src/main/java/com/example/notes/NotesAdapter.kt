package com.example.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(val context:Context, val listener:Listener): RecyclerView.Adapter<NotesAdapter.NoteviewHolder>() {
    val allNotes = ArrayList<Note>()
    inner class NoteviewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val textView = itemView.findViewById<TextView>(R.id.text)
        val deleteImage = itemView.findViewById<ImageView>(R.id.deleteImage)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteviewHolder {
       val viewHolder = NoteviewHolder(LayoutInflater.from(context).inflate(R.layout.news_item,parent,false))
        viewHolder.deleteImage.setOnClickListener(View.OnClickListener {
          listener.onItemClicked(allNotes[viewHolder.adapterPosition])
        })
        return  viewHolder
    }

    override fun onBindViewHolder(holder: NoteviewHolder, position: Int) {
        val currentNote = allNotes[position]
        holder.textView.setText(currentNote.text)
    }

    override fun getItemCount(): Int {
       return allNotes.size
    }
  fun update (list: List<Note>){
      allNotes.clear()
      allNotes.addAll(list)
      notifyDataSetChanged()
  }
    interface Listener {
        fun onItemClicked(note:Note)
    }
}