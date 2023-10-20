package build.services;

import build.model.Host;
import build.model.Log;
import build.response.Response;

public interface iLogService {
    
    public Response registrarLog(Log log);
    
    public Response eliminarLog(Log log);
    
    public Response buscarLog(Host host);
    
    public Response buscarUltimoLogPorHost(Host host);
    
    public Response establecerFin(Log latestLog);
}
