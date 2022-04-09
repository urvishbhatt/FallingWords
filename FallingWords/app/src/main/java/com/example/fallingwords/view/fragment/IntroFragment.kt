package com.example.fallingwords.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.fallingwords.R
import com.example.fallingwords.databinding.FragmentIntroBinding


class IntroFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentIntroBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_intro,container, false)

        binding.btnNextGamefrag.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_introFragment_to_gameFragment)
        }

        return binding.root
    }
}