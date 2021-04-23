package org.sopt.first

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.first.data.RepositoryInfo
import org.sopt.first.databinding.ItemRepositoryAdListBinding
import org.sopt.first.databinding.ItemRepositoryListBinding
import java.util.*

class RepositoryListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val repoList = mutableListOf<RepositoryInfo>()

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

    override fun getItemCount(): Int = repoList.size

    override fun getItemViewType(position: Int): Int {
        return repoList[position].type
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // holder.onBind(repoList[position])

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

    class RepositoryViewHolder(
        private val binding: ItemRepositoryListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(RepositoryInfo: RepositoryInfo){
            binding.repoName.text = RepositoryInfo.repoName
            binding.repoInfo.text = RepositoryInfo.repoInfo
            binding.repoLang.text = RepositoryInfo.repoLang
        }
    }

    class RepositoryAdViewHolder(
        private val binding: ItemRepositoryAdListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(RepositoryInfo: RepositoryInfo){
            binding.itemAd.text = RepositoryInfo.contents
        }
    }

    // 내부 데이터 값 제거
    fun removeTask(position: Int) {
        repoList.removeAt(position)
        notifyDataSetChanged()
    }

    //아이템 이동
    fun onItemMove(fromPos: Int, targetPos: Int): Unit {
        if (fromPos < targetPos) {
            for (i in fromPos until targetPos) {
                Collections.swap(repoList, i, i + 1)
            }
        } else {
            for (i in fromPos downTo targetPos + 1) {
                Collections.swap(repoList, i, i - 1)
            }
        }
        notifyItemMoved(fromPos, targetPos)
    }
}