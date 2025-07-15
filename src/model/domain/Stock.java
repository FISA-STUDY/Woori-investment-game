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
public class Stock {
	@NonNull
	private int sId;
	@NonNull
	private String sName;
	@NonNull
	private int sPrice;
	
	private double sGraph;
	
}
