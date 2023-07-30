package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> searchBooks(String searchKeyword) {
        // Implement your book search logic here
        // For example, you can use bookRepository.findByTitleContaining(searchKeyword);
        // to find books containing the specified keyword in their titles.
        // Return the list of matching books.
        return bookRepository.findByTitleContaining(searchKeyword);
    }

    public Book getBookById(Long bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        return book.orElse(null);
    }

    public Book updateBook(Long bookId, Book book) {
        if (!bookRepository.existsById(bookId)) {
            return null;
        }
        book.setId(bookId);
        return bookRepository.save(book);
    }

    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }
}
