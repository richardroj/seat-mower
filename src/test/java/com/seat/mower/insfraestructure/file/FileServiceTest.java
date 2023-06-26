package com.seat.mower.insfraestructure.file;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FileServiceTest {

    @Autowired
    private FileService fileService;

    @Test
    void shouldReadFileFromUrl(){
        //given
        String url = "src/test/resources/input.txt";

        //when
        var response = fileService.readFileAndGetCommands(url);

        //then
        assertThat(response).isNotEmpty();

    }

    @Test
    void shouldReturnErrorIfFileNotExist(){
        //given
        String url = "src/test/resources/empty.txt";

        //when
        var response = fileService.readFileAndGetCommands(url);

        //then
        assertThat(response).isEmpty();

    }
}