package tr.com.frontech.hw.server2.controller.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import tr.com.frontech.hw.server2.data.RandomDataContainer;
import tr.com.frontech.hw.server2.data.XYDataContainer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mert on 15.2.2015.
 */
@Controller
public class WebSocketController extends TextWebSocketHandler {

    @MessageMapping("/random" )
    @SendTo("/topic/random")
    public  RandomDataContainer getRandomData() throws Exception{
        System.out.println("getRandomData()");
        Thread.sleep(2000);
        RandomDataContainer randomDataContainer = new RandomDataContainer(new Long(999),"rndstr");
        return randomDataContainer;
    }

    @MessageMapping("/xy" )
    @SendTo("/topic/xy")
    public XYDataContainer getXYData() throws Exception {
        System.out.println("getXYData()");
        Thread.sleep(2000);
        XYDataContainer randomXYContainer = new XYDataContainer(5,2);
        return randomXYContainer;
    }

    @MessageMapping("/all" )
    @SendTo("/topic/all")
    public List getAllData() throws Exception {
        System.out.println("getAllData()");
        Thread.sleep(2000);
        RandomDataContainer randomDataContainer = new RandomDataContainer(new Long(123),"rndstr123");
        XYDataContainer randomXYContainer = new XYDataContainer(15,12);
        List finalData = new ArrayList();
        finalData.add(randomDataContainer);
        finalData.add(randomXYContainer);
        return finalData;
    }


    @RequestMapping("/start")
    public String start() {
        return "start";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }
}