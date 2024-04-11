package com.example.demo.commen;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Result<T> {
    private String code;
    private String msg;
    private T data;

    public Result() {
    }

    public Result(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * TODO 可以用泛型方法控制, 避免泛型类的原始使用, 虽然不影响使用,但会造成维护和阅读代码的歧义
     *  泛型方法的使用方法: 返回值,参数列表都可以作为泛型使用,
     *  泛型方法的模型特点: 有且仅有在目标方法被调用时, 泛型具体的指向类型,才会确定. 根据这个特点,可以自己指定入参,也可以自己控制出参的泛型引用是什么
     */
    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.setCode("200");
        result.setMsg("成功");
        return result;
    }

    /**
     * TODO 改造为全参构造函数.
     *  理由: 在创建实例对象时,进行直接初始化工作, 避免先实例化对象再进行设置的多余方法栈操作. 因为该类为全局统一结果出参包装类, 所以需要要尽可能减少多余的调用, 提高性能;
     */
    public static <T> Result<T> success(T data) {
//        Result<T> result = new Result<>(data);
//        result.setCode("200");
//        result.setMsg("成功");
//        return result;
        return new Result<>("200", "成功", data);
    }

    /**
     * TODO 理由与success方法保持一直
     */
    public static <T> Result<T> error(String code, String msg) {
        return new Result<>("code","msg",null);
    }
}
