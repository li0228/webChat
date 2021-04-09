package com.lhh.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lhh.bo.UserBO;
import com.lhh.entity.auto.Users;
import com.lhh.idworker.Sid;
import com.lhh.service.IUsersService;
import com.lhh.utils.FastDFSClient;
import com.lhh.utils.FileUtils;
import com.lhh.utils.ResultInfo;
import com.lhh.utils.MD5Utils;
import com.lhh.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

	@Autowired private FastDFSClient fastDFSClient;

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
		BeanUtils.copyProperties(users,userVo);
		return ResultInfo.ok(userVo);
	}

	@RequestMapping("/setNickname")
	@ResponseBody
	public ResultInfo setNickname(Users user){
		if(userService.updateById(user)){
			return ResultInfo.ok(userService.getById(user.getId()));
		}else{
			return ResultInfo.errorMsg("修改失败！");
		}
	}

	/**
	 * 图片上传
	 *
	 * @return
	 */
	@RequestMapping("/uploadFace")
	@ResponseBody
	public ResultInfo uploadPage(UserBO user) throws Exception {
		if(user == null || user.getUserId() == null || user.getFaceData() == null){
			return ResultInfo.errorMsg("上传失败");
		}

		// 获取base64字符串，转为文件对象再上传
		String base64Data = user.getFaceData();
		String userFacePath = "D:\\"+user.getUserId()+"userFaceBase64.png";

		// 调用FIleUtils类中的base64转为文件对象
		FileUtils.base64ToFile(userFacePath,base64Data);
		MultipartFile multipartFile = FileUtils.fileToMultipart(userFacePath);

		// 上传
		String path = fastDFSClient.uploadBase64(multipartFile);

		System.out.println(path);
		// 缩略图后缀
		String thump = "_150x150.";
		String[] arr = path.split("\\.");
		String thumpImgUrl = arr[0]+thump+arr[1];
		//更新用户头像
		Users users = new Users();
		users.setId(user.getUserId());
		users.setFaceImageBig(thumpImgUrl);
		users.setFaceImage(path);

		userService.updateById(users);

		return ResultInfo.ok(users);
	}

}
