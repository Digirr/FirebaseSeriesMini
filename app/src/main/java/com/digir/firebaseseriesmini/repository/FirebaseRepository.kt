package com.digir.firebaseseriesmini.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.digir.firebaseseriesmini.data.Car
import com.digir.firebaseseriesmini.data.User
import com.google.firebase.FirebaseApiNotAvailableException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class FirebaseRepository {
    private val REPO_DEBUG = "REPO_DEBUG"

    private val storage = FirebaseStorage.getInstance()
    private val auth = FirebaseAuth.getInstance()
    private val cloud = FirebaseFirestore.getInstance()

    fun getUserData(): LiveData<User> {
        val cloudResult = MutableLiveData<User>()
        //Wyciagamy uid uzytkownika ktory jest zalogowany
        val uid = auth.currentUser?.uid

        cloud.collection("users")   //Odnosze sie do kolekcji users
            .document(uid!!)    //Do dokumentu o nazwie uid
            .get()  //Uzyskaj ten dokument
            .addOnSuccessListener {
                //Od razu mapuje nam dane, ktore przyjda na odpowiedni obiekt (User)
                val user = it.toObject(User::class.java)
                //Ustalimy ta wartosc przez postValue do LiveData
                cloudResult.postValue(user)
            }
            .addOnFailureListener {
                Log.d(REPO_DEBUG, it.message.toString())
            }
        return cloudResult  //No i zwracanie LiveData
    }
    fun getCars(): LiveData<List<Car>> {
        val cloudResult = MutableLiveData<List<Car>>()

        cloud.collection("cars")
                .get()
                .addOnSuccessListener {
                    val user = it.toObjects(Car::class.java)
                    cloudResult.postValue(user)
                }
                .addOnFailureListener {
                    Log.d(REPO_DEBUG, it.message.toString())
                }
        return cloudResult  //No i zwracanie LiveData
    }

    fun getFavCars(list: List<String>?) : LiveData<List<Car>> {
        val cloudResult = MutableLiveData<List<Car>>()

        if(!list.isNullOrEmpty()){
            cloud.collection("cars")
                .whereIn("id", list)    //Filtrowanie, sprawdzamy ktore id sie pokrywaja
                .get()
                .addOnSuccessListener {
                    val resultList = it.toObjects(Car::class.java)
                    cloudResult.postValue(resultList)
                }
                .addOnFailureListener { exc ->
                    Log.d(REPO_DEBUG, exc.message.toString())
                }
        }
        return cloudResult
    }

    fun addFavCar(car : Car) {
        cloud.collection("users")
                .document(auth.currentUser?.uid!!)
                .update("favCars", FieldValue.arrayUnion(car.id))
                .addOnSuccessListener {
                    Log.d(REPO_DEBUG, "Dodana do ulubionych")
                }
                .addOnFailureListener { exc ->
                    Log.d(REPO_DEBUG, exc.message.toString())
                }
    }
    fun removeFavCar(car : Car) {
        cloud.collection("users")
                .document(auth.currentUser?.uid!!)
                .update("favCars", FieldValue.arrayRemove(car.id))
                .addOnSuccessListener {
                    Log.d(REPO_DEBUG, "Dodana do ulubionych")
                }
                .addOnFailureListener { exc ->
                    Log.d(REPO_DEBUG, exc.message.toString())
                }
    }


}