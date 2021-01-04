package com.epam.messagelocal.ui.search

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.epam.messagelocal.R

class SearchUsersFragment : Fragment(R.layout.fragment_search) {

    private val searchUsersViewModel by viewModels<SearchUsersViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchET = view.findViewById<AppCompatEditText>(R.id.searchET)
        view.findViewById<AppCompatButton>(R.id.searchBtn).setOnClickListener {
            if (searchET.text.toString().isNotEmpty()) {
                searchUsersViewModel.search(searchET.text.toString())
            } else {
                Toast.makeText(requireContext(), getString(R.string.enter_name), Toast.LENGTH_SHORT)
                    .show()
            }
        }

        val usersList: RecyclerView = view.findViewById(R.id.usersList)
        usersList.addItemDecoration(
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        )

        val usersAdapter = UsersAdapter {
            val chatId = searchUsersViewModel.getChatIdWith(it)
            val action =
                SearchUsersFragmentDirections.actionNavigationSearchToNavigationMessages(chatId)
            findNavController().navigate(action)
        }
        usersList.adapter = usersAdapter

        searchUsersViewModel.searchedUsers.observe(viewLifecycleOwner, {
            usersAdapter.submitItems(it.toMutableList())
        })
    }
}