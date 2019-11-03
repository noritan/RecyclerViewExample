package org.noritan.recyclerviewexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.one_item.view.*

class MainActivity : AppCompatActivity() {

    private val data: MutableList<CustomDataItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        data.add(CustomDataItem(R.drawable.bootloader_so8, "基板"))
        data.add(CustomDataItem(R.drawable.hitodeman, "ひとでまん"))
        data.add(CustomDataItem(R.drawable.kairosu, "かいろす"))
        data.add(CustomDataItem(R.drawable.kin1, "金将"))
        data.add(CustomDataItem(R.drawable.nasu64, "なす"))
        data.add(CustomDataItem(R.drawable.nasubo64, "なす棒"))
        data.add(CustomDataItem(R.drawable.nasuno_fujitaka480, "初夢"))
        data.add(CustomDataItem(R.drawable.nasuno_nasushirt, "なすシャツ"))
        data.add(CustomDataItem(R.drawable.neko_640, "ねこ"))
        data.add(CustomDataItem(R.drawable.poppo, "ぽっぽ"))
    }

    override fun onStart() {
        super.onStart()
        contentRecyclerView.adapter = CustomRecycleViewAdapter(data)
    }

    override fun onStop() {
        super.onStop()
        contentRecyclerView.adapter = null
    }

    data class CustomDataItem(val avatar: Int, val description: String)

    class CustomRecycleViewAdapter(val data: List<CustomDataItem>): RecyclerView.Adapter<CustomViewHolder>() {
        companion object {
            val EVEN_TYPE = 0
            val ODD_TYPE = 1
        }

        override fun getItemViewType(position: Int): Int {
            return if (position % 2 == 0) EVEN_TYPE else ODD_TYPE
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
            val layoutId = when (viewType) {
                EVEN_TYPE -> R.layout.one_item
                ODD_TYPE -> R.layout.another_item
                else -> R.layout.one_item
            }
            val view = LayoutInflater.from(parent.context)
                .inflate(layoutId, parent, false)
            return CustomViewHolder(view)
        }

        override fun getItemCount(): Int {
            return data.size
        }

        override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
            holder.avatarImageView.setImageResource(data[position].avatar)
            holder.descriptionTextView.text = data[position].description
        }
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var avatarImageView: ImageView = itemView.avatarImageView
        var descriptionTextView: TextView = itemView.descriptionTextView
    }
}
