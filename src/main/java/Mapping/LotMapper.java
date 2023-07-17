package Mapping;

import org.springframework.stereotype.Component;
import pro.sky.auction_coursework.dto.Bid;
import pro.sky.auction_coursework.dto.CreateLot;
import pro.sky.auction_coursework.dto.Status;
import pro.sky.auction_coursework.model.Lot;

@Component
public class LotMapper {
    public Bid toDTO(pro.sky.auction_coursework.model.Bid bid){
        Bid bidDTO = new Bid();
        bidDTO.setBidDate(bid.getDateTime());
        bidDTO.setBidderName(bid.getName());
        return bidDTO;
    }

    public Lot toEntity(CreateLot createLot){
        Lot lot = new Lot();
        lot.setStatus(Status.CREATED);
        lot.setTitle(createLot.getTitle());
        lot.setDescription(createLot.getDescription());
        lot.setStartPrice(createLot.getStartPrice());
        lot.setBidPrice(createLot.getBidPrice());
        return lot;
    }
    public pro.sky.auction_coursework.dto.Lot toDto(Lot lot){
        pro.sky.auction_coursework.dto.Lot lotDTO = new pro.sky.auction_coursework.dto.Lot();
        lotDTO.setTitle(lot.getTitle());
        lotDTO.setDescription(lot.getDescription());
        lotDTO.setStatus(lot.getStatus());
        lotDTO.setBidPrice(lot.getBidPrice());
        lotDTO.setStartPrice(lot.getStartPrice());
        return lotDTO;
    }
}
