package com.abdullah.nurseapp

import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.abdullah.nurseapp.utils.*
import java.util.*

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This is used to hide the status bar and make
        // the splash screen as a full screen activity.
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.activity_splash_screen)

        if (getStringFromPrefs(this, LANGUAGECODE) == "") {
            setPrefsString(this, LANGUAGECODE,"en")
        }
        changeLocale()
        // we used the postDelayed(Runnable, time) method
        // to send a message with a delayed time.
        Handler().postDelayed({
            if (getBooleanFromPrefs(this, ISLOGGEDIN)) {
                val intent = Intent(this, DashboardActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
            finish()
        }, 3000) // 3000 is the delayed time in milliseconds.
    }

    private fun changeLocale() {
        val languageCode = getStringFromPrefs(this, LANGUAGECODE)
        val locale = Locale(languageCode!!)
        Locale.setDefault(locale)
        val resources: Resources = resources
        val config: Configuration = resources.getConfiguration()
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.getDisplayMetrics())
    }
}
