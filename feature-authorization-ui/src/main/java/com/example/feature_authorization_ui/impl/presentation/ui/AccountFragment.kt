package com.example.feature_authorization_ui.impl.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.core_utils.common_helpers.lazyUnsafe
import com.example.feature_authorization_ui.databinding.FragmentAccountBinding

class AccountFragment : Fragment() {

    private val binding: FragmentAccountBinding by lazyUnsafe {
        FragmentAccountBinding.inflate(layoutInflater)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val view = binding.root

        initListeners()

        return view
    }

    private fun initListeners() {
        with(binding) {
            backBut.setOnClickListener {
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }
        }
    }
}