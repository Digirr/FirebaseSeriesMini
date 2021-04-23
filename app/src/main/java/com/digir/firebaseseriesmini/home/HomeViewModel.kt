package com.digir.firebaseseriesmini.home

import androidx.lifecycle.ViewModel
import com.digir.firebaseseriesmini.data.Car
import com.digir.firebaseseriesmini.repository.FirebaseRepository

class HomeViewModel : ViewModel() {
    private val repository = FirebaseRepository()

    val cars = repository.getCars()

    fun addFavCar(car: Car) {
        repository.addFavCar(car)
    }

}