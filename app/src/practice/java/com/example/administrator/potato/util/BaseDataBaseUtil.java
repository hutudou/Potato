package com.example.administrator.potato.util;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.administrator.potato.dao.StudentDao;
import com.example.administrator.potato.model.StudentModel;


/**
 * 频繁的创建数据库的实例是很耗资源的 因此使用单例模式 将数据库的实例作为全局共用
 * 这里采用线程安全的双重检查锁来实现
 *
 * @author potato
 * @date 2020/3/24
 */

@Database(entities = {StudentModel.class}, version = 1, exportSchema = false)
public abstract class BaseDataBaseUtil extends RoomDatabase {
    private static final String DATABASE_NAME = "test.db";
    private volatile static BaseDataBaseUtil dataBaseUtil = null;

    /**
     * 单例获取DataBaseUtil的实例
     */
    public static BaseDataBaseUtil getDataBaseUtil(Context context) {
        if (dataBaseUtil == null) {
            synchronized (BaseDataBaseUtil.class) {
                if (dataBaseUtil == null) {
                    dataBaseUtil = Room.databaseBuilder(context, BaseDataBaseUtil.class, DATABASE_NAME).build();
                }
            }
        }
        return dataBaseUtil;
    }

    public abstract StudentDao studentDao();
}
