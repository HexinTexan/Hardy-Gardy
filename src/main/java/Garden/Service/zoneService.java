/*package Garden.Service;
import Garden.entity.Zones;
import Garden.Domain.zoneRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class zoneService {
    zoneRepository zR;
    public zoneService(zoneRepository zR) {
        this.zR = zR;
    }

    public List<Zones>findAllZones(){
        return zR.findAllZones();
    }

    public Zones findZoneById(Integer zoneId) {
        return zR.findZoneById(zoneId);
    }

    public Zones findZoneByCold(Integer zoneCold) {
        return zR.findZoneByCold(zoneCold);
    }

    public Zones findZoneByHot(Integer zoneHot) {
        return zR.findZoneByHot(zoneHot);
    }

    public Zones saveZone(Zones z) {
        return zR.save(z);
    }
}
*/