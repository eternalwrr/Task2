package com.lysenko.tasktwo.service.impl;

import com.lysenko.tasktwo.service.TextManipulation;
import com.lysenko.tasktwo.component.TextComponent;
import com.lysenko.tasktwo.component.impl.TextComposite;
import com.lysenko.tasktwo.parser.LexemeParser;
import com.lysenko.tasktwo.parser.SentenceParser;
import com.lysenko.tasktwo.parser.SymbolParser;

import java.util.*;

public class TextManipulationImpl implements TextManipulation {

  private static final SymbolParser SYMBOL = new SymbolParser();
  private static final LexemeParser LEXEME = new LexemeParser(SYMBOL);
  private static final SentenceParser SENTENCE = new SentenceParser(LEXEME);

  @Override
  public int findSentences(TextComponent textComponent) {
    List<TextComponent> sentences = textComponent.getChild();
    List<Set<String>> sets = new ArrayList<>();
    for (TextComponent s : sentences) {
      sets.add(extractWords(s));
    }
    int max = 0;
    for (int i = 0; i < sets.size(); i++) {
      for (int j = i + 1; j < sets.size(); j++) {

        Set<String> common = new HashSet<>(sets.get(i));
        common.retainAll(sets.get(j));

        if (common.size() > max) {
          max = common.size();
        }
      }
    }

    return max;
  }

  private Set<String> extractWords(TextComponent sentence) {
    Set<String> words = new HashSet<>();

    for (TextComponent lex : sentence.getChild()) {
      String raw = lex.restoreText().toLowerCase();
      String clean = raw.replaceAll("[^a-zа-яё0-9]", "");
      if (!clean.isEmpty()) {
        words.add(clean);
      }
    }

    return words;
  }

  @Override
  public List<TextComponent> sortingSentences(String text) {
    TextComponent parsed = SENTENCE.parse(text);
    List<TextComponent> list = parsed.getChild();
    list.sort(Comparator.comparingInt(c -> c.getChild().size()));
    return list;
  }

  @Override
  public String changeLexeme(String text) {
    TextComponent parsed = SENTENCE.parse(text);
    List<TextComponent> sentences = parsed.getChild();
    StringBuilder sb = new StringBuilder();
    for (TextComponent sentence : sentences) {
      if (sentence instanceof TextComposite composite) {
        List<TextComponent> lexemes = composite.getChild();
        if (lexemes.size() > 1) {
          Collections.swap(lexemes, 0, lexemes.size() - 1);
        }
        for (TextComponent lexeme : lexemes) {
          sb.append(lexeme.restoreText());
        }
      }
      sb.append("\n");
    }
    return sb.toString().trim();
  }
}
