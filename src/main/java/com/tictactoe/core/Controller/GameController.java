package com.tictactoe.core.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.tictactoe.core.Repo.RoomsRepository.RoomRepository;
import com.tictactoe.core.model.Room;

@Controller
public class GameController {

    @Autowired
    private RoomRepository roomRepository;

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
    @SendTo("/topic/game")
    public Room makeMove(String roomId, int row, int col, String player) {
        Room room = roomRepository.findById(roomId).get();
        if (room != null) {
            List<List<String>> board = room.getBoard();
            board.get(row).set(col, player);
            room.getHistory().add(new ArrayList<>(board));
            roomRepository.save(room);
        }
        return room;
    }
}