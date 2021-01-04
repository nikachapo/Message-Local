package com.epam.messagelocal.ui.chats

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.epam.messagelocal.R

class ChatsFragment : Fragment(R.layout.fragment_chats) {

    private lateinit var chatsViewModel: ChatsViewModel

    private val chatsAdapter = ChatsAdapter {
        val action = ChatsFragmentDirections.actionNavigationChatsToMessagesFragment(it.id)
        findNavController().navigate(action)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chatsViewModel = ViewModelProvider(this).get(ChatsViewModel::class.java)
        val chatsList: RecyclerView = view.findViewById(R.id.chatsList)
        chatsList.run {
            addItemDecoration(
                DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            )
            adapter = chatsAdapter
        }
        chatsViewModel.chats.observe(viewLifecycleOwner, {
            chatsAdapter.submitList(it)
        })
    }
}