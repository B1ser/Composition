package com.example.composition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.composition.R
import com.example.composition.databinding.FragmentGameBinding
import com.example.composition.domain.entity.GameResult
import com.example.composition.domain.entity.GameSettings
import com.example.composition.domain.entity.Level


class GameFragment : Fragment() {
    private val viewModel: GameViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[GameViewModel::class.java]
    }

    private lateinit var level : Level
    private var _binding : FragmentGameBinding? = null
    private val binding : FragmentGameBinding
    get() = _binding ?: throw RuntimeException("FragmentGameBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvSum.setOnClickListener{
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main_container,GameFinishedFragment.newInstance(GameResult(true,30,30,
                    GameSettings(
                    10,
                    10,
                    70,
                    60
                )
                )))
                .addToBackStack(null)
                .commit()
        }

        viewModel.gameResult.observe(viewLifecycleOwner){

        }
        viewModel.enoughCount.observe(viewLifecycleOwner){

        }
        viewModel.enoughPercent.observe(viewLifecycleOwner){

        }
        viewModel.minPercent.observe(viewLifecycleOwner){

        }
        viewModel.formattedTime.observe(viewLifecycleOwner){

        }
        viewModel.question.observe(viewLifecycleOwner){

        }
        viewModel.progressAnswers.observe(viewLifecycleOwner){

        }
        viewModel.percentOfRightAnswer.observe(viewLifecycleOwner){

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun parseArgs(){

       arguments?.getParcelable(KEY_LEVEL,Level::class.java)?.let {
           level = it
       }

    }

    companion object {

        private const val KEY_LEVEL = "level"
        const val NAME = "GameFragment"

        fun newInstance(level: Level) =
            GameFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_LEVEL,level)
                }
            }
    }
}