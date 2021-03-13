package com.iot.controller;


/*用户控制器*/

import com.github.pagehelper.PageInfo;
import com.iot.domain.ResponseResult;
import com.iot.domain.Role;
import com.iot.domain.User;
import com.iot.domain.UserVO;
import com.iot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    /*根据条件查询  分页查询*/
    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVO userVO) {


        PageInfo<User> allUserByPage = userService.findAllUserByPage(userVO);

        return new ResponseResult(true, 200, "响应成功", allUserByPage);

    }


    /*修改用户的状态*/
    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(@RequestParam int id, @RequestParam String status) {

        userService.updateUserStatus(id, status);

        HashMap map = new HashMap();
        map.put("status", status);
        return new ResponseResult(true, 200, "响应成功", map);
    }


    /*用户登录的判断*/
    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) {

        ResponseResult result = null;
        try {
            User login = userService.login(user);
            if (login != null) {
                //保存access_token
                Map<String, Object> map = new HashMap<>();
                String access_token = UUID.randomUUID().toString();
                map.put("access_token", access_token);
                map.put("user_id", login.getId());
                HttpSession session = request.getSession();
                session.setAttribute("user_id", login.getId());
                session.setAttribute("access_token", access_token);
                result = new ResponseResult(true, 1, "响应成功", map);

            } else {
                result = new ResponseResult(true, 1, "用户名密码错误", null);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /*修改用户的信息*/
    @RequestMapping("/updateUserPassword")
    public ResponseResult updateUserPassword(@RequestBody UserVO userVO){

        userService.updateUserPassword(userVO);
        return new ResponseResult(true,200,"修改成功",null);

    };

    /*查询用户角色的信息*/
    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRelationRoleById(@RequestParam int id) {

        List<Role> roles = userService.findUserRelationRoleById(id);

        return new ResponseResult(true, 200, "分配角色回显成功", roles);


    }


    /*分配角色的信息*/
    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVO userVO) {

        userService.userContextRole(userVO);

        return new ResponseResult(true, 200, "分配成功", null);
    }


    /**
     * 获取用户权限 *
     */
    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request) {

        //获取请求头中的 token
        String authorization = request.getHeader("Authorization");

        //获取session中的access_token
        HttpSession session = request.getSession();
        String access_token = (String) session.getAttribute("access_token");

        //判断
        if (authorization.equals(access_token)) {
            int user_id = (Integer) session.getAttribute("user_id");
            ResponseResult result = userService.getUserPermissions(user_id);
            return result;
        } else {
            ResponseResult result = new ResponseResult(false, 400, "获取失败", "");
            return result;
        }
    }

}
