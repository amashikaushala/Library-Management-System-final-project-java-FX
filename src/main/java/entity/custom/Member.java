package entity.custom;

import entity.SuperEntity;
import lombok.*;

@NoArgsConstructor
@ToString
@Getter
@Setter
@AllArgsConstructor

public class Member implements SuperEntity {
    private String id;
    private String name;
    private String address;
    private String email;
    private String contact;



}




