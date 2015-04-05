package tr.com.frontech.hw.server2.controller.rest;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Mert on 15.2.2015.
 */
@Controller
public class ServerTwoController {


    @Autowired
    private SimpMessagingTemplate template;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView dispatchToMain() {
        ModelAndView model = new ModelAndView("index");
        return model;
    }


    @RequestMapping(value = "/push")
    public void updateXYHandler(final HttpSession session,
                                final HttpServletResponse response,
                                @RequestParam("rLong") final String rLong,
                                @RequestParam("rString") final String rString,
                                @RequestParam("Y") final String y) {

        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json; charset=UTF-8");
        System.out.println();
        System.out.println("Push message has been received from server 1");
        System.out.println("new long: " +rLong);
        System.out.println("new string: " +rString);
        System.out.println("new y: " +y);
        String message = rLong+","+rString+","+y;
        synchronized (this) {
            this.template.convertAndSend("/topic/xy", message);
            Integer yI = Integer.parseInt(y);
            System.out.println("Sleeping: "+ yI*1000 + "miliseconds");
            try {
                Thread.sleep(yI*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

/*
        System.out.println("...updateXYData()...");
        // butun calisan instanceleri oldur
        repositoryService.deleteDeployment(StaticXYDataContainer.deploymentId+"");
        //runtimeService.deleteProcessInstance(StaticXYDataContainer.processId+"","dataupdate");
        System.out.println("DeploymentID "+StaticXYDataContainer.deploymentId+" has been deleted");
        System.out.println("process id: "+StaticXYDataContainer.processId+" killed..");
        HashMap<String,Object> variablesKeyValuePair = new HashMap();
        variablesKeyValuePair.put("x",Integer.parseInt(x));
        variablesKeyValuePair.put("y",Integer.parseInt(y));
        runtimeService.startProcessInstanceById( ++StaticXYDataContainer.processId  + "", variablesKeyValuePair);
        System.out.println("...new process started, process id :"+StaticXYDataContainer.processId);
        System.out.println("...updateXYData()...");
        */
