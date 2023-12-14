package com.example.sssloginactivity

class User(private var name: String, private var id:String, private var password: String) :UserData(name, id, password) {
    override val username:String = name
    override val userid:String = id
    override val userpw:String = password
}

abstract class UserData(name:String, id:String, password:String) {
    abstract val username:String
    abstract val userid:String
    abstract val userpw:String
}

class DataList {
    companion object {
        // companion object를 사용하면 전역변수처럼 활용할 수 있음.
        var datalist = mutableListOf<User>()
    }
}
