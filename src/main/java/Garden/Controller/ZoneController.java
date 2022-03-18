package Garden.Controller;

import Garden.Domain.zoneRepository;

import Garden.entity.Zones;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ZoneController {
    @Autowired
    zoneRepository zoRe;
    //---------------------------Gets-----------------------------
    @GetMapping("/Zones")
    public ResponseEntity<List<Zones>> getAllZones(@RequestParam(required = false) Integer Zones){
        List<Zones> zones1 = new ArrayList<Zones>(zoRe.findAll());
        return new ResponseEntity<>(zones1, HttpStatus.OK);
    }
    //--------------------------Posts------------------------------
    @PostMapping("/Zones")
    public ResponseEntity<Zones> createZone (@RequestBody Zones zones) {
        Zones zones1 = zoRe.save(new Zones(zones.getZoneId(), zones.getZoneHigh(), zones.getZoneLow()));
                return new ResponseEntity<>(zones1, HttpStatus.CREATED);
    }
    //--------------------------Updates----------------------------
}

