package zhangruofan.realm.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main_item.view.*
import zhangruofan.realm.R

/**
 * Created by zhangruofan on 16-5-24.
 */
class MainAdapter(val mContext: Context) : RecyclerView.Adapter<MainAdapter.BaseViewHolder>() {

    private val mTitleList: Array<String>
    private val mContentList: Array<String>

    init {
        mTitleList = mContext.resources.getStringArray(R.array.realm_title_array)
        mContentList = mContext.resources.getStringArray(R.array.realm_content_array)
    }

    abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun update();
    }

    inner class CardViewHolder(itemView: View) : BaseViewHolder(itemView) {

        override fun update() {
            itemView.titleTv.text = mTitleList.get(layoutPosition)
            itemView.contentTv.text = mContentList.get(layoutPosition)
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder?, position: Int) {
        holder?.update()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder? {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.activity_main_item, parent, false)
        return CardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mTitleList.size
    }
}