package com.lhh.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author lihonghao
 * @date 2021/3/13 17:39
 */
@Data
@Accessors(chain = true)
public class UserVo {

	/**
	 * 用户id
	 */
	private String id;

	/**
	 * 用户名，账号，慕信号
	 */
	private String username;

	/**
	 * 我的头像，如果没有默认给一张
	 */
	private String faceImage;

	private String faceImageBig;

	/**
	 * 昵称
	 */
	private String nickname;

	/**
	 * 新用户注册后默认后台生成二维码，并且上传到fastdfs
	 */
	private String qrcode;

	private String cid;

}

