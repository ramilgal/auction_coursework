package pro.sky.auction_coursework.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
@AllArgsConstructor
@NoArgsConstructor
public class Bid {
    private String bidderName;
    private OffsetDateTime bidDate;

    public String getBidderName() {
        return bidderName;
    }

    public void setBidderName(String bidderName) {
        this.bidderName = bidderName;
    }

    public OffsetDateTime getBidDate() {
        return bidDate;
    }

    public void setBidDate(OffsetDateTime bidDate) {
        this.bidDate = bidDate;
    }
}
