package com.intellyze.recharge.database.room.dao
import androidx.room.Dao
import androidx.room.Query
import androidx.paging.DataSource
import com.intellyze.recharge.database.model.DbPlans
import com.wisilica.database.room.dao.BaseDao

@Dao
interface PlansDao : BaseDao<DbPlans> {
    @Query("SELECT * from tbl_plans")
    fun getPlans(): DataSource.Factory<Int, DbPlans>

    @Query("SELECT * from tbl_plans WHERE operator= :operator")
    fun getPlansByOperator(operator: String?): DataSource.Factory<Int, DbPlans>

    @Query("DELETE FROM tbl_plans")
    fun deleteAll()
}