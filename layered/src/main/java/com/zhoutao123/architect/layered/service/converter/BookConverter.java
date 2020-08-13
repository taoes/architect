package com.zhoutao123.architect.layered.service.converter;

import com.zhoutao123.architect.layered.dao.BookDO;
import com.zhoutao123.architect.layered.dao.UserDO;
import com.zhoutao123.architect.layered.service.AbstractConverter;
import com.zhoutao123.architect.layered.service.model.Book;
import com.zhoutao123.architect.layered.service.model.User;

import org.springframework.stereotype.Component;

public class BookConverter extends AbstractConverter<Book, BookDO> {

  @Override
  public Book converterTo(BookDO bookDO) {
    return new com.zhoutao123.architect.layered.service.model.Book()
        .setId(convertNullable(bookDO.getId(), String::trim))
        .setBookName(bookDO.getBookName());
  }

  @Override
  public BookDO converterFrom(Book book) {
    return new BookDO().setId(book.getId()).setBookName(book.getBookName());
  }
}
