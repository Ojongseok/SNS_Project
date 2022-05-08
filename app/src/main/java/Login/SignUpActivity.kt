package Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.techtown.sns_project.R

class SignUpActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // Initialize Firebase Auth
        auth = Firebase.auth

        checkButton.setOnClickListener {
            signUp()
        }
        gotoLoginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onBackPressed() {
        //super.onBackPressed() // 빽버튼 막기
        finishAffinity()  // 강하게 강종
    }

    fun signUp() {
        val email = emailEditText.text.toString()
        val password = passEditText.text.toString()
        val passwordCheck = passEditTextCheck.text.toString()

        if (email.length!=0&&password.length!=0&&passwordCheck.length!=0) {
            if (password.equals(passwordCheck)) {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val user = auth.currentUser
                            Toast.makeText(this, "회원가입을 성공했습니다.", Toast.LENGTH_SHORT).show()
                        } else {
                            if (task != null) {
                                Toast.makeText(this, "이메일 정보가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
            } else {
                Toast.makeText(this, "비밀번호 정보가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()

            }
        } else {
            Toast.makeText(this, "이메일 또는 비밀번호 정보를 입력해주세요.", Toast.LENGTH_SHORT).show()
        }
    }
    private fun reload() {
    }


}