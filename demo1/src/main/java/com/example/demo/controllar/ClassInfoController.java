package com.example.demo.controllar;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.commen.Result;
import com.example.demo.entity.ClassInfo;
import com.example.demo.mapper.ClassInfoMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
//@RequestMapping("/Class")
//TODO 接口uri的路径,一律按照驼峰式命名, 即: 首字母小写,其他词义的英文首字母大写
@RequestMapping("/class")
public class ClassInfoController {

    //    @Autowired
    //TODO 基于成员属性的Bean注入时,请尽量使用@Resource进行注入(@Resource是先以name进行注入,其次再根据type进行注入)
    // 虽然@Autowired是Spring框架提供的框架, 但官方并不提倡使用其用来进行字段注入, 因为会导致Spring的侵入性太强,
    // 而@Resource是java的原生注解,Spring框架对其进行了扩展,功能与@Autowired效果一样,@Autowired能做到的,它基本都能做到
    // 如果非常喜欢使用@Autowired, 请改造为构造注入或Setter注入.
    @Resource
    ClassInfoMapper classMapper;

    //新增用户数据
    @PostMapping("/add")
    public Result<?> add(@RequestBody ClassInfo user) {
        classMapper.insert(user);
        //TODO 不论出于什么目的,永远不建议在任何地方使用System.out.println进行任何形式的控制台打印.
        // 理由: 首先System.out.println方法底层会进行多次可重入的加锁. 虽然JVM的同步机制有在jdk8进行优化和升级,
        // 但在并发时,会导致多个线程同时获取到的System.out是单例(System.out默认是单例,可修改,但一般不会去修改),
        // 那么对于该单例进行同步加锁时,会造成其他线程的同步阻塞.则使得该接口不支持并发/并行,同时会造成大量线程进入CPU的就绪等待池,不断的去竞争锁资源.大大降低性能和消费CPU的调度能力
        return Result.success();
    }

    //根据ID删除数据
//    @DeleteMapping("/delete/ById")
    //TODO 接口uri的路径,一律按照驼峰式命名, 即: 首字母小写,其他词义的英文首字母大写
    @DeleteMapping("/delete/byId")
    public Result<?> byIdDelete(@RequestBody List<Integer> ids) {
        classMapper.deleteBatchIds(ids);
        return Result.success();
    }

    //TODO 不建议提供外界根据某些条件进行删除数据的接口,有且仅提供根据主键ID删除的接口.
    // 理由: 避免大批量错误删除, delete语句的where条件若书写的控制粒度存在差异, 会辐射到大批量不需要删除的数据
//    //根据信息删除表中数据
//    @DeleteMapping("/delete/batch")
//    public Result batchDelete(@RequestBody Map<String, Object> map) {
//        classMapper.deleteByMap(map);
//        return Result.success();
//    }

    @PostMapping("update")
    public Result<?> updateUser(@RequestBody ClassInfo user) {
        classMapper.updateById(user);
        return Result.success();
    }

    //TODO 控制泛型返回值的具体泛型目标类型
    @GetMapping("selectById/{id}")
    public Result<ClassInfo> selectById(@PathVariable Integer id) {
        ClassInfo userDate = classMapper.selectById(id);
        return Result.success(userDate);
    }

    //TODO 控制泛型返回值的具体泛型目标类型
    @GetMapping("selectBatchById")
    public Result<List<ClassInfo>> selectBatchById(@RequestBody List<Integer> ids) {
        List<ClassInfo> userDate = classMapper.selectBatchIds(ids);
        return Result.success(userDate);
    }

    //TODO 控制泛型返回值的具体泛型目标类型
    @GetMapping("selectMap")
    public Result<List<ClassInfo>> selectMap(@RequestBody Map<String, Object> map) {
        List<ClassInfo> userDate = classMapper.selectByMap(map);
        return Result.success(userDate);
    }

    //TODO 控制泛型返回值的具体泛型目标类型,同时请注意,不要在Controller层去写任何业务代码,不管是条件封装,还是逻辑判断.都放到指定的Service层.
    // Controller层要做的事情只有一个: 接收外部入参,将入参传给对应需要调用的Service层.一切的业务逻辑都在其内部处理
    @GetMapping("/selectByLike")
    public Result<List<ClassInfo>> selectByLike(@RequestParam String name, @RequestParam String age1, @RequestParam String age2) {
        QueryWrapper<ClassInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", name)
                .between("age", age1, age2);
        List<ClassInfo> list = classMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
        return Result.success(list);
    }


    //    @GetMapping("page")
    //    public Result<Page<ClassInfo>> testPage() {
    //        Page<ClassInfo> page = new Page<>(1, 3);
    //        classMapper.selectPage(page, null);
    //        return Result.success(page);
    //    }
    //TODO 控制泛型返回值的具体泛型目标类型,同时请注意,不要在Controller层去写任何业务代码,不管是条件封装,还是逻辑判断.都放到指定的Service层.
    // Controller层要做的事情只有一个: 接收外部入参,将入参传给对应需要调用的Service层.一切的业务逻辑都在其内部处理
    // 其次,分页查询,应使用Post请求, 理由: 需要提供相关筛选项作为额外的入参进行查询, Get请求本质上,不允许用来传递请求体.
    // 分页的参数(当前页,每页大小)也应该交由前端传递,可拼接在URL后,也可以放在具体的DTO入参类中,但最好放在URL上,直接通过MVC框架进行对象构建,避免多余的调用栈再去自己new Page<>();
    @PostMapping("page")
    public Result<Page<ClassInfo>> testPage(Page<ClassInfo> page, @RequestBody Object 具体的DTO入参类) {
        classMapper.selectPage(page, null);
        return Result.success(page);
    }

}
