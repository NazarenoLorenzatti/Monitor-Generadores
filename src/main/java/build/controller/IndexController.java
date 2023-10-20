package build.controller;

import build.services.impl.HostServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired
    private HostServiceImpl hostService;

    @GetMapping("/")
    public String getIndex(Model model, HttpServletRequest request) {
        request.getSession().setAttribute("hosts", hostService.listarHosts().getHosts());
        request.getSession().setAttribute("tablaLog", false);
        request.getSession().setAttribute("dibujarGraficos", true);
        return "index";
    }

    @GetMapping("/index")
    public String getIndex2(Model model, HttpServletRequest request) {
        request.getSession().setAttribute("hosts", hostService.listarHosts().getHosts());
        request.getSession().setAttribute("tablaLog", false);
        request.getSession().setAttribute("dibujarGraficos", true);
        return "index";
    }
}
