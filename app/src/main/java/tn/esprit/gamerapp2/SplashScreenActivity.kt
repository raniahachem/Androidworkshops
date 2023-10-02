package tn.esprit.gamerapp2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import android.os.Handler

class SplashScreenActivity : AppCompatActivity() {

    private val SPLASH_DELAY: Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Définir le contenu de l'activité sur l'écran de démarrage
        setContentView(R.layout.splash_screen)

        // Utiliser un Handler pour retarder le passage à l'écran de connexion
        Handler().postDelayed({
            // Maintenant, passez à l'écran de connexion
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_DELAY)
    }
}