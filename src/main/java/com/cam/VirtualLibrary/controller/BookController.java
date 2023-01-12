package com.cam.VirtualLibrary.controller;

import com.cam.VirtualLibrary.exception.BookNotFoundException;
import com.cam.VirtualLibrary.model.Book;
import com.cam.VirtualLibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @PostMapping("/book")
    Book newBook(@RequestBody Book newBook){
        return bookRepository.save(newBook);
    }
    @GetMapping("/library")
    List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/book/{id}")
    Book getBookById(@PathVariable Long id){
        return bookRepository.findById(id)
                .orElseThrow(()-> new BookNotFoundException(id));
    }

    @PutMapping("/book/{id}")
    Book updateBook(@RequestBody Book newBook, @PathVariable Long id){
        return bookRepository.findById(id)
                .map(book -> {
                    book.setTitle(newBook.getTitle());
                    book.setAuthor(newBook.getAuthor());
                    book.setRating(newBook.getRating());
                    book.setNotes(newBook.getNotes());
                    return bookRepository.save(book);
                }).orElseThrow(()->new BookNotFoundException(id));
    }

    @DeleteMapping("/book/{id}")
    String deleteBook(@PathVariable Long id){
        if(!bookRepository.existsById(id)) throw new BookNotFoundException(id);
        bookRepository.deleteById(id);
        return "Book with id " + id + " has been deleted successfully.";
    }
}
