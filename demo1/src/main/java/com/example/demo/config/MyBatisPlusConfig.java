package com.example.demo.config;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@MapperScan("com.example.demo.mapper")
//TODO 主启动类已经有该注解,无需重复注释. 效果会被覆盖, 也会导致Mybatis的底层Mapper扫描后置处理器重复扫描.浪费启动时间
public class MyBatisPlusConfig {
    @Bean
    public MybatisPlusInterceptor mayBatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
//        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
//        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());

        //TODO 在使用Mybatis-Plus提供的统一拦截器的内置插件时,需要注意插件的添加顺序, 分页拦截插件,有且一定要放在最后,
        // 因为addInnerInterceptor方法底层为一个List, 会通过迭代器去按照添加顺序取出每一个插件去执行接口的方法,
        // 分页插件为什么要放在最后? 理由: 因其他插件会通过相关手段修改你的原始SQL,在你的原始SQL下添加对应的额外语句, 最后会将改造完的SQL传给分页插件,
        // 是否执行分页插件的功能, 是基于经过其他插件修改后的SQL进行 count(*) 操作返回是否大于0来进行的. 所以分页必须作为最后一道门,否则可能会导致与你预期的结果不一致.
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

}
