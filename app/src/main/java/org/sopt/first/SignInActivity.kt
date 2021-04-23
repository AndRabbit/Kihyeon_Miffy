package org.sopt.first

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import org.sopt.first.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySignInBinding

    private val signUpActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        binding.etId.setText(it.data?.getStringExtra("id"))
        binding.etPw.setText(it.data?.getStringExtra("pw"))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initButtonClickEvent()

        Log.d("Log_SignIn", "onCreate")
    }

    private fun initButtonClickEvent(){
        binding.btnLogin.setOnClickListener{
            val userId = binding.etId.text
            
            // id가 비어있다면
            if(userId.isNullOrBlank()){
                Toast.makeText(
                    this@SignInActivity,
                    "Id를 입력해주세요", 
                    Toast.LENGTH_SHORT
                ).show()    
            }else{
                Toast.makeText(
                    this@SignInActivity,
                    "안녕하세요",
                    Toast.LENGTH_SHORT
                ).show()

                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
        }

        binding.tvSignUp.setOnClickListener {
            val intent = Intent(this@SignInActivity,
                SignUpActivity::class.java)
            signUpActivityLauncher.launch(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("Log_SignIn", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Log_SignIn", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Log_SignIn", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Log_SignIn", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Log_SignIn", "onDestroy")
    }
}