package com.tictactoe.core.model;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private String id;
    private List<String> players;
    private List<String> gameHistory;

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
}
