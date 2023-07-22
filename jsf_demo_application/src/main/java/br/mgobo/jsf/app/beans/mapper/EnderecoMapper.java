package br.mgobo.jsf.app.beans.mapper;

import br.mgobo.jsf.app.beans.dto.PessoaDto;
import br.mgobo.jsf.app.domain.Endereco;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface EnderecoMapper {

    @Mappings(value = {
            @Mapping(source = "enderecoDto.id", target = "id"),
            @Mapping(source = "enderecoDto.logradouro", target = "logradouro"),
            @Mapping(source = "enderecoDto.nro", target = "nro"),
            @Mapping(source = "enderecoDto.estado", target = "estado"),
            @Mapping(source = "enderecoDto.cidade", target = "cidade"),
            @Mapping(source = "enderecoDto.cep", target = "cep")
    })
    Endereco toEntity(PessoaDto pessoaDto);

    @Mappings(value = {
            @Mapping(target = "enderecoDto.id", source = "id"),
            @Mapping(target = "enderecoDto.logradouro", source = "logradouro"),
            @Mapping(target = "enderecoDto.nro", source = "nro"),
            @Mapping(target = "enderecoDto.estado", source = "estado"),
            @Mapping(target = "enderecoDto.cidade", source = "cidade"),
            @Mapping(target = "enderecoDto.cep", source = "cep")
    })
    PessoaDto toDto(Endereco endereco);
}
