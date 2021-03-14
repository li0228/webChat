package com.lhh.service.impl;

import com.lhh.entity.auto.Users;
import com.lhh.mapper.auto.UsersMapper;
import com.lhh.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lihonghao
 * @since 2021-03-13
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {
	@Autowired
	private UsersMapper usersMapper;
	@Override
	public List<Users> findAllUser() {
		return usersMapper.findAllUser();
	}
}
