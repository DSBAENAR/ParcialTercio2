package com.tictactoe.core.model.DTO;

public record MoveRequest(String roomId, int row, int col, String player) {}
