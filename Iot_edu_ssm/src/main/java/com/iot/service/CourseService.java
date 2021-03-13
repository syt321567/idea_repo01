package com.iot.service;

import com.iot.domain.Course;
import com.iot.domain.CourseVO;
import com.iot.domain.Teacher;

import java.util.List;


/*业务层的接口*/
public interface CourseService {


    public List<Course> findCourseByCondition(CourseVO courseVO);


    /*保存课程和教师的信息*/
    public  void saveCourseOrTeacher(CourseVO courseVO);


    /*根据id查询课程相关的信息*/
    public  CourseVO findCourseById(Integer id);


    /*更新课程的信息   更新教师的信息*/
    public  void  updateCourseOrTeacher(CourseVO courseVO);

    /*更新课程的状态信息*/
    public void  updateCourseStatus(int id ,int status);
}
