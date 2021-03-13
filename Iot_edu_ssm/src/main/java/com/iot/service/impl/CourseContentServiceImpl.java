package com.iot.service.impl;

import com.iot.dao.CourseContentMapper;
import com.iot.domain.Course;
import com.iot.domain.CourseSection;
import com.iot.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseContentServiceImpl implements CourseContentService {

    @Autowired
    CourseContentMapper courseContentMapper;

    @Override
    public List<CourseSection> findSectionAndLessonByCourseId(int id) {

        return courseContentMapper.findSectionAndLessonByCourseId(id);
    }

    @Override
    public Course findCourseByCourseId(Integer id) {
        return courseContentMapper.findCourseByCourseId(id);
    }

    @Override
    public void saveSection(CourseSection courseSection) {

        /*更新时间*/
        Date date = new Date();
        courseSection.setUpdateTime(date);
        courseSection.setCreateTime(date);

        courseContentMapper.saveSection(courseSection);
    }

    @Override
    public void updateSection(CourseSection courseSection) {


        /*补全课程的信息*/
        courseSection.setUpdateTime(new Date());

        courseContentMapper.updateSection(courseSection);

    }

    @Override
    public void updateSectionStatus(int id, int status) {
        CourseSection section = new CourseSection();

        section.setId(id);

        section.setStatus(status);

        section.setUpdateTime(new Date());

        courseContentMapper.updateSectionStatus(section);

    }


}
