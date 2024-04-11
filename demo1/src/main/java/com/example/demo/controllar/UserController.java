package com.example.demo.controllar;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.commen.Result;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
//TODO 没有注释的地方,请直接参看ClassInfoController和UserClassController的相关理由
public class UserController {
    @Autowired
    UserMapper userMapper;

    //新增用户数据
    @PostMapping("/add")
    public Result add(@RequestBody User user) {
        userMapper.insert(user);
        System.out.println(user);
        return Result.success();
    }

    //根据ID删除数据
    @DeleteMapping("/delete/ById")
    public Result byIdDelete(@RequestBody List<Integer> ids) {
        userMapper.deleteBatchIds(ids);
        return Result.success();
    }

    //根据信息删除表中数据
    @DeleteMapping("/delete/batch")
    public Result batchDelete(@RequestBody Map<String, Object> map) {
        userMapper.deleteByMap(map);
        return Result.success();
    }

    @PostMapping("update")
    public Result updateUser(@RequestBody User user) {
//        User userid = userMapper.selectById(user);
//        System.out.println("当前8号的年纪："+userid.getAge());
//        System.out.println("version"+userid.getVersion());
//        userid.setAge(userid.getAge()+1);
//        userMapper.updateById(userid);
//        System.out.println("当前8号的年纪："+userid.getAge());
//        System.out.println("version"+userid.getVersion());
        User userid = userMapper.selectById(user);
        int i = userid.getVersion();
//        userid.setAge(user.getAge());
//        userid.setUserType(user.getUserType());
//        userid.setName(user.getName());
        userid = user;
        userid.setVersion(i);
        System.out.println("version" + userid.getVersion());
        userMapper.updateById(userid);
        System.out.println("version" + userid.getVersion());
        return Result.success();
    }

    @GetMapping("selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        User userDate = userMapper.selectById(id);
        return Result.success(userDate);
    }

    @GetMapping("selectBatchById")
    public Result selectBatchById(@RequestBody List<Integer> ids) {
        List<User> userDate = userMapper.selectBatchIds(ids);
        return Result.success(userDate);
    }

    @GetMapping("selectMap")
    public Result selectMap(@RequestBody Map<String, Object> map) {
        List<User> userDate = userMapper.selectByMap(map);
        return Result.success(userDate);
    }

    @GetMapping("/selectByLike")
    public Result selectByLike(@RequestParam String name, @RequestParam String age1, @RequestParam String age2) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", name)
                .between("age", age1, age2);
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
        return Result.success(list);
    }

    @GetMapping("page")
    public Result testPage() {
        Page<User> page = new Page<>(1, 3);
        userMapper.selectPage(page, null);
        return Result.success(page);
    }
}
