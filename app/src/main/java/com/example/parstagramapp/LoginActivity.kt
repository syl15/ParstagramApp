package com.example.parstagramapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.util.Log
import android.widget.Toast
import com.parse.ParseObject
import com.parse.ParseUser

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // check if there's a user logged in
        // if logged in, take to MainActivity
        if (ParseUser.getCurrentUser() != null) {
            goToMainActivity()
        }

        findViewById<Button>(R.id.login_button).setOnClickListener {
            val username = findViewById<EditText>(R.id.et_username).text.toString()
            val password = findViewById<EditText>(R.id.et_password).text.toString()
            loginUser(username, password)
        }

        findViewById<Button>(R.id.signupBtn).setOnClickListener {
            val username = findViewById<EditText>(R.id.et_username).text.toString()
            val password = findViewById<EditText>(R.id.et_password).text.toString()
            signUpUser(username, password)
        }
    }

    private fun signUpUser(username: String, password: String) {
        // Create the ParseUser
        val user = ParseUser()

        // Set fields for the user to be created
        user.setUsername(username)
        user.setPassword(password)

        user.signUpInBackground { e ->
            if (e == null) {
                // User has successfully created a new account
                Toast.makeText(this, "Sign up successful", Toast.LENGTH_SHORT).show()
                // navigate user to main activity
                goToMainActivity()
            } else {
                Toast.makeText(this, "Sign up unsuccessful", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        }
    }

    private fun loginUser(username: String, password: String) {
        // want to log in in the background because don't
        // know how long the network call is going to take
        ParseUser.logInInBackground(username, password, ({ user, e ->
            if (user != null) {
                Log.i(TAG, "Successfully logged in user!")
                goToMainActivity()
            } else {
                e.printStackTrace() // exception that comes back if user is null
                Toast.makeText(this, "Error logging in", Toast.LENGTH_SHORT).show()
            }})
        )
    }

    // if the user has successfully logged in
    private fun goToMainActivity() {
        // navigate to main activity after logging in
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        // close out log in activity - back button exits app instead of back to login activity
        finish()
    }

    companion object {
        const val TAG = "LoginActivity"
    }


}