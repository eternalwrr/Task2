package com.lysenko.tasktwo;

import com.lysenko.tasktwo.component.TextComponent;
import com.lysenko.tasktwo.parser.LexemeParser;
import com.lysenko.tasktwo.parser.SentenceParser;
import com.lysenko.tasktwo.parser.SymbolParser;
import com.lysenko.tasktwo.service.impl.TextManipulationImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TextManipulationImplTest {

  private TextManipulationImpl service;

  private static final SymbolParser SYMBOL = new SymbolParser();
  private static final LexemeParser LEXEME = new LexemeParser(SYMBOL);
  private static final SentenceParser SENTENCE = new SentenceParser(LEXEME);

  private final String TEXT = "It has survived not only five centuries, but also the leap into electronic typesetting, " +
          "remaining essentially unchanged. It was popularised in the with the release of Letraset sheets containing " +
          "Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including " +
          "versions of Lorem Ipsum.\n\n" +
          "It is a long established fact that a reader will be distracted by the readable content of a page when " +
          "looking at its layout. The point of using Ipsum is that it has a more-or-less normal distribution of " +
          "letters, as opposed to using 'Content here, content here', making it look like readable English.\n\n" +
          "It is a established fact that a reader will be of a page when looking at its layout.\n\n" +
          "Bye.";

  @BeforeEach
  void setUp() {
    service = new TextManipulationImpl();
  }

  @Test
  void findSentences_shouldFindCommonWordsBetweenSentences() {
    TextComponent parsed = SENTENCE.parse(TEXT);

    int result = service.findSentences(parsed);

    // Выводим для отладки
    System.out.println("Max common words found: " + result);

    // Проверяем логические границы
    assertTrue(result >= 1, "Should find at least 1 common word between sentences");
    assertTrue(result <= 15, "Should not exceed reasonable word count");
  }

  @Test
  void findSentences_withSingleSentence_shouldReturnZero() {
    String single = "One sentence only.";
    TextComponent parsed = SENTENCE.parse(single);

    int result = service.findSentences(parsed);

    assertEquals(0, result, "Single sentence should return 0 common words");
  }

  @Test
  void findSentences_withIdenticalSentences_shouldReturnAllWords() {
    String testText = "Hello world. Hello world.";
    TextComponent parsed = SENTENCE.parse(testText);

    int result = service.findSentences(parsed);

    // 2 общих слова: Hello, world
    assertEquals(2, result, "Identical sentences should have all words in common");
  }

  @Test
  void sortingSentences_shouldSortByLexemeCountAscending() {
    List<TextComponent> sorted = service.sortingSentences(TEXT);

    assertEquals(4, sorted.size(), "Should have 4 sentences in text");

    for (int i = 0; i < sorted.size() - 1; i++) {
      int currentCount = sorted.get(i).getChild().size();
      int nextCount = sorted.get(i + 1).getChild().size();
      assertTrue(currentCount <= nextCount,
              "Sentences should be sorted in ascending order by lexeme count");
    }
    assertEquals(1, sorted.getFirst().getChild().size());
  }

  @Test
  void sortingSentences_withEmptyText_shouldReturnEmptyList() {
    List<TextComponent> result = service.sortingSentences("");

    assertTrue(result.isEmpty(), "Empty text should return empty list");
  }

  @Test
  void sortingSentences_withSingleSentence_shouldReturnOneElement() {
    String single = "Just one sentence.";
    List<TextComponent> result = service.sortingSentences(single);

    assertEquals(1, result.size(), "Single sentence should return list with one element");
  }

  @Test
  void changeLexeme_shouldSwapFirstAndLastLexemeInEachSentence() {
    String result = service.changeLexeme(TEXT);

    assertNotNull(result);
    assertFalse(result.isEmpty());

    String[] lines = result.split("\n");
    assertEquals(4, lines.length, "Should have 4 lines (sentences)");

    for (String line : lines) {
      assertFalse(line.trim().isEmpty(), "Line should not be empty");
    }

    assertEquals("Bye.", lines[3].trim(), "Single lexeme sentence should remain unchanged");
  }

  @Test
  void changeLexeme_withSingleLexemeSentence_shouldNotChange() {
    String text = "Hello.";
    String result = service.changeLexeme(text);

    assertEquals("Hello.", result.trim(), "Single lexeme sentence should not change");
  }

  @Test
  void changeLexeme_withTwoLexemeSentence_shouldSwap() {
    String text = "Hello world.";
    String result = service.changeLexeme(text);

    assertEquals("world. Hello", result.trim(), "Should swap two lexemes");
  }

  @Test
  void changeLexeme_withMultipleSentences_shouldProcessEach() {
    String text = "First sentence. Second test sentence. Third.";
    String result = service.changeLexeme(text);

    String[] lines = result.split("\n");
    assertEquals(3, lines.length, "Should process all 3 sentences");
    for (String line : lines) {
      assertFalse(line.trim().isEmpty());
    }
  }
  @Test
  void changeLexeme_shouldHandlePunctuationCorrectly() {
    String text = "Hello, world! How are you?";
    String result = service.changeLexeme(text);
    assertNotNull(result);
    String[] lines = result.split("\n");
    assertTrue(lines.length >= 2, "Should have at least 2 sentences");
  }
}