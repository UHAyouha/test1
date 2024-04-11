package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Demo1ApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Test
    public void testInsert(){
        User user = new User();
        user.setId(2);
        user.setAge(3);
        user.setName("sbb");
        user.setUserType(1);
        userMapper.insert(user);
    }
}
