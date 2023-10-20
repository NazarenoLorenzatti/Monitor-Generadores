package build.services.impl;

import build.Dao.iLogDao;
import build.model.Host;
import build.model.Log;
import build.response.ResponseLog;
import build.services.iLogService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LogServiceImpl implements iLogService {

    @Autowired
    private iLogDao logDao;

    @Override
    public ResponseLog registrarLog(Log log) {
        ResponseLog respuesta = new ResponseLog();
        List<Log> logs = new ArrayList();
        try {
            if (log != null) {
                logs.add(logDao.save(log));
                respuesta.setLogs(logs);
                respuesta.setMetadata("Correcto", "00", "Se Registro el Log");
                return respuesta;
            } else {
                respuesta.setMetadata("Error", "-1", "No se pudo Guardar El Rgistro");
                return respuesta;
            }
        } catch (Exception e) {
            e.getStackTrace();
            respuesta.setMetadata("Error", "-1", "Ocurrio un Error al intentar guardar el Registro");
            return respuesta;
        }
    }

    @Override
    public ResponseLog eliminarLog(Log log) {
        ResponseLog respuesta = new ResponseLog();
        try {
            if (log != null) {
                logDao.delete(log);
                respuesta.setMetadata("Correcto", "00", "Se Registro el Log");
                return respuesta;
            } else {
                respuesta.setMetadata("Error", "-1", "No se pudo Guardar El Rgistro");
                return respuesta;
            }
        } catch (Exception e) {
            e.getStackTrace();
            respuesta.setMetadata("Error", "-1", "Ocurrio un Error al intentar guardar el Registro");
            return respuesta;
        }
    }

    @Override
    public ResponseLog buscarLog(Host host) {
        ResponseLog respuesta = new ResponseLog();
        List<Log> logs;
        try {
            if (host != null) {
                logs = logDao.findByHost(host);
                respuesta.setMetadata("Correcto", "00", "Se Registro el Log");
                return respuesta;
            } else {
                respuesta.setMetadata("Error", "-1", "No se pudo Guardar El Rgistro");
                return respuesta;
            }
        } catch (Exception e) {
            e.getStackTrace();
            respuesta.setMetadata("Error", "-1", "Ocurrio un Error al intentar guardar el Registro");
            return respuesta;
        }
    }
    
    // Buscamos el ultimo Log del Host para identificar si se debe cerrar la fecha o crear un nuevo Log
    @Override
    public ResponseLog buscarUltimoLogPorHost(Host host) {
        ResponseLog respuesta = new ResponseLog();
        List<Log> logs = new ArrayList();
        try {
            if (host != null) {
                for (Log l : logDao.findByHost(host)) {
                    if (l.getFin() == null) {
                        logs.add(l);
                        respuesta.setLogs(logs);
                        respuesta.setMetadata("Correcto", "00", "Se Registro el Log");
                        return respuesta;
                    }
                }
            } else {
                respuesta.setMetadata("Error", "-1", "No se pudo Guardar El Rgistro");
                return respuesta;
            }
        } catch (Exception e) {
            e.getStackTrace();
            respuesta.setMetadata("Error", "-1", "Ocurrio un Error al intentar guardar el Registro");
            return respuesta;
        }
        respuesta.setMetadata("Error", "-1", "No se pudo Guardar El Rgistro");
        return respuesta;
    }

    @Override
    public ResponseLog establecerFin(Log latestLog) {
        ResponseLog respuesta = new ResponseLog();
        List<Log> logs = new ArrayList();
        try {
            if (latestLog != null) {
                Optional<Log> logEncontrado = logDao.findById(latestLog.getIdLog());
                if (logEncontrado.isPresent()) {
                    logs.add(logDao.save(logEncontrado.get()));
                    respuesta.setLogs(logs);
                    respuesta.setMetadata("Correcto", "00", "Se Registro el Log");
                    return respuesta;
                }

            } else {
                respuesta.setMetadata("Error", "-1", "No se pudo Guardar El Rgistro");
                return respuesta;
            }
        } catch (Exception e) {
            e.getStackTrace();
            respuesta.setMetadata("Error", "-1", "Ocurrio un Error al intentar guardar el Registro");
            return respuesta;
        }
        respuesta.setMetadata("Error", "-1", "No se pudo Guardar El Rgistro");
        return respuesta;
    }

    public void vaciar() {
        logDao.deleteAll();
    }
}
