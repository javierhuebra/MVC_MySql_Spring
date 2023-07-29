package com.codingdojo.mvc.controllers;

// ..
import com.codingdojo.mvc.models.Book;
import com.codingdojo.mvc.services.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BooksApi {
    private final BookService bookService;
    public BooksApi(BookService bookService){
        this.bookService = bookService;
    }

    //GET GENERAL
    @RequestMapping("/api/books")
    public List<Book> index() {
        System.out.println("Se han solicitado los datos");

        return bookService.allBooks();
    }

    //POST PARA CARGAR DE A UN LIBRO
    @RequestMapping(value="/api/books", method= RequestMethod.POST)
    public Book create(@RequestParam(value="title") String title,
                       @RequestParam(value="description") String desc,
                       @RequestParam(value="language") String lang,
                       @RequestParam(value="pages") Integer numOfPages) {
        Book book = new Book(title, desc, lang, numOfPages);
        return bookService.createBook(book);
    }


    //GET PARA SELECCIONAR DE A UN LIBRO
    @RequestMapping("/api/books/{id}")
    public Book show(@PathVariable("id") Long id) {
        Book book = bookService.findBook(id);
        return book;
    }

    //PUT para actualizar datos
    @RequestMapping(value="/api/books/{id}", method=RequestMethod.PUT)
    public Book update(@PathVariable("id") Long id,//Para poder editar debo mandar la direccion unica de ese libro
                       @RequestParam(value="title") String title,
                       @RequestParam(value="description") String desc,
                       @RequestParam(value="language") String lang,
                       @RequestParam(value="pages") Integer numOfPages) {
        Book book = bookService.updateBook(id, title, desc, lang, numOfPages);
        return book;
    }

    //DELETE para eliminar
    @RequestMapping(value="/api/books/{id}", method=RequestMethod.DELETE)
    public String destroy(@PathVariable("id") Long id) {
        bookService.deleteBook(id);

        return "Libro "+id+ " eliminado";
    }
}

