package com.example.threadyoutube.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.threadyoutube.item_view.ThreadItem
import com.example.threadyoutube.utils.SharedPref
import com.example.threadyoutube.viewmodel.HomeViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun Home(navHostController: NavHostController) {
    val context = LocalContext.current

    val homeViewModel: HomeViewModel = viewModel()
    val threadAndUsers by homeViewModel.threadsAndUsers.observeAsState(null)

    LazyColumn {
        items(threadAndUsers?: emptyList()) { pairs ->
            ThreadItem(thread = pairs.first,users = pairs.second,navHostController,FirebaseAuth.getInstance().currentUser!!.uid)
        }
    }
}
