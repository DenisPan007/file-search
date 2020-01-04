package panasyuk.file;

import org.junit.Test;

import java.io.File;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

public class FileServiceTest {

    @Test
    public void getInnerFiles() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource("test_dir").getFile());
        FileService fileService = spy(FileService.class);
        doReturn(file).when(fileService).getFile(any());

        Map<String, String> innerFiles = fileService.getInnerFiles("someTestString");
        assertEquals(11, innerFiles.size());

    }
}