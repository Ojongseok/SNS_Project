package Login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_password_reset.*
import kotlinx.android.synthetic.main.activity_sign_up.emailEditText
import org.techtown.sns_project.R

class PassRestActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_reset)

        // Initialize Firebase Auth
        auth = Firebase.auth

        sendButton.setOnClickListener {
            send()
        }
    }

    fun send() {
        val email = emailEditText.text.toString()

        if (email.length != 0) {
            auth.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "이메일을 보냈습니다.", Toast.LENGTH_SHORT).show()

                        finish()
                    }
                }
        } else {
            Toast.makeText(this, "이메일 또는 비밀번호 정보를 입력해주세요.", Toast.LENGTH_SHORT).show()
        }
    }
}