package com.ly.controller;

import com.ly.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @ProjectName: springboot_2.0.1
 * @Package: com.ly.controller
 * @ClassName: SwaggerController
 * @Author: lin
 * @Description: 使用swagger2定义接口
 * @Date: 2019-04-30 16:34
 * @Version: 1.0
 */
@RestController
@RequestMapping("/users")
@Api(tags = "1.1",value = "测试swagger2")
public class SwaggerController {


    @GetMapping
    @ApiOperation(value = "条件查询（DONE）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "String", paramType = "query"),
            @ApiImplicitParam()
    })
    public User query(String username, String password) {
        System.out.println("多个参数用  @ApiImplicitParams");
        return new User(1, username, password);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "主键查询（DONE）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户编号", dataType = "Integer", paramType = "path"),
    })
    public User get(@PathVariable int id) {
        System.out.println("单个参数用  @ApiImplicitParam");
        return new User(id, "u1", "p1");
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除用户（DONE）")
    @ApiImplicitParam(name = "id", value = "用户编号", dataType = "integer", paramType ="path")
    public String delete(@PathVariable Long id) {
        System.out.println("单个参数用 ApiImplicitParam");
        return "执行删除操作";
    }

    @PostMapping
    @ApiOperation(value = "添加用户（DONE）")
    public User post(@RequestBody User user) {
        System.out.println("如果是 POST PUT 这种带 @RequestBody 的可以不用写 @ApiImplicitParam");
        return user;
    }


    @PutMapping("/{id}")
    @ApiOperation(value = "修改用户（DONE）")
    public String put(@PathVariable Long id, @RequestBody User user) {
        System.out.println("如果你不想写 @ApiImplicitParam 那么 swagger 也会使用默认的参数名作为描述信息 ");
        return "执行修改操作";
    }



    /**
     * @Api：描述Controller
     * @ApiIgnore：忽略该Controller，指不对当前类做扫描
     * @ApiOperation：描述Controller类中的method接口
     * @ApiParam：单个参数描述，与@ApiImplicitParam不同的是，他是写在参数左侧的。如（@ApiParam(name = "username",value = "用户名") String username）
     * @ApiModel：描述POJO对象
     * @ApiProperty：描述POJO对象中的属性值
     * @ApiImplicitParam：描述单个入参信息
     * @ApiImplicitParams：描述多个入参信息
     * @ApiResponse：描述单个出参信息
     * @ApiResponses：描述多个出参信息
     * @ApiError：接口错误所返回的信息
     * */
}
