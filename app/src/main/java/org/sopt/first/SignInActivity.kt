package org.sopt.first

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.android.synthetic.main.activity_sign_in.view.*
import org.sopt.first.api.ServiceCreator
import org.sopt.first.data.request.RequestLoginData
import org.sopt.first.data.response.ResponseLoginData
import org.sopt.first.databinding.ActivitySignInBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

                val requestLoginData = RequestLoginData(
                    email = binding.etId.text.toString(),
                    password = binding.etPw.text.toString()
                )

                val call: Call<ResponseLoginData> = ServiceCreator.soptService
                    .postLogin(requestLoginData)

                call.enqueue(object : Callback<ResponseLoginData>{
                    override fun onResponse(
                        call: Call<ResponseLoginData>,
                        response: Response<ResponseLoginData>
                    ) {
                        if(response.isSuccessful){
                            val data = response.body()?.data
                            Toast.makeText(this@SignInActivity, data?.user_nickname, Toast.LENGTH_SHORT)
                                .show()
                            startHomeActivity()
                        } else{
                            // 여긴 서버 통신 status가 200~300이 아닌 경우
                            Toast.makeText(this@SignInActivity, "아이디와 비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }

                    override fun onFailure(call: Call<ResponseLoginData>, t: Throwable) {
                        Log.d("NetworkTest", "error:$t")
                    }
                })
            }
        }

        // 회원가입 버튼 눌렀을 때
        binding.tvSignUp.setOnClickListener {
            val intent = Intent(this@SignInActivity,
                SignUpActivity::class.java)
            signUpActivityLauncher.launch(intent)
        }

    }

    private fun startHomeActivity(){
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
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