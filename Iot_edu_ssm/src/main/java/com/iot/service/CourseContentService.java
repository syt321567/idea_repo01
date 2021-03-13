package com.iot.service;

import com.iot.domain.Course;
import com.iot.domain.CourseSection;

import java.util.List;

public interface CourseContentService {


    /**
     * 查询课程下的章节与课时信息
     * * */
    public List<CourseSection> findSectionAndLessonByCourseId(int id);

    /*根据id查询课程信息*/
    public Course findCourseByCourseId( Integer id);

    /*保存章的信息*/
    public void saveSection(CourseSection courseSection);

    /*修改章节的信息*/
    public    void updateSection(CourseSection courseSection);

    /*修改章节的状态*/
    public  void updateSectionStatus(int id, int status);
}
