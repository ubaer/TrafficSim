package API;

import com.ai.trafficsim.ChartData;
import com.ai.trafficsim.TrafficSimMain;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class StatisticsController {

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping("/api/crash")
    public List<Integer> crash() {
        TrafficSimMain instance = TrafficSimMain.getInstance();
        return instance.GetStatistics().GetCrashData();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping("/api/wait-time")
    @ResponseBody
    public List<ChartData> waitTime() {
        TrafficSimMain instance = TrafficSimMain.getInstance();
        return instance.GetStatistics().GetWaitTime();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping("/api/route-finished")
    @ResponseBody
    public List<ChartData> routeFinished() {
        TrafficSimMain instance = TrafficSimMain.getInstance();
        return instance.GetStatistics().GetRouteFinished();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping("/api/current-vehicle")
    @ResponseBody
    public List<ChartData> currentVehicle() {
        TrafficSimMain instance = TrafficSimMain.getInstance();
        return instance.GetStatistics().GetCurrentVehicles();
    }
}
