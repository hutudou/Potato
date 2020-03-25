package com.example.administrator.potato.util;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import com.example.administrator.potato.dao.StudentDao;
import com.example.administrator.potato.model.StudentModel;


/**
 * @author 0119 huwei
 * @date 2020/3/24
 */

@Database(entities = {StudentModel.class}, version = 1, exportSchema = false)
public abstract class DataBaseUtil extends RoomDatabase {
    private static final String DATABASE_NAME = "test.db";
    private static DataBaseUtil dataBaseUtil;

    /**
     * 单例获取DataBaseUtil
     */
    public static DataBaseUtil getDataBaseUtil(Context context) {
        if (dataBaseUtil == null) {
            dataBaseUtil = Room.databaseBuilder(context, DataBaseUtil.class, DATABASE_NAME).build();
        }
        return dataBaseUtil;
    }

    public abstract StudentDao studentDao();
}
