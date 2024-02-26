package com.example.testnotas

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testnotas.databinding.FragmentNoteListBinding
import com.example.testnotas.databinding.FragmentWelcomeBinding


class WelcomeFragment : Fragment() {

    companion object {
        fun newInstance(): WelcomeFragment = WelcomeFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewBinding = FragmentWelcomeBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        viewBinding.btnAddnote.setOnClickListener {
            val intent = Intent(it.context, ChangeNoteActivity::class.java)
            startActivity(intent)
        }

        return viewBinding.root
    }


}