package com.lysenko.tasktwo.service;

import java.util.List;
import com.lysenko.tasktwo.component.TextComponent;

public interface TextManipulation {
  String changeLexeme(String text);
  int findSentences(TextComponent textComponent);
  List<TextComponent> sortingSentences(String text);
}
