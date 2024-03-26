package com.example.composition.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.composition.R
import com.example.composition.databinding.FragmentChooseLevelBinding
import com.example.composition.domain.entity.GameResult
import com.example.composition.domain.entity.Level

class ChooseLevelFragment : Fragment() {


    private var _binding : FragmentChooseLevelBinding? = null
    private val binding : FragmentChooseLevelBinding
        get() = _binding ?: throw RuntimeException("FragmentChooseLevelBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseLevelBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonLevelTest.setOnClickListener{
            launchCurrentLevel(Level.TEST)
        }
        binding.buttonLevelEasy.setOnClickListener{
            launchCurrentLevel(Level.EASY)
        }
        binding.buttonLevelNormal.setOnClickListener{
            launchCurrentLevel(Level.NORMAL)
        }
        binding.buttonLevelHard.setOnClickListener{
            launchCurrentLevel(Level.HARD)
        }
    }

    private fun launchCurrentLevel(level: Level) {
        val fragment = GameFragment.newInstance(level)
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment)
            .addToBackStack(GameFragment.NAME)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object {

        fun newInstance () : ChooseLevelFragment{
            return ChooseLevelFragment()
        }
    }
}