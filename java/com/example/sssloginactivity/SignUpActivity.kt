package com.example.sssloginactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.sssloginactivity.DataList.Companion.datalist

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val name = findViewById<EditText>(R.id.account_name)
        val id = findViewById<EditText>(R.id.account_id)
        val pw = findViewById<EditText>(R.id.account_password)
        val btn = findViewById<Button>(R.id.signup_button)

        btn.setOnClickListener {
            if(name.text.toString().isNotEmpty() && id.text.toString().isNotEmpty() && pw.text.toString().isNotEmpty()) {
                if (!datalist.any{ it.userid == id.text.toString() || it.username == name.text.toString() }) {
                    userdata(name.text.toString(), id.text.toString(), pw.text.toString())
                    val i = Intent(this, SignInActivity::class.java)
                    i.putExtra("Name",name.text.toString())
                    i.putExtra("ID", id.text.toString())
                    i.putExtra("Password", pw.text.toString())
                    setResult(RESULT_OK,i)      // Result 설정 = RESULT_OK = 성공 반환, Intent 데이터 전달(startActivity가 아니라서 창이 열리지 않음)
                    toast("회원가입이 완료되었습니다.")
                    finish()                    // 창 닫기
                } else {
                    toast("이미 등록된 정보가 존재합니다.")
                }
            } else {
                toast("입력되지 않은 정보가 있습니다.")
            }
        }
    }

    fun toast(string:String) {
        Toast.makeText(this,string, Toast.LENGTH_SHORT).show()
    }

    fun userdata(name:String,id:String,pw:String) {
        datalist.add(User(name, id, pw))
    }
}
