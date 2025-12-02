package com.lysenko.tasktwo.parser;

import com.lysenko.tasktwo.component.TextComponent;
import com.lysenko.tasktwo.component.impl.ComponentType;
import com.lysenko.tasktwo.component.impl.TextComposite;
import com.lysenko.tasktwo.component.impl.SymbolLeaf;


public class SymbolParser extends AbstractTextParser {
  public SymbolParser() {
    super(null);
  }
  @Override
  public TextComponent parse(String text) {
    TextComponent lexeme = new TextComposite(ComponentType.LEXEME);
    for(int i = 0; i < text.length(); i++) {
      lexeme.add(new SymbolLeaf(text.charAt(i)));
    }
    return lexeme;
  }
}