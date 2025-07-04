package model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter	
@NonNull
public class News {
	private int n_id;
	private Boolean n_isGood;
	private String n_message;
	private String s_name;
}
