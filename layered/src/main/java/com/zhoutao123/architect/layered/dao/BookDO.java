package com.zhoutao123.architect.layered.dao;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookDO {

  //  用户ID
  private String id;

  // 书名
  private String bookName;

  // 创建时间
  private LocalDateTime createTime;

  // 更新时间
  private LocalDateTime updateTime;

  public BookDO(String id, String bookName) {
    this.id = id;
    this.bookName = bookName;
  }
}
