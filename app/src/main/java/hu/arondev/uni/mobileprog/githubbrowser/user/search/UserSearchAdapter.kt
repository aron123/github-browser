package hu.arondev.uni.mobileprog.githubbrowser.user.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.CornerFamily
import com.squareup.picasso.Picasso
import hu.arondev.uni.mobileprog.core.domain.User
import hu.arondev.uni.mobileprog.githubbrowser.R
import kotlinx.android.synthetic.main.user_item.view.*

class UserSearchAdapter(
        private val context: Context,
        private val users: MutableList<User> = mutableListOf(),
        private val itemClickListener: (User) -> Unit) : RecyclerView.Adapter<UserSearchAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userProfileImageView: ShapeableImageView = view.user_search_profile_pic
        val usernameTextView: TextView = view.user_search_username
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]

        val profileImageView = holder.itemView.findViewById<ShapeableImageView>(R.id.user_search_profile_pic)
        profileImageView.shapeAppearanceModel.toBuilder()
                .setAllCorners(CornerFamily.ROUNDED, context.resources.getDimension(R.dimen.nav_profile_pic_radius))
                .build().also { profileImageView.shapeAppearanceModel = it }
        Picasso.get()
                .load(user.avatar_url)
                .placeholder(R.drawable.ic_profile)
                .error(R.drawable.ic_profile)
                .into(holder.userProfileImageView)

        holder.usernameTextView.text = user.login
        holder.itemView.setOnClickListener{ itemClickListener.invoke(user) }
    }

    override fun getItemCount(): Int = users.size

    fun update(users: List<User>) {
        this.users.clear()
        this.users.addAll(users)
        notifyDataSetChanged()
    }
}