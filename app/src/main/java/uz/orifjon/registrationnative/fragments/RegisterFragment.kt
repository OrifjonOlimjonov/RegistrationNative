package uz.orifjon.registrationnative.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.orifjon.registrationnative.databinding.FragmentRegisterBinding
import uz.orifjon.registrationnative.models.User
import uz.orifjon.registrationnative.models.UserDatabase
import java.lang.reflect.Type

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRegisterBinding.inflate(inflater, container, false)


        binding.btnSave.setOnClickListener {

            val field =
                binding.username.text.toString().isEmpty() ||
                binding.password.text.toString().isEmpty() ||
                binding.fullName.text.toString().isEmpty() ||
                binding.confirmpassword.text.toString().isEmpty()
            if (field) {
                Toast.makeText(requireContext(), "Maydonlarni to'ldiring!!", Toast.LENGTH_SHORT).show()
            } else if (binding.password.text.toString() == binding.confirmpassword.text.toString()) {
                val fullName: String = binding.fullName.text.toString()
                val username: String =  binding.username.text.toString()
                val password: String =  binding.password.text.toString()
                val encryption = encryption(username,password)
                val user = User(0,fullName,username,password,encryption)
                var check = false
                val list = UserDatabase.getDatabase(requireContext()).userDao().list()
                for (item in list) {
                    if(item.cryptography == encryption){
                        Toast.makeText(requireContext(), "Bunday User mavjud!", Toast.LENGTH_SHORT).show()
                        check = true
                    }
                }
                if(!check){
                    UserDatabase.getDatabase(requireContext()).userDao().add(user)
                    Toast.makeText(
                        requireContext(),
                        "Muvaffaqiyatli ro'yxatdan o'tdingiz!!",
                        Toast.LENGTH_SHORT
                    ).show()
                    findNavController().popBackStack()
                }
            } else {
                Toast.makeText(requireContext(), "passwords are not compatible!", Toast.LENGTH_SHORT).show()
            }
        }



        return binding.root
    }


    companion object {
        init {
            System.loadLibrary("registrationnative")
        }
    }

    external fun encryption(login:String,password:String): String
}