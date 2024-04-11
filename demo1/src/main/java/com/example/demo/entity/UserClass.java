package com.example.demo.entity;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;

/**
 * 学生课程关系表
 * @TableName t_user_class
 */
@TableName(value ="t_user_class")
@Data
public class UserClass implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 学生ID
     */

    private String userId;

    /**
     * 课程ID
     */
    private String classId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime update;

    @Version
    private Integer version;

}