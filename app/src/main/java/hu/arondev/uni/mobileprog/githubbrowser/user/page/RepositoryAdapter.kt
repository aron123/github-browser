package hu.arondev.uni.mobileprog.githubbrowser.user.page

import android.content.Context
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hu.arondev.uni.mobileprog.core.domain.Repo
import hu.arondev.uni.mobileprog.framework.util.AgoFormatter
import hu.arondev.uni.mobileprog.githubbrowser.R
import kotlinx.android.synthetic.main.repo_item.view.*
import java.text.SimpleDateFormat
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

class RepositoryAdapter(
        private val context: Context,
        private val repos: List<Repo> = listOf(),
        private val itemClickListener: (Repo) -> Unit)
    : RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val repoNameTextView: TextView = view.repo_item_name
        val updatedTextView: TextView = view.repo_item_updated
        val descriptionTextView: TextView = view.repo_item_desc
        val languageTextView: TextView = view.repo_item_language
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.repo_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.run {
        holder.repoNameTextView.text = repos[position].name
        holder.updatedTextView.text = context.getString(R.string.updated_at,
                AgoFormatter.stringDateToAgoString(repos[position].updated_at))
        holder.descriptionTextView.text = repos[position].description
        holder.languageTextView.text = repos[position].language
        holder.itemView.setOnClickListener { itemClickListener.invoke(repos[position]) }
    }

    override fun getItemCount(): Int = repos.size
}