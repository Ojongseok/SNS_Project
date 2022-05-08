package Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.emailEditText
import kotlinx.android.synthetic.main.activity_sign_up.passEditText
import kotlinx.android.synthetic.main.activity_sign_up.checkButton
import org.techtown.sns_project.MainActivity
import org.techtown.sns_project.R

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize Firebase Auth
        auth = Firebase.auth

        checkButton.setOnClickListener {
            login()
        }
        passSendButton.setOnClickListener {
            val intent= Intent(this, PassRestActivity::class.java)
            startActivity(intent)
        }
    }

    fun login() {
        val email = emailEditText.text.toString()
        val password = passEditText.text.toString()

        if (email.length != 0 && password.length != 0) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(baseContext, "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show()
                        val user = auth.currentUser
                        val intent= Intent(this, MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        startActivity(intent)
                    } else {
                        Toast.makeText(baseContext, "로그인에 실패했습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
        } else {
            Toast.makeText(this, "이메일 또는 비밀번호 정보를 입력해주세요.", Toast.LENGTH_SHORT).show()
        }
    }
}