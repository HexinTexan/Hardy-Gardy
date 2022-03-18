package Garden.Domain;
import Garden.entity.Flower;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface flowerRepository extends JpaRepository<Flower, Integer> {
    @Query("From Flower Where zoneId = :zoneId")

    List<Flower> findAllByZoneId(Integer zoneId);
   /* Page<Flower> findByFlowColorAndHeight(String flowColor, Integer height); */
    List<Flower> findByFlowColor(String flowColor);

    /*  Page<Flower> findAllByZones(Integer zoneId, Pageable page); */
 /*  List<Flower> findByZoneId(Integer ZoneId); */
   /* List<Flower> findByZones(Integer zones); */
}
