package com.example.tugas8

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.tugas8.databinding.ActivityMainBinding
import com.example.tugas8.databinding.FragmentRegisterBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
interface DataListener {
    fun onDataReceived(dataUsername: String, dataEmail: String, dataPhone: String, dataPassword: String)
}
/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

//    Mengirim Data dari Fragment

    private lateinit var dataListener: DataListener
    private lateinit var username: EditText
    private lateinit var email: EditText
    private lateinit var phone: EditText
    private lateinit var password: EditText


    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            dataListener = context as DataListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement DataListener")
        }
    }

// Sampai Sini

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        username = view.findViewById(R.id.register_username)
        email = view.findViewById(R.id.register_email)
        phone = view.findViewById(R.id.register_phone)
        password = view.findViewById(R.id.register_password)
        val loginText = view.findViewById<TextView>(R.id.login_page)
        val button = view.findViewById<Button>(R.id.button_register)
        val checkbox = view.findViewById<CheckBox>(R.id.checkbox_register)

        button.setOnClickListener {
            val dataUsername = username.text.toString()
            val dataEmail = email.text.toString()
            val dataPhone = phone.text.toString()
            val dataPassword = password.text.toString()

            if(checkbox.isChecked()) {
                if(dataUsername.isEmpty()){
                    Toast.makeText(context, "Username Belum Diisi", Toast.LENGTH_SHORT).show()
                } else if(dataEmail.isEmpty()){
                    Toast.makeText(context, "Email Belum Diisi", Toast.LENGTH_SHORT).show()
                } else if(dataPhone.isEmpty()){
                    Toast.makeText(context, "Nomor Handphone Belum Diisi", Toast.LENGTH_SHORT).show()
                } else if(dataEmail.isEmpty()){
                    Toast.makeText(context, "Password Belum Diisi", Toast.LENGTH_SHORT).show()
                } else {
                    dataListener.onDataReceived(dataUsername, dataEmail, dataPhone, dataPassword)
                }
            } else if(!checkbox.isChecked()) {
                Toast.makeText(context, "Mohon Melakukan Checkbox Terlebih Dahulu", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RegisterFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegisterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}