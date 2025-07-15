package model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {

    @NonNull
    private String uName;    

    @NonNull
    private String uPassword;  

    private long uWallet;
    
    // 커스텀 생성자 (회원가입 시 사용)
    public User(String uName, String uPassword) {
        this.uName = uName;
        this.uPassword = uPassword;
    }
}
