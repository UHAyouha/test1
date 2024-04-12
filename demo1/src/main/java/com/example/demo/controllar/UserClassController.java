package com.example.demo.controllar;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.commen.Result;
import com.example.demo.entity.UserClass;
import com.example.demo.entity.duo;
import com.example.demo.mapper.UserClassMapper;
import com.example.demo.service.UserClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/userClass")
//TODO 没有注释的地方,请直接参看ClassInfoController的相关理由
public class UserClassController {
    @Autowired(required = false)
    UserClassMapper userClassMapper;



    @Autowired
    private UserClassService service;

    //新增用户数据
    @PostMapping("/add")
    public Result add(@RequestBody UserClass user) {
        userClassMapper.insert(user);
        System.out.println(user);
        return Result.success();
    }

    //根据ID删除数据
    @DeleteMapping("/delete/ById")
    public Result byIdDelete(@RequestBody List<Integer> ids) {
        userClassMapper.deleteBatchIds(ids);
        return Result.success();
    }

    //根据信息删除表中数据
    @DeleteMapping("/delete/batch")
    public Result batchDelete(@RequestBody Map<String, Object> map) {
        userClassMapper.deleteByMap(map);
        return Result.success();
    }

    //TODO 这里看出来了 "乐观锁" 的相关写法 以及 搭配插件的使用 , 但用法不是最佳实现, 版本号应该由前端直接作为入参提供.
    // 理由: 查询对于数据库来说, 是共享锁, 而不是排他锁. 虽然在代码中,先查询出来,拿到最新的版本号再去更新,是没问题.
    // 因为在更新的时候, 是排他锁, 会触发行锁/间隙锁/临键锁/表锁, 依旧会串行执行, 但整体多余. 存在IO浪费, 额外产生多余的对象, 浪费内存.
    @PostMapping("update")
    public Result updateUser(@RequestBody UserClass user) {
        //TODO 查询方式错误, 应为 userClassMapper.selectById(user.getId())
        UserClass userid = userClassMapper.selectById(user.getId());
        //TODO 多余操作
        user.setVersion(userid.getVersion());
        //TODO 理由与ClassInfoController.add()方法一致
        System.out.println("" + userid.getVersion());
        userClassMapper.updateById(userid);
        return Result.success();
    }

    @GetMapping("selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        UserClass userDate = userClassMapper.selectById(id);
        return Result.success(userDate);
    }

    @GetMapping("selectBatchById")
    public Result selectBatchById(@RequestBody List<Integer> ids) {
        List<UserClass> userDate = userClassMapper.selectBatchIds(ids);
        return Result.success(userDate);
    }

    @GetMapping("selectMap")
    public Result selectMap(@RequestBody Map<String, Object> map) {
        List<UserClass> userDate = userClassMapper.selectByMap(map);
        return Result.success(userDate);
    }

    @GetMapping("/selectByLike")
    public Result selectByLike(@RequestParam String name, @RequestParam String age1, @RequestParam String age2) {
        QueryWrapper<UserClass> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", name)
                .between("age", age1, age2);
        List<UserClass> list = userClassMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
        return Result.success(list);
    }

    @GetMapping("page")
    public Result testPage() {
        Page<UserClass> page = new Page<>(1, 3);
        userClassMapper.selectPage(page, null);
        return Result.success(page);
    }

    @GetMapping("getClassByUser")
    public Result<List<duo>> getClassByUser(int id) {
        return Result.success(service.getClassByUser(id));
    }
}
