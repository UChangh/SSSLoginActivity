package com.example.sssloginactivity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.sssloginactivity.DataList.Companion.datalist
import kotlin.random.Random
import kotlin.random.nextInt

class SignInActivity : AppCompatActivity() {
    lateinit var id_text:EditText
    lateinit var pw_text:EditText
    lateinit var icon:ImageView

    val iconlist = listOf("aircraft","bomb","bomber","plane","rain","warfare")
    var random = 0
    var name = ""
    var id = ""
    var pw = ""

    val startForResult = registerForActivityResult(     // 결과를 반환하는 코드?? (중요!!!)
        ActivityResultContracts.StartActivityForResult()
    ) { it:ActivityResult ->
        if(it.resultCode == RESULT_OK) {
            name = it.data?.getStringExtra("Name")!!
            id = it.data?.getStringExtra("ID")!!
            pw = it.data?.getStringExtra("Password")!!
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        id_text = findViewById(R.id.et_id)
        pw_text = findViewById(R.id.et_pwd)
        icon = findViewById(R.id.my_icon)

        datalist.add(User("admin","root","1234"))
    }

    override fun onResume() {
        super.onResume()
        random = Random.nextInt(iconlist.indices)
        setImg(iconlist[random])    // 이미지를 아무때나 생성하고 싶을 경우 -> Log를 보고 작동되는 타이밍에 넣기

        id_text.setText(id)         // 메인 페이지로 돌아올 때마다 실행
        pw_text.setText(pw)
    }

    fun btnClicked(view:View) {     // 버튼 클릭 처리
        when(view.id) {
            R.id.login_btn -> {
                val i = Intent(this,HomeActivity::class.java)
                if(id_text.text.toString().isNotEmpty() && pw_text.text.toString().isNotEmpty()) {
                    if(id_text.text.toString() == datalist[0].userid && pw_text.text.toString() == datalist[0].userpw) {
                        i.putExtra("Name", datalist[0].username)
                        i.putExtra("id", datalist[0].userid)
                        startActivity(i)
                        toast("Admin 계정 로그인 성공!")
                    } else if(datalist.any{ it.userid == id_text.text.toString() && it.userpw == pw_text.text.toString() && it.username.indexOf(it.userid) == it.username.indexOf(it.userpw)} ) {
                        i.putExtra("Name", datalist[datalist.indexOf(datalist.filter{ it -> it.userid == id_text.text.toString()}.first())].username)
                        // datalist.filter => 유저 아이디와 입력된 아이디를 비교해 존재하는 아이디면 list에 넣고 first로 첫번째 원소를 반환
                        // datalist.indexOf() => 반환받은 원소값의 인덱스를 찾음
                        // datalist[].username => 받은 인덱스 값으로 username을 찾음
                        i.putExtra("id", id_text.text.toString())
                        startActivity(i)
                        toast("로그인 성공!")
                    } else {
                        toast("아이디와 비밀번호를 확인해주세요.")
                    }
                } else {
                    toast("입력되지 않은 칸이 존재합니다.")
                }
            }
            R.id.sign_btn -> {
                val i = Intent(this,SignUpActivity::class.java)
                startForResult.launch(i)        // 갔다가 돌아오기
            }
        }
    }

    fun toast(string:String) {
        Toast.makeText(this,string,Toast.LENGTH_SHORT).show()
    }

    fun setImg(img:String) {
        val imgResource = resources.getIdentifier("${img}_01", "drawable",packageName)
        icon.setImageResource(imgResource)
    }
}