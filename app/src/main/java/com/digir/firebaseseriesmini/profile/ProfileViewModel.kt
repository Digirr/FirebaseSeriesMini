package com.digir.firebaseseriesmini.profile

import androidx.lifecycle.ViewModel
import com.digir.firebaseseriesmini.repository.FirebaseRepository

class ProfileViewModel : ViewModel() {
    //Propagacja, czyli przesuniecie z Modelu do ViewModel
    private val repository = FirebaseRepository()
    //Nieprywatna zmienna
    val user = repository.getUserData()
}