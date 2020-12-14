package com.Engulf.controller;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {
    /**
     * 文件上传
     * @return
     */
    @RequestMapping("/fileupload")
    public String fileUpload(HttpServletRequest request) throws Exception {
        System.out.println("文件上传");

        //使用fileupload组件完成文件上传
        // 上传的位置
        String path = request.getSession().getServletContext().getRealPath("/uploads/");
        System.out.println(path);
        //判断，该路径是否存在
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }

        //解析request对象，获取上传的文件项
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        //解析request
        List<FileItem> fileItems = upload.parseRequest(request);
        //遍历
        for(FileItem f:fileItems){
            //进行判断,判断当前f对象是否是上传文件项，还是普通表单项
            if(f.isFormField()){
                // true 说明是普通表单项
            }else{
                //是上传文件项
                //获取到上传文件的名称
                String filename = f.getName();
                //把文件的名称设置唯一值，防止覆盖uuid
                String uuid = UUID.randomUUID().toString().replace("-","");
                filename = uuid + "_" + filename;
                //完成文件上传,向path路径传filename文件
                f.write(new File(path,filename));
                //删除临时文件
                f.delete();
            }
        }

        return "success";
    }


    /**
     *
     * @param request
     * @param upload 这个参数的名称必须和上传文件的form表单的name属性一样
     * @return
     */
    @RequestMapping("/fileupload2")
    public String fileUpload2(HttpServletRequest request, MultipartFile upload) throws Exception {
        System.out.println("文件上传了");
        // 上传的位置
        String path = request.getSession().getServletContext().getRealPath("/uploads/");
        System.out.println(path);
        //判断，该路径是否存在
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }

        //获取到上传文件的名称
        String filename = upload.getOriginalFilename();
        //把文件的名称设置唯一值，防止覆盖uuid
        String uuid = UUID.randomUUID().toString().replace("-","");
        filename = uuid + "_" + filename;
        //完成文件上传,向path路径传filename文件
        upload.transferTo(new File(path,filename));

        return "success";
    }


    /**
     * 跨服务器的文件上传
     * @param upload
     * @return
     * @throws Exception
     */
    @RequestMapping("/fileupload3")
    public String fileUpload3(MultipartFile upload) throws Exception {
        System.out.println("跨服务器文件上传了");

        //定义上传文件服务器的路径
        String path = "http://localhost:9090/FileuploadServer_war_exploded/uploads/";

        //获取到上传文件的名称
        String filename = upload.getOriginalFilename();
        //把文件的名称设置唯一值，防止覆盖uuid
        String uuid = UUID.randomUUID().toString().replace("-","");
        filename = uuid + "_" + filename;
        //完成文件上传，跨服务器的上传
        //创建客户端的对象
        Client client = Client.create();
        //和图片服务器进行连接
        WebResource resource = client.resource(path + filename);
        //上传文件
        resource.put(upload.getBytes());

        return "success";
    }
}
