package br.mgobo.jsf.app.beans.mapper;

import br.mgobo.jsf.app.beans.dto.PessoaDto;
import br.mgobo.jsf.app.domain.Pessoa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(uses = {DateMapper.class})
public interface PessoaMapper {

    @Mappings(value = {
            @Mapping(target = "idade", source = "idade", dateFormat = "dd/MM/yyyy", conditionExpression = "java(pessoaDto.getIdade() != null && !pessoaDto.getIdade().equals(\"\"))"),
    })
    Pessoa toEntity(PessoaDto pessoaDto);
    PessoaDto toDto(Pessoa pessoa);
}
