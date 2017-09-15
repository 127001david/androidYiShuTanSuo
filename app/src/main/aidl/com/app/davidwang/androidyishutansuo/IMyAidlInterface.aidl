// IMyAidlInterface.aidl
package com.app.davidwang.androidyishutansuo;

import com.app.davidwang.androidyishutansuo.Student;

interface IMyAidlInterface {
    int getPid();
    void addStudent(in Student student);
    List<Student> getStudents();
}
