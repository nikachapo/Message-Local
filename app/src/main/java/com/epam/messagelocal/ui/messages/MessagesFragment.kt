package com.epam.messagelocal.ui.messages

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.epam.messagelocal.R

@Suppress("UNCHECKED_CAST")
class MessagesFragment : Fragment(R.layout.fragment_messages) {

    private lateinit var viewModel: MessagesViewModel

    private val safeArgs: MessagesFragmentArgs by navArgs()

    private lateinit var messagesList: RecyclerView
    private val adapter = MessagesAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        messagesList = view.findViewById(R.id.messagesList)
        val messageET = view.findViewById<AppCompatEditText>(R.id.messageET)
        view.findViewById<AppCompatButton>(R.id.sendBtn).setOnClickListener {
            if (messageET.text.toString().isNotEmpty()) {
                viewModel.sendMessage(messageET.text.toString())
            }
        }

        messagesList.adapter = adapter
        viewModel = ViewModelProvider(this).get(MessagesViewModel::class.java)
        viewModel.loadMessages(safeArgs.chatId)

        bindObservers()

    }

    private fun bindObservers() {
        viewModel.messages.observe(viewLifecycleOwner, {
            adapter.submitItems(it.toMutableList())
            messagesList.scrollToPosition(it.size - 1)
        })
        viewModel.user2.observe(viewLifecycleOwner, {
            (activity as AppCompatActivity).supportActionBar?.title = it.username
        })
    }

}