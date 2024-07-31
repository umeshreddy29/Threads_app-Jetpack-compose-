package com.example.threadyoutube.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.threadyoutube.model.ThreadModel
import com.example.threadyoutube.model.UserModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SearchViewModel : ViewModel() {

    val db = FirebaseDatabase.getInstance()
    val users = db.getReference("users")

    private val _users = MutableLiveData<List<UserModel>>()
    val userList: LiveData<List<UserModel>> = _users

    init {
        fetchUsers {
            _users.value = it
        }
    }

    private fun fetchUsers(onResult: (List<UserModel>) -> Unit) {
        users.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                val result = mutableListOf<UserModel>()
                for (threadSnapshot in snapshot.children) {
                    val thread = threadSnapshot.getValue(UserModel::class.java)
                    result.add(thread!!)

                }
                onResult(result)
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
}

fun fetchUserFromThread(thread: ThreadModel, onResult: (UserModel) -> Unit) {
    db.getReference("users").child(thread.userId)
        .addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue(UserModel::class.java)
                user?.let(onResult)

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
}

}