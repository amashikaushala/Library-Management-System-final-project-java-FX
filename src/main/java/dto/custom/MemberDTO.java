package dto.custom;

import dto.SuperDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO  implements SuperDTO {
    private String id;
    private String name;
    private String address;
    private String email;
    private String contact;
}
