package com.example.batscanapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.batscanapp.models.Equipment
import kotlinx.android.synthetic.main.res_item_equipament.view.*


class EquipamentAdapter(
    private val onItemClicked: (Equipment) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<Equipment> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return LiveViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.res_item_equipament, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is LiveViewHolder -> {
                holder.bind(items[position], onItemClicked)
            }
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setList(liveList: List<Equipment>) {
        this.items = liveList
    }

    class LiveViewHolder constructor(
        itemView: View,
    ) : RecyclerView.ViewHolder(itemView) {

        private val vendor = itemView.vendor
        private val rssi_mac = itemView.rssi_mac

        fun bind(live: Equipment, onItemClicked: (Equipment) -> Unit) {

            vendor.text = live.vendor
            rssi_mac.text = live.rssi_mac

//            val requestOptions = RequestOptions()
//                .placeholder(R.drawable.ic_launcher_background)
//                .error(R.drawable.ic_launcher_background)
//
//            Glide.with(itemView.context)
//                .applyDefaultRequestOptions(requestOptions)
//                .load(live.thumbnailUrl)
//                .into(liveThumbnail)

            itemView.setOnClickListener {
                onItemClicked(live)
            }

        }

    }

}