package com.lct.bean;


import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName
@KeySequence(clazz = Long.class)
public class User {


    //@TableId(type = IdType.AUTO,value="id") //指定id类型为自增长
    @TableId(type = IdType.INPUT,value="id")
    private Long id;
    private String name;
    //查询时不返回此字段
//    @TableField(select =false)
    private Integer age;
    //解决字段与数据表中的不一致
//    @TableField(value = "email")
    private String email;
}