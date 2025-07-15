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
	private String pName;
	private int pAmount;
	private int pPrice;
	private String uName;
}
