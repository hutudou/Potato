package com.example.administrator.potato.model;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * 测试的学生表
 *
 * @author 0119 huwei
 * @date 2020/3/24
 */
@Entity(tableName = "student")
public class StudentModel {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "student_id")
    public long studentId;

    @ColumnInfo(name = "student_name")
    public String studentName;

    @ColumnInfo(name = "student_age")
    public int studentAge;

    @ColumnInfo(name = "student_address")
    public String studentAddress;


    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAge(int studentAge) {
        this.studentAge = studentAge;
    }

    public int getStudentAge() {
        return studentAge;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public long getStudentId() {
        return studentId;
    }

}
