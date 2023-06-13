package com.bookStore.controllor;

import com.bookStore.entity.Book;
import com.bookStore.entity.MyBookList;
import com.bookStore.service.BookService;
import com.bookStore.service.MyBookListService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BookController {

  @Autowired
  private BookService service;

  @Autowired
  private MyBookListService myBookListService;

  @GetMapping("/")
  public String home() {
    return "Home";
  }

  @GetMapping("/book_register")
  public String bookRegister() {
    return "bookRegister";
  }

  @GetMapping("/available_books")
  public ModelAndView getAvaBook() {
    List<Book> list = service.getAllBooks();
    return new ModelAndView("bookList", "book", list);
  }

  @PostMapping("/save")
  public String addBook(@ModelAttribute Book b) {
    service.save(b);
    return "redirect:/available_books";
  }

  @GetMapping("/my_books")
  public String getMyBooks(Model model) {
    List<MyBookList> myBookLists = myBookListService.getAllMyBooks();
    model.addAttribute("book", myBookLists);
    return "MyBooks";
  }

  @RequestMapping("/mylist/{id}")
  public String getMyList(@PathVariable("id") Integer id) {
    Book book = service.getBookById(id);
    MyBookList myBookList = new MyBookList(book.getId(), book.getName(), book.getAuthor(), book.getPrice());
    myBookListService.saveMyBook(myBookList);
    return "redirect:/my_books";
  }

  @RequestMapping("/editBook/{id}")
  public String editBook(@PathVariable("id") Integer id, Model model) {
    Book book = service.getBookById(id);
    model.addAttribute("book", book);
    return "bookEdit";
  }

  @RequestMapping("/deleteBook/{id}")
  public String deleteBook(@PathVariable("id") Integer id) {
    service.deleteById(id);
    return "redirect:/available_books";
  }
}
