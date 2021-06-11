# 김기현 과제 2
</br>

<p align="center"><img src="https://user-images.githubusercontent.com/59547069/115892150-d8d54200-a491-11eb-8083-ac0558c2e30e.gif" width="30%" height="30%"></p>

## 📌 수행 과제

- level 1
- level 2-1
- level 2-2
- level 2-3

# Level 1

---
<p align="center"><img src="https://user-images.githubusercontent.com/59547069/115892020-b9d6b000-a491-11eb-9836-fe1f7c2fa901.png" width="30%" height="30%"></p>


### ✅ **HomeActivity**

- FragmentContainerView 추가
- item_repository_list 만들어서 리사이클러뷰 안에 들어갈 아이템 생성
- RepositoryInfo에서 데이터 관리
- RepositoryListAdapter에서 어댑터와 뷰홀더 관리
- RepositoryListFragment에 만든 리사이클러뷰 어댑터 넣고 내용 관리

### ✅ 레포지터리 이름 / 설명 너무 긴 경우

- item_repository_list.xml에서 변경
- ellipsize와 maxLine을 이용해 뒤에 ...이 나오도록 설정

```kotlin
<TextView
    android:id="@+id/repo_name"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="레포지터리 이름"
    android:ellipsize="end"
    android:maxLines="1"
    android:textSize="17dp"
    android:textStyle="bold"
    android:textColor="#000000"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toTopOf="parent" />
```

### ✅ MORE 버튼 추가

- HomeActivity에서 기능 추가
- 클릭 시 FollowingListFragment를 가진 UserInfoActivity를 띄움

</br>

# Level 2

---

<p align="center"><img src="https://user-images.githubusercontent.com/59547069/115892046-be9b6400-a491-11eb-87b7-8ba696d77964.png" width="30%" height="30%"></p>


### ✅ GridLayoutManager 사용

- fragment_repository_list.xml에서 변경
- layoutManager를 GridLayoutManager로 변경
- spanCount은 3으로 설정

```kotlin
<androidx.recyclerview.widget.RecyclerView
    android:id="@+id/repo_list"
    android:layout_width="match_parent"
    android:layout_height="0dp"
    android:orientation="vertical"
    app:layoutManager="androidx.recyclerview.widget.GridLayoutManager"
    app:layout_constraintTop_toBottomOf="@+id/btn_change"
    app:spanCount="3"
    tools:listitem="@layout/item_repository_list" />
```

### ✅ RecyclerView 안에 2가지이상의 뷰를 보여주기

- 두개의 뷰를 보여주기 위해 두개의 ViewHolder가 필요
- 두개의 뷰 타입을 구분하기 위해 Repository 데이터 클래스에 type 추가
- 타입을 보고 다른 뷰홀더를 바인딩함

```kotlin
override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    return when(viewType) {
        1 -> {
            val binding = ItemRepositoryListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            RepositoryViewHolder(binding)
        }
        else -> {
            val binding = ItemRepositoryAdListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            RepositoryAdViewHolder(binding)
        }
    }
}
```

```kotlin
override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    when(repoList[position].type) {
        1 -> {
            (holder as RepositoryViewHolder).onBind(repoList[position])
            holder.setIsRecyclable(false)
        }
        else -> {
            (holder as RepositoryAdViewHolder).onBind(repoList[position])
            holder.setIsRecyclable(false)
        }
    }
}
```

### ✅ RecyclerView Item 기능 구현하기

1️⃣ item을 길게 눌러 위치 변경하기

- RepositoryListFragment.kt에서 ItemTouchHelper.SimpleCallback을 이용하여 onMove()를 오버라이딩해 사용
- onMove()에서 부르는 위치 변경을 위한 함수는 RepositoryListAdapter.kt에 정의

```kotlin
fun onItemMove(fromPos: Int, targetPos: Int): Unit {
    if (fromPos < targetPos) {
        for (i in fromPosuntiltargetPos) {
            Collections.swap(repoList, i, i + 1)
        }
    } else {
        for (i in fromPosdownTotargetPos + 1) {
            Collections.swap(repoList, i, i - 1)
        }
    }
    notifyItemMoved(fromPos, targetPos)
}
```

- getMovementFlags()를 오버라이딩해 움직일 방향 설정

    → 상하좌우 모두 위치 이동 가능하게 설정함

    ```kotlin
    val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN or 
    								ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
    ```

2️⃣ item을 옆으로 슬라이드 하면 삭제하는 기능 넣기

- RepositoryListFragment.kt에서 ItemTouchHelper.SimpleCallback을 이용하여 onSwiped()를 오버라이딩해 사용
- onSwiped()에서 부르는 위치 변경을 위한 함수는 RepositoryListAdapter.kt에 정의

```kotlin
fun removeTask(position: Int) {
    repoList.removeAt(position)
    notifyDataSetChanged()
}
```

- getMovementFlags()를 오버라이딩해 움직일 방향 설정

→좌우 모두 위치 이동 가능하게 설정함

```kotlin
val swipeFlags = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
```

3️⃣ 버튼을 추가해서 Linear형식에서 Grid로 변경되도록 하기

- 버튼 클릭마다 layoutManager를 변경하며 바꿔줌

```kotlin
binding.btnChange.setOnClickListener{
if (layout_type == 1){
        binding.repoList.layoutManager= LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        layout_type = 0
    }
    else{
        binding.repoList.layoutManager= GridLayoutManager(context, 3)
        layout_type = 1
    }

}
```

</br>

# Level 3

---

### ✅ notifyDataSetChanged

📌 **notifyDataSetChanged**

- 어댑터에 연결된 List의 데이터를 갱신한다.
- 하지만 바뀌지 않은 item이 많더라도 전부 다 업데이트하게 된다.
- 이렇게 불필요한 교체 비용을 줄이기 위해 고안된 것이 바로 **DiffUtil**이다.

📌 **DiffUtil**

- 교체가 필요한 아이템에 대해서 부분적으로 데이터를 교체하라는 notify가 실행된다.
- item의 개수가 많을 경우 연산 시간이 길어질 수 있고, 구현상의 제약으로 DiffUtil이 처리할 수 있는 리스트의 최대 크기는 2²⁶이다.

📌 **ListAdapter**

- DiffUtil을 활용하여 리스트를 업데이트할 수 있는 기능을 추가한 Adapter
- 기존 어댑터와 달리 DiffUtil 기능에 대한 콜백 기능 클래스만 구현하면 되므로 생산성, 효율성을 높일 수 있음
