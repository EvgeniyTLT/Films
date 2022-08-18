package com.example.films

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class RegistrationActivity : AppCompatActivity() {
    private val signInLauncher = registerForActivityResult( //  создали объект авторизации
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        this.onSignInResult(res) // запуск самого экрана
    }
    private lateinit var database: DatabaseReference // создали объект для записи в базу данных

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        database = Firebase.database.reference // инициализация базы данных
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build() // список регистрации, которые мы используем
        )

// Create and launch sign-in intent
        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build() // создали интент для экрана firebase
        signInLauncher.launch(signInIntent) // запустили экран firebase auth
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse // результат с экрана Firebase auth
        if (result.resultCode == RESULT_OK) { // если результат ОК, то вызывается код, иначе esle
            val authUser =
                FirebaseAuth.getInstance().currentUser //создаем объект текущего пользователя Firebase
            authUser?.let {//если он существует, мы сохраняем его в базу данных
                val email = it.email.toString() // извлекаем email нашего пользователя
                val uid = it.uid // извлекаем uid нашего пользователя
                val firebaseUser = User(email, uid) //создаем новый обьект User c (email, uid)
                database.child("users").child(uid)
                    .setValue(firebaseUser) // сохранение в БД пользователя
                onBackPressed()
            }

            // ...
        } else { // если результат не ок, должны обработать ошибку
            // Sign in failed. If response is null the user canceled the
            // sign-in flow using the back button. Otherwise check
            // response.getError().getErrorCode() and handle the error.
            // ...
        }
    }
}