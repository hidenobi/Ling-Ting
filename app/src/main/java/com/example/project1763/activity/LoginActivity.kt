package com.example.project1763.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.project1763.databinding.ActivityLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class LoginActivity : BaseActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateUI()
    }

    private fun updateUI() {
// Khởi tạo Firebase Auth
        auth = Firebase.auth

        // Khởi tạo views

        binding.apply {
            btnLogin.setOnClickListener {
                val email = etUsername.text.toString().trim()
                val password = etPassword.text.toString().trim()
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    loginUser(email, password)
                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        "Vui lòng nhập email và mật khẩu",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        }
    }

    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Đăng nhập thành công
                    Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    // Đăng nhập thất bại
                    Toast.makeText(
                        this,
                        "Đăng nhập thất bại: ${task.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}