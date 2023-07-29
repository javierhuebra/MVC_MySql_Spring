package com.codingdojo.mvc.services;

// ...
import com.codingdojo.mvc.models.Book;
import com.codingdojo.mvc.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BookService {
    //Agregando el book al repositorio como una dependencia
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    //Devolviendo todos los libros.
    public List<Book> allBooks() {
        return bookRepository.findAll();
    }
    //Creando un libro.
    public Book createBook(Book b) {
        return bookRepository.save(b);
    }

    //Editar un libro
    public Book updateBook(Long id, String title, String desc, String lang, Integer numOfPages) {

        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setTitle(title);
            book.setDescription(desc);
            book.setNumberOfPages(numOfPages);
            book.setLanguage(lang);

            return bookRepository.save(book);
        } else {
            // Manejar el caso en el que no se encuentre el libro por su ID
            throw new NoSuchElementException("Libro no encontrado para el ID: " + id);
        }


    }

    //Obteniendo la información de un libro
    public Book findBook(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isPresent()) {
            return optionalBook.get();
        } else {
            return null;
        }
    }

    //Eliminando un libro
    public void deleteBook(Long id) {

        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("No se encontró ningún libro con el ID proporcionado.");//Para agregar errores esta sentencia
        }
    }
}


