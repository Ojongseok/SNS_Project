package org.techtown.sns_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity : AppCompatActivity() {
    val database = Firebase.database
    val myRef = database.getReference("message")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        myRef.setValue("Hello, World!")

        val layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        recyclerView.layoutManager=layoutManager

        val adapter=ChatAdapter()

        adapter.items.add(ChatDataInfo("안녕하세요","오종석"))

        recyclerView.adapter=adapter

    }
}