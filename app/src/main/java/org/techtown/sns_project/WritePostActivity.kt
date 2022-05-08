package org.techtown.sns_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_member_info.*
import kotlinx.android.synthetic.main.activity_write_post.*

class WritePostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_post)

        postOkButton.setOnClickListener {
            uploadPost()
        }
    }

    fun uploadPost() {
        val title = input1.text.toString()
        val contents = input2.text.toString()
        val publisher = Firebase.auth.currentUser!!.uid

        if (title.length>0&&contents.length>0) {
            val user = Firebase.auth.currentUser
            val db = Firebase.firestore
            val memberInfo = WriteInfo(title, contents,publisher)

            db.collection("posts")
                .add(memberInfo)
                .addOnSuccessListener { documentReference ->
                    Toast.makeText(this, "게시글 작성이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                    finish()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "게시글 작성에 실패했습니다.", Toast.LENGTH_SHORT).show()
                }

        } else {
            Toast.makeText(this, "게시글을 입력해주세요.", Toast.LENGTH_SHORT).show()
        }
    }
}