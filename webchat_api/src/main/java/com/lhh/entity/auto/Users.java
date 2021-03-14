package com.lhh.entity.auto;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author lihonghao
 * @since 2021-03-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Users extends Model {

	private static final long serialVersionUID = 1L;

	private String id;

	/**
	 * 用户名，账号，慕信号
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;

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
