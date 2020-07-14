package com.intellyze.recharge.database.room
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.intellyze.recharge.database.model.DbOperator
import com.intellyze.recharge.database.model.DbPlans
import com.intellyze.recharge.database.room.dao.OperatorDao
import com.intellyze.recharge.database.room.dao.PlansDao

@Database(entities = arrayOf(
    DbOperator::class,DbPlans::class),
    version = 2, exportSchema = false)
abstract class RRoomDatabase : RoomDatabase() {
    abstract fun operatroDao(): OperatorDao
    abstract fun plansDao(): PlansDao
    companion object {
        @Volatile
        private var INSTANCE: RRoomDatabase? = null
         fun getDatabase(context: Context): RRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(RRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            RRoomDatabase::class.java, "recharge_db"
                        )  .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }


}
