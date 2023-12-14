package com.example.sssloginactivity

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random
import kotlin.random.nextInt

class HomeActivity : AppCompatActivity() {
    val iconlist = listOf<String>("angrybirds", "avatar", "cat", "character", "duck", "ghost", "jigglypuff", "target")

    lateinit var myIcon:ImageView
    lateinit var main:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val myId:TextView = findViewById(R.id.login_id)
        val myName:TextView = findViewById(R.id.login_name)
        val myAge:TextView = findViewById(R.id.login_age)
        val myMbti:TextView = findViewById(R.id.login_mbti)

        myIcon = findViewById(R.id.login_icon)

        val name = intent.getStringExtra("Name")
        val id = intent.getStringExtra("id")

        val random = Random.nextInt(iconlist.indices)
        setImg(iconlist[random])

        val printid = "${resources.getString(R.string.id)} : $id"
        val printname = "${resources.getString(R.string.name)} : $name"
        val printage = "${resources.getString(R.string.age)} : ${randomage()}"
        val printmbti = "${resources.getString(R.string.mbti)} : ${randommbti()}"

        myId.text = printid
        myName.text = printname
        myAge.text = printage
        myMbti.text = printmbti

        main = findViewById(R.id.btn_backToSignin)
        main.setOnClickListener {
            finish()
        }
    }

    private fun setImg(img:String) {        // 이미지 랜덤으로 설정
        val imgResource = resources.getIdentifier("${img}_03", "drawable", packageName)
        myIcon.setImageResource(imgResource)
    }

    private fun randomage():Int {           // 나이 랜덤
        return Random.nextInt(1..99)
    }

    private fun randommbti():String {       // mbti 랜덤
        var mbti = ""
        val resultType = listOf(
            listOf("E", "I"),
            listOf("N", "S"),
            listOf("T", "F"),
            listOf("J", "P")
        )
        for (i in resultType.indices) {
            val random = Random.nextInt(0..1)
            mbti += resultType[i][random]
        }
        return mbti
    }
}