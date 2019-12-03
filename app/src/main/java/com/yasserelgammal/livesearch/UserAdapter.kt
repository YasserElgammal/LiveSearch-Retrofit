package com.yasserelgammal.livesearch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yasserelgammal.livesearch.model.User
import kotlinx.android.synthetic.main.users_list.view.*

class UserAdapter(private val users : List<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.users_list, parent, false))
    }

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.view.UsersId.text = user.id.toString()
        holder.view.UsersPersonName.text = user.name
        holder.view.UsersPersonEmail.text = user.email
        holder.view.UsersShortPersonName.text = user.name.substring(0,1).capitalize()

    }


    class UserViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}