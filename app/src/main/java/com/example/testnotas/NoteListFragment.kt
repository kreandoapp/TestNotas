package com.example.testnotas

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testnotas.databinding.FragmentNoteListBinding


class NoteListFragment : Fragment() {

    private lateinit var adapter: NoteAdapter

    companion object {
        fun newInstance(): NoteListFragment = NoteListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewBinding = FragmentNoteListBinding.inflate(inflater, container, false)

        viewBinding.root.layoutManager = LinearLayoutManager(activity)

        adapter = NoteAdapter(NoteLab.notes)
        adapter.setOnItemClickListener(object : NoteAdapter.OnItemClickListener {
            override fun onItemClick(note: Note) {

                val intent = ChangeNoteActivity.newIntent(requireContext(), note.id)
                startActivity(intent)
            }
        })

        viewBinding.root.adapter = adapter

        return viewBinding.root
    }

    override fun onResume() {
        super.onResume()

        changeList()
    }

    private fun changeList() {
        // Actualiza la lista de notas y notifica al adaptador
        adapter.notifyDataSetChanged()
    }
}
