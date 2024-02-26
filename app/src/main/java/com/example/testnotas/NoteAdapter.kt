package com.example.testnotas

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testnotas.databinding.ViewNoteListItemBinding

class NoteAdapter(private val mNotes: List<Note>) : RecyclerView.Adapter<NoteAdapter.NoteHolder>() {

    interface OnItemClickListener {
        fun onItemClick(note: Note)
    }
    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    inner class NoteHolder(
        private val mViewBinding: ViewNoteListItemBinding
    ) : RecyclerView.ViewHolder(mViewBinding.root), View.OnClickListener {

        private var mNote: Note? = null

        fun bind(note: Note) {
            mNote = note
            mViewBinding.tvNoteTitle.text = note.title

            mViewBinding.tvNoteTitle.setOnClickListener {
               onClick(it)
            }
        }


        override fun onClick(view: View) {

            mNote?.let {
                onItemClickListener?.onItemClick(it)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NoteHolder = NoteHolder(ViewNoteListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int {
        return mNotes.size
    }

    override fun onBindViewHolder(
        holder: NoteHolder,
        position: Int
    ) {
        val note = mNotes[position]
        holder.bind(note)
    }
}