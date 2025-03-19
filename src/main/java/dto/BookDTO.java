package dto;


import lombok.*;

@NoArgsConstructor
@ToString
@Data
@AllArgsConstructor
public class BookDTO {

        private int id;
        private String name;
        private String isbn;
        private double price;
        private  String author;
        private int publisherId;
        private  int mainCategoryId;

}
