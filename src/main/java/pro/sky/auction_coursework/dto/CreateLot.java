package pro.sky.auction_coursework.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateLot {
    @NotBlank(message = "Input title")
    @Size(min = 3, max = 64)
    private String title;
    @NotBlank(message = "Input description")
    @Size(min = 1, max = 4096)
    private String description;
    @NotBlank(message = "Input startPrice")
    @Min(1)
    private int startPrice;
    @NotBlank(message = "Input bidPrice")
    @Min(1)
    private int bidPrice;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(int startPrice) {
        this.startPrice = startPrice;
    }

    public int getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(int bidPrice) {
        this.bidPrice = bidPrice;
    }
}
