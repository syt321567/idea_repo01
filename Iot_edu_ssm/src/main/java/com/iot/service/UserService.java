package com.iot.service;

import com.github.pagehelper.PageInfo;
import com.iot.domain.ResponseResult;
import com.iot.domain.Role;
import com.iot.domain.User;
import com.iot.domain.UserVO;

import java.util.List;

public interface UserService {


    public PageInfo findAllUserByPage(UserVO userVO);

    /*修改用户的状态*/

    public void updateUserStatus(int id, String status);


    /*用户登录*/
    public User login(User user) throws Exception;


    /*修改密码*/
    public void updateUserPassword(UserVO userVO);

    /*根据id查询给用户的角色的信息*/
    public List<Role> findUserRelationRoleById(int id);


    /*
    用户关联角色
    */
    void userContextRole(UserVO userVo);

    /*
     * 获取用户权限
     * */
    ResponseResult getUserPermissions(Integer id);


}
