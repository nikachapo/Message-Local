package com.epam.messagelocal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.epam.messagelocal.model.Chat
import com.epam.messagelocal.model.Message
import com.epam.messagelocal.model.User
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private val providers: List<AuthUI.IdpConfig> = listOf(AuthUI.IdpConfig.GoogleBuilder().build())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun onResume() {
        super.onResume()
        val currentFirebaseUser = FirebaseAuth.getInstance().currentUser
        if (currentFirebaseUser == null) {
            showSignInOption()
        } else {
            with(currentFirebaseUser) {
                UserManager.currentUser = User(uid, email, displayName, photoUrl.toString())
                UserManager.initFakeData()
            }
            findNavController(R.id.navHostFragment).navigate(R.id.nav_main)
        }
    }

    private fun showSignInOption() {
        startActivityForResult(
            AuthUI.getInstance().createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setIsSmartLockEnabled(true) //set true to save credentials
                .setTheme(R.style.AuthUiTheme)
                .build(),
            LOGIN_REQUEST
        )
    }

    companion object {
        private const val LOGIN_REQUEST = 22
    }
}