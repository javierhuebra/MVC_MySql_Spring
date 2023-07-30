package com.codingdojo.mvc.controllers;

import com.codingdojo.mvc.models.Book;
import com.codingdojo.mvc.services.BookService;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class BooksController {
    private final BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    //GET principal, muestra la lista de libros en el index
    @RequestMapping("/")
    public String index(Model model) {
        List<Book> books = bookService.allBooks();
        model.addAttribute("books", books);
        System.out.println(model);
        return "index";
    }
    
    //Crear nuevos libros, dirige a la pestaña new que tiene el form que va a cargar el objeto Book
    @RequestMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "new";
    }

    //POST agregar libro
    @RequestMapping(value="/books", method= RequestMethod.POST)
    public String create(@Valid @ModelAttribute("book") Book book, BindingResult result) {
        if (result.hasErrors()) {
            
            return "new";
        } else {
            bookService.createBook(book);
            System.out.println(result.hasErrors());
            return "redirect:/";
        }
    }

    //GET vista detalle del libro
    @RequestMapping("/books/{id}")
    public String showBook(@PathVariable("id") Long id, Model model) {
        Book book = bookService.findBook(id);
        model.addAttribute("book", book);
        return "detalle";
    }
}
