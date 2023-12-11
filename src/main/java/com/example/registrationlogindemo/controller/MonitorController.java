package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.dto.MonitorDto;
import com.example.registrationlogindemo.service.MonitorService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Controller
public class MonitorController {
    private MonitorService monitorService;
    /** Logger. */
//    private static final Logger log = Logger.getLogger(SseController.class);
    /** List of all emitters connected to the service. */
    private final List<SseEmitter> sseEmitter = new LinkedList<>();
    /** Temporary state for demonstration. */
    /** Counter for state changes. */
    private int counter = 1;
    public MonitorController(MonitorService monitorService) {
        this.monitorService = monitorService;
        ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(4);
        scheduledPool.scheduleWithFixedDelay(changeState, 0, 1, TimeUnit.SECONDS);
    }


    @GetMapping("/monitor")
    public String listRegisteredUsers(Model model){
        List<MonitorDto> monitors = monitorService.findAllMonitorTbs();
        model.addAttribute("monitors", monitors);
        return "monitor";
    }

    /**
     * Viewer can register here to get sse messages.
     * @return an server state event emitter
     * @throws IOException if registering the new emitter fails
     */
    @RequestMapping(path = "/retrieve", method = RequestMethod.GET)
    public SseEmitter register() throws IOException {
        System.out.println("Registering a stream.");
        SseEmitter emitter = new SseEmitter();

        synchronized (sseEmitter) {
            sseEmitter.add(emitter);
        }
        emitter.onCompletion(() -> sseEmitter.remove(emitter));

        return emitter;
    }



    private final Runnable changeState = () -> {
        List<MonitorDto> monitors = monitorService.findAllMonitorTbs();

        synchronized (sseEmitter) {
            sseEmitter.forEach((SseEmitter emitter) -> {
                try {
                    emitter.send(monitors, MediaType.APPLICATION_JSON);
                } catch (IOException e) {
                    emitter.complete();
                    sseEmitter.remove(emitter);
                }
            });
        }
    };

}
