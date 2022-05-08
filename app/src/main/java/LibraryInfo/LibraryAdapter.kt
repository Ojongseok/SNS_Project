package LibraryInfo

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_chat.view.*
import org.techtown.sns_project.R

class LibraryAdapter() : RecyclerView.Adapter<LibraryAdapter.ViewHolder>() {
    var items = ArrayList<LibraryDataInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_lib,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.setItem(item)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, LibraryPostActivity::class.java)
            intent.putExtra("name",item.name)
            ContextCompat.startActivity(holder.itemView.context,intent,null)
            Toast.makeText(it.context,"$position 아이템 클릭 !",Toast.LENGTH_SHORT).show()
        }
    }
    override fun getItemCount() = items.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setItem(item:LibraryDataInfo) {
            itemView.libName.text=item.name
            itemView.libTime.text=item.time
            itemView.libAddress.text = item.address
        }
    }
}