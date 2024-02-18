package com.example.bottleshop_app

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bottleshop_app.chatListRecyclerView.ChatListAdapter
import com.example.bottleshop_app.chatListRecyclerView.ChatListItem
import com.example.bottleshop_app.databinding.FragmentChatBinding

class ChatFragment : Fragment() {
    lateinit var mainActivity: MainActivity  // context를 할당할 변수

    private lateinit var binding: FragmentChatBinding
    private lateinit var adapter: ChatListAdapter

    override fun onAttach(context: Context) {  // context에 정의된 메서드(runOnUiThread 등)를 사용하기 위함
        super.onAttach(context)
        mainActivity = context as MainActivity  // context를 액티비티로 형변환해서 할당
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatBinding.inflate(inflater)

        setRecyclerView()

        return binding.root
    }

    private fun setRecyclerView() {
        val listItem = mutableListOf<ChatListItem>()
        // 데이터 예시
        listItem.add(ChatListItem(R.drawable.ic_info, "가게 1", "네 그럼 금일 3시에 뵙겠습니다."))
        listItem.add(ChatListItem(R.drawable.ic_info, "가게 2", "추천해주실 수 있으실까요?"))
        listItem.add(ChatListItem(R.drawable.ic_info, "가게 5", "재고 있나요?"))
        listItem.add(ChatListItem(R.drawable.ic_info, "가게 3", "죄송하지만 문의주신 와인은 현재 재고가 없습니다."))
        listItem.add(ChatListItem(R.drawable.ic_info, "가게 4", "네 감사합니다!"))

        mainActivity.runOnUiThread {  // ui 작업이므로 ui 스레드에서 실행
            adapter = ChatListAdapter(listItem)  // 어댑터 객체 할당
            binding.chatListRecyclerView.adapter = adapter  // 리사이클러뷰 레이아웃에 어댑터 설정
            binding.chatListRecyclerView.layoutManager = LinearLayoutManager(requireContext())  // 레이아웃 매니저 설정
            // Activity에서는 binding.chatListRecyclerView.layoutManager = LinearLayoutManager(this)로 작성
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}