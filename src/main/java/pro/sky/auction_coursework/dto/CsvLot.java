package pro.sky.auction_coursework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CsvLot {
private int id;
private String title;
private Status status;
private String lastBidder;
private int currentPrice;


}
