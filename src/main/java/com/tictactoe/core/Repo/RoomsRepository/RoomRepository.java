package com.tictactoe.core.Repo.RoomsRepository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tictactoe.core.model.Room;

public interface RoomRepository extends MongoRepository<Room, String> {
    
}
