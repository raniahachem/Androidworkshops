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

class OtpValidationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_validation)

        val code1EditText = findViewById<TextInputEditText>(R.id.tiCode1)
        val code2EditText = findViewById<TextInputEditText>(R.id.tiCode2)
        val code3EditText = findViewById<TextInputEditText>(R.id.tiCode3)
        val code4EditText = findViewById<TextInputEditText>(R.id.tiCode4)
        val verifyButton = findViewById<Button>(R.id.btnVerify)
        val resendCodeButton = findViewById<Button>(R.id.btnResendCode)

        // Ajout d'un TextWatcher pour chaque champ de code
        val codeEditTexts = listOf(code1EditText, code2EditText, code3EditText, code4EditText)

        codeEditTexts.forEachIndexed { index, editText ->
            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val code = s.toString().trim()
                    if (code.length == 1) {
                        if (index < codeEditTexts.size - 1) {
                            codeEditTexts[index + 1].requestFocus()
                        }
                    }
                }

                override fun afterTextChanged(s: Editable?) {}
            })
        }

        // Gestion du bouton "Verify"
        verifyButton.setOnClickListener {
            val code1 = code1EditText.text.toString().trim()
            val code2 = code2EditText.text.toString().trim()
            val code3 = code3EditText.text.toString().trim()
            val code4 = code4EditText.text.toString().trim()

            // Validation avant de vérifier
            if (code1.isEmpty() || code2.isEmpty() || code3.isEmpty() || code4.isEmpty()) {
                Snackbar.make(it, "Tous les champs doivent être remplis", Snackbar.LENGTH_SHORT).show()
            } else {
                // Si le code est valide, naviguez vers ResetPasswordActivity
                val intent = Intent(this@OtpValidationActivity, ResetPasswordActivity::class.java)
                startActivity(intent)
            }
        }

        // Gestion du bouton "Resend Code"
        resendCodeButton.setOnClickListener {
            // Affichez un message "Coming soon" lorsque le bouton "Resend Code" est cliqué
            Snackbar.make(it, "Coming soon", Snackbar.LENGTH_SHORT).show()
        }
    }

}