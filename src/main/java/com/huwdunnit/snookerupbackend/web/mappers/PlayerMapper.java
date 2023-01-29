package com.huwdunnit.snookerupbackend.web.mappers;

import com.huwdunnit.snookerupbackend.model.Player;
import com.huwdunnit.snookerupbackend.web.model.PlayerDto;
import org.mapstruct.Mapper;

/**
 * Mapstruct mapper for moving between Player DB entities and DTOs.
 *
 * @author Huw
 */
@Mapper
public interface PlayerMapper {

    PlayerDto playerToPlayerDto(Player player);

    Player playerDtoToPlayer(PlayerDto playerDto);
}
