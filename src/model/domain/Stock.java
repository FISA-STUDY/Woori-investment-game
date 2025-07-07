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
	private String s_name;
	@NonNull
	private int s_price;
	private double s_graph;
	private int s_amount;
}
