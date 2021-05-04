package hu.arondev.uni.mobileprog.githubbrowser.repo.page

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hu.arondev.uni.mobileprog.core.domain.File
import hu.arondev.uni.mobileprog.framework.util.StringUtil
import hu.arondev.uni.mobileprog.githubbrowser.R
import kotlinx.android.synthetic.main.file_item.view.*

class FileAdapter(private val files: List<File> = listOf(),
                  private val itemClickListener: (File) -> Unit)
    : RecyclerView.Adapter<FileAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val fileIconImageView: ImageView = view.repo_file_dir_icon
        val filenameTextView: TextView = view.repo_filename
        val filesizeTextView: TextView = view.repo_filesize
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.file_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.fileIconImageView.setImageResource(
            if (files[position].type == "dir") R.drawable.ic_folder else R.drawable.ic_file
        )
        holder.filenameTextView.text = files[position].name

        val filesize = files[position].size
        if (filesize == 0) {
            holder.filesizeTextView.visibility = View.INVISIBLE
        } else {
            holder.filesizeTextView.text = StringUtil.readableFileSize(files[position].size)
        }

        holder.itemView.setOnClickListener{ itemClickListener.invoke(files[position]) }
    }

    override fun getItemCount(): Int = files.size
}