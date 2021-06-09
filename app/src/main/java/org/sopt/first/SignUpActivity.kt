package org.sopt.first

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import org.sopt.first.api.ServiceCreator
import org.sopt.first.data.SoptUserAuthStorage
import org.sopt.first.data.request.RequestJoinData
import org.sopt.first.data.response.ResponseJoinData
import org.sopt.first.databinding.ActivitySignUpBinding
import org.sopt.first.utils.enqueueResponseUtil
import org.sopt.first.utils.showToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
                val requestJoinData = RequestJoinData(
                    email = binding.etSignUpId.text.toString(),
                    password = binding.etSignUpPw.text.toString(),
                    sex = "0",
                    nickname = binding.etSignUpName.text.toString(),
                    phone = "010",
                    birth = "1111"
                )

                val call: Call<ResponseJoinData> = ServiceCreator.soptService
                    .postJoin(requestJoinData)

                fun startSignInActivity(){
                    val intent = Intent()
                    intent.putExtra("name", name.toString())
                    intent.putExtra("id", id.toString())
                    intent.putExtra("pw", password.toString())
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                }

                call.enqueueResponseUtil(
                    onSuccess = { response ->
                        val data = response.data
                        showToast(data?.nickname+"님 가입을 환영합니다.")
                        startSignInActivity()
                    } ,
                    onError = {
                        // 여긴 서버 통신 status가 200~300이 아닌 경우
                        showToast("다시 시도해주세요.")
                    }

                )

//                call.enqueue(object : Callback<ResponseJoinData> {
//                    override fun onResponse(
//                        call: Call<ResponseJoinData>,
//                        response: Response<ResponseJoinData>
//                    ) {
//                        if(response.isSuccessful){
//                            val data = response.body()?.data
//                            Toast.makeText(this@SignUpActivity, data?.nickname+"님 가입을 환영합니다.", Toast.LENGTH_SHORT)
//                                .show()
//                            startSignInActivity()
//                        } else{
//                            // 여긴 서버 통신 status가 200~300이 아닌 경우
//                            Toast.makeText(this@SignUpActivity, "다시 시도해주세요."+response.code(), Toast.LENGTH_SHORT)
//                                .show()
//                        }
//                    }

//                    override fun onFailure(call: Call<ResponseJoinData>, t: Throwable) {
//                        Log.d("NetworkTest", "error:$t")
//                    }
//                })
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