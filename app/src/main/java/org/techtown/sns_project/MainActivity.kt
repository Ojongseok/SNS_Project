package org.techtown.sns_project

import LibraryInfo.LibraryActivity
import LibraryInfo.LibraryAdapter
import Login.MemberInfoActivity
import Login.SignUpActivity
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.activity_library.*
import kotlinx.android.synthetic.main.activity_main.*
import org.techtown.sns_project.camera2basic.CameraActivity

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user = Firebase.auth.currentUser
        if (user == null) {
            myStartActivity(SignUpActivity())
        } else {
            gotoCameraButton.setOnClickListener {
                startCamereActivity()
            }
            updateMemberButton.setOnClickListener {
                myStartActivity(MemberInfoActivity())
                Toast.makeText(this, "회원정보 업데이트가 완료되었습니다.", Toast.LENGTH_SHORT).show()
            }
            writingButton.setOnClickListener {
                myStartActivity(WritePostActivity())
            }
            chatButton.setOnClickListener {
                myStartActivity(ChatActivity())
            }
            gotoLibButton.setOnClickListener {
                myStartActivity(LibraryActivity())
            }


            val db = Firebase.firestore
            val refDb = db.collection("users").document(user.uid)

            refDb.get().addOnSuccessListener {
                if (it.exists()) {
                    Toast.makeText(this, "회원정보가 기존에 존재합니다.", Toast.LENGTH_SHORT).show()
                }
                else {
                    myStartActivity(MemberInfoActivity())
                }
            }.addOnFailureListener{
                Toast.makeText(this, "회원정보를 불러오는데 실패했습니다.", Toast.LENGTH_SHORT).show()
            }
        }

        logOut.setOnClickListener {
            Firebase.auth.signOut()
            myStartActivity(SignUpActivity())
        }
    }

    fun startCamereActivity() {
        val intent = Intent(this, CameraActivity::class.java)
        startActivity(intent)
    }
    fun myStartActivity(activity:Activity) {
        val intent = Intent(this, activity::class.java)
        startActivity(intent)
    }
}