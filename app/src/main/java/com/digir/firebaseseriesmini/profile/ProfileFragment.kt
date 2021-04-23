package com.digir.firebaseseriesmini.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.digir.firebaseseriesmini.R
import com.digir.firebaseseriesmini.data.Car
import com.digir.firebaseseriesmini.data.User
import com.digir.firebaseseriesmini.home.CarAdapter
import com.digir.firebaseseriesmini.home.OnCarItemLongClick
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() , OnCarItemLongClick{
    private val PROFILE_DEBUG = "PROFILE_DEBUG"

    //Delegacja
    private val profileVm by viewModels<ProfileViewModel>()
    private val adapter = CarAdapter(this)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSubmitDataClick()
        recyclerFavCars.layoutManager = LinearLayoutManager(requireContext())
        recyclerFavCars.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        profileVm.user.observe(viewLifecycleOwner, { user ->
            bindUserData(user)
        })

        profileVm.favCars.observe(viewLifecycleOwner, { list ->
            list?.let{
                adapter.setCars(it)
            }
        })
    }
    override fun onCarLongClick(car: Car, position: Int) {
        profileVm.removeFavCar(car)
        adapter.removeCar(car, position)
    }

    private fun bindUserData(user: User) {
        Log.d(PROFILE_DEBUG, user.toString())
        userNameET.setText(user.name)
        userSurnameET.setText(user.surname)
        userEmailET.setText(user.email)
    }
    private fun setupSubmitDataClick() {
        submitDataProfile.setOnClickListener {
            val name = userNameET.text.trim().toString()
            val surname = userSurnameET.text.trim().toString()

            val map = mapOf("name" to name, "surname" to surname)
            profileVm.editProfileData(map)
        }
    }


}