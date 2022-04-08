package com.eurodyn.mappers;

import com.eurodyn.dtos.AccountDTO;
import com.eurodyn.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountDTO entityToDto(Account entity);

    Account dtoToEntity(AccountDTO dto);

    void dtoToEntity(AccountDTO dto, @MappingTarget Account entity);

    List<AccountDTO> entityListToDTOList(List<Account> entitylist);
}
