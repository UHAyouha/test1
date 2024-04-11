package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 课程信息表
 * @TableName t_class_info
 */
@TableName(value ="t_class_info")
@Data
public class ClassInfo implements Serializable {
    /**
     * 主键(课程ID)
     */
    private String id;

    /**
     * 课程名称
     */
    private String className;

    /**
     * 课程描述(描述该学科的学习方向)
     */
    private String des;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 创建人ID
     */
    private String createBy;

    /**
     * 编辑人
     */
    private String updateBy;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}