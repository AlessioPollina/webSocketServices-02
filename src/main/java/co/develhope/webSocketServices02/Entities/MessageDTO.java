package co.develhope.webSocketServices02.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {

    private String sender;
    private String type;
    private String message;
}
