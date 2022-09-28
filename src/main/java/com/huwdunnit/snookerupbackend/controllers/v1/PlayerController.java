package com.huwdunnit.snookerupbackend.controllers.v1;

import com.huwdunnit.snookerupbackend.controllers.exceptions.ResourceNotFoundException;
import com.huwdunnit.snookerupbackend.model.Player;
import com.huwdunnit.snookerupbackend.model.PlayerList;
import com.huwdunnit.snookerupbackend.repositories.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for API requests related to players.
 *
 * @author Huw
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/players")
public class PlayerController {

    private final PlayerRepository playerRepository;

    @GetMapping
    public PlayerList getAllPlayers(){
        return new PlayerList(playerRepository.findAll());
    }

    @GetMapping({"/{id}"})
    public Player getPlayerById(@PathVariable Long id){
        return playerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Player createNewPlayer(@RequestBody Player player){
        return playerRepository.save(player);
    }
}
