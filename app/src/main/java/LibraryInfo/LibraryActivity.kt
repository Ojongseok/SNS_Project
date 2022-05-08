package LibraryInfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_library.*
import org.json.JSONObject
import org.techtown.sns_project.R

class LibraryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library)

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        libRecycler.layoutManager = layoutManager
        val adapter= LibraryAdapter()

        val assetManager = resources.assets
        val inputStream = assetManager.open("sample.json")
        val jsonString = inputStream.bufferedReader().use { it.readText() }
        val jObject = JSONObject(jsonString)
        val jArray = jObject.getJSONArray("row")

        for (i in 0 until jArray.length()) {
            val obj = jArray.getJSONObject(i)
            val name = obj.getString("LBRRY_NAME")
            val time = obj.getString("FDRM_CLOSE_DATE")
            val address = obj.getString("ADRES")

            adapter.items.add(LibraryDataInfo(name,address,time))
        }

        libRecycler.adapter=adapter

        libSearchButton.setOnClickListener {
            val adapter= LibraryAdapter()

            val searchStr = libSearch.text

            for (i in 0 until jArray.length()) {
                val obj = jArray.getJSONObject(i)
                val addName = obj.getString("ADRES")
                if (addName.contains(searchStr)){
                    val obj = jArray.getJSONObject(i)
                    val name = obj.getString("LBRRY_NAME")
                    val time = obj.getString("FDRM_CLOSE_DATE")
                    val address = obj.getString("ADRES")

                    adapter.items.add(LibraryDataInfo(name,address,time))
                }

            }
            libRecycler.adapter=adapter
        }



    }
}