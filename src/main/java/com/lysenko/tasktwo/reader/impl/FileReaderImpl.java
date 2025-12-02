package com.lysenko.tasktwo.reader.impl;

import com.lysenko.tasktwo.exception.ComponentException;
import com.lysenko.tasktwo.reader.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReaderImpl implements FileReader {
  private static final Logger log = LogManager.getLogger();
  @Override
  public String readFile(String filePath) throws ComponentException {
    Path path = Path.of(filePath);
    String lines;
    try {
      lines = Files.readString(path);
      log.info("File read complete successfully.");
    }
    catch (IOException e) {
      throw new ComponentException("File not found or read! Cause: " + e.getCause());
    }
    return lines;
  }
}
