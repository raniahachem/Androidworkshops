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

class ForgetPasswordActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)
        val emailEditText = findViewById<TextInputEditText>(R.id.tiEmail)
        val emailLayout = findViewById<TextInputLayout>(R.id.tiEmailLayout)
        val submitButton = findViewById<Button>(R.id.btnSubmit)
        val sendSmsButton = findViewById<Button>(R.id.btnSendSMS)

        // Ajout d'un TextWatcher pour l'e-mail
        emailEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val email = s.toString().trim()
                if (email.isEmpty()) {
                    emailLayout.error = "Champ e-mail ne peut pas être vide"
                } else {
                    emailLayout.error = null
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        // Gestion du bouton "Submit"
        submitButton.setOnClickListener {
            val email = emailEditText.text.toString()

            // Validation avant de soumettre
            if (email.isEmpty()) {
                emailLayout.error = "Champ e-mail ne peut pas être vide"
            }

            if (!email.isEmpty()) {
                // Si les informations sont valides, naviguez vers OTPValidationActivity avec le code 1234
                val intent = Intent(this@ForgetPasswordActivity, OtpValidationActivity::class.java)
                intent.putExtra("code", "1234")
                startActivity(intent)
            } else {
                // Si les informations ne sont pas valides, affichez un message d'erreur
                Snackbar.make(it, "Veuillez corriger les erreurs de saisie", Snackbar.LENGTH_SHORT).show()
            }
        }

        // Gestion du bouton "SendSMS"
        sendSmsButton.setOnClickListener {
            val email = emailEditText.text.toString()

            // Validation avant de soumettre
            if (email.isEmpty()) {
                emailLayout.error = "Champ e-mail ne peut pas être vide"
            }

            if (!email.isEmpty()) {
                // Si les informations sont valides, naviguez vers OTPValidationActivity avec le code 6789
                val intent = Intent(this@ForgetPasswordActivity, OtpValidationActivity::class.java)
                intent.putExtra("code", "6789")
                startActivity(intent)
            } else {
                // Si les informations ne sont pas valides, affichez un message d'erreur
                Snackbar.make(it, "Veuillez corriger les erreurs de saisie", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}