# 김기현 과제 4
</br>


## 📌 수행 과제

- level 1

# Level 1

<img src="https://user-images.githubusercontent.com/59547069/118366439-35cc9f80-b5db-11eb-98ce-2b3ba5683b4c.gif" width="40%" height="40%">

### ✅ 로그인, 회원가입 통신 구현하기

- 로그인 / 회원가입 API를 참고하여  구현
- 서버에 post를 보내는 함수 구현 ( postLogin(), postJoin() )

```kotlin
@POST("/login/signin")
fun postLogin(
    @Body body: RequestLoginData
) : Call<ResponseLoginData>

@POST("/login/signup")
fun postJoin(
    @Body body: RequestJoinData
) : Call<ResponseJoinData>
```

- RequestData 클래스에서 서버 요청을 위해 보내는 데이터 관리
- ResponseData 클래스에서 서버 응답 데이터를 관리

<img src="https://user-images.githubusercontent.com/59547069/118366441-36653600-b5db-11eb-9c2f-c49006a7c356.png" width="30%" height="30%">

### ✅ PostMan 테스트 사진

- 회원가입 테스트
<img src="https://user-images.githubusercontent.com/59547069/118366435-349b7280-b5db-11eb-9418-965374d0beb3.png" width="50%" height="50%">

- 로그인 테스트

<img src="https://user-images.githubusercontent.com/59547069/118366438-35340900-b5db-11eb-9985-3641cd6e3500.png" width="80%" height="80%">
