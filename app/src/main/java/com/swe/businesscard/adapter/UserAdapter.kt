package com.swe.businesscard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.swe.businesscard.R
import com.swe.businesscard.database.User

class UserAdapter(private val users: List<User>, private val onItemClick: (User) -> Unit) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
    return UserViewHolder(view)
  }

  override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
    val user = users[position]
    holder.bind(user)

    holder.itemView.setOnClickListener {
      onItemClick(user) // Trigger the click callback when an item is clicked
    }
  }

  override fun getItemCount(): Int {
    return users.size
  }

  inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    // Initialize the views
    private val userNameTextView: TextView = itemView.findViewById(R.id.userName)
    private val userRoleTextView: TextView = itemView.findViewById(R.id.userRole)
    private val userEmailTextView: TextView = itemView.findViewById(R.id.userEmail)
    private val userPhoneTextView: TextView = itemView.findViewById(R.id.userPhone)

    fun bind(user: User) {
      // Set the text for each view
      userNameTextView.text = user.name
      userRoleTextView.text = user.role
      userEmailTextView.text = user.email
      userPhoneTextView.text = user.phone
    }
  }
}