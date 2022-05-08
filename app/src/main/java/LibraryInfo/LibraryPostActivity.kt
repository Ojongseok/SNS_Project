package LibraryInfo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_library_post.*
import kotlinx.android.synthetic.main.detail_dialog.*
import org.json.JSONObject
import org.techtown.sns_project.R

class LibraryPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library_post)

        postBackButton.setOnClickListener {
            finish()
        }

        val libName = intent.getStringExtra("name")
        libHeadText.text=libName

        imageButton.setOnClickListener {
            val dialog = CustomDialog(this)

            val assetManager = resources.assets
            val inputStream = assetManager.open("sample.json")
            val jsonString = inputStream.bufferedReader().use { it.readText() }
            val jObject = JSONObject(jsonString)
            val jArray = jObject.getJSONArray("row")

            for (i in 0 until jArray.length()) {
                val obj = jArray.getJSONObject(i)
                if (obj.getString("LBRRY_NAME").equals(libName)) {
                    Toast.makeText(this,obj.getString("LBRRY_NAME"),Toast.LENGTH_SHORT).show()
                    dialog.showDialog(
                        obj.getString("LBRRY_NAME"),
                        obj.getString("ADRES"),
                        obj.getString("TEL_NO"),
                        obj.getString("OP_TIME"),
                        obj.getString("FDRM_CLOSE_DATE"),
                        obj.getString("HMPG_URL")
                    )
                    break
                }
            }
        }

    }
}