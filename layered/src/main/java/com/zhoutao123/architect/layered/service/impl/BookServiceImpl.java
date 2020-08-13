package com.zhoutao123.architect.layered.service.impl;

import com.zhoutao123.architect.layered.dao.BookDO;
import com.zhoutao123.architect.layered.dao.mapper.BookMapper;
import com.zhoutao123.architect.layered.service.BookService;
import com.zhoutao123.architect.layered.service.converter.BookConverter;

import com.zhoutao123.architect.layered.service.model.Book;
import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

  private final BookConverter converter = new BookConverter();

  private final BookMapper bookMapper;

  public BookServiceImpl(BookMapper bookMapper) {
    this.bookMapper = bookMapper;
  }

  @Override
  public Book findById(String id) {
    BookDO bookDO = bookMapper.findByBookname(id);
    return converter.converterTo(bookDO);
  }

  @Override
  public Book updated(String id, String bookName, BigDecimal price) {
    return null;
  }
}
