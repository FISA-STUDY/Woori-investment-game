package model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import model.domain.News;
import model.domain.Stock;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsStockPair {
    private News news;
    private Stock stock;
    
    
}