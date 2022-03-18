package Garden.Controller;
import Garden.Domain.flowerRepository;
import Garden.Domain.zoneRepository;
import Garden.entity.Flower;
import Garden.entity.Zones;
import Test.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FlowerController {

    @Autowired
    private final zoneRepository zoRe;
    @Autowired
    private final flowerRepository floRe;
    //------------Gets--------------
    //ZoneId
    @GetMapping("/Zones/{zoneId}/Flower")
    public ResponseEntity<List<Flower>> getFlowersByZoneId(
            @PathVariable(value = "zoneId") Integer zones) {
            List<Flower> flow = floRe.findAllByZoneId(zones);
            return new ResponseEntity<>(flow, HttpStatus.OK);
    }

    //Color
    @GetMapping("/Flower/Color/{flowColor}")
    public ResponseEntity<List<Flower>> getAllFlowersByColor(
            @PathVariable(value = "flowColor") String flowColor) {

        List<Flower> flow = floRe.findByFlowColor(flowColor);
        return new ResponseEntity<>(flow, HttpStatus.OK);
    }
    //Height
   /* @GetMapping("/Zones/{zoneId}/Flower")
    public ResponseEntity<List<Flower>> getAllFlowersByHeight(
            @PathVariable(value = "zoneId") Integer Zones) {
        if(!zoRe.existsById(Zones)) {
            throw new ResourceNotFoundException("not found");
        }
        List<Flower> flow = floRe.findAll();
        return new ResponseEntity<>(flow, HttpStatus.OK);
    }
    //Color and Height
    @GetMapping("/Flower/{flowColor}/{height}")
    public ResponseEntity<List<Flower>> getFlowersByFlowColorAndHeight(
            @PathVariable(value = "flowColor")String flowColor,
            @PathVariable(value = "height") Integer height) {

            List<Flower> flow = floRe.findByFlowColorAndHeight(flowColor,height);
        return new ResponseEntity<>(flow, HttpStatus.OK);
    }
    //Name

    @GetMapping("/Zones/{zoneId}/Flower")
    public ResponseEntity<List<Flower>> getAllFlowersByName(
            @PathVariable(value = "zoneId") Integer Zones) {
        if(!zoRe.existsById(Zones)) {
            throw new ResourceNotFoundException("not found");
        }
        List<Flower> flow = floRe.findAll();
        return new ResponseEntity<>(flow, HttpStatus.OK);
    } */
    //---------------Posts----------------------
    @PostMapping("/Zones/{zoneId}/Flower")
    public ResponseEntity<Flower> createFlower(@PathVariable(value = "zoneId") Integer zoneId,
                                                @RequestBody Flower flowReq) {
        Flower flow = zoRe.findById(zoneId).map(zones -> {
            flowReq.setZones(zones);

            return floRe.save(flowReq);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Zone with id = " + zoneId));
        return new ResponseEntity<>(flow, HttpStatus.CREATED);
    }
    //------------------Updates--------------------------
    @PutMapping("/Flower/{flowId}")
    public ResponseEntity<Flower> updateFlower(@PathVariable("flowId") Integer flowId, @RequestBody Flower flowReq) {
        Flower flow = floRe.findById(flowId).orElseThrow(() -> new ResourceNotFoundException("Flower " + flowId + "not found"));
        flow.setFlowColor(flowReq.getFlowColor());
        flow.setFlowName(flowReq.getFlowName());
        flow.setHeight(flowReq.getHeight());
        flow.setFlowId(flowReq.getFlowId());
        return new ResponseEntity<>(floRe.save(flow), HttpStatus.OK);
    }
    //---------------------Deletes----------------------------
    @DeleteMapping("/Flower/{flowId}")
    public ResponseEntity<HttpStatus> deleteFlower(@PathVariable("flowId") Integer flowId) {
        floRe.deleteById(flowId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

