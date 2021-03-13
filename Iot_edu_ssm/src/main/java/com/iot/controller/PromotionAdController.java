package com.iot.controller;

import com.github.pagehelper.PageInfo;
import com.iot.domain.PromotionAd;
import com.iot.domain.PromotionAdVo;
import com.iot.domain.ResponseResult;
import com.iot.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {

    @Autowired
    PromotionAdService promotionAdService;

    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllPromotionAdByPage(PromotionAdVo promotionAdVo) {


        PageInfo promotionAdByPage = promotionAdService.findPromotionAdByPage(promotionAdVo);

        return new ResponseResult(true, 200, "响应成功", promotionAdByPage);
    }


    /*广告的图片的上传 */
    @RequestMapping("/PromotionAdUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file,
                                     HttpServletRequest request) throws IOException {

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


    /*根据id查询广告的信息*/
    @RequestMapping("/findPromotionAdById")
    public  ResponseResult findPromotionAdById(@RequestParam int id){
        PromotionAd promotionAd = promotionAdService.findPromotionAdById(id);

        return new ResponseResult(true,200,"响应成功",promotionAd);

    }


    /*添加或者修改广告信息*/
    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd(@RequestBody PromotionAd promotionAd) {

        //id为空就是添加的操作  id不为空就是  修改
        if (promotionAd.getId() == null) {

            promotionAdService.savePromotionAd(promotionAd);

            return new ResponseResult(true, 200, "响应成功", null);
        } else {
            promotionAdService.updatePromotionAd(promotionAd);

            return new ResponseResult(true, 200, "响应成功", null);

        }

    }

    /*修改广告状态*/
    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionAdStatus(@RequestParam int id,@RequestParam int status){

        promotionAdService.updatePromotionAdStatus(id,status);


        /*保存修改后的状态 并且返回*/
        HashMap<String,Integer> map=new HashMap<>();

        map.put("status",status);

        return   new ResponseResult(true,200,"响应成功",map);


    }


}
