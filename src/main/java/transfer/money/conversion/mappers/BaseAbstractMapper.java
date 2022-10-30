package transfer.money.conversion.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

public abstract class BaseAbstractMapper<ENT, DTO> {

	public abstract ENT convertToEntity(DTO dto);

	public abstract DTO convertToDto(ENT entity);

	public List<ENT> convertToEntityList(List<DTO> dtos) {
		if (CollectionUtils.isNotEmpty(dtos)) {
			return dtos.stream().map(dto -> convertToEntity(dto)).collect(Collectors.toList());
		}
		return new ArrayList<ENT>();
	}

	public List<DTO> convertToDtoList(List<ENT> entities) {
		if (CollectionUtils.isNotEmpty(entities)) {
			return entities.stream().map(entity -> convertToDto(entity)).collect(Collectors.toList());
		}
		return new ArrayList<DTO>();
	}
}
