package build.services;

import build.model.Host;
import build.response.Response;

public interface iHostService {

    public Response guardarHost(Host host);

    public Response editarHost(Host host);

    public Response elimiarHost(Host host);

    public Response eliminarHost(Long idHost);

    public Response eliminarHost(String nombreHost);

    public Response listarHosts();

    public Response buscarHost(String nombreHost);

    public Response buscarHost(Long idHost);

    public Response buscarHostPorIp(String ipHost);
    
    public String ping(String ipHost);

}
