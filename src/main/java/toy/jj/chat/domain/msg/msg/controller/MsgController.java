package toy.jj.chat.domain.msg.msg.controller;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RestController;
import toy.jj.chat.domain.msg.msg.dto.MsgDto;
import toy.jj.chat.domain.msg.msg.service.MsgService;

import java.time.LocalDateTime;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MsgController {
    private final MsgService msgService;

    @MessageMapping("/chats/messages/{room-id}")
    public void message(@DestinationVariable("room-id") Long roomId, MsgDto msgDto) {


    }
}