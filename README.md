# 김기현 과제 1
</br>

## 📌 수행 과제

- level 1
- level 2
- level 3

</br>

## Level 1

### 화면 전환 후 데이터를 가져오는 로직
SignUpActivity 에서 SignInActivity로 이동할 경우 SignUpActivity의 id, pw 데이터를 SignInActivity로 넘겨줌
- SignUpActivity에서 버튼을 누를 경우

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
- SignInActivity에서 데이터를 가져오는 부분

        - registerForActivityResult를 이용한다
``` Kotlin
    private val signUpActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        binding.etId.setText(it.data?.getStringExtra("id"))
        binding.etPw.setText(it.data?.getStringExtra("pw"))
    }
```

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

------------
## Level 3


### ViewBinding 이름의 뜻이 뭘까요?

📌 **뷰 바인딩**

- 레이아웃 XML 파일에 정의되어 있는 뷰를 자동으로 생성된 클래스를 통해 자바/코틀린 코드에서 참조할 수 있게 해주며, 코틀린 안드로이드 익스텐션에서 뷰 접근을 위해 제공하는 합성 프로퍼티 (Synthetic property)와 거의 동일한 기능을 제공한다.

- 뷰와 상호 작용하는 코드를 보다 쉽게 작성할 수 있는 기능이다.
- 모듈의 build.gradle에서 뷰 바인딩 속성이 활성화되면 해당 모듈에있는 각 XML 레이아웃 파일에 대한 바인딩 클래스가 자동으로 생성된다.
- 바인딩 클래스 인스턴스에는 해당 레이아웃에 ID가 있는 모든 뷰에 대해 직접적으로 참조된다.
- 대부분 뷰 바인딩을 사용하는 것으로 findViewById 메서드를 대체할 수 있다.

➡️ 자연스레 액티비티에는 로직만을 위한 코드만 남게 되고 뷰와 관련된 작업은 레이아웃 파일에 정의된다.

📌 **데이터 바인딩**

**데이터와 뷰를 연결하는 작업을 레이아웃에 처리**하는 기술

📌  **뷰 바인딩 vs 데이터 바인딩**

- 둘다 뷰를 직접 참조하는 바인딩 클래스를 생성한다.
- 데이터 바인딩 라이브러리는 <layout> 태그를 사용하여 만든 레이아웃만 처리한다.
- 뷰 바인딩은 레이아웃 변수 또는 레이아웃 표현식을 지원하지 않으므로 XML의 데이터와 레이아웃을 바인딩 하는데 사용할 수 없다.
- 내부적으로 데이터 바인딩 클래스를 생성할 때 루트 뷰에 tag를 삽입하는데 뷰 바인딩은 그런 작업이 없다.
- 뷰 바인딩은 데이터 바인딩보다 어노테이션 프로세싱의 일부를 사용하기 때문에 더 빠르게 바인딩 클래스를 생성한다.

```kotlin
// findViewById
textView.text = "kh" 
 
// 뷰 바인딩
binding.textView.text = "kh"

// 데이터 바인딩
<TextView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:text="@{user.name}" />
```

### 객체지향 어느정도 다뤄보셨나요?

- 코틀린도 객체지향 언어이다.
- **MainActivity**는 **AppCompatActivity**의 하위클래스이므로 **Activity** 또한 상속받는다.
- Activity는 안드로이드의 핵심 클래스로, 안드로이드 앱 UI를 그리거나 이벤트들을 입력받는 역할을 한다.
- 모든 액티비티는 그와 연결된 레이아웃 파일을 가지며, 레이아웃 파일은 res에 있는 XML파일이다. (ex. activity_main.xml)
- **MainActivity**에 있는 **setContentView()**는 레이아웃과 액티비티를 연결하고, 해당 레이아웃을 액티비티가 만들어질 때 실제 객체로 만든다.
- 이 과정을 통해 **Activity**는 객체들을 스크린에 그리고, 변형하는 작업을 할 수 있다.

### 아키텍쳐라고 들어보셨나요?

📌  **MVC**

- **Control** : 사용자의 입력을 받는다. (예. OnClickListener)
- **View** : Control로부터 알림을 받고, 이를 Model과 함께 사용자의 화면을 구성한다.
- **Model** : Data와 관련된 처리를 담당

📌  **Activity / Fragment**

- View / Control 역할을 함께 한다.
- ex) OnClickListener
- 하나의 화면 안에서 Control인 setOnClickListener이 발생하고, 이를 View에서 모두 처리하는 형태이다.

- 서버의 MVC는 View와 Control 따로 분리되어 있지만 안드로이드의 MVC는  View와 Control이 액티비티(Activity) / 프래그먼트(Fragment) 같은 View에 관련된 녀석들이 모두 가지고 있다.

        
</br>
        
## 이번 과제를 통해 배운 내용
화면을 전환하고 데이터를 가져올 때 registerForActivityResult를 이용해보고 배우게 되었다.
그리고 이에 대해서 더 알고 싶어 찾아보았는데

기존에는 StartActivityForResult로 결과를 가져올 액티비티를 실행하고, 어떤 액티비티를 실행했는지에 상관 없이 반드시 onActivityResult Callback에서 결과를 처리해야 했다.

이렇게 되면 한 콜백에서 매우 많은 분기 처리가 필요하여 좋지 않게 된다. 그래서 registerForActivityResult, Launcher를 쓰게 되면 콜백이 모두 분리되어서 매핑하는 로직이 사라져 좋다고 한다.
