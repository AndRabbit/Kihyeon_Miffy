# 김기현 과제 4
</br>

[https://s3-us-west-2.amazonaws.com/secure.notion-static.com/13b4b432-d977-44a6-be6b-e317e6c7503e/Android_Emulator_-_Pixel_3a_API_30_x86_5554_2021-04-23_11-10-36.mp4](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/13b4b432-d977-44a6-be6b-e317e6c7503e/Android_Emulator_-_Pixel_3a_API_30_x86_5554_2021-04-23_11-10-36.mp4)

## 📌 수행 과제

- level 1

# Level 1

---

[https://s3-us-west-2.amazonaws.com/secure.notion-static.com/32a792dd-43e7-4377-97d1-c16e5e8ddd82/_2021_05_11_23_40_01_339.mp4](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/32a792dd-43e7-4377-97d1-c16e5e8ddd82/_2021_05_11_23_40_01_339.mp4)

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

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/a9374051-3aa7-4940-a138-3c82024d6d2a/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/a9374051-3aa7-4940-a138-3c82024d6d2a/Untitled.png)

### ✅ PostMan 테스트 사진

- 회원가입 테스트

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/9135389b-51f1-48d7-837a-1528554420f4/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/9135389b-51f1-48d7-837a-1528554420f4/Untitled.png)

- 로그인 테스트

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/6ce4ec19-29ac-4986-add0-4c97f6bbb922/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/6ce4ec19-29ac-4986-add0-4c97f6bbb922/Untitled.png)