package com.example.administrator.potato.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.administrator.potato.model.StudentModel;

import java.util.List;

import io.reactivex.Flowable;


/**
 * @author 0119 huwei
 * @date 2020/3/24
 */
@Dao
public interface StudentDao {
    /**
     * 查询所有的学生信息
     */
    @Query("SELECT * FROM student")
    Flowable<List<StudentModel>> getAllStudents();

    /**
     * 插入多条数据
     */
    @Insert
    void insertAll(StudentModel... studentModels);
}
