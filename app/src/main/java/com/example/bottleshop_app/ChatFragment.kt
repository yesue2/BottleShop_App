package com.example.bottleshop_app

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bottleshop_app.ChatListRecyclerView.ChatListAdapter
import com.example.bottleshop_app.ChatListRecyclerView.ChatListItem
import com.example.bottleshop_app.databinding.FragmentChatBinding

class ChatFragment : Fragment() {
    lateinit var mainActivity: MainActivity

    private lateinit var binding: FragmentChatBinding
    private lateinit var adapter: ChatListAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatBinding.inflate(inflater)

        setRecyclerView()

        return binding.root
    }

    private fun setRecyclerView() {
        val listItem = mutableListOf<ChatListItem>()
        listItem.add(ChatListItem(R.drawable.ic_info, "가게 1", "네 그럼 금일 3시에 뵙겠습니다."))
        listItem.add(ChatListItem(R.drawable.ic_info, "가게 2", "추천해주실 수 있으실까요?"))
        listItem.add(ChatListItem(R.drawable.ic_info, "가게 5", "재고 있나요?"))
        listItem.add(ChatListItem(R.drawable.ic_info, "가게 3", "죄송하지만 문의주신 와인은 현재 재고가 없습니다."))
        listItem.add(ChatListItem(R.drawable.ic_info, "가게 4", "네 감사합니다!"))

        mainActivity.runOnUiThread {
            adapter = ChatListAdapter(listItem)
            binding.chatListRecyclerView.adapter = adapter
            binding.chatListRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}