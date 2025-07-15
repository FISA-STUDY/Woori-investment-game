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
	private Long pId;
	private int pPrice;
	private int pAmount;
	private String uName;
	private String sName;
	private Long sId;
	
	
}
