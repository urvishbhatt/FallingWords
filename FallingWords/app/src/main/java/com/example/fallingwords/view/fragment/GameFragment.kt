package com.example.fallingwords.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.fallingwords.R
import com.example.fallingwords.databinding.FragmentGameBinding
import com.example.fallingwords.viewmodel.GameViewModel

class GameFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding : FragmentGameBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)
        val viewModel : GameViewModel = ViewModelProvider(this)[GameViewModel::class.java]

        return binding.root
    }
}