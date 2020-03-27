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
    /**
     * 数据库的名字
     */
    private static final String DATABASE_NAME = "test.db";
    /**
     * 使用volatile来确保 每个线程获取dataBaseUtil实例的时候都能拿到最新的值
     */
    private volatile static BaseDataBaseUtil dataBaseUtil = null;

    /**
     * 单例获取DataBaseUtil的实例
     */
    public static BaseDataBaseUtil instance(Context context) {
        if (dataBaseUtil == null) {
            synchronized (BaseDataBaseUtil.class) {
                if (dataBaseUtil == null) {
                    //如果想要允许在主线程中查询 需要设置这个属性allowMainThreadQueries
                    //但是，请勿在主线程中做任何耗时的操作，不然很容易程序未响应
//                    dataBaseUtil = Room.databaseBuilder(context, BaseDataBaseUtil.class, DATABASE_NAME).build();
                    dataBaseUtil = Room.databaseBuilder(context, BaseDataBaseUtil.class, DATABASE_NAME).allowMainThreadQueries().build();
                }
            }
        }
        return dataBaseUtil;
    }

    /**
     * 学生表dao文件
     *
     * @return
     */
    public abstract StudentDao studentDao();
}
