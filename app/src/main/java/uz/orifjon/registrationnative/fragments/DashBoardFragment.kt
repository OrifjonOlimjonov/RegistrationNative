package uz.orifjon.registrationnative.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.orifjon.registrationnative.R
import uz.orifjon.registrationnative.databinding.FragmentDashBoardBinding

class DashBoardFragment : Fragment() {

    private lateinit var binding:FragmentDashBoardBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashBoardBinding.inflate(inflater,container,false)


        return binding.root
    }


}