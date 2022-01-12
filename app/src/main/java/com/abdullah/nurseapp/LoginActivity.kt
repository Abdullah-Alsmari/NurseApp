package com.abdullah.nurseapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.abdullah.nurseapp.utils.*
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginActivity : AppCompatActivity() {

    private lateinit var signIn : Button
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        signIn = findViewById(R.id.signIn)
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("223065949340-j4mcaglk86om991t79g09n04njflfbp2.apps.googleusercontent.com")
            .requestEmail()
            .build()

        var googleSignInClient = GoogleSignIn.getClient(this, gso)
        signIn.setOnClickListener {
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, 50)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == 50) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
//                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w("aa", "Google sign in failed", e)
            }
        }
    }
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
//                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    Log.i("infoo",user!!.displayName.toString())
                    Log.i("infoo",user!!.email.toString())
                    Toast.makeText(this,"Sign in Successful\n"+user!!.displayName.toString(),Toast.LENGTH_LONG).show()
                    setPrefsString(this, EMAIL,user!!.email.toString())
                    setPrefsString(this, USERNAME,user!!.displayName.toString())
                    setPrefsString(this, PHONENUMBER,user!!.phoneNumber.toString())
                    setPrefsBoolean(this, ISLOGGEDIN,true)
                    val intent = Intent(this,DashboardActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // If sign in fails, display a message to the user.
//                    Log.w(TAG, "signInWithCredential:failure", task.exception)
//                    updateUI(null)
                }
            }
    }

}