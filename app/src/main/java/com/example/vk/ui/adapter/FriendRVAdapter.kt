package com.example.vk.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.example.vk.R
import com.example.vk.mvp.model.entity.user.UserDetail
import com.example.vk.mvp.presenter.list.IFriendListPresenter
import com.example.vk.mvp.view.IFriendItemView
import kotlinx.android.synthetic.main.item_friend.view.*

class FriendRVAdapter(private val presenter: IFriendListPresenter): RecyclerView.Adapter<FriendRVAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), IFriendItemView {


//        var position: Int = 0

        override fun init(friend: UserDetail) {
            itemView.fio.text = "${friend.lastName} ${friend.firstName}"
            itemView.city.text = friend?.city?.title

            friend.photo_100?.let { url ->
                itemView.avatar.load(url) {
                    transformations(CircleCropTransformation())
                }
            }
        }

        override fun getPos(): Int {
            return position
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater =LayoutInflater.from(context)

        val userView =inflater.inflate(R.layout.item_friend, parent, false)

        val viewHolder = ViewHolder(userView)

        return  viewHolder
    }

    override fun getItemCount(): Int {
        return presenter.getCount()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.position = position
        holder.itemView.setOnClickListener(View.OnClickListener { presenter.onItemClick(holder) })

        presenter.bindView(holder)
    }
}