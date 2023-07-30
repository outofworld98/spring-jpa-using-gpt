package com.example.demo;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class BookServiceTest {

    private BookService bookService;
    private BookRepository bookRepository;

    @BeforeEach
    public void setUp() {
        bookRepository = mock(BookRepository.class);
        bookService = new BookService(bookRepository);
    }

    @Test
    public void testAddBook() {
        Book bookToAdd = Book.builder()
                .isbn("1234567890")
                .title("Sample Book")
                .author("John Doe")
                .introduce("This is a sample book.")
                .tags(singletonList("fiction, sample"))
                .build();

        when(bookRepository.save(any(Book.class))).thenReturn(bookToAdd);

        Book addedBook = bookService.addBook(bookToAdd);

        assertNotNull(addedBook);
        assertEquals("Sample Book", addedBook.getTitle());
        assertEquals("John Doe", addedBook.getAuthor());
        assertEquals("1234567890", addedBook.getIsbn());
        assertEquals("This is a sample book.", addedBook.getIntroduce());
        assertEquals(singletonList("fiction, sample"), addedBook.getTags());

        verify(bookRepository, times(1)).save(bookToAdd);
    }

    @Test
    public void testGetAllBooks() {
        List<Book> books = new ArrayList<>();
        books.add(Book.builder()
                .id(1L)
                .title("Book 1")
                .author("Author 1")
                .isbn("123456")
                .introduce("Introduction 1")
                .tags(singletonList("Tag1, Tag2"))
                .build());
        books.add(Book.builder()
                .id(2L)
                .title("Book 2")
                .author("Author 2")
                .isbn("789012")
                .introduce("Introduction 2")
                .tags(singletonList("Tag3, Tag4"))
                .build());

        when(bookRepository.findAll()).thenReturn(books);

        List<Book> allBooks = bookService.getAllBooks();

        assertNotNull(allBooks);
        assertEquals(2, allBooks.size());
        assertEquals("Book 1", allBooks.get(0).getTitle());
        assertEquals("Book 2", allBooks.get(1).getTitle());

        verify(bookRepository, times(1)).findAll();
    }

    @Test
    public void testGetBookById() {
        Book book = Book.builder()
                .id(1L)
                .title("Book 1")
                .author("Author 1")
                .isbn("123456")
                .introduce("Introduction 1")
                .tags(singletonList("Tag1, Tag2"))
                .build();

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        Book foundBook = bookService.getBookById(1L);

        assertNotNull(foundBook);
        assertEquals("Book 1", foundBook.getTitle());

        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    public void testUpdateBook() {
        Book existingBook = Book.builder()
                .id(1L)
                .title("Book 1")
                .author("Author 1")
                .isbn("123456")
                .introduce("Introduction 1")
                .tags(singletonList("Tag1, Tag2"))
                .build();

        Book updatedBookData = Book.builder()
                .title("Updated Book 1")
                .author("Updated Author 1")
                .isbn("789012")
                .introduce("Updated Introduction 1")
                .tags(singletonList("Tag3, Tag4"))
                .build();

        when(bookRepository.existsById(1L)).thenReturn(true);
        when(bookRepository.save(any(Book.class))).thenReturn(updatedBookData);

        Book updatedBook = bookService.updateBook(1L, updatedBookData);

        assertNotNull(updatedBook);
        assertEquals("Updated Book 1", updatedBook.getTitle());
        assertEquals("Updated Author 1", updatedBook.getAuthor());
        assertEquals("789012", updatedBook.getIsbn());
        assertEquals("Updated Introduction 1", updatedBook.getIntroduce());
        assertEquals(singletonList("Tag3, Tag4"), updatedBook.getTags());

        verify(bookRepository, times(1)).existsById(1L);
        verify(bookRepository, times(1)).save(updatedBookData);
    }

    @Test
    public void testDeleteBook() {
        long bookId = 1L;

        bookService.deleteBook(bookId);

        verify(bookRepository, times(1)).deleteById(bookId);
    }
}
