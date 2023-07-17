package pro.sky.auction_coursework.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pro.sky.auction_coursework.dto.FullLot;
import pro.sky.auction_coursework.dto.Status;
import pro.sky.auction_coursework.model.Lot;

import java.util.Optional;

public interface LotRepository extends JpaRepository<Lot, Integer> {
    //    @Query("""
//    SELECT new pro.sky.auction_coursework.dto.FullLot(l.id, l.status, l.title, l.description, l.start_price, l.bid_price,
//    (SELECT count(b.id) FROM Bid b WHERE b.lot.id = :lotId), )
//    FROM Lot l INNER JOIN Bid b ON b.lot=l GROUP BY WHERE l.id = : lotId
//
//    """)
    @Query(value = """
                SELECT
                l.id as id,
                l.status as status,
                l.title as title,
                l.description as description,
                l.start_price as startPrice,
                l.bid_price as bidPrice,
                l.start_price + l.bid_price * (SELECT count(b.id) FROM bids b WHERE b.lot.id = :lotId) as currentPrice,
                a.name as bidderName,
                a.date_time as bidDate
                FROM lots l,
                (SELECT b.name, b.date_time FROM bids b WHERE b.lot.id = :lotId ORDER BY b.date_time DESC LIMIT 1) a
                WHERE l.id = :lotId
                """, nativeQuery = true)
    Optional<FullLot> getFullLot(@Param("lotId") int lotId);

    Page<Lot> findAllByStatus (Status status, Pageable pageable);


}
