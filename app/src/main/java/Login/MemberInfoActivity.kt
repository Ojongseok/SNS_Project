package Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_member_info.*
import kotlinx.android.synthetic.main.activity_sign_up.checkButton
import org.techtown.sns_project.R
import org.techtown.sns_project.camera2basic.CameraActivity

class MemberInfoActivity : AppCompatActivity() {
    private val PICK_IMAGE = 10001
    private val user = Firebase.auth.currentUser
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member_info)

        checkButton.setOnClickListener {
            profileUpdate()
        }
        profileImageView.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            startActivityForResult(intent,0)

        }
        photoButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT).apply { type = "image/*" }
            intent.setType("image/*")
            startActivityForResult(intent, PICK_IMAGE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode==PICK_IMAGE) {
            val currentImageUri = data?.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, currentImageUri)
            profileImageView.setImageBitmap(bitmap)
        }
    }

    fun profileUpdate() {
        val name = nameEditText.text.toString()
        val phone = phoneEditText.text.toString()
        val date = dateEditText.text.toString()
        val address = addressEditText.text.toString()

        if (name.length != 0&&phone.length>10&&date.length>5&&address.length>0) {

            val memberInfo = MemberInfoData(name,phone,date,address)
            db.collection("users").document(user!!.uid).set(memberInfo)
                .addOnSuccessListener { documentReference ->
                    Toast.makeText(this, "회원정보 등록이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                    finish()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "회원정보 등록에 실패했습니다.", Toast.LENGTH_SHORT).show()
                }

        } else {
            Toast.makeText(this, "회원 정보를 입력해주세요.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }

}