package com.bj186.ssm.controller;

import com.bj186.ssm.entity.FileBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller
public class FileUploadController {

    @RequestMapping(value = "/fileUploadPage", method = RequestMethod.GET)
    public ModelAndView fileUploadPage() {
        FileBean fileBean = new FileBean();
        ModelAndView modelAndView = new ModelAndView("redirect:/statics/html/upload.html", "command", fileBean);
        return modelAndView;
    }
    // 返回页面应该改为ajax方式
    @RequestMapping(value="/fileUploadGo", method = RequestMethod.POST)
    public String fileUpload(@Validated FileBean fileBean, BindingResult result, ModelMap model, HttpServletRequest request) throws IOException {
        if (fileBean.getFile().isEmpty()) {
            System.out.println("上传失败");
            return "../../fileUploadPage";
        } else {
            System.out.println("开始上传");
            String uploadPath = request.getServletContext().getRealPath("") + File.separator + "upload" + File.separator;
            MultipartFile multipartFile = fileBean.getFile();
            String fileName = multipartFile.getOriginalFilename();
            File newFile = new File(uploadPath + fileName);
            System.out.println("newFile=" + newFile);
            FileCopyUtils.copy(multipartFile.getBytes(), newFile);

            model.addAttribute("fileName", fileName);
            return "success";
        }
    }
}
