package pro.sky.auction_coursework.dto;
import jakarta.validation.constraints.*;

public class Bidder {
    @NotBlank(message = "Input name")
    @Size(min = 1, max = 64)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
