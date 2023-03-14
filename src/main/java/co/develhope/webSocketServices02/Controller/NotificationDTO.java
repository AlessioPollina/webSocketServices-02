package co.develhope.webSocketServices02.Controller;

import co.develhope.webSocketServices02.Entities.ClientMessageDTO;
import co.develhope.webSocketServices02.Entities.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationDTO {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping("/broadcast") //Server to Client(page Google Web Socket)
    @SendTo("/broadcast/message")
    public void handleMessageFromWebSocket(@RequestBody MessageDTO message) {
        simpMessagingTemplate.convertAndSend("/topic/broadcast", message);
    }

    @MessageMapping("/client-message") //app/hello-Client to Server
    @SendTo("/topic/broadcast")
    public MessageDTO handleMessageFromWebSocket(ClientMessageDTO message){
        System.out.println("Arrived something on /app/client - " + message.toString());
        return new MessageDTO(message.getClientName(), message.getClientAlert(), message.getClientMsg());
    }
}
