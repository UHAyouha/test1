package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.UserClass;
import com.example.demo.entity.duo;
import com.example.demo.service.UserClassService;
import com.example.demo.mapper.UserClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 
* @description 针对表【t_user_class(学生课程关系表)】的数据库操作Service实现
* @createDate 2024-04-08 10:05:04
*/
@Service
public class UserClassServiceImpl extends ServiceImpl<UserClassMapper, UserClass>
    implements UserClassService{

    @Autowired
    private UserClassMapper mapper;

    @Override
    public List<duo> getClassByUser(int id){
        return mapper.getClassByUser(id);
    }

}




