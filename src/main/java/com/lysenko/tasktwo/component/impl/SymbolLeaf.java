package com.lysenko.tasktwo.component.impl;

import com.lysenko.tasktwo.component.TextComponent;


import java.util.ArrayList;
import java.util.List;
public class SymbolLeaf implements TextComponent {
  private final char symbol;


  public SymbolLeaf(char symbol) {
    this.symbol = symbol;
  }
  @Override
  public String restoreText() {
    return String.valueOf(symbol);
  }
  @Override
  public int count() {
    return 1;
  }
  @Override
  public boolean remove(TextComponent component) {
    return false;
  }
  @Override
  public boolean add(TextComponent component) {
    return false;
  }
  @Override
  public List<TextComponent> getChild() {
    return new ArrayList<>();
  }
}
