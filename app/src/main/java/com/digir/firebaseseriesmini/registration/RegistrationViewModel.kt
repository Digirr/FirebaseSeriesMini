package com.digir.firebaseseriesmini.registration

import androidx.lifecycle.ViewModel
import com.digir.firebaseseriesmini.data.User
import com.digir.firebaseseriesmini.repository.FirebaseRepository

class RegistrationViewModel : ViewModel() {
    private val repository = FirebaseRepository()

    fun createNewUser(user : User) {
        repository.createNewUser(user)
    }

}