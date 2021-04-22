package com.digir.firebaseseriesmini.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.digir.firebaseseriesmini.R
import com.digir.firebaseseriesmini.data.User

class ProfileFragment : Fragment() {
    private val PROFILE_DEBUG = "PROFILE_DEBUG"

    //Delegacja
    private val profileVm by viewModels<ProfileViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        profileVm.user.observe(viewLifecycleOwner, { user ->
            bindUserData(user)
        })
    }

    private fun bindUserData(user: User) {
        Log.d(PROFILE_DEBUG, user.toString())
    }

}