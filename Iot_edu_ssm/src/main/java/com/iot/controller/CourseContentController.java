package com.iot.controller;


import com.iot.domain.Course;
import com.iot.domain.CourseSection;
import com.iot.domain.ResponseResult;
import com.iot.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {

    @Autowired
    CourseContentService courseContentService;


    /*查找课程和章节和课时的信息*/
    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLessonByCourseId(Integer id) {

        List<CourseSection> sectionAndLesson = courseContentService.findSectionAndLessonByCourseId(id);

        ResponseResult result = new ResponseResult(true, 200, "响应成功", sectionAndLesson);
        return result;
    }


    /*根据课程的id 查找课程的相关的信息*/
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(@RequestParam int courseId) {

        Course course = courseContentService.findCourseByCourseId(courseId);

        ResponseResult result = new ResponseResult(true, 200, "响应成功", course);

        return result;

    }

    /*保存章节的信息*/
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveSection(@RequestBody CourseSection courseSection) {

        if (courseSection.getId() == null) {
            courseContentService.saveSection(courseSection);
            return new ResponseResult(true, 200, "响应成功", null);

        } else {
            courseContentService.updateSection(courseSection);
            return new ResponseResult(true, 200, "响应成功", null);

        }
    }



    /*修改章节的状态*/
    @RequestMapping("updateSectionStatus")
    public  ResponseResult updateSectionStatus(@RequestParam int id,@RequestParam int status){

        courseContentService.updateSectionStatus(id,status);

        //封装最新的状态信息
        Map<String,Integer> map = new HashMap<>(); map.put("status",status);

        return  new ResponseResult(true,200,"响应成功",map);
    }







}
