package com.lhh.mapper.auto;

import com.lhh.entity.auto.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lihonghao
 * @since 2021-03-13
 */
@Mapper
public interface UsersMapper extends BaseMapper<Users> {
	List<Users> findAllUser();
}
