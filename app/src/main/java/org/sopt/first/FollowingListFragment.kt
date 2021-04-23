package org.sopt.first

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.sopt.first.data.FollowingUserInfo
import org.sopt.first.databinding.FragmentFollowingListBinding

class FollowingListFragment : Fragment() {
    private var _binding: FragmentFollowingListBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")

    private lateinit var followingListAdapter: FollowingListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowingListBinding.inflate(
            inflater,
            container,
            false
        )
        //val binding = FragmentFollowingListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        followingListAdapter = FollowingListAdapter()
        binding.userList.adapter = followingListAdapter

        followingListAdapter.userList.addAll(
            listOf<FollowingUserInfo>(
                FollowingUserInfo(
                    userImage = "지금은 빈칸",
                    userName = "kihyeon1111"
                ),
                FollowingUserInfo(
                    userImage = "지금은 빈칸",
                    userName = "teagh82"
                ),
                FollowingUserInfo(
                    userImage = "지금은 빈칸",
                    userName = "123"
                ),
                FollowingUserInfo(
                    userImage = "지금은 빈칸",
                    userName = "나는기현"
                )
            )
        )

        // Adapter의 모든 데이터가 변했으니 다시 불러와라
        followingListAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}