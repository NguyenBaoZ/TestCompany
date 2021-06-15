package com.example.companymanagement.model

import androidx.annotation.Keep
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.PropertyName
import com.google.firebase.firestore.ServerTimestamp
import java.util.*

@Keep
data class TaskInfoModel(
    @get: PropertyName("content")
    @set: PropertyName("content")
    var Content: String? = null,

    @get: PropertyName("deadline")
    @set: PropertyName("deadline")
    var Deadline: Date? = null,

    @get: PropertyName("sent_time")
    @set: PropertyName("sent_time")
    var SentTime: Date? = null,

    @get: PropertyName("sender")
    @set: PropertyName("sender")
    var Sender: String? = null,

    @get: PropertyName("status")
    @set: PropertyName("status")
    var Status: String? = null,

    @get: PropertyName("infor")
    @set: PropertyName("infor")
    var Infor: String? = null,

){
    @DocumentId
    val taskid: String? = null

    //document id shall auto parse from doc by to object function . it should be unsetable

    @ServerTimestamp
    @get: PropertyName("create_time")
    @set: PropertyName("create_time")
    var CreateTime: Date? = null

    @ServerTimestamp
    @get: PropertyName("update_time")
    @set: PropertyName("update_time")
    var UpdateTime: Date? = null

    init {
        CreateTime = Date();
        UpdateTime = Date();
    }
}