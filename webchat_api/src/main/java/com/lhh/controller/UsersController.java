package com.lhh.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lhh.entity.auto.Users;
import com.lhh.idworker.Sid;
import com.lhh.service.IUsersService;
import com.lhh.utils.ResultInfo;
import com.lhh.utils.MD5Utils;
import com.lhh.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lihonghao
 * @since 2021-03-13
 */
@RestController
@RequestMapping("/users")
public class UsersController {
	@Autowired private IUsersService userService;

	@Autowired private Sid sid;
	@PostMapping("/getUser")
	public Users getUser() {
		return userService.getById(1);
	}

	@PostMapping("/queryByUserName")
	public Users queryByUserName() {
		QueryWrapper<Users> wrapper = new QueryWrapper<>();
		wrapper.eq("username", "admin");
		return userService.getOne(wrapper);
	}

	@PostMapping("/findAllUser")
	public List<Users> findAllUser() {
		return userService.findAllUser();
	}

	/***
	 * 用户登录和注册
	 *
	 */
	@PostMapping("/registerOrLogin")
	@ResponseBody
	public ResultInfo registerOrLogin(Users user){
		QueryWrapper<Users> wrapper = new QueryWrapper<>();
		wrapper.eq("username", user.getUsername());
		Users users = userService.getOne(wrapper);
		// 登录
		if(users != null){
			if(!users.getPassword().equals(MD5Utils.getPwd(user.getPassword()))){
				return ResultInfo.errorMsg("密码不正确");
			}
		}else{
			// 注册
			user.setUsername("王文");
			user.setNickname("");
			user.setQrcode("");
			user.setPassword(MD5Utils.getPwd(user.getPassword()));
			user.setFaceImage("");
			user.setFaceImageBig("");
			user.setId(sid.nextShort());
			userService.save(user);
		}
		UserVo userVo = new UserVo();
		// 可以进行copy
		BeanUtils.copyProperties(user,userVo);
		return ResultInfo.ok(userVo);
	}

}
