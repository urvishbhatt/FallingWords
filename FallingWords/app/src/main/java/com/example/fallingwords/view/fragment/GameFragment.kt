package com.example.fallingwords.view.fragment

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.fallingwords.R
import com.example.fallingwords.databinding.FragmentGameBinding
import com.example.fallingwords.repository.network.Constants
import com.example.fallingwords.viewmodel.GameViewModel

class GameFragment : Fragment() {

    private val TAG = "GameFragment"

    private lateinit var animator : ObjectAnimator
    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)
        val viewModel : GameViewModel = ViewModelProvider(this)[GameViewModel::class.java]

        viewModel.wordPairs.observe(this, { wordProperty ->
            binding.lang1Textview.text = wordProperty.lang_1_word
            binding.lang2Textview.text = wordProperty.lang_2_word
            startAnimation(binding, viewModel)
        })

        binding.correctBtn.setOnClickListener {
            stopAnimation()
            viewModel.playerResponse(true)
        }

        binding.wrongBtn.setOnClickListener {
            stopAnimation()
            viewModel.playerResponse(false)
        }

        viewModel.score.observe(this, { score ->
            binding.scoreTextview.text = viewModel.scoreString()
            if (score == Constants.TOTAL_QUESTIONS){
                Log.d(TAG,"GAME WON")
                stopAnimation()
                view?.findNavController()?.navigate(GameFragmentDirections.actionGameFragmentToResultFragment(true))
            }
        })

        viewModel.retry.observe(this, { retry ->
            binding.retryTextview.text = viewModel.retryString()
            if (retry == 0){
                Log.d(TAG,"GAME FAIL")
                stopAnimation()
                view?.findNavController()?.navigate(GameFragmentDirections.actionGameFragmentToResultFragment(false))
            }
        })

        return binding.root
    }

    private fun startAnimation(binding : FragmentGameBinding, viewModel : GameViewModel) {
        Log.d(TAG, "Animation start")
        val screen_height = Resources.getSystem().displayMetrics.heightPixels.toFloat()

        animator = ObjectAnimator.ofFloat(binding.lang2Textview, View.TRANSLATION_Y, 0f, screen_height)

        animator.duration = Constants.TIME_PER_WORD

        animator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator?) {
                binding.lang2Textview.visibility = View.VISIBLE
            }
            override fun onAnimationEnd(animation: Animator?) {
                binding.lang2Textview.visibility = View.INVISIBLE
                viewModel.playerResponse(null)
            }
        })
        animator.start()
    }

    private fun stopAnimation(){
        Log.d(TAG, "Animation stop")
        animator.removeAllListeners();
        animator.end();
        animator.cancel();
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "Binding is clear")
        _binding = null
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "Animation paused")
        animator.pause()
    }

    override fun onResume() {
        super.onResume()
        if (this::animator.isInitialized){
            Log.d(TAG, "Animation Resume")
            animator.resume()
        }
    }
}