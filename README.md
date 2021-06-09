# 김기현 과제 7
</br>

## 📌 수행 과제

- level 1

# Level 1

---
<p align="center"><img src="https://user-images.githubusercontent.com/59547069/121373153-cea3d000-c979-11eb-94a5-d17b22668d67.gif" width="30%" height="30%"></p>
### ✅ 자동 로그인 구현하기

- 처음 로그인을 할 때 성공했다면 SharedPreference에 ID와 PW를 저장한다.

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

- 이후 로그인을 할 때 SharedPreference에 ID와 PW가 있다면 로그인 과정을 건너뛰고 HomeActivity로 이동한다.

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

- 서비스에서 로그아웃 버튼을 클릭하면 SharedPreference를 clear하고 SignInActivity로 이동한다.

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