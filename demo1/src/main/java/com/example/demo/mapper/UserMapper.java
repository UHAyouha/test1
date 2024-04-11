package com.example.demo.mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author 
* @description 针对表【t_user_info】的数据库操作Mapper
* @createDate 2024-04-08 10:02:39
* @Entity com.example.demo.entity.User
*/
//TODO 已经使用了@MapperScan,无需使用Bean的注解, 其次, 注解使用错误, 如要使用,应使用@Mapper
//@Repository
public interface UserMapper extends BaseMapper<User> {

}




