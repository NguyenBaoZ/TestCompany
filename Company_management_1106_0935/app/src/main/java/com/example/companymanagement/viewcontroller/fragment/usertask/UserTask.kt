package com.example.companymanagement.viewcontroller.fragment.usertask

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.companymanagement.R
import com.example.companymanagement.model.TaskInfoModel
import com.example.companymanagement.viewcontroller.adapter.ProjectRecyclerViewAdapter
import com.example.companymanagement.viewcontroller.fragment.user.UserInfoViewModel
import com.example.companymanagement.viewcontroller.fragment.user.UserTaskViewModel
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers


class UserTask : Fragment(){

    var auth = FirebaseAuth.getInstance()

    var db = FirebaseFirestore.getInstance()
    var taskmodel: UserTaskViewModel = UserTaskViewModel()
    lateinit var taskAdapter: ProjectRecyclerViewAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_user_project, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val query : Query = db.collection("task")
        val firestoreRecyclerOptions: FirestoreRecyclerOptions<TaskInfoModel> = FirestoreRecyclerOptions.Builder<TaskInfoModel>()
            .setQuery(query,TaskInfoModel::class.java)
            .build()
        taskAdapter = ProjectRecyclerViewAdapter(firestoreRecyclerOptions)

        val layout = LinearLayoutManager(context);
        val recyclerView = view?.findViewById<RecyclerView>(R.id.user_project_container)
        layout.orientation = RecyclerView.VERTICAL;
        recyclerView?.adapter = taskAdapter;
        recyclerView?.layoutManager = layout

        taskAdapter!!.setOnClickListener(object : ProjectRecyclerViewAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                //Toast.makeText(this@UserTask.context,"Clicked $position",Toast.LENGTH_SHORT).show()
//                var task_Dialog : Task_Dialog = Task_Dialog()
//                task_Dialog.show(childFragmentManager,"TestTaskDialog")
                val builder: AlertDialog.Builder = AlertDialog.Builder(this@UserTask.context)
                val inflater: LayoutInflater = layoutInflater
                val dialoglayout: View = inflater.inflate(R.layout.fragment_data_project, null)

                taskmodel.retriveTaskInfo("S7wDj0LYoeQOXgWHkiYp")

                var content = dialoglayout.findViewById<TextView>(R.id.taskContent)
                var sendertime = dialoglayout.findViewById<TextView>(R.id.taskSenderTime)
                var deadline = dialoglayout.findViewById<TextView>(R.id.taskDeadline)
                var sender = dialoglayout.findViewById<TextView>(R.id.taskSender)
                var status = dialoglayout.findViewById<TextView>(R.id.taskStatus)
                var infor = dialoglayout.findViewById<TextView>(R.id.taskInfor)

                // creating a variable for document reference.
                // creating a variable for document reference.
                val documentReference: DocumentReference = db.collection("task").document("S7wDj0LYoeQOXgWHkiYp")

                // adding snapshot listener to our document reference.

                // adding snapshot listener to our document reference.
                documentReference.addSnapshotListener(object : EventListener<DocumentSnapshot?> {
                    override fun onEvent(
                        @Nullable value: DocumentSnapshot?,
                        @Nullable error: FirebaseFirestoreException?
                    ) {
                        // inside the on event method.
                        if (error != null) {
                            // this method is called when error is not null
                            // and we gt any error
                            // in this cas we are displaying an error message.
                            Toast.makeText(
                                this@UserTask.context,
                                "Error found is $error",
                                Toast.LENGTH_SHORT
                            ).show()
                            return
                        }
                        if (value != null && value.exists()) {
                            // if the value from firestore is not null then we are
                            // getting our data and setting that data to our text view.
                            // after getting the value from firebase firestore
                            // we are setting it to our text view and image view.
                            content.text = value.data!!["Content"].toString()
//                            sendertime.text = "Thời gian giao việc: " + (value.data!!["SentDate"].toString())
//                            deadline.text = "Deadline: " + value.data!!["Deadline"].toString()
                            sender.text = "Người giao task: " + value.data!!["Sender"].toString()
                            status.text = "Tình trạng: " + value.data!!["Status"].toString()
                            infor.text = value.data!!["Infor"].toString()
                        }
                    }
                })
                builder.setView(dialoglayout)
                    .setPositiveButton("Oke"
                    ) { dialog, which -> }
                builder.show()
            }
        })
    }
    override fun onStart() {
        super.onStart()
        taskAdapter.startListening()
    }

    override fun onDestroy() {
        super.onDestroy()
        taskAdapter.stopListening()
    }
}
