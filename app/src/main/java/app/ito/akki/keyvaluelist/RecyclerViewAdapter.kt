package app.ito.akki.keyvaluelist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import app.ito.akki.keyvaluelist.databinding.ListItemBinding

class RecyclerViewAdapter(
    private val context: Context,
    private var taskList: MutableList<String>
) : RecyclerView.Adapter<RecyclerViewAdapter.TaskViewHolder>(){

    //リスト一つ分のViewを保持している
    class TaskViewHolder(val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root)

    //新しくViewHolderのオブジェクトを生成する
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false)
        return TaskViewHolder(binding)
    }

    //表示内容とレイアウトを結びつける役割をする
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val item = taskList[position]
        holder.binding.contentTextView.text = item
        holder.binding.imageView.setImageResource(R.drawable.ic_launcher_background)
    }

    //リストの数を指定
    override fun getItemCount(): Int {
        return taskList.size
    }
}