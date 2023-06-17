package com.huwdunnit.snookerupbackend.web.controllers.v1;

import com.huwdunnit.snookerupbackend.services.PlayerService;
import com.huwdunnit.snookerupbackend.web.model.PlayerDto;
import com.huwdunnit.snookerupbackend.web.model.PlayerDtoList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.huwdunnit.snookerupbackend.web.controllers.v1.PlayerController.URL_PATH;

/**
 * REST controller for API requests related to players.
 *
 * @author Huw
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(URL_PATH)
public class PlayerController {

    static final String URL_PATH = "/api/v1/players";

    private final PlayerService playerService;

    @GetMapping
    public ResponseEntity<PlayerDtoList> getAllPlayers(){
        PlayerDtoList playerList = playerService.listPlayers();
        return new ResponseEntity<>(playerList, HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<PlayerDto> getPlayerById(@PathVariable Long id){
        PlayerDto playerDto = playerService.findPlayerById(id);
        return new ResponseEntity<>(playerDto, HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PlayerDto> createNewPlayer(@RequestBody PlayerDto playerDto){
        PlayerDto savedDto = playerService.savePlayer(playerDto);

        // Set the location of the newly created user
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", URL_PATH + savedDto.getId());

        return new ResponseEntity<>(savedDto, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping(path = {"/{id}"})
    public ResponseEntity updatePlayer(@PathVariable Long id, @RequestBody PlayerDto playerDto){
        playerService.updatePlayer(id, playerDto);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
