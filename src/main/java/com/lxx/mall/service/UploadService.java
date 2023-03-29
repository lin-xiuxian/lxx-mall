package com.lxx.mall.service;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author 林修贤
 * @date 2023/3/29
 * @description
 */
public interface UploadService {
    String uploadImage(MultipartFile file) throws IOException;

    void createFile(@RequestParam("file") MultipartFile file, File fileDirectory, File destFile);

    String getNewFileName(@RequestParam("file") MultipartFile file);

    String uploadFile(MultipartFile file);
}
