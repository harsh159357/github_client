package com.harsh.githubClient.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.harsh.githubClient.data.model.Repository
import com.harsh.githubClient.databinding.RepoItemBinding

class ReposAdapter() : RecyclerView.Adapter<ReposAdapter.ItemViewHolder>() {
    interface RepoListener {
        fun onRepoClick(repository: Repository, position: Int)
    }

    var repos: MutableList<Repository> = mutableListOf()
    var repoListener: RepoListener? = null

    class ItemViewHolder(val binding: RepoItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    fun updateRepos(items: List<Repository>) {
        repos = items.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RepoItemBinding.inflate(layoutInflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = repos[position]
        holder.binding.repoItem = currentItem
        holder.binding.repoParent.setOnClickListener {
            repoListener?.onRepoClick(currentItem, position)
        }
    }

    override fun getItemCount(): Int {
        return repos.size
    }
}