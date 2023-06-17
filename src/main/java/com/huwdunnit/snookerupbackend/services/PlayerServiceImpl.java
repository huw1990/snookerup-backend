package com.huwdunnit.snookerupbackend.services;

import com.huwdunnit.snookerupbackend.model.Player;
import com.huwdunnit.snookerupbackend.repositories.PlayerRepository;
import com.huwdunnit.snookerupbackend.web.mappers.PlayerMapper;
import com.huwdunnit.snookerupbackend.web.model.PlayerDto;
import com.huwdunnit.snookerupbackend.web.model.PlayerDtoList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of the player service.
 *
 * @author Huw
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class PlayerServiceImpl implements PlayerService {

    /** Repository for DB operations related to players. */
    private final PlayerRepository playerRepository;

    /** Mapper for going between DB entities and DTOs. */
    private final PlayerMapper playerMapper;

    @Override
    public PlayerDtoList listPlayers() {
        log.debug("listPlayers");
        List<Player> players = playerRepository.findAll();
        log.debug("Number of players={}", players.size());
        List <PlayerDto> playerDtos = players.stream()
                .map(playerMapper::playerToPlayerDto)
                .collect(Collectors.toList());
        return new PlayerDtoList(playerDtos);
    }

    @Override
    public PlayerDto findPlayerById(Long playerId) {
        log.debug("findPlayerById, playerId={}", playerId);
        Player player = playerRepository.findById(playerId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Player Not Found, ID: " + playerId));
        log.debug("Found player={}", player);
        return playerMapper.playerToPlayerDto(player);
    }

    @Override
    public PlayerDto savePlayer(PlayerDto playerDto) {
        log.debug("savePlayer, playerDto={}", playerDto);
        // Convert to a Player to insert in the DB, then back to a DTO to return from the service
        Player savedPlayer = playerRepository.save(playerMapper.playerDtoToPlayer(playerDto));
        log.debug("Player saved with ID={}", savedPlayer.getId());
        return playerMapper.playerToPlayerDto(savedPlayer);
    }

    @Override
    public void updatePlayer(Long playerId, PlayerDto playerDto) {
        log.debug("updatePlayer, playerId={} playerDto={}", playerId, playerDto);
        if (playerId != playerDto.getId()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Player IDs not matching");
        }
        Optional<Player> playerOptional = playerRepository.findById(playerId);

        playerOptional.ifPresentOrElse(player -> {
            player.setEmail(playerDto.getEmail());
            player.setFirstName(playerDto.getFirstName());
            player.setLastName(playerDto.getLastName());
            playerRepository.save(player);
        }, () -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Player Not Found. ID: " + playerId);
        });
    }
}
