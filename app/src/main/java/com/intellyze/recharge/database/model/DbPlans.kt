package com.intellyze.recharge.database.model
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.intellyze.recharge.cloud.response.plans.PlansResponse

@Entity(tableName = "tbl_plans")
class DbPlans {

    @PrimaryKey
    var ids: Long? = null
    var description: String? = null
    var operator: String? = null
    var planType: String? = null
    var price: String? = null
    var talktime: String? = null
    var validity: String? = null

    fun postSuccess(): DbPlans {
        return this
    }
}