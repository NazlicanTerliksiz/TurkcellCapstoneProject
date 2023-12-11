package com.nazlican.turkcellcapstoneproject.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.nazlican.turkcellcapstoneproject.R
import com.nazlican.turkcellcapstoneproject.common.viewBinding
import com.nazlican.turkcellcapstoneproject.databinding.FragmentDetailBinding
import com.nazlican.turkcellcapstoneproject.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val binding by viewBinding(FragmentProfileBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}