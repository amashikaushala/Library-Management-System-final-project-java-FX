package entity.custom;

import entity.SuperEntity;
import lombok.*;

@ToString
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor


public class Category implements SuperEntity {
    private int id;
    private String name;

}
