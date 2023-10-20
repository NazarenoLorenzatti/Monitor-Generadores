package build.controller;

import build.model.Host;
import build.model.Log;
import build.services.impl.HostServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/host")
public class HostController {

    @Autowired
    private HostServiceImpl hostService;



    @GetMapping("/getHosts")
    public String obtenerHosts(HttpServletRequest request) throws Exception {
        request.getSession().setAttribute("hosts", hostService.listarHosts().getHosts());
        return "index";
    }

    @PostMapping(value = "/saveHost", consumes = "application/x-www-form-urlencoded")
    public String guardarNuevoHost(@ModelAttribute Host host, HttpServletRequest request) throws Exception {
        hostService.guardarHost(host);
        return "index";
    }

    @PostMapping(value = "/editHost", consumes = "application/x-www-form-urlencoded")
    public String editarHost(@ModelAttribute Host host, HttpServletRequest request) throws Exception {
        hostService.editarHost(host);
        return "index";
    }

    @GetMapping("/eliminarHost/{idHost}")
    public String eliminarHost(@PathVariable Long idHost, HttpServletRequest request) throws Exception {
        hostService.eliminarHost(idHost);
        return "index";
    }

    @DeleteMapping("/eliminarHost/{nombreHost}")
    public String eliminarHost(@PathVariable String nombreHost, HttpServletRequest request) throws Exception {
        request.getSession().setAttribute("hosts", hostService.eliminarHost(nombreHost).getHosts());
        return "index";
    }

    @GetMapping("/obtenerLog/{ipHost}")
    public String obtenerLogs(@PathVariable String ipHost, Model model, HttpServletRequest request) throws Exception {
        List<Log> logs = hostService.buscarHostPorIp(ipHost).getHosts().get(0).getLogs();
        model.addAttribute("logs", logs);
        model.addAttribute("tiempoEncendido", hostService.tiempoEncendido(ipHost));
        model.addAttribute("tiempoEncendidoMesActual", hostService.tiempoEncendidoMesActual(ipHost));
        if(!logs.isEmpty()){
            model.addAttribute("ultimoEncendido",logs.get(logs.size()-1).getInicio()); 
        }
        request.getSession().setAttribute("tablaLog", true);
        request.getSession().setAttribute("dibujarGraficos", false);
        return "index";
    }

    @GetMapping("/ping/{ipHost}")
    @ResponseBody
    public String realizarPing(@PathVariable String ipHost) {
        return hostService.ping(ipHost);
    }
    

}
