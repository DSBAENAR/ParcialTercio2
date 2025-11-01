package com.tictactoe.core.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Rooms")
public class Room {

    @Id
    private String id;
    private List<String> players;
    private List<String> gameHistory;
    private List<List<String>> board;

    public Room(){}
    public Room(String id) {
        this.id = id;
        this.players = new ArrayList<>();
        this.gameHistory = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public List<String> getPlayers() {
        return players;
    }

    public List<String> getGameHistory() {
        return gameHistory;
    }

    public boolean addPlayer(String player) {
        if (players.size() < 2) {
            players.add(player);
            return true;
        }
        return false;
    }

    public void addGameState(String state) {
        gameHistory.add(state);
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBoard(List<List<String>> board) {
        this.board = board;
    }

    public List<List<String>> getBoard() {
        return board;
    }

    public List<List<List<String>>> getHistory() {
        return null;
    }
}
