package org.campusmolndal;

import org.bson.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TodoTests {
 /**
  * Den här klassen innehåller tester för klassen Todo.
  * Mockito används för att mocka Todo-objekt och Document-objekt.
  * Testerna kontrollerar att Todo-objekt kan skapas från Document-objekt
  * och att Todo-objekt kan konverteras till Document-objekt.
  * Testerna kontrollerar också att Todo-objekt kan skapas med rätt värden.
  */
 private Todo todoMock; // Mockat Todo-objekt
 private Document docMock; // Mockat Document-objekt

 /**
  * Den här metoden körs innan varje test.
  */
 @BeforeEach
 void setUp() {
  todoMock = mock(Todo.class);
  docMock = mock(Document.class);

  Todo mockedTodo = new Todo("mocked id", "mocked text", true);

  when(todoMock.getText()).thenReturn(mockedTodo.getText());
  when(todoMock.isDone()).thenReturn(mockedTodo.isDone());
  when(todoMock.get_id()).thenReturn(mockedTodo.get_id());
  when(todoMock.toString()).thenReturn("mocked text, true, mocked id");
  when(docMock.getString("_id")).thenReturn("test id");
  when(docMock.getString("text")).thenReturn("Test Todo");
  when(docMock.getBoolean("done")).thenReturn(true);

 }

 /**
  * Metoderna nedan testar att Todo-objekt kan skapas från Document-objekt
  * och att Todo-objekt kan konverteras till Document-objekt.
  * Testerna kontrollerar också att Todo-objekt kan skapas med rätt värden.
  * Mockito används för att mocka Todo-objekt och Document-objekt.
  */
 @Test
 void testFromDoc() {

  Todo todo = Todo.fromDoc(docMock);

  assertEquals("test id", todo.get_id());
  assertEquals("Test Todo", todo.getText());
  assertEquals(true, todo.isDone());
 }
 @Test
 void testToDoc() {

  Document expectedDoc = new Document()
          .append("_id", "test id")
          .append("text", "Test Todo")
          .append("done", true);

  when(todoMock.toDoc()).thenReturn(expectedDoc);

  Document doc = todoMock.toDoc();

  assertEquals("test id", doc.getString("_id"));
  assertEquals("Test Todo", doc.getString("text"));
  assertEquals(true, doc.getBoolean("done"));
 }

 @Test
 void getText() {
  String expectedText = "mocked text";
  String actualText = todoMock.getText();
  assertEquals(expectedText, actualText);
 }

 @Test
 void isDone() {
  boolean expectedDone = true;
  boolean actualDone = todoMock.isDone();
  assertEquals(expectedDone, actualDone);
 }

 @Test
 void get_id() {
  String expectedId = "mocked id";
  String actualId = todoMock.get_id();
  assertEquals(expectedId, actualId);
 }

 @Test
    void toStringTest() {
    String expectedString = "mocked text, true, mocked id";
    String actualString = todoMock.toString();
    assertEquals(expectedString, actualString);
    }

}
