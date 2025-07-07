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
public class News {
    private int n_id;
    
    @NonNull
    private Boolean n_isGood;
    
    @NonNull
    private String n_message;
    
    @NonNull
    private String s_name;
}