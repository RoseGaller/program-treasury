package com.lct.bean;


import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

//@TableName("xc_user")//当实体类的名称跟表的名称不匹配，需要添加@TableName注解，指定具体的表的名称
public class User {


    //@TableId(type = IdType.AUTO,value="id") //需指定指定id类型为自增长
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    //插入时自动填充
//    @TableField(fill = FieldFill.INSERT )
    private String name;
    //查询时不返回此字段
//    @TableField(select =false)
    private Integer age;
    //解决字段与数据表中的不一致
//    @TableField(value = "email")
    private String email;
}