package com.lysenko.tasktwo.component.impl;

import com.lysenko.tasktwo.component.TextComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
public class TextComposite implements TextComponent {
  private static final Logger log = LogManager.getLogger();
  private final ComponentType type;
  private final List<TextComponent> components = new ArrayList<>();
  public TextComposite(ComponentType type) {
    this.type = type;
  }

  @Override
  public String restoreText() {
    StringBuilder str = new StringBuilder();
    for (TextComponent component : components) {
      str.append(component.restoreText());
      if (type == ComponentType.TEXT) {
        str.append("\n");
      }
      else {
        if (type == ComponentType.PARAGRAPH) {
          str.insert(0,"\t");
          }
        else {
          if (type == ComponentType.LEXEME) {
            str.append(" ");
          }
        }
      }
    }
    return str.toString();
  }

  @Override
  public boolean add(TextComponent component) {
    return components.add(component);
  }
  @Override
  public boolean remove(TextComponent component) {
    return components.remove(component);
  }
  @Override
  public int count() {
    return 0;
  }
  @Override
  public List<TextComponent> getChild() {
    return new ArrayList<>(components);
  }
}
