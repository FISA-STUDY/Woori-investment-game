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
    private int nId;
    
    @NonNull
    private Boolean nIsGood;
    
    @NonNull
    private String nMessage;
    
    @NonNull
    private String sName;
}