package com.lysenko.tasktwo.parser;

import com.lysenko.tasktwo.component.TextComponent;
import com.lysenko.tasktwo.component.impl.ComponentType;
import com.lysenko.tasktwo.component.impl.TextComposite;
import com.lysenko.tasktwo.component.impl.SymbolLeaf;
import java.util.List;

public class ParagraphParser extends AbstractTextParser {
  private static final String REGEX_PARAGRAPH = "\\n\\s*\\n";

  public ParagraphParser(AbstractTextParser nextParser) {
    super(nextParser);
  }

  @Override
  public TextComponent parse(String text) {
    TextComponent paragraph = new TextComposite(ComponentType.PARAGRAPH);
    List<String> parts = List.of(text.split(REGEX_PARAGRAPH));
    for (String part : parts) {
      TextComponent sentence = getNextParser().parse(part);
      paragraph.add(new SymbolLeaf('\n'));
      paragraph.add(new SymbolLeaf('\t'));
    }
    return paragraph;
  }
}
