package build.services.impl;

import build.Dao.iHostDao;
import build.model.Host;
import build.model.Log;
import build.response.ResponseHost;
import build.services.iHostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.InetAddress;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class HostServiceImpl implements iHostService {

    @Autowired
    private iHostDao hostDao;

    @Autowired
    private LogServiceImpl logService;

    @Override
    public ResponseHost guardarHost(Host host) {
        ResponseHost respuesta = new ResponseHost();
        List<Host> hosts = new ArrayList();
        try {
            if (host != null) {
                hosts.add(hostDao.save(host));
                respuesta.setHosts(hosts);
                respuesta.setMetadata("Correcto", "00", "Se Guardo Correctamente el Host");
                return respuesta;
            } else {
                respuesta.setMetadata("Error", "-1", "No se pudo Guardar El Host");
                return respuesta;
            }
        } catch (Exception e) {
            e.getStackTrace();
            respuesta.setMetadata("Error", "-1", "Ocurrio un Error al intentar guardar el Host");
            return respuesta;
        }
    }

    @Override
    public ResponseHost editarHost(Host host) {
        ResponseHost respuesta = new ResponseHost();
        List<Host> hosts = new ArrayList();
        try {
            if (host != null) {
                hosts.add(hostDao.save(host));
                respuesta.setHosts(hosts);
                respuesta.setMetadata("Correcto", "00", "Se Edito Correctamente el Host");
                return respuesta;
            } else {
                respuesta.setMetadata("Error", "-1", "No se pudo Editar El Host");
                return respuesta;
            }
        } catch (Exception e) {
            e.getStackTrace();
            respuesta.setMetadata("Error", "-1", "Ocurrio un Error al intentar Editar el Host");
            return respuesta;
        }
    }

    @Override
    public ResponseHost elimiarHost(Host host) {
        ResponseHost respuesta = new ResponseHost();
        try {
            if (host != null) {
                hostDao.delete(host);
                respuesta.setMetadata("Correcto", "00", "Se Elimino Correctamente el Host");
                return respuesta;
            } else {
                respuesta.setMetadata("Error", "-1", "No se pudo Encontrar El Host");
                return respuesta;
            }
        } catch (Exception e) {
            e.getStackTrace();
            respuesta.setMetadata("Error", "-1", "Ocurrio un Error al intentar Eliminar el Host");
            return respuesta;
        }
    }

    @Override
    public ResponseHost eliminarHost(Long idHost) {
        ResponseHost respuesta = new ResponseHost();
        try {
            Optional<Host> hostOptional = hostDao.findById(idHost);
            if (hostOptional.isPresent()) {
                hostDao.delete(hostOptional.get());
                respuesta.setMetadata("Correcto", "00", "Se Elimino Correctamente el Host");
                return respuesta;
            } else {
                respuesta.setMetadata("Error", "-1", "No se pudo Encontrar El Host");
                return respuesta;
            }
        } catch (Exception e) {
            e.getStackTrace();
            respuesta.setMetadata("Error", "-1", "Ocurrio un Error al intentar Eliminar el Host");
            return respuesta;
        }
    }

    @Override
    public ResponseHost eliminarHost(String nombreHost) {
        ResponseHost respuesta = new ResponseHost();
        try {
            Host hostEncontrado = hostDao.findByNombreHost(nombreHost);
            if (hostEncontrado != null) {
                hostDao.delete(hostEncontrado);
                respuesta.setMetadata("Correcto", "00", "Se Elimino Correctamente el Host");
                return respuesta;
            } else {
                respuesta.setMetadata("Error", "-1", "No se pudo Encontrar El Host");
                return respuesta;
            }
        } catch (Exception e) {
            e.getStackTrace();
            respuesta.setMetadata("Error", "-1", "Ocurrio un Error al intentar Eliminar el Host");
            return respuesta;
        }
    }

    @Override
    public ResponseHost listarHosts() {
        ResponseHost respuesta = new ResponseHost();
        try {
            List<Host> hosts = hostDao.findAll();
            if (hosts != null) {
                respuesta.setHosts(hosts);
                respuesta.setMetadata("Correcto", "00", "Lista de Host conseguida");
                return respuesta;
            } else {
                respuesta.setMetadata("Error", "-1", "No se pudo Encontrar la lista de Host");
                return respuesta;
            }
        } catch (Exception e) {
            e.getStackTrace();
            respuesta.setMetadata("Error", "-1", "Ocurrio un Error al intentar obtener la lista de Host");
            return respuesta;
        }
    }

    @Override
    public ResponseHost buscarHost(String nombreHost) {
        ResponseHost respuesta = new ResponseHost();
        List<Host> hosts = new ArrayList();
        try {
            Host hostEncontrado = hostDao.findByNombreHost(nombreHost);
            hosts.add(hostEncontrado);
            if (hostEncontrado != null) {
                respuesta.setHosts(hosts);
                respuesta.setMetadata("Correcto", "00", "Se Elimino Correctamente el Host");
                return respuesta;
            } else {
                respuesta.setMetadata("Error", "-1", "No se pudo Encontrar El Host");
                return respuesta;
            }
        } catch (Exception e) {
            e.getStackTrace();
            respuesta.setMetadata("Error", "-1", "Ocurrio un Error al intentar Eliminar el Host");
            return respuesta;
        }
    }

    @Override
    public ResponseHost buscarHost(Long idHost) {
        ResponseHost respuesta = new ResponseHost();
        List<Host> hosts = new ArrayList();
        try {
            Optional<Host> hostOptional = hostDao.findById(idHost);

            if (hostOptional.isPresent()) {
                hosts.add(hostOptional.get());
                respuesta.setHosts(hosts);
                respuesta.setMetadata("Correcto", "00", "Se Elimino Correctamente el Host");
                return respuesta;
            } else {
                respuesta.setMetadata("Error", "-1", "No se pudo Encontrar El Host");
                return respuesta;
            }
        } catch (Exception e) {
            e.getStackTrace();
            respuesta.setMetadata("Error", "-1", "Ocurrio un Error al intentar Eliminar el Host");
            return respuesta;
        }
    }

    @Override
    public ResponseHost buscarHostPorIp(String ipHost) {
        ResponseHost respuesta = new ResponseHost();
        List<Host> hosts = new ArrayList();
        try {
            Host hostEncontrado = hostDao.findByIpHost(ipHost);
            hosts.add(hostEncontrado);
            if (hostEncontrado != null) {
                respuesta.setHosts(hosts);
                respuesta.setMetadata("Correcto", "00", "Se Elimino Correctamente el Host");
                return respuesta;
            } else {
                respuesta.setMetadata("Error", "-1", "No se pudo Encontrar El Host");
                return respuesta;
            }
        } catch (Exception e) {
            e.getStackTrace();
            respuesta.setMetadata("Error", "-1", "Ocurrio un Error al intentar Eliminar el Host");
            return respuesta;
        }
    }

    @Override
    public String ping(String ipHost) {
        ResponseHost respuesta = new ResponseHost();
        ObjectMapper mapper = new ObjectMapper();
        List<Host> hosts = new ArrayList();
        boolean reachable;
        try {
            boolean registrarLog = true;
            try {
                InetAddress address = InetAddress.getByName(ipHost);
                reachable = address.isReachable(10000); // Ping con un timeout de 10 segundos
            } catch (IOException e) {
                reachable = false;
            }

            // Obtén el Host correspondiente a la IP
            Host host = buscarHostPorIp(ipHost).getHosts().get(0);
            hosts.add(host);

            if (null != host) {

                try {
                    Log latestLog = logService.buscarUltimoLogPorHost(host).getLogs().get(0);
                    if (latestLog.getFin() == null) {
                        registrarLog = false;
                    }
                } catch (Exception e) {
                    if (reachable) {
                        registrarLog = true;
                    }
                }

                if (reachable) {
                    if (registrarLog) {
                        Log log = new Log();
                        log.setHost(host);

                        Instant instant = Instant.now();
                        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
                        localDateTime = localDateTime.withSecond(0).withNano(0); // Elimina segundos y milisegundos

                        Timestamp timestamp = Timestamp.valueOf(localDateTime);
                        log.setInicio(timestamp);
                        logService.registrarLog(log);
                        respuesta.setHosts(hosts);
                        respuesta.setMetadata("Dispositivo alcanzable", "00", host.getNombreHost());
                        String jsonResponse = mapper.writeValueAsString(respuesta);
                        return jsonResponse;
                    } else {
                        respuesta.setHosts(hosts);
                        respuesta.setMetadata("Dispositivo alcanzable", "00", host.getNombreHost());
                        String jsonResponse = mapper.writeValueAsString(respuesta);
                        return jsonResponse;
                    }

                } else {
                    // Dispositivo no alcanzable, actualiza el log existente con la fecha de fin
                    if (!registrarLog) {
                        Log latestLog = logService.buscarUltimoLogPorHost(host).getLogs().get(0);
                        if (latestLog != null && latestLog.getFin() == null) {
                            Instant instant = Instant.now();
                            LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
                            localDateTime = localDateTime.withSecond(0).withNano(0); // Elimina segundos y milisegundos

                            Timestamp timestamp = Timestamp.valueOf(localDateTime);
                            latestLog.setFin(timestamp);

                            long diferenciaEnMilisegundos = latestLog.getFin().getTime() - latestLog.getInicio().getTime();
                            double diferenciaEnHoras = diferenciaEnMilisegundos / 3600000.0;
                            DecimalFormat decimalFormat = new DecimalFormat("#.#");
                            latestLog.setDiferenciaEnHoras(Double.parseDouble(decimalFormat.format(diferenciaEnHoras).replace(",", ".")));
                            logService.establecerFin(latestLog);
                        }
                    }
                    respuesta.setHosts(hosts);
                    respuesta.setMetadata("Dispositivo no alcanzable", "-1", host.getNombreHost());
                    String jsonResponse = mapper.writeValueAsString(respuesta);
                    return jsonResponse;
                }
            } else {
                respuesta.setHosts(hosts);
                respuesta.setMetadata("Host no encontrado en la base de datos.", "-1", "null");
                String jsonResponse = mapper.writeValueAsString(respuesta);
                return jsonResponse;
            }
        } catch (IOException e) {
            return "Error al realizar el ping: ";
        }
    }

    public double tiempoEncendido(String ipHost) {
        Host host = buscarHostPorIp(ipHost).getHosts().get(0);
        double totalEncendido = 0.0;
        for (Log l : host.getLogs()) {
            if (l.getDiferenciaEnHoras() != null) {
                totalEncendido += l.getDiferenciaEnHoras();
            }
        }
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        return Double.parseDouble(decimalFormat.format(totalEncendido).replace(",", "."));

    }

    public double tiempoEncendidoMesActual(String ipHost) {
        Host host = buscarHostPorIp(ipHost).getHosts().get(0);
        double totalEncendido = 0.0;
        LocalDateTime fechaActual = LocalDateTime.now();

        for (Log l : host.getLogs()) {
            if (l.getDiferenciaEnHoras() != null) {
                Date date = l.getInicio();
                LocalDateTime logDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

                if (logDate.getYear() == fechaActual.getYear() && logDate.getMonth() == fechaActual.getMonth()) {
                    totalEncendido += l.getDiferenciaEnHoras();
                }
            }
        }
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        return Double.parseDouble(decimalFormat.format(totalEncendido).replace(",", "."));
    }
}
