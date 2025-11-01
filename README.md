## A partir de este Juego construya una aplicación interactiva usando WebSockets que permita:

1. Crear una sala

Primero se crea el modelo de la sala que se va a guardar en alguna base de datos

```java
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

    public Room(String id) {
        this.id = id;
        this.players = new ArrayList<>();
        this.gameHistory = new ArrayList<>();
    }
    // ...
    // getters and setters 
}
```

Para persistir las salas se crea el repositorio, en este caso usando MongoDB porque no se debe persistir mucha información.

```java
package com.tictactoe.core.Repo.RoomsRepository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tictactoe.core.model.Room;

public interface RoomRepository extends MongoRepository<Room, String> {
    
}
```


