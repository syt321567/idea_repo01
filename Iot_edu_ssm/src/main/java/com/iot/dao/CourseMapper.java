package com.iot.dao;

import com.iot.domain.Course;
import com.iot.domain.CourseVO;
import com.iot.domain.Teacher;

import java.util.List;

public interface CourseMapper {


    /*根据条件查询课程*/
       public List<Course> findCourseByCondition(CourseVO courseVO);


     /*保存课程的信息*/
    public void saveCourse(Course coruse);



    /*保存教师的信息*/
    public  void saveTeacher(Teacher teacher);


    /*根据id查询课程相关的信息*/
    public  CourseVO findCourseById(Integer id);

    /*更新课程的信息*/
    public void updateCourse(Course course);


    /*更新教师的信息*/
    public  void updateTeacher(Teacher teacher);



    /*更新课程的状态信息*/
    public  void  updateCourseStatus(Course course);



}
