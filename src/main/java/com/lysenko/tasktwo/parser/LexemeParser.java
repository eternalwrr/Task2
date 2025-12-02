package com.lysenko.tasktwo.parser;

import com.lysenko.tasktwo.component.impl.ComponentType;
import com.lysenko.tasktwo.component.TextComponent;
import com.lysenko.tasktwo.component.impl.TextComposite;
import com.lysenko.tasktwo.delimiters.Delimiter;
import java.util.List;

public class LexemeParser extends AbstractTextParser {
  private static final String REGEX = Delimiter.LEXEME;

  public LexemeParser(AbstractTextParser next) {
    super(next);
  }

  @Override
  public TextComponent parse(String text) {
    TextComponent sentence = new TextComposite(ComponentType.SENTENCE);
    List<String> parts = List.of(text.split(REGEX));

    for (String part : parts) {
      sentence.add(getNextParser().parse(part));
    }
    return sentence;
  }
}