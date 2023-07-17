package pro.sky.auction_coursework.service;

import Mapping.LotMapper;
import exceptions.LotNodStartedException;
import exceptions.LotNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pro.sky.auction_coursework.dto.*;
import pro.sky.auction_coursework.model.Lot;
import pro.sky.auction_coursework.repository.BidRepository;
import pro.sky.auction_coursework.repository.LotRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class LotService {
    private final LotRepository lotRepository;
    private final BidRepository bidRepository;
    private final LotMapper lotMapper;

    public Bid getFirstBidder(int idOfLot) {


//                (PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "dateTime")));
        return bidRepository.findAllByLot_IdOrderByDateTimeAsc(idOfLot)
                .map(lotMapper::toDTO)
                .orElseThrow(LotNotFoundException::new);
//                Bid firstBidder;
    }
    public Bid getMostFrequentBidder(int idOfLot){

        return bidRepository.findTheMostFrequentBidder(idOfLot)
                .orElseThrow(LotNotFoundException::new);
    }
    public FullLot getFullLot (int idOfLot){

        return lotRepository.getFullLot(idOfLot)
                .orElseThrow(LotNotFoundException::new);
    }

    public void startAuction(int id){
    Lot lot = lotRepository.findById(id)
            .orElseThrow(LotNotFoundException::new);
    lot.setStatus(Status.STARTED);
    lotRepository.save(lot);
    }

    public void createBid(int id, Bidder bidder){
        Lot lot = lotRepository.findById(id)
            .orElseThrow(LotNotFoundException::new);
                if(lot.getStatus() == Status.CREATED || lot.getStatus() == Status.STOPPED){
                throw new LotNodStartedException();
                }
        Bid bid = new Bid();
                bid.setBidderName(bidder.getName());
                bid.setBidDate(bid.getBidDate());
    }

    public void stopAuction(int id){
        Lot lot = lotRepository.findById(id)
                .orElseThrow(LotNotFoundException::new);
        lot.setStatus(Status.STOPPED);
        lotRepository.save(lot);
    }
    public pro.sky.auction_coursework.dto.Lot createLot(CreateLot createLot){
        Lot lot = lotRepository.save(lotMapper.toEntity(createLot));
        return lotMapper.toDto(lot);
    }

    public List<pro.sky.auction_coursework.dto.Lot> findLots (Status status, int page){
        Pageable pageable = PageRequest.of(page, 10);
        return Optional.ofNullable(status)
                .map(s->lotRepository.findAllByStatus(s, pageable))
                .orElseGet(()->lotRepository.findAll(pageable))
                .stream()
                .map(lotMapper::toDto)
                .collect(Collectors.toList());
    }
}
