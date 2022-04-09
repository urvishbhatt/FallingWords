package com.example.fallingwords.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.fallingwords.R
import com.example.fallingwords.databinding.FragmentResultBinding

class ResultFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentResultBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_result, container, false)

        if (arguments?.let { ResultFragmentArgs.fromBundle(it).isWon } == true) {
            isWon(binding)
        } else {
            isLoss(binding)
        }

        return binding.root
    }

    private fun isWon(binding: FragmentResultBinding) {
        binding.resultImg.setImageResource(R.drawable.game_won)
        binding.resultImg.layoutParams.width = binding.root.layoutParams.width
        binding.gameTitle.text = getString(R.string.game_won_h2)
    }

    private fun isLoss(binding: FragmentResultBinding) {
        binding.resultImg.setImageResource(R.drawable.game_loss)
        binding.resultImg.layoutParams.width = binding.root.layoutParams.width
        binding.gameTitle.text = getString(R.string.game_loss_h2)
    }

}