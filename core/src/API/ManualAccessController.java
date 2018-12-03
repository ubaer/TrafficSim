package API;

import Obstacles.TrafficLight;
import com.ai.trafficsim.TrafficSimMain;
import org.springframework.web.bind.annotation.*;

@RestController
public class ManualAccessController {

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping("/api/traffic-light/{id}")
    @ResponseBody
    public TrafficLight trafficLightToggle(@PathVariable("id") int id) {
        TrafficSimMain instance = TrafficSimMain.getInstance();
        return instance.ToggleTrafficLight(id);
    }
}
