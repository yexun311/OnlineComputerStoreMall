package com.ye.controller;

import com.ye.common.result.Result;
import com.ye.common.result.ResultSet;
import com.ye.model.resp.UserInfoResp;
import com.ye.exception.FailException;
import com.ye.service.IUserService;
import com.ye.util.SessionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import java.io.File;
import java.util.UUID;

import static com.ye.common.constant.AVATAR_MAX_SIZE;

@Api("用户管理")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    IUserService userService;

    /** 个人资料显示 */
    @ApiOperation("个人资料显示")
    @RequestMapping("/get_info")
    public ResultSet<UserInfoResp> getByUid(HttpSession session){
        UserInfoResp data = userService.getByUid(SessionUtil.getUidFromSession(session));
        return Result.success(data);
    }

    /** 修改个人资料 */
    @ApiOperation("修改个人资料")
    @RequestMapping("/change_info")
    public ResultSet<Void> changeInfo(@RequestBody UserInfoResp userInfoResp, HttpSession session){
        // user 对象中有四部分数据 username、 phone、 email、 gender
        // 控制层给业务层传递uid,并在业务层通过user.setUid(uid);将uid封装到user中
        userService.changeInfo(SessionUtil.getUidFromSession(session), userInfoResp);
        return Result.success("修改成功");
    }

    // TODO 没调成功
    /** 修改头像 */
    @ApiOperation("修改头像")
    @RequestMapping("change_avatar")
    public ResultSet<String> changeAvatar(HttpSession session,
                                           MultipartFile file) {
        /*
         * 1.参数名为什么必须用file:在upload.html页面的147行<input type=
         * "file" name="file">中的name="file",所以必须有一个方法的参数名
         * 为file用于接收前端传递的该文件.如果想要参数名和前端的name不一
         * 样:@RequestParam("file")MultipartFile ffff:把表单中name=
         * "file"的控件值传递到变量ffff上
         * 2.参数类型为什么必须是MultipartFile:这是springmvc中封装的一个
         * 包装接口,如果类型是MultipartFile并且参数名和前端上传文件的name
         * 相同,则会自动把整体的数据包传递给file
         */
        //判断文件是否为null
        if (file.isEmpty()) {
            throw new FailException("文件为空");
        }
        if (file.getSize()>AVATAR_MAX_SIZE) {
            throw new FailException("文件超出限制");
        }
        /* //判断文件的类型是否是我们规定的后缀类型
        String contentType = file.getContentType();
        //如果集合包含某个元素则返回值为true
        if (!AVATAR_TYPE.contains(contentType)) {
            throw new FileTypeException("文件类型不支持");
        } */

        //上传的文件路径:.../upload/文件名.png
        /*
         * session.getServletContext()获取当前Web应用程序的上下文
         * 对象(每次启动tomcat都会创建一个新的上下文对象)
         * getRealPath("/upload")的/代表当前web应用程序的根目录,通过该相
         * 对路径获取绝对路径,返回一个路径字符串,如果不能进行映射返回null,单
         * 斜杠可要可不要
         */
        String parent =
                session.getServletContext().getRealPath("/upload");
        System.out.println(parent);//调试用

        //File对象指向这个路径,通过判断File是否存在得到该路径是否存在
        File dir = new File(parent);
        if (!dir.exists()) {//检测目录是否存在
            boolean flag = dir.mkdirs();//创建当前目录
        }

        //获取这个文件名称(文件名+后缀,如avatar01.png,不包含父目录结构)用UUID
        // 工具生成一个新的字符串作为文件名(好处:避免了因文件名重复发生的覆盖)
        String originalFilename = file.getOriginalFilename();
        System.out.println("OriginalFilename="+originalFilename);
        assert originalFilename != null;
        int index = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(index);
        //filename形如SAFS1-56JHIOHI-HIUGHUI-5565TYRF.png
        String filename =
                UUID.randomUUID().toString().toUpperCase()+suffix;

        //在dir目录下创建filename文件(此时是空文件)
        File dest = new File(dir, filename);

        //java可以把一个文件的数据直接写到同类型的文件中,这里将参数file中的数据写入到空文件dest中
        try {
            file.transferTo(dest);//transferTo是一个封装的方法,用来将file文件中的数据写入到dest文件

            /*
             * 先捕获FileStateException再捕获IOException是
             * 因为后者包含前者,如果先捕获IOException那么
             * FileStateException就永远不可能会被捕获
             */
        } catch (Exception e) {
            throw new FailException("文件状态异常");
        }

        Integer uid = SessionUtil.getUidFromSession(session);
        String username = SessionUtil.getUsernameFromSession(session);
        String avatar = "/upload/"+filename;
        userService.changeAvatar(uid,avatar);
        //返回用户头像的路径给前端页面,将来用于头像展示使用
        return Result.success("", avatar);
    }

}
