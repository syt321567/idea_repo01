package com.iot.dao;

import com.iot.domain.Course;
import com.iot.domain.CourseSection;

import java.util.List;

public interface CourseContentMapper {


        /**
         * 查询课程下的章节与课时信息
         * * */

        public List<CourseSection> findSectionAndLessonByCourseId(int id);


        /*根据id查询课程信息*/
        public Course findCourseByCourseId( Integer id);



        /*新建章节的信息*/
        public  void saveSection(CourseSection courseSection);


        /*修改章节的信息*/

        public  void updateSection(CourseSection courseSection);

        /*修改章节的状态id=7&status=1*/
        public  void  updateSectionStatus(CourseSection courseSection);


}
