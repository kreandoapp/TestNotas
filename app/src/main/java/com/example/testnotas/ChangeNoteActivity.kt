package com.example.testnotas

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.testnotas.databinding.ActivityChangeNoteBinding
import java.util.UUID

class ChangeNoteActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_NOTE_ID = "com.devskiller.intent.note_id"

        fun newIntent(context: Context, uuid: UUID?): Intent {
            return Intent(context, ChangeNoteActivity::class.java).apply {
                putExtra(EXTRA_NOTE_ID, uuid)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewBinding = ActivityChangeNoteBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val noteId = intent?.extras?.getSerializable(EXTRA_NOTE_ID) as? UUID

        if (noteId != null) {
            val existingNote = NoteLab.getNote(noteId)
            if (existingNote != null) {
                viewBinding.etTitle.setText(existingNote.title)
                viewBinding.etDescription.setText(existingNote.description)
                viewBinding.bSave.text = "Edit" // Cambiar el texto del botón para indicar edición
            }
        }

        viewBinding.bSave.setOnClickListener {
            val title = viewBinding.etTitle.text.toString()
            val description = viewBinding.etDescription.text.toString()

            if(title.isEmpty()){
                viewBinding.etTitle.error = getString(R.string.field_not_be_empty_error)
            }else {
                if (noteId != null) {
                    NoteLab.updateNote(Note(description, noteId, title))
                } else {
                    if (title.isBlank()) {
                        viewBinding.etTitle.error = getString(R.string.field_not_be_empty_error)
                    } else {
                        val newNote = Note(title = title, description = description)
                        NoteLab.addNote(newNote)
                    }
                }
                finish()
            }
        }
    }
}

