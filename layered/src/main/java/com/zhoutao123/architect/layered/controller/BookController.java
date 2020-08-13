package com.zhoutao123.architect.layered.controller;

import com.zhoutao123.architect.layered.model.Response;
import com.zhoutao123.architect.layered.model.exception.ErrorCode;
import com.zhoutao123.architect.layered.model.exception.NotFoundException;
import com.zhoutao123.architect.layered.service.BookService;
import com.zhoutao123.architect.layered.service.model.Book;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {

  private final BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @PostMapping("/{bookName}")
  public Response<Book> login(@PathVariable("bookName") String bookName) {
    Book book = bookService.findById(bookName);
    return Response.ok(book);
  }

  @GetMapping("/exception-test")
  public Response<Book> find() {
    throw new NotFoundException(ErrorCode.PARAM_ERROR, "从深V方便的");
  }
}
