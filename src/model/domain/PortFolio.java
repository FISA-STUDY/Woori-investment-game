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
public class PortFolio {
	private String p_name;
	private int p_amount;
	private int p_price;
	private String u_name;
}
