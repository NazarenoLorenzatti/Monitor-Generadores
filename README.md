# Monitoreo de Grupos Electrogenos
Es una aplicacion enladata para el monitoreo de grupos electrogenos en cada Shelter de la red FTTH.

Cada Grupo electrogeno y banco de bateria esta conectado a una ONU, la cual se enciende al encender el grupo. A cada una de estas ONUs se le realiza un Ping y se grafica la respuesta en graficos de linea.
Cuando el grupo esta encendido el Ping es correcto por lo que se da aviso mediante diferentes alertas que el grupo esta encendido y se registra un Log de actividad. De esta forma queda registrado cuanto tiempo de uso tuvo el grupo electrogeno y cuando fue la ultima vez que se encendio. 
