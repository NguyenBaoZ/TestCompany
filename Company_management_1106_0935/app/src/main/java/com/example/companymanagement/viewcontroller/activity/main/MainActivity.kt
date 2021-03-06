package com.example.companymanagement.viewcontroller.activity.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.companymanagement.R
import com.example.companymanagement.viewcontroller.activity.login.LoginActivity
import com.example.companymanagement.viewcontroller.fragment.usertask.Task_Dialog
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    var auth: FirebaseAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide();
        setContentView(R.layout.activity_main)
        auth.addAuthStateListener { p0: FirebaseAuth ->
            Log.d("User", p0.currentUser.toString());
            if (p0.currentUser == null) {
                val usernull = Intent(this, LoginActivity::class.java)
                startActivity(usernull)
            }
        };
    }
    fun openDialog(view: View){
        var task_dialog : Task_Dialog = Task_Dialog()
        task_dialog.show(supportFragmentManager,"testTaskDialog")
    }
}