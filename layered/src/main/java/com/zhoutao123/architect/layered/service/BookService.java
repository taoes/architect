package com.zhoutao123.architect.layered.service;


import com.zhoutao123.architect.layered.service.model.Book;
import java.math.BigDecimal;

public interface BookService {


  Book findById(String id);

  Book updated(String id, String bookName, BigDecimal price);


}
