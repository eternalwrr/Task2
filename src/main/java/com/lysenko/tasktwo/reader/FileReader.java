package com.lysenko.tasktwo.reader;

import com.lysenko.tasktwo.exception.ComponentException;

public interface FileReader {
  public String readFile(String filePath) throws ComponentException;
}
