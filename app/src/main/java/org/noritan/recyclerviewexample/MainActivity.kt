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
        data.add(CustomDataItem(R.drawable.hitodeman, "ひとでまん (2)"))
        data.add(CustomDataItem(R.drawable.bootloader_so8, "基板 (2)"))
        data.add(CustomDataItem(R.drawable.kin1, "金将 (2)"))
        data.add(CustomDataItem(R.drawable.kairosu, "かいろす (2)"))
        data.add(CustomDataItem(R.drawable.nasubo64, "なす棒 (2)"))
        data.add(CustomDataItem(R.drawable.nasu64, "なす (2)"))

        contentRecyclerView.adapter = CustomRecycleViewAdapter(data)
    }

    data class CustomDataItem(val avatar: Int, val description: String)

    class CustomRecycleViewAdapter(val data: List<CustomDataItem>): RecyclerView.Adapter<CustomViewHolder>() {
        companion object {
            const val EVEN_TYPE = 0
            const val ODD_TYPE = 1
        }

        override fun getItemViewType(position: Int): Int {
            return if (position % 2 == 0) EVEN_TYPE else ODD_TYPE
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
            val itemView = if (viewType == EVEN_TYPE) {
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.one_item, parent, false)
            } else {
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.another_item, parent, false)
            }
            return CustomViewHolder(itemView)
        }

        override fun getItemCount(): Int {
            return data.size
        }

        override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
            val dataItem = data[position]
            holder.avatarImageView.setImageResource(dataItem.avatar)
            holder.descriptionTextView.text = dataItem.description
        }
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val avatarImageView: ImageView = itemView.avatarImageView
        val descriptionTextView: TextView = itemView.descriptionTextView
    }
}
