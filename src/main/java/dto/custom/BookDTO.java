package dto.custom;


import dto.SuperDTO;
import lombok.*;

@NoArgsConstructor
@ToString
@Getter
@Setter
@AllArgsConstructor
public class BookDTO implements SuperDTO {

        private int id;
        private String name;
        private String isbn;
        private double price;
        private  String author;
        private int publisherId;
        private  int mainCategoryId;

}
