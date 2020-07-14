package com.intellyze.recharge.database.room.dao
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Query
import com.intellyze.recharge.database.model.DbOperator
import com.intellyze.recharge.database.model.DbPlans
import com.wisilica.database.room.dao.BaseDao


@Dao
interface OperatorDao : BaseDao<DbOperator> {
    @Query("SELECT * from tbl_operators")
    fun getAllOperator():  DataSource.Factory<Int, DbOperator>

    @Query("SELECT * from tbl_operators  WHERE type=:type")
    fun getOperatorByID(type:String):  DataSource.Factory<Int, DbOperator>



    @Query("DELETE FROM tbl_operators")
    fun deleteAll()

}