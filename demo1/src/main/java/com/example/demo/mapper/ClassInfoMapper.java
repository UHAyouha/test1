package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.ClassInfo;

/**
 * @author
 * @description 针对表【t_class_info(课程信息表)】的数据库操作Mapper
 * @createDate 2024-04-08 10:05:18
 * @Entity com.example.demo.entity.ClassInfo
 */
//TODO 已经使用了@MapperScan,无需使用Bean的注解, 其次, 注解使用错误, 如要使用,应使用@Mapper
//@Repository
public interface ClassInfoMapper extends BaseMapper<ClassInfo> {
}




