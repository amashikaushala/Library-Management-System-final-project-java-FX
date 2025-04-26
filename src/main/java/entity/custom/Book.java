package entity.custom;



import entity.SuperEntity;
import lombok.*;

@NoArgsConstructor
@ToString
@Getter
@Setter
@AllArgsConstructor
public class Book implements SuperEntity {
    private int id;
    private String name;
    private String isbn;
    private double price;
    private String author;
    private int publisherId;
    private int mainCategoryId;

}
