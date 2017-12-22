package mx.infotec.dads.kukulkan.assets.service.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import mx.infotec.dads.kukulkan.assets.domain.Asset;
import mx.infotec.dads.kukulkan.assets.domain.Discipline;
import mx.infotec.dads.kukulkan.assets.domain.File;
import mx.infotec.dads.kukulkan.assets.domain.Granularity;
import mx.infotec.dads.kukulkan.assets.domain.LevelOfImplementation;
import mx.infotec.dads.kukulkan.assets.domain.Phase;
import mx.infotec.dads.kukulkan.assets.domain.ProblemDomain;
import mx.infotec.dads.kukulkan.assets.domain.State;
import mx.infotec.dads.kukulkan.assets.service.dto.AssetDTO;

/**
 * Mapper for the entity Asset and its DTO AssetDTO.
 */
public class AssetMapper {
    
    private AssetMapper(){
        
    }

    public static Asset toEntity(AssetDTO dto) {
        Asset to = new Asset();
        to.setAuthor(dto.getAuthor());
        to.setComments(dto.getComments());
        Discipline discipline = new Discipline();
        discipline.setId(dto.getDisciplineId());
        to.setDiscipline(discipline);
        Granularity granularity = new Granularity();
        granularity.setId(dto.getGranularityId());
        to.setGranularity(granularity);
        LevelOfImplementation levelOfImplementation = new LevelOfImplementation();
        levelOfImplementation.setId(dto.getImplementationLevelId());
        to.setImplementationLevel(levelOfImplementation);
        to.setLocation(dto.getLocation());
        to.setName(dto.getName());
        Phase phase = new Phase();
        phase.setId(dto.getPhaseId());
        to.setPhase(phase);
        to.setProblemDescription(dto.getProblemDescription());
        ProblemDomain problemDomain = new ProblemDomain();
        problemDomain.setId(dto.getProblemDomainId());
        to.setProblemDomain(problemDomain);
        State state = new State();
        state.setId(dto.getStateId());
        to.setState(state);
        to.setUseInstructions(dto.getUseInstructions());
        to.setVersion(dto.getVersion());
        File file = new File();
        file.setFile(dto.getFile());
        file.setFileContentType(dto.getFileContentType());
        to.setFile(file);
        return to;
    }

    public static Asset fromId(String id) {
        if (id == null) {
            return null;
        }
        Asset asset = new Asset();
        asset.setId(id);
        return asset;
    }

    public static AssetDTO toDto(Asset entity) {
        AssetDTO to = new AssetDTO();
        to.setId(entity.getId());
        to.setAuthor(entity.getAuthor());
        to.setComments(entity.getComments());
        to.setDisciplineId(entity.getDiscipline().getId());
        to.setGranularityId(entity.getGranularity().getId());
        to.setImplementationLevelId(entity.getImplementationLevel().getId());
        to.setLocation(entity.getLocation());
        to.setName(entity.getName());
        to.setPhaseId(entity.getPhase().getId());
        to.setProblemDescription(entity.getProblemDescription());
        to.setProblemDomainId(entity.getProblemDomain().getId());
        to.setStateId(entity.getState().getId());
        to.setUseInstructions(entity.getUseInstructions());
        to.setVersion(entity.getVersion());
        Optional.of(entity.getFile()).ifPresent(file -> {
            to.setFile(file.getFile());
            to.setFileContentType(file.getFileContentType());
        });
        return to;
    }

    public static List<Asset> toEntity(List<AssetDTO> dtoList) {
        List<Asset> toList = new ArrayList<>();
        dtoList.forEach(dto -> toList.add(toEntity(dto)));
        return toList;
    }

    public static List<AssetDTO> toDto(List<Asset> entityList) {
        List<AssetDTO> toList = new ArrayList<>();
        entityList.forEach(entity -> toList.add(toDto(entity)));
        return toList;
    }

}
