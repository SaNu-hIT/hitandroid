package com.intellyze.recharge.database.room.repo

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.intellyze.recharge.database.model.DbOperator
import com.intellyze.recharge.database.room.RRoomDatabase
import com.intellyze.recharge.database.room.dao.OperatorDao
import java.util.concurrent.Callable
import java.util.concurrent.Executors

class OperatorRepository(application: Application) {
    var operatorDao: OperatorDao?

    init {
        val db = RRoomDatabase.getDatabase(application)
        operatorDao = db?.operatroDao()
    }

    fun getAllOperaor(): LiveData<PagedList<DbOperator>> {
        val callable = Callable<LiveData<PagedList<DbOperator>>> {
            operatorDao?.getAllOperator()?.toLiveData(pageSize = 50)
        }
        val service = Executors.newSingleThreadExecutor()
        val f = service.submit<LiveData<PagedList<DbOperator>>>(callable)
        return f.get()
    }


    fun getOperatorById(type:String): LiveData<PagedList<DbOperator>> {
        val callable = Callable<LiveData<PagedList<DbOperator>>> {
            operatorDao?.getOperatorByID(type)?.toLiveData(pageSize = 50)
        }
        val service = Executors.newSingleThreadExecutor()
        val f = service.submit<LiveData<PagedList<DbOperator>>>(callable)
        return f.get()
    }


    fun deleteAll() {
        operatorDao?.deleteAll()
    }


}