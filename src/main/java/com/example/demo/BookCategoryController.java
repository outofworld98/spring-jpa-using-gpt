package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class BookCategoryController {
    private final BookCategoryService bookCategoryService;

    @Autowired
    public BookCategoryController(BookCategoryService bookCategoryService) {
        this.bookCategoryService = bookCategoryService;
    }

    // Endpoint to add a book category
    @PostMapping
    public ResponseEntity<BookCategory> addCategory(@RequestBody BookCategory category) {
        BookCategory addedCategory = bookCategoryService.addCategory(category);
        return ResponseEntity.ok(addedCategory);
    }

    // Endpoint to get all book categories
    @GetMapping
    public ResponseEntity<List<BookCategory>> getAllCategories() {
        List<BookCategory> allCategories = bookCategoryService.getAllCategories();
        return ResponseEntity.ok(allCategories);
    }

    // Endpoint to get a book category by ID
    @GetMapping("/{categoryId}")
    public ResponseEntity<BookCategory> getCategoryById(@PathVariable Long categoryId) {
        BookCategory category = bookCategoryService.getCategoryById(categoryId);
        if (category == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(category);
    }

    // Endpoint to update a book category
    @PutMapping("/{categoryId}")
    public ResponseEntity<BookCategory> updateCategory(@PathVariable Long categoryId, @RequestBody BookCategory category) {
        BookCategory updatedCategory = bookCategoryService.updateCategory(categoryId, category);
        if (updatedCategory == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedCategory);
    }

    // Endpoint to delete a book category
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId) {
        bookCategoryService.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }

    // Endpoint to add a book to a category
    @PostMapping("/{categoryId}/books")
    public ResponseEntity<Void> addBookToCategory(@PathVariable Long categoryId, @RequestParam Long bookId) {
        bookCategoryService.addBookToCategory(categoryId, bookId);
        return ResponseEntity.ok().build();
    }

    // Endpoint to get books belonging to a category
    @GetMapping("/{categoryId}/books")
    public ResponseEntity<List<Book>> getBooksByCategoryId(@PathVariable Long categoryId) {
        List<Book> books = bookCategoryService.getBooksByCategoryId(categoryId);
        if (books == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(books);
    }

    // Endpoint to remove a book from a category
    @DeleteMapping("/{categoryId}/books/{bookId}")
    public ResponseEntity<Void> removeBookFromCategory(@PathVariable Long categoryId, @PathVariable Long bookId) {
        bookCategoryService.removeBookFromCategory(categoryId, bookId);
        return ResponseEntity.noContent().build();
    }
}
