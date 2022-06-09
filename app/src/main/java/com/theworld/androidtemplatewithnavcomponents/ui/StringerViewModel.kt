package com.theworld.androidtemplatewithnavcomponents.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.theworld.androidtemplatewithnavcomponents.data.stringer.request.CreateStringerRequestData
import com.theworld.androidtemplatewithnavcomponents.data.stringer.request.DeleteStringerRequestData
import com.theworld.androidtemplatewithnavcomponents.data.stringer.request.EditStringerRequestData
import com.theworld.androidtemplatewithnavcomponents.data.stringer.response.Stringer
import com.theworld.androidtemplatewithnavcomponents.network.Api
import com.theworld.androidtemplatewithnavcomponents.utils.Resource
import com.theworld.androidtemplatewithnavcomponents.utils.SafeApiCall
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StringerViewModel @Inject constructor(
    private val api: Api,
) : ViewModel(), SafeApiCall {

    companion object {
        private const val TAG = "StringerViewModel"
    }

    private var _isChanged = MutableSharedFlow<Boolean>()
    val isChanged = _isChanged.asSharedFlow()

    private var _stringer =
        MutableStateFlow<Resource<List<Stringer>>>(Resource.Loading)
    val stringer = _stringer.asStateFlow()

    init {
        fetchStringer()
    }


    fun isDataChanged() = viewModelScope.launch {
        _isChanged.emit(true)
    }


/*------------------------------------ Change Password -----------------------------------------*/

    fun fetchStringer() = flow {
        val list = safeApiCall {
            api.fetchStringers()
        }

//        _stringer.emit(list)
        emit(list)
    }.flowOn(Dispatchers.IO)
        .conflate()


    fun createStringer(data: CreateStringerRequestData) = flow {
        val list = safeApiCall { api.createStringer(data) }
        emit(list)
        fetchStringer()
    }.flowOn(Dispatchers.IO)
        .conflate()


    fun updateStringer(data: EditStringerRequestData) = flow {
        val list = safeApiCall { api.updateStringer(data) }
        emit(list)
        fetchStringer()
    }.flowOn(Dispatchers.IO)
        .conflate()


    fun deleteStringer(data: DeleteStringerRequestData) = flow {
        val list = safeApiCall { api.deleteStringer(data) }
        emit(list)
//        fetchStringer()
//        _isDataChanged.emit(true)
    }.flowOn(Dispatchers.IO)
        .conflate()

}
