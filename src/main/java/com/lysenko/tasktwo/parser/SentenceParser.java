package com.lysenko.tasktwo.parser;

import com.lysenko.tasktwo.component.TextComponent;
import com.lysenko.tasktwo.component.impl.TextComposite;
import com.lysenko.tasktwo.component.impl.ComponentType;

import java.util.List;

public class SentenceParser extends AbstractTextParser {
  private static final String REGEX_SENTENCE = "[.!?]+\\s";

  public SentenceParser(AbstractTextParser next) {
    super(next);
  }

  @Override
  public TextComponent parse(String text) {
    TextComponent paragraph = new TextComposite(ComponentType.PARAGRAPH);
    List<String> parts = List.of(text.split(REGEX_SENTENCE));

    for (String partSentence : parts) {
      if (!partSentence.trim().isEmpty()) {
        paragraph.add(getNextParser().parse(partSentence));
      }
    }
    return paragraph;
  }
}