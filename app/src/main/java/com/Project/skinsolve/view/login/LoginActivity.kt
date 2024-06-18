import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.Project.skinsolve.R
import com.Project.skinsolve.view.register.RegisterActivity

class LoginActivity : AppCompatActivity() {
    private var passwordShowing = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val usernameET = findViewById<EditText>(R.id.usernameET)
        val passwordET = findViewById<EditText>(R.id.passwordET)
        val passwordIcon = findViewById<ImageView>(R.id.passwordIcon)
        val signUpBtn = findViewById<TextView>(R.id.signUpBtn)
        passwordIcon.setOnClickListener { // checking if password is showing or not
            if (passwordShowing) {
                passwordShowing = false
                passwordET.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                passwordIcon.setImageResource(R.drawable.password_show)
            } else {
                passwordShowing = true
                passwordET.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                passwordIcon.setImageResource(R.drawable.password_hide)
            }

            // move the cursor at last of the text
            passwordET.setSelection(passwordET.length())
        }
        signUpBtn.setOnClickListener {
            startActivity(
                Intent(
                    this@LoginActivity,
                    RegisterActivity::class.java
                )
            )
        }
    }
}
