package com.iot.service.impl;

import com.iot.dao.CourseMapper;
import com.iot.domain.Course;
import com.iot.domain.CourseVO;
import com.iot.domain.Teacher;
import com.iot.service.CourseService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

/*业务层的实现类*/
@Service
@Transactional
public class CourseServiceImpl implements CourseService  {

    @Autowired
    private CourseMapper courseMapper;


    /*根据条件查询课程的信息*/
    @Override
    public List<Course> findCourseByCondition(CourseVO courseVO) {

        return courseMapper.findCourseByCondition(courseVO);

    }

    /*保存课程和教师的信息*/
    @Override
    public void saveCourseOrTeacher(CourseVO courseVO) {

        //封装课程和教师的信息
        try {
            Course course = new Course();

            //设置日期时间
            Date date = new Date();
            course.setCreateTime(date);
            course.setUpdateTime(date);
            /*将CourseVO的course的信息 封装到course中*/
            BeanUtils.copyProperties(course, courseVO);

            //保存课程的信息
            courseMapper.saveCourse(course);

            /*测试  course的id 值存在所以  要封装教师的信息*/

            Teacher teacher = new Teacher();
           teacher.setCreateTime(date);
            teacher.setUpdateTime(date);
            teacher.setCourseId(course.getId());
            //封装教师类

            BeanUtils.copyProperties(teacher, courseVO);
            courseMapper.saveTeacher(teacher);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    /*根据id查询课程相关的信息*/
    @Override
    public CourseVO findCourseById(Integer id) {
      return   courseMapper.findCourseById(id);
    }



    /*更新课程的状态的信息*/
    @Override
    public void updateCourseStatus(int id, int status) {

        Course course=new Course();
        Date date=new Date();
        course.setId(id);
        course.setStatus(status);
        course.setUpdateTime(date);

        /*更新课程的状态的信息*/
      courseMapper.updateCourseStatus(course);

    }

    @Override
    /*更新课程的信息   更新教师的信息*/
    public  void  updateCourseOrTeacher(CourseVO courseVO){

        try {

            /*将前台发送的courseVO的信息 保存到 课程中*/
            Course course=new Course();
            BeanUtils.copyProperties(course,courseVO);

            /*封装时间的属性*/
            Date date=new Date();
            course.setUpdateTime(date);
            course.setUpdateTime(date);

            //更新课程的信息
            courseMapper.updateCourse(course);

            /*更新教师信息*/
            Teacher teacher=new Teacher();

            //封装教师的信息
            BeanUtils.copyProperties(teacher,courseVO);
            teacher.setUpdateTime(date);
            teacher.setCourseId(course.getId());

            //更新数据
            courseMapper.updateTeacher(teacher);
        } catch (Exception e) {
            e.printStackTrace();
        }

    };


}
