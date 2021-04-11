# 김기현 과제 1
</br>


## Level 1

### 화면 전환 후 데이터를 가져오는 로직
SignUpActivity 에서 SignInActivity로 이동할 경우 SignUpActivity의 id, pw 데이터를 SignInActivity로 넘겨줌

``` Kotlin
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
```

``` Kotlin
    private val signUpActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        binding.etId.setText(it.data?.getStringExtra("id"))
        binding.etPw.setText(it.data?.getStringExtra("pw"))
    }
```

<img src="https://user-images.githubusercontent.com/59547069/114302705-243c3780-9b05-11eb-9d81-d5f6d5d0da08.png" width="50%" height="50%">

### 생명주기 로그 띄우기
<img src="https://user-images.githubusercontent.com/59547069/114302620-ca3b7200-9b04-11eb-8cfb-3e9c6241d6a5.png" width="50%" height="50%">

+ __안드로이드의 생명주기__ 

<img src="https://user-images.githubusercontent.com/59547069/114308215-0d560f00-9b1e-11eb-9185-873f00d9fa5a.png" width="40%" height="40%">

------------
## Level 2

### 변수 이름 체크
위젯명을 먼저 쓰고 뒤에서 무슨 역할을 하는 위젯인지 설명하는 방식으로 변수의 이름을 설정함

-  SignInActivity

        - cl_login_id : id를 입력하는 constraint layout
        - tv_id : id 텍스트
        - et_id : id를 입력받는 부분
        - cl_login_pw : pw를 입력하는 constraint layout
        - tv_pw : pw 텍스트
        - et_pw : pw를 입력받는 부분
        - btn_login : 로그인 버튼
        - tv_no_id : 아이디와 비밀번호가 없는지 말하는 텍스트
        - tv_sign_up : 회원가입 뷰 (SignUpActivity)로 이동하는 버튼 텍스트뷰

-  SignUpActivity

        - cl_sign_up_name : name를 입력하는 constraint layout
        - tv_sign_up_name : name 텍스트
        - et_sign_up_name : name를 입력받는 부분
        - cl_sign_up_id : id를 입력하는 constraint layout
        - tv_sign_up_id : id 텍스트
        - et_sign_up_id : id를 입력받는 부분
        - cl_sign_up_pw : pw를 입력하는 constraint layout
        - tv_sign_up_pw : pw 텍스트
        - et_sign_up_pw : pw를 입력받는 부분
        - btn_sign_up : 회원가입을 완료하는 버튼


-  HomeActivity

        - guideline/guideline2 : 레이아웃을 위한 가이드라인
        - cl_profile : 프로필이 들어가는 constraint layout
        - img_home_profile : 프로필 사진이 보여주는 이미지뷰
        - tv_home_profile_id : 프로필 아이디가 보여주는 텍스트뷰
        - tv_home_profile_name : 프로필 이름을 보여주는 텍스트뷰
        - tv_home_profile_intro : 프로필 자기소개를 보여주는 텍스트뷰

### Guideline 사용
세로로 0.05%, 0.95%의 위치에 가이드라인을 만들어 사용
</br>
<img src="https://user-images.githubusercontent.com/59547069/114306610-4343c500-9b17-11eb-868d-0b45a37a3bdb.png" width="50%" height="50%">


### 스크롤뷰 사용
자기소개 내용이 길어질 경우 스크롤해서 내용을 볼 수 있도록 스크롤뷰 사용
</br>
<img src="https://user-images.githubusercontent.com/59547069/114306667-75552700-9b17-11eb-95fc-b6a20fcc8163.png" width="50%" height="50%">


## 이번 과제를 통해 배운 내용
화면을 전환하고 데이터를 가져올 때 registerForActivityResult를 이용해보고 배우게 되었다.
그리고 이에 대해서 더 알고 싶어 찾아보았는데

기존에는 StartActivityForResult로 결과를 가져올 액티비티를 실행하고, 어떤 액티비티를 실행했는지에 상관 없이 반드시 onActivityResult Callback에서 결과를 처리해야 했다.

이렇게 되면 한 콜백에서 매우 많은 분기 처리가 필요하여 좋지 않게 된다. 그래서 registerForActivityResult, Launcher를 쓰게 되면 콜백이 모두 분리되어서 매핑하는 로직이 사라져 좋다고 한다.
