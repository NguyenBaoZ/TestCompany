package com.example.companymanagement.model

import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.tasks.await
import java.util.*

class TaskInfoRepository(var col: CollectionReference) {
    // apply couroutine
    // it should be handle with try catch if a call is cancelation , for some purpose everyone also know, i throw them.But u guy should implement it
    suspend fun addDoc(uuid: String) {
        col.add(uuid).await();
    }

    suspend fun findDoc(uuid: String): TaskInfoModel? {
        return col.document(uuid).get().await().toObject(TaskInfoModel::class.java)
    }

    suspend fun updateDoc(info: TaskInfoModel) {
        info.UpdateTime = Date();
        col.document(info.taskid!!).set(info).await()
    }

    suspend fun deleteDoc(info: TaskInfoModel) {
        col.document(info.taskid!!).delete().await()
    }
}