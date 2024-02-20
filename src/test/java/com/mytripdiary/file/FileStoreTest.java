package com.mytripdiary.file;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FileStoreTest {

    @Autowired
    private FileStore fileStore;

    @Value("${file.dir}")
    private String path;

    @Test
    void 파일_저장_경로(){
        //given
        String filename = "12345.jpg";

        //when
        String fullPath = fileStore.getFullPath(filename);
        //then
        Assertions.assertThat(fullPath).isEqualTo("/Users/eddy/Desktop/dev/project/mytripdiary/src/main/resources/webapp/images/12345.jpg");
    }



}
