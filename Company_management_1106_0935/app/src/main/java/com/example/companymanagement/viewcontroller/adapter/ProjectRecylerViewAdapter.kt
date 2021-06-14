package com.example.companymanagement.viewcontroller.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.companymanagement.R
import com.example.companymanagement.model.TaskInfoModel
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions


class ProjectRecyclerViewAdapter(options: FirestoreRecyclerOptions<TaskInfoModel>) :
    FirestoreRecyclerAdapter<TaskInfoModel,ProjectRecyclerViewAdapter.TaskHolder>(options) {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnClickListener(listener: onItemClickListener){
        mListener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int,
    ): TaskHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cardview_project, parent, false)
        return TaskHolder(view,mListener)
    }

    class TaskHolder(itemView: View,listener: onItemClickListener) : RecyclerView.ViewHolder(itemView){
        var taskName = itemView.findViewById<TextView>(R.id.taskName_textview)
        var taskTime = itemView.findViewById<TextView>(R.id.taskTime_textview)
        init {
            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }

    }

    override fun onBindViewHolder(holder: TaskHolder, position: Int, model: TaskInfoModel) {
        holder.taskName.text = model.Content.toString()
        holder.taskTime.text = model.Deadline.toString()
    }
}