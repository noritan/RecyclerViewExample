package org.noritan.recyclerviewexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import org.noritan.recyclerviewexample.databinding.AnotherItemBinding
import org.noritan.recyclerviewexample.databinding.OneItemBinding

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

        contentRecyclerView.adapter = CustomRecycleViewAdapter(data)
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
            val holder: CustomViewHolder
            if (viewType == EVEN_TYPE) {
                holder = EvenViewHolder.onCreateViewHolder(parent, viewType)
            } else {
                holder = OddViewHolder.onCreateViewHolder(parent, viewType)
            }
            return holder
        }

        override fun getItemCount(): Int {
            return data.size
        }

        override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
            holder.setContent(data[position])
        }
    }

    abstract class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun setContent(dataItem: CustomDataItem)
    }

    class EvenViewHolder(itemView: View) : CustomViewHolder(itemView) {
        companion object {
            fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.one_item, parent, false)
                return EvenViewHolder(itemView)
            }
        }

        private val binding = DataBindingUtil.bind<OneItemBinding>(itemView)

        override fun setContent(dataItem: CustomDataItem) {
            binding?.avatarImageView?.setImageResource(dataItem.avatar)
            binding?.descriptionTextView?.text = dataItem.description
        }
    }

    class OddViewHolder(itemView: View) : CustomViewHolder(itemView) {
        companion object {
            fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.another_item, parent, false)
                return OddViewHolder(itemView)
            }
        }

        private val binding = DataBindingUtil.bind<AnotherItemBinding>(itemView)

        override fun setContent(dataItem: CustomDataItem) {
            binding?.avatarImageView?.setImageResource(dataItem.avatar)
            binding?.descriptionTextView?.text = dataItem.description
        }
    }
}
