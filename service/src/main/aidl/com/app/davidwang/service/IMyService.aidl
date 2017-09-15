package com.app.davidwang.service;

import com.app.davidwang.service.Student;

interface IMyService {

    List<Student> getStudent();
    void addStudent(in Student student);
}
