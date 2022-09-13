package com.example.demo.controller;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.example.demo.common.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

//@RestController的意思是表示自己为返回json的控制器
@RestController
@RequestMapping("/files")//@RequestMapping是usercontroller统一的路由
public class FileController {
    @Value("${server.port}")//获取项目的端口号
    private String port;

    private static final String ip="http://localhost";//获取项目的IP

    @PostMapping("/upload")
    public Result<?> upload(MultipartFile file) throws IOException {
        String originalFilename=file.getOriginalFilename();//获取文件名
        String flag = IdUtil.fastSimpleUUID();// 定义文件的唯一标识（前缀）
        String rootFilePath=System.getProperty("user.id")+"/springboot/src/main/resources/files"+flag + "_" +originalFilename;//获取files文件夹的绝对路径
        FileUtil.writeBytes(file.getBytes(),rootFilePath);//写入到上传的路径
        return Result.success( ip + ":" + port + "/files/" + flag);  // 返回结果 url
    }
    @GetMapping("/getfile/{flag}")
    public void getFiles(@PathVariable String flag, HttpServletResponse response){//由于文件的下载是通过流的形式，不需要Result返回
        OutputStream os;  // 新建一个输出流对象
        String basePath = System.getProperty("user.dir") + "/springboot/src/main/resources/files/";  // 定于文件上传的根路径
        List<String> fileNames = FileUtil.listFileNames(basePath);  // 获取所有的文件名称
        String fileName = fileNames.stream().filter(name -> name.contains(flag)).findAny().orElse("");  // 找到跟参数一致的文件
        try {
            if (StrUtil.isNotEmpty(fileName)) {
                response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
                response.setContentType("application/octet-stream");
                byte[] bytes = FileUtil.readBytes(basePath + fileName);  // 通过文件的路径读取文件字节流
                os = response.getOutputStream();   // 通过输出流返回文件
                os.write(bytes);
                os.flush();
                os.close();
            }
        } catch (Exception e) {
            System.out.println("文件下载失败");
        }
    }
}
