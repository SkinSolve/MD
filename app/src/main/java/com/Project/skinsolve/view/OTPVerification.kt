package com.Project.skinsolve.view

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.Project.skinsolve.R

class OTPVerification : AppCompatActivity() {

    private val textWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable) {
            if (s.length > 0) {
                when (selectedETPosition) {
                    0 -> {
                        selectedETPosition = 1
                        otpEt2?.let { showKeyboard(it) }
                    }
                    1 -> {
                        selectedETPosition = 2
                        otpEt3?.let { showKeyboard(it) }
                    }
                    2 -> {
                        selectedETPosition = 3
                        otpEt4?.let { showKeyboard(it) }
                    }
                }
            }
        }
    }

    private var otpEt1: EditText? = null
    private var otpEt2: EditText? = null
    private var otpEt3: EditText? = null
    private var otpEt4: EditText? = null
    private var resendBtn: TextView? = null
    private var resendEnabled = false
    private val resendTime = 60
    private var selectedETPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_o_t_p_verification)

        otpEt1 = findViewById(R.id.otpET1)
        otpEt2 = findViewById(R.id.otpET2)
        otpEt3 = findViewById(R.id.otpET3)
        otpEt4 = findViewById(R.id.otpET4)
        resendBtn = findViewById(R.id.resendBtn)
        val verifyBtn = findViewById<Button>(R.id.verifyBtn)
        val otpEmail = findViewById<TextView>(R.id.otpEmail)
        val otpMobile = findViewById<TextView>(R.id.otpMobile)

        val getEmail = intent.getStringExtra("email")
        val getMobile = intent.getStringExtra("mobile")

        otpEmail.text = getEmail
        otpMobile.text = getMobile

        otpEt1?.addTextChangedListener(textWatcher)
        otpEt2?.addTextChangedListener(textWatcher)
        otpEt3?.addTextChangedListener(textWatcher)
        otpEt4?.addTextChangedListener(textWatcher)

        showKeyboard(otpEt1)

        startCountDownTimer()

        resendBtn?.setOnClickListener {
            if (resendEnabled) {
                // tangani kode kirim ulang di sini
                startCountDownTimer()
            }
        }

        verifyBtn.setOnClickListener {
            val generateOtp = otpEt1?.text.toString() + otpEt2?.text.toString() + otpEt3?.text.toString() + otpEt4?.text.toString()
            if (generateOtp.length == 4) {
                // tangani verifikasi OTP di sini
            }
        }
    }

    private fun showKeyboard(otpET: EditText?) {
        otpET?.requestFocus()
        val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(otpET, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun startCountDownTimer() {
        resendEnabled = false
        resendBtn?.setTextColor(Color.parseColor("#99000000"))

        object : CountDownTimer((resendTime * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                resendBtn?.text = "Resend Code (${millisUntilFinished / 1000})"
            }

            override fun onFinish() {
                resendEnabled = true
                resendBtn?.text = "Resend Code"
                resendBtn?.setTextColor(resources.getColor(R.color.birdonk))
            }
        }.start()
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_DEL) {
            when (selectedETPosition) {
                3 -> {
                    selectedETPosition = 2
                    otpEt3?.let { showKeyboard(it) }
                }
                2 -> {
                    selectedETPosition = 1
                    otpEt2?.let { showKeyboard(it) }
                }
                1 -> {
                    selectedETPosition = 0
                    otpEt1?.let { showKeyboard(it) }
                }
            }
            return true
        } else {
            return super.onKeyUp(keyCode, event)
        }
    }
}
