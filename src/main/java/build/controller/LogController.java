package build.controller;

import build.model.Host;
import build.model.Log;
import build.response.ResponseLog;
import build.services.impl.LogServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogServiceImpl logService;

    @PostMapping("/obtenerLog")
    public String obtenerLog(@RequestBody Host host, HttpServletRequest request) {
        request.getSession().setAttribute("hosts", logService.buscarLog(host).getLogs());
        return "index";
    }

    @PostMapping("/registrar")
    public String registrarLog(@RequestBody Log log, HttpServletRequest request) {
        request.getSession().setAttribute("hosts", logService.registrarLog(log).getLogs());
        return "index";
    }

    @DeleteMapping("/eliminarRegistro")
    public String eliminarLog(@RequestBody Log log, HttpServletRequest request) {
        request.getSession().setAttribute("hosts", logService.eliminarLog(log).getLogs());
        return "index";

    }
    
    @DeleteMapping("/vaciar")
    public String vaciar(){
        logService.vaciar();
        return "index";
    }

}
