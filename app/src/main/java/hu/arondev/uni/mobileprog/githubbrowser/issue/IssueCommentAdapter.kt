package hu.arondev.uni.mobileprog.githubbrowser.issue

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.CornerFamily
import com.squareup.picasso.Picasso
import hu.arondev.uni.mobileprog.core.domain.IssueComment
import hu.arondev.uni.mobileprog.framework.util.AgoFormatter
import hu.arondev.uni.mobileprog.githubbrowser.R
import kotlinx.android.synthetic.main.issue_comment_item.view.*

class IssueCommentAdapter(
    private val context: Context,
    private val issueComments: List<IssueComment> = listOf()) : RecyclerView.Adapter<IssueCommentAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val profilePicImageView: ShapeableImageView = view.issue_comment_profile_pic
        val usernameTextView: TextView = view.issue_comment_username
        val createdAgoTextView: TextView = view.issue_comment_created_at
        val commentTextView: TextView = view.issue_comment_content
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.issue_comment_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val issueComment = issueComments[position]

        val profileImageView = holder.profilePicImageView
        profileImageView.shapeAppearanceModel.toBuilder()
            .setAllCorners(CornerFamily.ROUNDED, context.resources.getDimension(R.dimen.nav_profile_pic_radius))
            .build().also { profileImageView.shapeAppearanceModel = it }
        Picasso.get()
            .load(issueComment.user.avatar_url)
            .placeholder(R.drawable.ic_profile)
            .error(R.drawable.ic_profile)
            .into(profileImageView)

        holder.usernameTextView.text = issueComment.user.login
        holder.createdAgoTextView.text = AgoFormatter.stringDateToAgoString(issueComment.created_at)
        holder.commentTextView.text = issueComment.body
    }

    override fun getItemCount(): Int = issueComments.size
}