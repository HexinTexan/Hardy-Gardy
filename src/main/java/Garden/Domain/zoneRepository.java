package Garden.Domain;
import Garden.entity.Zones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface zoneRepository extends JpaRepository<Zones, Integer> {
    List<Zones> findById (int zoneId);
   /* Zones findByFlowId(int flowId); */
}
