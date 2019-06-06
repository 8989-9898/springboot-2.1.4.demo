package com.ly.entity;


import com.ly.annotation.Groups;
import com.sun.xml.internal.ws.api.FeatureListValidatorAnnotation;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.entity
 * @ClassName: Auther
 * @Author: lin
 * @Description: 数据认证实体类
 * @Date: 2019-06-06 15:51
 * @Version: 1.0
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Auther {

    @NotNull(message = "id不能为空",groups = Groups.Update.class)
    @Min(0)
    @Max(100)
    private Integer id;
    @NotBlank(message = "姓名不能为空",groups = Groups.Default.class)
    @Length(message = "姓名长度必须大于20小于5",max = 20,min = 5)
    private String name;

    @NotBlank(message = "性别不能为空",groups = Groups.Default.class)
    @Length(message = "性别只能为一个字符",max = 1,min = 1)
    private String gender;
    @Max(30)
    @Min(18)
    private Integer age;
    @NotBlank(message = "出生日期不能为空",groups = Groups.Default.class)
    private String birthday;

}
