package build.config;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class PingWebSocketHandler extends TextWebSocketHandler {
    
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Aquí puedes realizar el ping a las direcciones IP deseadas
        // Calcula el tiempo de respuesta y envía los resultados al cliente
        // Puedes usar el método session.sendMessage() para enviar datos al cliente
    }
}
