package tn.esprit.gamerapp2

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SignUpActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)



        val fullNameEditText = findViewById<TextInputEditText>(R.id.tiFullName)
        val emailEditText = findViewById<TextInputEditText>(R.id.tiEmail)
        val passwordEditText = findViewById<TextInputEditText>(R.id.tiPassword)
        val confirmPasswordEditText = findViewById<TextInputEditText>(R.id.tiConfirmPassword)
        val fullNameLayout = findViewById<TextInputLayout>(R.id.tiFullNameLayout)
        val emailLayout = findViewById<TextInputLayout>(R.id.tiEmailLayout)
        val passwordLayout = findViewById<TextInputLayout>(R.id.tiPasswordLayout)
        val confirmPasswordLayout = findViewById<TextInputLayout>(R.id.tiConfirmPasswordLayout)



        // Ajout d'un TextWatcher pour le fullname
        fullNameEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val fullName = s.toString().trim()
                if (fullName.isEmpty()) {
                    fullNameLayout.error = "Champ nom complet ne peut pas être vide"
                } else {
                    fullNameLayout.error = null
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        // Ajout d'un TextWatcher pour l'e-mail
        emailEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val email = s.toString().trim()
                if (email.isEmpty()) {
                    emailLayout.error = "Champ e-mail ne peut pas être vide"
                } else if (email.length < 6) {
                    passwordLayout.error = "Mot de passe trop court (min. 6 caractères)"
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

        // Ajout d'un TextWatcher pour la confirmation du mot de passe
        confirmPasswordEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val confirmPassword = s.toString().trim()
                if (confirmPassword.isEmpty()) {
                    confirmPasswordLayout.error = "Champ confirmation du mot de passe ne peut pas être vide"
                } else if (confirmPassword != passwordEditText.text.toString().trim()) {
                    confirmPasswordLayout.error = "Les mots de passe ne correspondent pas"
                } else {
                    confirmPasswordLayout.error = null
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        // Gestion du bouton "Privacy Policy"
        val privacyPolicyButton = findViewById<Button>(R.id.btnTermsAndPolicy)
        privacyPolicyButton.setOnClickListener {
            Snackbar.make(it, "Coming soon", Snackbar.LENGTH_SHORT).show()
        }

        // Gestion du bouton "Submit"
        val submitButton = findViewById<Button>(R.id.btnSignUp)
        submitButton.setOnClickListener {
            val fullName = fullNameEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val confirmPassword = confirmPasswordEditText.text.toString()

            // Validation avant l'inscription
            if (fullName.isEmpty()) {
                fullNameLayout.error = "Champ ne peut pas être vide"
            }
            if (email.isEmpty()) {
                emailLayout.error = "Champ e-mail ne peut pas être vide"
            }
            if (password.isEmpty()) {
                passwordLayout.error = "Champ ne peut pas être vide"
            }
            if (confirmPassword.isEmpty()) {
                confirmPasswordLayout.error = "Champ ne peut pas être vide"
            }
            if (password != confirmPassword) {
                confirmPasswordLayout.error = "Les mots de passe ne correspondent pas"
            }

            if (!fullName.isEmpty() && !email.isEmpty() && !password.isEmpty() && !confirmPassword.isEmpty() && password == confirmPassword) {
                //les informations d'inscription sont valides alors la naviguation vers LoginActivity
                val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
                startActivity(intent)
            } else {
                //les informations d'inscription ne sont pas valides, alors affichage d'un message d'erreur
                Snackbar.make(it, "Veuillez corriger les erreurs de saisie", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}