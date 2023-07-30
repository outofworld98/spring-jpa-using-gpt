package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookCategoryService {
    private final BookCategoryRepository bookCategoryRepository;
    private final BookRepository bookRepository;

    @Autowired
    public BookCategoryService(BookCategoryRepository bookCategoryRepository, BookRepository bookRepository) {
        this.bookCategoryRepository = bookCategoryRepository;
        this.bookRepository = bookRepository;
    }

    public BookCategory addCategory(BookCategory category) {
        return bookCategoryRepository.save(category);
    }

    public List<BookCategory> getAllCategories() {
        return bookCategoryRepository.findAll();
    }

    public BookCategory getCategoryById(Long categoryId) {
        Optional<BookCategory> category = bookCategoryRepository.findById(categoryId);
        return category.orElse(null);
    }

    public BookCategory updateCategory(Long categoryId, BookCategory category) {
        if (!bookCategoryRepository.existsById(categoryId)) {
            return null;
        }
        category.setId(categoryId);
        return bookCategoryRepository.save(category);
    }

    public void deleteCategory(Long categoryId) {
        bookCategoryRepository.deleteById(categoryId);
    }

    public void addBookToCategory(Long categoryId, Long bookId) {
        Optional<BookCategory> category = bookCategoryRepository.findById(categoryId);
        Optional<Book> book = bookRepository.findById(bookId);

        if (category.isPresent() && book.isPresent()) {
            category.get().getBooks().add(book.get());
            bookCategoryRepository.save(category.get());
        }
    }

    public List<Book> getBooksByCategoryId(Long categoryId) {
        Optional<BookCategory> category = bookCategoryRepository.findById(categoryId);
        return category.map(BookCategory::getBooks).orElse(null);
    }

    public void removeBookFromCategory(Long categoryId, Long bookId) {
        Optional<BookCategory> category = bookCategoryRepository.findById(categoryId);
        if (category.isPresent()) {
            category.get().getBooks().removeIf(book -> book.getId().equals(bookId));
            bookCategoryRepository.save(category.get());
        }
    }
}
