package com.lysenko.tasktwo.component;

import java.util.List;

public interface TextComponent {
  String restoreText();
  int count();
  boolean add(TextComponent component);
  boolean remove(TextComponent component);
  List<TextComponent> getChild();
}
