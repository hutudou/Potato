package com.example.administrator.potato.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.administrator.potato.model.StudentModel;

import java.util.List;

import io.reactivex.Flowable;


/**
 * @author potato
 * @date 2020/3/24
 */
@Dao
public interface StudentDao {
    /**
     * 查询所有的学生信息 普通版本
     */
    @Query("select * from student")
    List<StudentModel> getAllStudentsByNormal();


    /**
     * 查询所有的学生信息 rxjava版
     */
    @Query("SELECT * FROM student")
    Flowable<List<StudentModel>> getAllStudentsByRxjava();

    /**
     * 插入多条数据
     * @return 插入的数据所在的行数Id集合
     */
    @Insert
    List<Long> insertAll(StudentModel... studentModels);

    /**
     * 删除指定的数据项
     *
     * @param studentModels
     * @return 被删除的行总数
     */
    @Delete
    int delete(StudentModel... studentModels);

    /**
     * 更改指定项
     *
     * @return 修改的行总数
     */
    @Update
    int update(StudentModel... studentModels);
}
