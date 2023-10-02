package tn.esprit.gamerapp2

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class ResetPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        val passwordEditText = findViewById<TextInputEditText>(R.id.tiPassword)
        val confirmPasswordEditText = findViewById<TextInputEditText>(R.id.tiConfirmPassword)
        val submitButton = findViewById<Button>(R.id.btnSubmit)
        val passwordLayout = findViewById<TextInputLayout>(R.id.tiPasswordLayout)
        val confirmPasswordLayout = findViewById<TextInputLayout>(R.id.tiConfirmPasswordLayout)

        // Ajout d'un TextWatcher pour le champ de mot de passe
        passwordEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val password = s.toString().trim()
                if (password.length < 6) {
                    // Mot de passe trop court
                    passwordLayout.error = "Mot de passe trop court (min. 6 caractères)"
                } else {
                    passwordLayout.error = null // Réinitialiser l'erreur si le mot de passe est valide
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        // Ajout d'un TextWatcher pour le champ de confirmation de mot de passe
        confirmPasswordEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val confirmPassword = s.toString().trim()
                val password = passwordEditText.text.toString().trim()
                if (confirmPassword != password) {
                    // Les mots de passe ne correspondent pas
                    confirmPasswordLayout.error = "Les mots de passe ne correspondent pas"
                } else {
                    confirmPasswordLayout.error = null // Réinitialiser l'erreur si les mots de passe correspondent
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        // Gestion du bouton "Submit"
        submitButton.setOnClickListener {
            val password = passwordEditText.text.toString().trim()
            val confirmPassword = confirmPasswordEditText.text.toString().trim()

            // Validation avant la soumission
            if (password.length < 6 || password != confirmPassword) {
                Snackbar.make(it, "Veuillez corriger les erreurs de saisie", Snackbar.LENGTH_SHORT).show()
            } else {
                // Si les données sont valides, naviguez vers LoginActivity et détruisez la pile d'activités
                val intent = Intent(this@ResetPasswordActivity, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
    }

}