package com.zhoutao123.architect.layered.dao.mapper;

import com.zhoutao123.architect.layered.dao.BookDO;
import java.util.UUID;
import org.springframework.stereotype.Component;

// Mock MyBatis Mapper
@Component
public class BookMapper {

  public BookDO findByBookname(String bookName) {
    return new BookDO(UUID.randomUUID().toString(), bookName);
  }
}
