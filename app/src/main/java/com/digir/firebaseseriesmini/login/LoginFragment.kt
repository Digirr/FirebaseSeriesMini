package com.digir.firebaseseriesmini.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.digir.firebaseseriesmini.BaseFragment
import com.digir.firebaseseriesmini.R
import com.digir.firebaseseriesmini.activites.MainActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_sign_in.*
import kotlinx.android.synthetic.main.fragment_sign_up.*

class LoginFragment : BaseFragment() {

    private val fbAuth = FirebaseAuth.getInstance()
    private val LOG_DEBUG = "LOG_DEBUG"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLoginClick()
        setupRegistrationClick()
    }

    private fun setupRegistrationClick() {
        //Przenies do RegistrationFragment
        signUpButton.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections
                .actionLoginFragmentToRegistrationFragment().actionId)
        }

    }

    private fun setupLoginClick() {
        //Dokonaj logowania
        loginButton.setOnClickListener {
            val email = emailLoginInput.text?.trim().toString()
            val pass = passLoginInput.text?.trim().toString()

            fbAuth.signInWithEmailAndPassword(email, pass)
                .addOnSuccessListener { authRes ->
                    if(authRes.user != null) {
                        startApp()
                    }

                }
                .addOnFailureListener { exc ->
                    Snackbar.make(requireView(), "Something went wrong...", Snackbar.LENGTH_SHORT)
                        .show()
                    Log.d(LOG_DEBUG, exc.message.toString())
                }
        }
    }

}