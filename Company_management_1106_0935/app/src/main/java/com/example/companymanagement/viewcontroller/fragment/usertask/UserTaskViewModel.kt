package com.example.companymanagement.viewcontroller.fragment.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.companymanagement.model.TaskInfoRepository
import com.example.companymanagement.model.TaskInfoModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class UserTaskViewModel : ViewModel() {
    var info: MutableLiveData<TaskInfoModel> = MutableLiveData();
    var repo = TaskInfoRepository(FirebaseFirestore.getInstance().collection("task"))

    fun retriveTaskInfo(uuid: String) {
        viewModelScope.launch {
            info.postValue(repo.findDoc(uuid));
        }
    }
}
