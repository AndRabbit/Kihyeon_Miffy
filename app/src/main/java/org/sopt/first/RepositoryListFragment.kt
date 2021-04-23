package org.sopt.first

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import org.sopt.first.data.RepositoryInfo
import org.sopt.first.databinding.FragmentRepositoryListBinding


class RepositoryListFragment : Fragment() {
    private var _binding: FragmentRepositoryListBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")

    private lateinit var repositoryListAdapter: RepositoryListAdapter

    private var layout_type = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRepositoryListBinding.inflate(
            inflater,
            container,
            false
        )
        //val binding = FragmentRepositoryListBinding.inflate(inflater, container, false)

        binding.btnChange.setOnClickListener {
            if (layout_type == 1){
                binding.repoList.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                layout_type = 0
            }
            else{
                binding.repoList.layoutManager = GridLayoutManager(context, 3)
                layout_type = 1
            }

        }

        ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                //adapter의 onItemMove 호출
                (binding.repoList.adapter as RepositoryListAdapter).onItemMove(
                    viewHolder.getAdapterPosition(),
                    target.getAdapterPosition()
                )
                return true
            }

            override fun onSwiped(
                viewHolder: RecyclerView.ViewHolder,
                direction: Int
            ) { // Adapter에 아이템 삭제 요청
                (binding.repoList.adapter as RepositoryListAdapter).removeTask(viewHolder.adapterPosition)
            }

            override fun isLongPressDragEnabled(): Boolean =  true

            override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: ViewHolder): Int {
                val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                val swipeFlags = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                return ItemTouchHelper.Callback.makeMovementFlags(
                    dragFlags,
                    swipeFlags
                )
            }

        }).apply { // ItemTouchHelper에 RecyclerView 설정
            attachToRecyclerView(binding.repoList)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        repositoryListAdapter = RepositoryListAdapter()
        binding.repoList.adapter = repositoryListAdapter

        repositoryListAdapter.repoList.addAll(
            listOf<RepositoryInfo>(
                RepositoryInfo(
                    repoName = "레포지터리 이름이 너무너무너무 길~~ 경우",
                    repoInfo = "kihyeon1111",
                    repoLang = "언어",
                    contents = "",
                    type = 1
                ),
                RepositoryInfo(
                    repoName = "지금은 빈칸",
                    repoInfo = "레포지터리 설명이 너무너무너무너무 길~~~~~~ 경우",
                    repoLang = "언어",
                    contents = "",
                    type = 1
                ),
                RepositoryInfo(
                    repoName = "레포지터리 이름",
                    repoInfo = "kihyeon1111",
                    repoLang = "언어",
                    contents = "",
                    type = 1
                ),
                RepositoryInfo(
                    repoName = "",
                    repoInfo = "",
                    repoLang = "",
                    contents = "다른 뷰홀더 입니다.",
                    type = 2
                ),
                RepositoryInfo(
                    repoName = "레포지터리 이름이 너무너무너무 길~~ 경우",
                    repoInfo = "kihyeon1111",
                    repoLang = "언어",
                    contents = "",
                    type = 1
                ),
                RepositoryInfo(
                    repoName = "지금은 빈칸",
                    repoInfo = "레포지터리 설명이 너무너무너무너무 길~~~~~~ 경우",
                    repoLang = "언어",
                    contents = "",
                    type = 1
                ),
                RepositoryInfo(
                    repoName = "레포지터리 이름",
                    repoInfo = "kihyeon1111",
                    repoLang = "언어",
                    contents = "",
                    type = 1
                ),
                RepositoryInfo(
                    repoName = "지금은 빈칸",
                    repoInfo = "kihyeon1111",
                    repoLang = "언어",
                    contents = "",
                    type = 1
                ),
                RepositoryInfo(
                    repoName = "레포지터리 이름이 너무너무너무 길~~ 경우",
                    repoInfo = "kihyeon1111",
                    repoLang = "언어",
                    contents = "안녕하세요",
                    type = 2
                ),
                RepositoryInfo(
                    repoName = "지금은 빈칸",
                    repoInfo = "레포지터리 설명이 너무너무너무너무 길~~~~~~ 경우",
                    repoLang = "언어",
                    contents = "",
                    type = 1
                ),
                RepositoryInfo(
                    repoName = "",
                    repoInfo = "",
                    repoLang = "",
                    contents = "다른 뷰홀더",
                    type = 2
                ),
                RepositoryInfo(
                    repoName = "지금은 빈칸",
                    repoInfo = "kihyeon1111",
                    repoLang = "언어",
                    contents = "",
                    type = 1
                ),
                RepositoryInfo(
                    repoName = "레포지터리 이름이 너무너무너무 길~~ 경우",
                    repoInfo = "kihyeon1111",
                    repoLang = "언어",
                    contents = "",
                    type = 1
                ),
                RepositoryInfo(
                    repoName = "",
                    repoInfo = "",
                    repoLang = "",
                    contents = "다른 뷰홀더",
                    type = 2
                ),
                RepositoryInfo(
                    repoName = "지금은 빈칸",
                    repoInfo = "kihyeon1111",
                    repoLang = "언어",
                    contents = "",
                    type = 1
                ),
                RepositoryInfo(
                    repoName = "지금은 빈칸",
                    repoInfo = "kihyeon1111",
                    repoLang = "언어",
                    contents = "",
                    type = 1
                ),
                RepositoryInfo(
                    repoName = "",
                    repoInfo = "",
                    repoLang = "",
                    contents = "다른 뷰홀더이다!",
                    type = 2
                ),
                RepositoryInfo(
                    repoName = "지금은 빈칸",
                    repoInfo = "레포지터리 설명이 너무너무너무너무 길~~~~~~ 경우",
                    repoLang = "언어",
                    contents = "",
                    type = 1
                ),
                RepositoryInfo(
                    repoName = "지금은 빈칸",
                    repoInfo = "kihyeon1111",
                    repoLang = "언어",
                    contents = "",
                    type = 1
                ),
                RepositoryInfo(
                    repoName = "지금은 빈칸",
                    repoInfo = "kihyeon1111",
                    repoLang = "언어",
                    contents = "",
                    type = 1
                )
            )
        )

        // Adapter의 모든 데이터가 변했으니 다시 불러와라
        repositoryListAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}