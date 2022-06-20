package com.harsh.githubClient.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.harsh.githubClient.data.model.PullRequest
import com.harsh.githubClient.databinding.PrItemBinding

class PullRequestsAdapter() : RecyclerView.Adapter<PullRequestsAdapter.ItemViewHolder>() {
    interface PRListener {
        fun onPRClick(pullRequest: PullRequest, position: Int)
    }

    var pullRequests: MutableList<PullRequest> = mutableListOf()
    var prListener: PRListener? = null

    class ItemViewHolder(val binding: PrItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    fun updateRepos(items: List<PullRequest>) {
        pullRequests = items.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = PrItemBinding.inflate(layoutInflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = pullRequests[position]
        holder.binding.prItem = currentItem
        holder.binding.prParent.setOnClickListener {
            prListener?.onPRClick(currentItem, position)
        }
    }

    override fun getItemCount(): Int {
        return pullRequests.size
    }
}