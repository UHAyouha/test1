package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.ClassInfo;
import com.example.demo.service.ClassInfoService;
import com.example.demo.mapper.ClassInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author 
* @description 针对表【t_class_info(课程信息表)】的数据库操作Service实现
* @createDate 2024-04-08 10:05:18
*/
@Service
public class ClassInfoServiceImpl extends ServiceImpl<ClassInfoMapper, ClassInfo>
    implements ClassInfoService{

}




