package com.tictactoe.core.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.tictactoe.core.Repo.RoomsRepository.RoomRepository;
import com.tictactoe.core.model.Room;
import com.tictactoe.core.model.DTO.MoveRequest;

@Controller
public class GameController {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private SimpMessagingTemplate messaging;

    @MessageMapping("/createRoom")
    @SendTo("/topic/rooms")
    public Room createRoom(String roomId) {
        Room room = new Room();
        room.setId(roomId);
        room.setBoard(new ArrayList<>(List.of(
            new ArrayList<>(List.of(null, null, null)),
            new ArrayList<>(List.of(null, null, null)),
            new ArrayList<>(List.of(null, null, null))
        )));
        roomRepository.save(room);
        return room;
    }

    @MessageMapping("/joinRoom")
    @SendTo("/topic/rooms")
    public Room joinRoom(String roomId, String playerId) {
        Room room = roomRepository.findById(roomId).get();
        if (room != null && room.getPlayers().size() < 2) {
            room.getPlayers().add(playerId);
            roomRepository.save(room);
        }
        return room;
    }

    @MessageMapping("/makeMove")
    public void makeMove(@Payload MoveRequest req) {
        if (req == null || req.roomId() == null) return;
        if (req.row() < 0 || req.row() > 2 || req.col() < 0 || req.col() > 2) return;
        if (req.player() == null || req.player().isBlank()) return;

        messaging.convertAndSend("/topic/game/" + req.roomId(), req);
    }

}