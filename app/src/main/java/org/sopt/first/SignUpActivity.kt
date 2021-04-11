package org.sopt.first

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import org.sopt.first.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initButtonClickEvent()

        Log.d("Log_SignUp", "onCreate")
    }

    private fun initButtonClickEvent(){
        var id = binding.etSignUpId.text
        var name = binding.etSignUpName.text
        var password = binding.etSignUpPw.text

        binding.btnSignUp.setOnClickListener{
            if (name.isEmpty() || id.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "빈 칸이 있는지 확인해주세요", Toast.LENGTH_SHORT).show()
            }
            else{
                val intent = Intent()
                intent.putExtra("name", name.toString())
                intent.putExtra("id", id.toString())
                intent.putExtra("pw", password.toString())
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("Log_SignUp", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Log_SignUp", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Log_SignUp", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Log_SignUp", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Log_SignUp", "onDestroy")
    }
}