package com.lysenko.tasktwo.parser;

import com.lysenko.tasktwo.component.TextComponent;

public abstract class AbstractTextParser {
  private AbstractTextParser next;

  public AbstractTextParser(AbstractTextParser next) {
    this.next = next;
  }

  public AbstractTextParser getNextParser() {
    return next;
  }
  public abstract TextComponent parse(String text);
}
