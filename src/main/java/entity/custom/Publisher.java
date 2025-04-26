package entity.custom;

import entity.SuperEntity;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Publisher implements SuperEntity {
    private int id;
    private String name;
    private String location;
    private String contact;

}
