package com.lysenko.tasktwo.parser;

import com.lysenko.tasktwo.component.impl.ComponentType;
import com.lysenko.tasktwo.component.TextComponent;
import com.lysenko.tasktwo.component.impl.TextComposite;
import java.util.List;

public class LexemeParser extends AbstractTextParser {
  private static final String REGEX_LEXEME = "\\s+";

  public LexemeParser(AbstractTextParser next) {
    super(next);
  }

  @Override
  public TextComponent parse(String text) {
    TextComponent sentence = new TextComposite(ComponentType.SENTENCE);
    List<String> parts = List.of(text.split(REGEX_LEXEME));

    for (String part : parts) {
      sentence.add(getNextParser().parse(part));
    }
    return sentence;
  }
}