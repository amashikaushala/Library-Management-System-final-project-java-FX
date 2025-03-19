package entity;

import dto.MemberDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString
@Data
@AllArgsConstructor

public class Member extends MemberDTO {
    private String id;
    private String name;
    private String address;
    private String email;
    private String contact;



    }




