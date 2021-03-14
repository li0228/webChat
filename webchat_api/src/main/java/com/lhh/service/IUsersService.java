package com.lhh.service;

import com.lhh.entity.auto.Users;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lihonghao
 * @since 2021-03-13
 */
public interface IUsersService extends IService<Users> {
	List<Users> findAllUser();
}
