package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.UserClass;
import com.example.demo.entity.duo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author
 * @description 针对表【t_user_class(学生课程关系表)】的数据库操作Mapper
 * @createDate 2024-04-08 10:05:04
 * @Entity com.example.demo.entity.UserClass
 */
//TODO 已经使用了@MapperScan,无需使用Bean的注解, 其次, 注解使用错误, 如要使用,应使用@Mapper
//@Repository
public interface UserClassMapper extends BaseMapper<UserClass> {

    List<duo> getClassByUser(@Param("id") int id);


}




