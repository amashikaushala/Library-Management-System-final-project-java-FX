package entity;

import com.sun.jdi.event.StepEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString
@Data
@AllArgsConstructor
public class Book {
    private int id;
    private String name;
    private String isbn;
    private double price;
    private  String author;
    private int publisherId;
    private  int mainCategoryId;
  }
