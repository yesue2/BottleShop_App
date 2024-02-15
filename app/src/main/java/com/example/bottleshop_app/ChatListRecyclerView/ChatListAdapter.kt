package com.example.bottleshop_app.ChatListRecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bottleshop_app.databinding.ItemChatListBinding

class ChatListAdapter(private val chatListItem: MutableList<ChatListItem>) :
    RecyclerView.Adapter<ChatListAdapter.ChatListViewHolder>() {

    inner class ChatListViewHolder(binding: ItemChatListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val profile = binding.profile
        val storeName = binding.storeName
        val endContent = binding.endContent
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatListViewHolder {
        val binding =
            ItemChatListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatListViewHolder, position: Int) {
        // TODO("profile 사진 넣는 코드 작성하기")
        holder.storeName.text = chatListItem[position].storeName
        holder.endContent.text = chatListItem[position].endContent
    }

    override fun getItemCount(): Int {
        return chatListItem.size
    }
}
