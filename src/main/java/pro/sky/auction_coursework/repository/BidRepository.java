package pro.sky.auction_coursework.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pro.sky.auction_coursework.dto.FullLot;
import pro.sky.auction_coursework.model.Bid;

import java.util.Optional;

public interface BidRepository extends JpaRepository<Bid, Integer> {
    Optional<Bid> findAllByLot_IdOrderByDateTimeAsc(int lotId);
        @Query("""
    SELECT new pro.sky.auction_coursework.dto.Bid(b.name, b.dateTime) FROM Bid b
    WHERE b.name = (SELECT b.name FROM Bid b GROUP BY b.name ORDER BY count(b.name) DESC LIMIT 1)
    ORDER BY b.dateTime DESC LIMIT 1
    """)
    Optional<pro.sky.auction_coursework.dto.Bid> findTheMostFrequentBidder(@Param("lotId") int lotId);







}
