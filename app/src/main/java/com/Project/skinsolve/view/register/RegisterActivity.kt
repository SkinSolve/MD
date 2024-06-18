package com.Project.skinsolve.view.register

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.Project.skinsolve.R
import com.Project.skinsolve.view.OTPVerification


class RegisterActivity : AppCompatActivity() {
    private var passwordShowing = false
    private var conPasswordShowing = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val email = findViewById<EditText>(R.id.emailET)
        val mobile = findViewById<EditText>(R.id.mobileET)
        val password = findViewById<EditText>(R.id.passwordET)
        val conPassword = findViewById<EditText>(R.id.conPasswordET)
        val passwordIcon = findViewById<ImageView>(R.id.passwordIcon)
        val conPasswordIcon = findViewById<ImageView>(R.id.conPasswordIcon)
        val signUpBtn = findViewById<AppCompatButton>(R.id.signUpBtn)
        val signInBtn = findViewById<TextView>(R.id.signInBtn)
        passwordIcon.setOnClickListener { // checking if password is showing or not
            if (passwordShowing) {
                passwordShowing = false
                password.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                passwordIcon.setImageResource(R.drawable.password_show)
            } else {
                passwordShowing = true
                password.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                passwordIcon.setImageResource(R.drawable.password_hide)
            }

            // move the cursor at last of the text
            password.setSelection(password.length())
        }
        conPasswordIcon.setOnClickListener { // checking if password is showing or not
            if (conPasswordShowing) {
                conPasswordShowing = false
                conPassword.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                conPasswordIcon.setImageResource(R.drawable.password_show)
            } else {
                conPasswordShowing = true
                conPassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                conPasswordIcon.setImageResource(R.drawable.password_hide)
            }

            // move the cursor at last of the text
            conPassword.setSelection(conPassword.length())
        }
        signUpBtn.setOnClickListener {
            val getMobileTxt = mobile.text.toString()
            val getEmailTxt = email.text.toString()

            // opening OTP Verification Activity along with mobile and email
            val intent = Intent(
                this@RegisterActivity,
                OTPVerification::class.java
            )
            intent.putExtra("mobile", getMobileTxt)
            intent.putExtra("email", getEmailTxt)
            startActivity(intent)
        }
        signInBtn.setOnClickListener { finish() }
    }
}