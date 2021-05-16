package hu.arondev.uni.mobileprog.githubbrowser.issue

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hu.arondev.uni.mobileprog.core.domain.Issue
import hu.arondev.uni.mobileprog.githubbrowser.R
import kotlinx.android.synthetic.main.issue_item.view.*

class IssueAdapter(
    private val context: Context,
    private val issues: MutableList<Issue> = mutableListOf(),
    private val itemClickListener: (Issue) -> Unit) : RecyclerView.Adapter<IssueAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.issue_browse_title
        val stateTextView: TextView = view.issue_browse_state
        val numberTextView: TextView = view.issue_browse_number
        val usernameTextView: TextView = view.issue_browse_username
        val commentsTextView: TextView = view.issue_browse_comments
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.issue_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val issue = issues[position]
        holder.titleTextView.text = issue.title
        holder.stateTextView.text = context.getString(
            if (issue.state == "open") R.string.issue_state_open else R.string.issue_state_closed
        )
        holder.stateTextView.setTextColor(context.resources.getColor(
            if (issue.state == "open") R.color.colorGreen else R.color.colorRed, context.theme)
        )
        holder.numberTextView.text = context.getString(R.string.issue_number, issue.number)
        holder.usernameTextView.text = issue.user.login
        holder.commentsTextView.text = context.resources.getQuantityString(
            R.plurals.issue_comments, issue.comments, issue.comments
        )
        holder.itemView.setOnClickListener {
            itemClickListener.invoke(issue)
        }
    }

    override fun getItemCount(): Int = issues.size

    fun update(issues: List<Issue>) {
        this.issues.clear()
        this.issues.addAll(issues)
        notifyDataSetChanged()
    }
}