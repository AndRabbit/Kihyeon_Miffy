# 김기현 과제 7
</br>

## 📌 수행 과제

- level 1
- level 2

# Level 1

---
<p align="center"><img src="https://user-images.githubusercontent.com/59547069/121373153-cea3d000-c979-11eb-94a5-d17b22668d67.gif" width="30%" height="30%"></p>

### ✅ 자동 로그인 구현하기

#### - 처음 로그인을 할 때 성공했다면 SharedPreference에 ID와 PW를 저장한다.

```kotlin
**SignInActivity.kt**
// SharedPreference에 ID와 PW가 있는지 찾는 코드
// 만약 없다면 SharedPreference에 ID와 PW를 저장한다.
private fun searchUserAuthStorage(){
        if ( !SoptUserAuthStorage.hasUserData(this) ){
            with(binding){
                SoptUserAuthStorage.saveUserId(this@SignInActivity, etId.text.toString())
                SoptUserAuthStorage.saveUserPw(this@SignInActivity, etPw.text.toString())
            }
        }
        else {
            startHomeActivity()
        }
    }

**SoptUserAuthStorage.kt**
fun saveUserId(context: Context, id: String){
        val sharedPreferences = context.getSharedPreferences(
            "${context.packageName}.$STORAGE_KEY",
            Context.MODE_PRIVATE
        )
        sharedPreferences.edit()
            .putString(USER_ID, id)
            .apply()
    }

    fun saveUserPw(context: Context, pw: String){
        val sharedPreferences = context.getSharedPreferences(
            "${context.packageName}.$STORAGE_KEY",
            Context.MODE_PRIVATE
        )
        sharedPreferences.edit()
            .putString(USER_PW, pw)
            .apply()
    }
```

#### - 이후 로그인을 할 때 SharedPreference에 ID와 PW가 있다면 로그인 과정을 건너뛰고 HomeActivity로 이동한다.

```kotlin
**SignInActivity.kt**
// SharedPreference에 ID와 PW가 있는지 찾는 코드
// 만약 있다면 바로 HomeActivity로 이동한다.
private fun searchUserAuthStorage(){
        if ( !SoptUserAuthStorage.hasUserData(this) ){
            with(binding){
                SoptUserAuthStorage.saveUserId(this@SignInActivity, etId.text.toString())
                SoptUserAuthStorage.saveUserPw(this@SignInActivity, etPw.text.toString())
            }
        }
        else {
            startHomeActivity()
        }
    }

**SoptUserAuthStorage.kt**
fun hasUserData(context: Context) : Boolean {
        val sharedPreferences = context.getSharedPreferences(
            "${context.packageName}.$STORAGE_KEY",
            Context.MODE_PRIVATE
        )
        return !sharedPreferences.getString(USER_ID, "").isNullOrBlank() &&
                !sharedPreferences.getString(USER_PW, "").isNullOrBlank()
    }
```

#### - 서비스에서 로그아웃 버튼을 클릭하면 SharedPreference를 clear하고 SignInActivity로 이동한다.

```kotlin
**HomeActivity.kt**
// 로그아웃 버튼을 누르면 SharedPreference의 모든 값을 지워줌
binding.btnLogout.setOnClickListener{
            SoptUserAuthStorage.clearAuthStorage(this)
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }

**SoptUserAuthStorage.kt**
fun clearAuthStorage(context: Context) {
        val sharedPreferences = context.getSharedPreferences(
            "${context.packageName}.$STORAGE_KEY",
            Context.MODE_PRIVATE
        )
        sharedPreferences.edit()
            .clear()
            .apply()
    }
```


# Level 2

---

### ✅ 확장 함수 사용하기

#### - 서버와 연결하고 Response를 받아오는 부분을 확장 함수로 구현
#### - SignInActivity에서 로그인을 시도할 때 사용
#### - SignUpActivity에서 회원가입을 시도할 때 사용

```kotlin
**SignInActivity.kt**
call.enqueueResponseUtil(
                onSuccess = { response ->
                    val data = response.data

                    showToast(data?.user_nickname.toString())
                    if (!SoptUserAuthStorage.hasUserData(this@SignInActivity)){
                        SoptUserAuthStorage.saveUserId(this@SignInActivity, requestLoginData.email)
                        SoptUserAuthStorage.saveUserPw(this@SignInActivity, requestLoginData.password)
                    }

                    startHomeActivity()
                    showToast("안녕하세요")
                } ,
                onError = {
                    // 여긴 서버 통신 status가 200~300이 아닌 경우
                    showToast("아이디와 비밀번호가 틀렸습니다.")
                }

            )

**SignUpActivity.kt**
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

**Util/EnqueueResponseUtil.kt**
fun <ResponseType> Call<ResponseType>.enqueueResponseUtil(
    onSuccess: (ResponseType) -> Unit,
    onError: ((stateCode: Int) -> Unit)? = null
) {
    this.enqueue(object : Callback<ResponseType> {
        override fun onResponse(call: Call<ResponseType>, response: Response<ResponseType>) {
            if (response.isSuccessful) {
                onSuccess.invoke(response.body() ?: return)
            } else {
                onError?.invoke(response.code()?: return)
            }
        }

        override fun onFailure(call: Call<ResponseType>, t: Throwable) {
            Log.d("NetworkTest", "error:$t")
        }
    })
}
```
