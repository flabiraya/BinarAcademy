package id.logtivity.codechallenge.features.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import id.logtivity.codechallenge.R
import id.logtivity.codechallenge.config.AndroidList
import id.logtivity.codechallenge.preferences.inflates
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.itemss.view.*


class   AdapterHome (var allData: List<AndroidList>, var listener: (AndroidList) -> Unit) :
    RecyclerView.Adapter<AdapterHome.HomeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(parent.inflates(R.layout.itemss))
    }

    override fun getItemCount(): Int = allData.size
    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bindView(allData.get(position), listener)
    }

    inner class HomeViewHolder(override var containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindView(data: AndroidList, listener: (AndroidList) -> Unit) {
            containerView.tv_Android_Name.text = data.codeName
            containerView.tv_Android_Version.text = data.version
            containerView.tv_Api_Level.text = data.apiLevel
            itemView.setOnClickListener { listener(data) }
        }
    }

}
