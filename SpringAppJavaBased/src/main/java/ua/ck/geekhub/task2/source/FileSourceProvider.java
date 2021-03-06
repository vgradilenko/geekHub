package ua.ck.geekhub.task2.source;

import org.springframework.context.annotation.Bean;
import ua.ck.geekhub.task2.IOUtils;
import ua.ck.geekhub.task2.SourceLoadingException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Implementation for loading content from local file system.
 * This implementation supports absolute paths to local file system without specifying file:// protocol.
 * Examples c:/1.txt or d:/pathToFile/file.txt
 */

public class FileSourceProvider implements SourceProvider {

    @Override
    public boolean isAllowed(String pathToSource) {
        return new File(pathToSource).isFile();

    }

    @Override
    public String load(String pathToSource) throws SourceLoadingException {
        try {
            return IOUtils.toString(new FileInputStream(pathToSource));
        } catch (IOException e) {
            throw new SourceLoadingException("File not available", e);
        }
    }

    @Bean
    public FileSourceProvider fileSourceProvider() {
        return new FileSourceProvider();
    }
}
