package com.blog.BackendBlogApplicationAPIs.services.impl;

import com.blog.BackendBlogApplicationAPIs.payloads.ApiResponse;
import com.blog.BackendBlogApplicationAPIs.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        String fileName1 = null;
        String name = file.getOriginalFilename();
        String randomID = UUID.randomUUID().toString();
        if(name.substring(name.lastIndexOf(".")).equals(".png") || name.substring(name.lastIndexOf(".")).equals(".jpg") || name.substring(name.lastIndexOf(".")).equals(".jpeg")){
            fileName1 = randomID.concat(name.substring(name.lastIndexOf('.')));
            String filePath = path + File.separator + fileName1;
            File f = new File(path);
            if(!f.exists()) {
                f.mkdir();
            }
            Files.copy(file.getInputStream(), Paths.get(filePath));
        }
        return fileName1;
    }
    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath = path + File.separator + fileName;
        InputStream is = new FileInputStream(fullPath);
        return is;
    }
}
