package com.intellyze.recharge.database.model
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "tbl_operators")
class DbOperator {
    @PrimaryKey
    var ids : Long? = null;
    var createdAt: String? = null
    var logoUrls: String? = null
    var name: String? = null
    var type: String? = null
    var operatorId: String? = null
}

