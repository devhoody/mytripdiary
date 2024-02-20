package com.mytripdiary.file;

import com.mytripdiary.domain.UploadFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileStore {

    @Value("${file.dir}")
    private String path;

    public String getFullPath(String filename) {
        return path + filename;
    }

    public List<UploadFile> filesStore(List<MultipartFile> multipartFiles) throws IOException {
        List<UploadFile> list = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if(!multipartFile.isEmpty()){
                UploadFile uploadFile = fileStore(multipartFile);
                list.add(uploadFile);
            }
        }
        return list;
    }

    public UploadFile fileStore(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            return null;
        }

        String originalFilename = multipartFile.getOriginalFilename();
        String storeFilename = createStoreFilename(originalFilename);

        multipartFile.transferTo(new File(getFullPath(storeFilename)));

        return new UploadFile(originalFilename, storeFilename);
    }

    private static String createStoreFilename(String originalFilename) {
        String ext = getExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    private static String getExt(String originalFilename) {
        int cos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(cos + 1);
    }

}
