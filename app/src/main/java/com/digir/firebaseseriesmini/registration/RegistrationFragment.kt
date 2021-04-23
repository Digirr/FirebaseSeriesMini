package com.digir.firebaseseriesmini.registration

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.digir.firebaseseriesmini.BaseFragment
import com.digir.firebaseseriesmini.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_sign_up.*

class RegistrationFragment : BaseFragment() {

    private val fbAuth = FirebaseAuth.getInstance()
    private val REG_DEBUG = "REG_DEBUG"
    private val regVm by viewModels<RegistrationViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSignUpClick()
    }
    private fun setupSignUpClick() {
        signUpButtonRegistration.setOnClickListener {
            val email = email_registration.text?.trim().toString()
            val pass = pass_registration.text?.trim().toString()
            val repeatPass = repeat_pass_registration.text?.trim().toString()

            if(pass==repeatPass){
                fbAuth.createUserWithEmailAndPassword(email, pass)
                    .addOnSuccessListener {authRes ->
                        if(authRes.user != null){
                            val user = com.digir.firebaseseriesmini.data.User(
                                    authRes.user!!.uid,
                                    "",
                                    "",
                                    authRes.user!!.email,
                                    listOf(),
                                    "")

                            regVm.createNewUser(user)
                            startApp()
                        }
                    }
                    .addOnFailureListener{exc ->
                        Snackbar.make(requireView(), "Upss...Something went wrong...", Snackbar.LENGTH_SHORT)
                            .show()
                        Log.d(REG_DEBUG, exc.message.toString())
                    }
            }
        }
    }
}