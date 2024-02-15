package com.example.bottleshop_app.ChatListRecyclerView

data class ChatListItem(
    var profile:Int? = null,  // TODO(Url로 받아와서 String 형으로 수정 필요)
    var storeName:String? = null,
    var endContent:String? = null
)
