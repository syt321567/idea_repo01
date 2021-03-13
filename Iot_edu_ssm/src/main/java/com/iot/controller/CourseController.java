package com.iot.controller;


import com.iot.domain.Course;
import com.iot.domain.CourseVO;
import com.iot.domain.ResponseResult;
import com.iot.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MultipartFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*表现层*/
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    /*根据条件课程的信息*/
    @RequestMapping("/findCourseByCondition")
    public ResponseResult findCourseByCondition(@RequestBody CourseVO courseVO) {


        List<Course> coursebByCondition = courseService.findCourseByCondition(courseVO);

        return new ResponseResult(true, 0, "响应成功", coursebByCondition);
    }

    /*图片上传的接口*/
    @RequestMapping("/courseUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {

        //1.判断文件是否为空
        if (file.isEmpty()) {
            throw new RuntimeException();
        }
        /*获取项目的部署路径*/
        //Volumes/c/tomcat/webapps/Iot_edu_ssm_war/
        String realPath = request.getServletContext().getRealPath("/");

        //截取需要的路径//Volumes/c/tomcat/webapps
        String webappsPath = realPath.substring(0, realPath.indexOf("Iot_edu_ssm"));

        //获取文件的文件名
        String fileName = file.getOriginalFilename();


        //设置新的文件的名字
        String newFileName = System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."));

        //上传文件
        String uploadPath = webappsPath + "upload/";
        File filePath = new File(uploadPath, newFileName);

        //如果目录不存在就创建目录
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录: " + filePath);
        }

        file.transferTo(filePath);

        //6.将文件名和文件路径返回

        Map<String, String> map = new HashMap<>();
        map.put("fileName", newFileName);
        map.put("filePath", "http://localhost:8080/upload/" + newFileName);

        ResponseResult result = new ResponseResult(true, 200, "响应成功", map);

        return result;


    }

    /*保存或修改课程的信息*/
    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVO courseVO){

        /*如果id为null 就是 添加课程的操作*/
        if(courseVO.getId()==null){
            courseService.saveCourseOrTeacher(courseVO);
            ResponseResult result=new ResponseResult(true,200,"响应成功",null);
            return  result;
        }else {
            courseService.updateCourseOrTeacher(courseVO);
            ResponseResult result=new ResponseResult(true,200,"响应成功",null);

            return   result;
        }

    }

    /*通过id 查询课程的信息*/
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById( Integer id){
        CourseVO course= courseService.findCourseById(id);

         ResponseResult responseResult=new ResponseResult(true,200,"查询成功",course);

         return  responseResult;

    }


    /*更新课程的状态的信息*/
    @RequestMapping("updateCourseStatus")
    public ResponseResult  updateCourseStatus(@RequestParam int id,@RequestParam int status){

        /*修改课程的状态*/
        courseService.updateCourseStatus(id,status);

        /*保存修改后的状态 并且返回*/
        HashMap<String,Integer> map=new HashMap<>();

        map.put("status",status);


        //返回响应的结果
        ResponseResult result=new ResponseResult(true,200,"响应成功",map);
        return  result;

    }

}
