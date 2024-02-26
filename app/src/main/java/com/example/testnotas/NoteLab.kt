package com.example.testnotas

import androidx.annotation.VisibleForTesting
import java.util.UUID

object NoteLab {

    private val mNotes = mutableListOf<Note>()

    val notes: List<Note>
        get() = mNotes

    fun addNote(note: Note) {
        mNotes.add(note)
    }

    fun getNote(uuid: UUID): Note? = mNotes.firstOrNull { note ->
        note.id == uuid
    }
    fun updateNote(updatedNote: Note) {
        val index = mNotes.indexOfFirst { it.id == updatedNote.id }
        if (index != -1) {
            mNotes[index] = updatedNote
        }
    }
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    internal fun clear() = mNotes.clear()
}
