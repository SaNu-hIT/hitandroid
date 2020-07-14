package com.intellyze.recharge.database.room

import android.content.Context
import androidx.paging.PagedList
import androidx.room.Room

class DbPaginationConfig {
    companion object {
        fun getConfig(): PagedList.Config {
            val pagedListConfig = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(100)
                .setPrefetchDistance(5)
                .setPageSize(100).build()
            return pagedListConfig
        }

    }
}