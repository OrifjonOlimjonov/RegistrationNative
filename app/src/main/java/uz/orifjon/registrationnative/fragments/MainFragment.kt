package uz.orifjon.registrationnative.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import uz.orifjon.registrationnative.R
import uz.orifjon.registrationnative.databinding.FragmentMainBinding
import uz.orifjon.registrationnative.models.User
import uz.orifjon.registrationnative.models.UserDatabase

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        binding.tvRegister.setOnClickListener {
            findNavController().navigate(R.id.registerFragment)
        }

        binding.btnLogin.setOnClickListener {
            val str = encryption(binding.username.text.toString(), binding.password.text.toString())
            val list = UserDatabase.getDatabase(requireContext()).userDao().list()
            var check = false
            
            for (i in list.indices) {
                if (list[i].cryptography == str) {
                    val bundle = Bundle()
                    bundle.putSerializable("user", list[i])
                    check = true
                    findNavController().navigate(R.id.dashBoardFragment, bundle)
                }
            }
            
            if(!check){
                Toast.makeText(requireContext(), "Bunday user mavjud emas!", Toast.LENGTH_SHORT).show()
            }
//            Toast.makeText(requireContext(), str  , Toast.LENGTH_SHORT).show()
//            Log.d("CHIQISHI KERAK", "onCreateView: $str")

//            val str = decryption("salom")
//            Toast.makeText(requireContext(), str, Toast.LENGTH_SHORT).show()
//            Log.d("CHIQISHI KERAK", "onCreateView: $str")

        }

        return binding.root
    }


    external fun stringFromJNI(): String
    external fun encryption(login: String, password: String): String
    external fun decryption(info: String): String
    //external fun stringParse(info:String): String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.username.setText("")
        binding.password.setText("")
    }

    override fun onStart() {
        super.onStart()
        binding.username.setText("")
        binding.password.setText("")
    }

    companion object {
        init {
            System.loadLibrary("registrationnative")
        }
    }

}