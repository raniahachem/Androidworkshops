package tn.esprit.gamerapp2

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


            val forgotPasswordButton = findViewById<Button>(R.id.btnForgotPassword)
        val facebookButton = findViewById<ImageView>(R.id.btnFacebookLogin)
        val googleButton = findViewById<ImageView>(R.id.btnGoogleLogin)
        val registerNowButton = findViewById<Button>(R.id.btnCreateAccount)
        val emailEditText = findViewById<TextInputEditText>(R.id.tiEmail)
        val passwordEditText = findViewById<TextInputEditText>(R.id.tiPassword)
        val emailLayout = findViewById<TextInputLayout>(R.id.tiEmailLayout)
        val passwordLayout = findViewById<TextInputLayout>(R.id.tiPasswordLayout)


        forgotPasswordButton.setOnClickListener {
            val intent = Intent(this@LoginActivity, ForgetPasswordActivity::class.java)
            startActivity(intent)
        }


        facebookButton.setOnClickListener {
            Snackbar.make(it, "Coming soon", Snackbar.LENGTH_SHORT).show()
        }
        googleButton.setOnClickListener {
            Snackbar.make(it, "Coming soon", Snackbar.LENGTH_SHORT).show()
        }

        registerNowButton.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)
        }

        // Ajout d'un TextWatcher pour l'e-mail
        emailEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val email = s.toString().trim()
                if (email.isEmpty()) {
                    emailLayout.error = "Champ e-mail ne peut pas être vide"
                }
                else {
                    emailLayout.error = null
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        // Ajout d'un TextWatcher pour le mot de passe
        passwordEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val password = s.toString().trim()
                if (password.isEmpty()) {
                    passwordLayout.error = "Champ mot de passe ne peut pas être vide"
                } else if (password.length < 6) {
                    passwordLayout.error = "Mot de passe trop court (min. 6 caractères)"
                } else {
                    passwordLayout.error = null
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        // Gestion du bouton de connexion
        val loginButton = findViewById<Button>(R.id.btnLogin)
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Validation avant la connexion
            if (email.isEmpty()) {
                emailLayout.error = "Champ e-mail ne peut pas être vide"
            }
            if (password.isEmpty()) {
                passwordLayout.error = "Champ mot de passe ne peut pas être vide"
            }

            if (!email.isEmpty() && !password.isEmpty() && isValidPassword(password)) {
                // Si les informations de connexion sont valides, naviguez vers HomeActivity
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
            } else {
                // Si les informations de connexion ne sont pas valides, affichez un message d'erreur
                Snackbar.make(it, "Veuillez corriger les erreurs de saisie", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    // Fonction de validation de mot de passe (exemple)
    private fun isValidPassword(password: String): Boolean {
        // Ajoutez ici votre logique de validation de mot de passe (par exemple, longueur minimale)
        return password.length >= 6
    }






}