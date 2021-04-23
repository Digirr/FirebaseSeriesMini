package com.digir.firebaseseriesmini.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.digir.firebaseseriesmini.data.Car
import com.digir.firebaseseriesmini.repository.FirebaseRepository

class ProfileViewModel : ViewModel() {
    //Propagacja, czyli przesuniecie z Modelu do ViewModel
    private val repository = FirebaseRepository()
    //Nieprywatna zmienna
    val user = repository.getUserData()
    //Konwersja na livedata chyba
    val favCars = user.switchMap {
        repository.getFavCars(it.favCars)
    }
    fun removeFavCar(car: Car) {
        repository.removeFavCar(car)
    }
    fun editProfileData(map: Map<String, String>) {
        repository.editProfileData(map)
    }
    fun uploadUserPhoto(bytes: ByteArray) {
        repository.uploadUserPhoto(bytes)
    }
}