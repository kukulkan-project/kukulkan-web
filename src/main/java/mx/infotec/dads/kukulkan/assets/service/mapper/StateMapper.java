package mx.infotec.dads.kukulkan.assets.service.mapper;

import java.util.ArrayList;
import java.util.List;

import mx.infotec.dads.kukulkan.assets.domain.State;
import mx.infotec.dads.kukulkan.assets.service.dto.StateDTO;

/**
 * Mapper for the entity State and its DTO StateDTO.
 */
public class StateMapper {

    private StateMapper() {

    }

    public static State fromId(String id) {
        if (id == null) {
            return null;
        }
        State state = new State();
        state.setId(id);
        return state;
    }

    public static State toEntity(StateDTO dto) {
        State to = new State();
        to.setBriefDescription(dto.getBriefDescription());
        to.setDescription(dto.getDescription());
        to.setName(dto.getName());
        to.setOrder(dto.getOrder());
        return to;
    }

    public static StateDTO toDto(State entity) {
        StateDTO to = new StateDTO();
        to.setId(entity.getId());
        to.setBriefDescription(entity.getBriefDescription());
        to.setDescription(entity.getDescription());
        to.setName(entity.getName());
        to.setOrder(entity.getOrder());
        return to;
    }

    public static List<State> toEntity(List<StateDTO> dtoList) {
        List<State> toList = new ArrayList<>();
        dtoList.forEach(dto -> toList.add(toEntity(dto)));
        return toList;
    }

    public static List<StateDTO> toDto(List<State> entityList) {
        List<StateDTO> toList = new ArrayList<>();
        entityList.forEach(entity -> toList.add(toDto(entity)));
        return toList;
    }
}
