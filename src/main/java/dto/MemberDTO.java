package dto;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private String id;
    private String name;
    private String address;
    private String email;
    private String contact;
}
