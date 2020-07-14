package com.intellyze.recharge.database.room.repo

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.intellyze.recharge.database.model.DbPlans
import com.intellyze.recharge.database.room.RRoomDatabase
import com.intellyze.recharge.database.room.dao.PlansDao
import java.util.concurrent.Callable
import java.util.concurrent.Executors

class PlansRepository(application: Application) {
    var plansDao: PlansDao?

    init {
        val db = RRoomDatabase.getDatabase(application)
        plansDao = db?.plansDao()
    }
    fun getAllPlans(): LiveData<PagedList<DbPlans>> {
        val callable = Callable<LiveData<PagedList<DbPlans>>> {
            plansDao?.getPlans()?.toLiveData(pageSize = 50)
        }
        val service = Executors.newSingleThreadExecutor()
        val f = service.submit<LiveData<PagedList<DbPlans>>>(callable)
        return f.get()
    }

    fun getPlansByOperator(operator: String?): LiveData<PagedList<DbPlans>> {
        val callable = Callable<LiveData<PagedList<DbPlans>>> {
            plansDao?.getPlansByOperator(operator)?.toLiveData(pageSize = 50)
        }
        val service = Executors.newSingleThreadExecutor()
        val f = service.submit<LiveData<PagedList<DbPlans>>>(callable)
        return f.get()
    }
//    fun getPlansByOperator(operator: String): List<DbPlans> {
//        val callable = Callable<List<DbPlans>> {
//            plansDao?.getPlansByOperator(operator)
//        }
//        val service = Executors.newSingleThreadExecutor()
//        val f = service.submit<List<DbPlans>>(callable)
//        return f.get()
//    }
    fun deleteAll() {
        plansDao?.deleteAll()
    }


}