package com.example.demo.service;

import com.example.demo.entity.UserClass;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.duo;

import java.util.List;

/**
* @author 
* @description 针对表【t_user_class(学生课程关系表)】的数据库操作Service
* @createDate 2024-04-08 10:05:04
*/
public interface UserClassService extends IService<UserClass> {

    List<duo> getClassByUser(int id);
}
