package com.softdevelop.biomedplus.service.impl;

import com.softdevelop.biomedplus.exception.GenericException;
import com.softdevelop.biomedplus.exception.NotFoundException;
import com.softdevelop.biomedplus.model.dto.EquipmentDto;
import com.softdevelop.biomedplus.model.dto.RolDto;
import com.softdevelop.biomedplus.model.dto.UserDto;
import com.softdevelop.biomedplus.model.entity.RolEntity;
import com.softdevelop.biomedplus.model.entity.UserEntity;
import com.softdevelop.biomedplus.repository.RolRepository;
import com.softdevelop.biomedplus.repository.UserRepository;
import com.softdevelop.biomedplus.service.RolService;
import com.softdevelop.biomedplus.service.UserService;
import com.softdevelop.biomedplus.service.translator.UserTranslator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<RolDto> getRoles() {
        List<RolDto> roles = new ArrayList<>();
        try {
            List<RolEntity> allRoles = rolRepository.findAll();
            if (allRoles == null || allRoles.isEmpty()) {
                throw new NotFoundException("Roles not found");
            }
            roles = modelMapper.map(allRoles, new TypeToken<List<RolDto>>() {
            }.getType());
        } catch (RuntimeException e) {
            throw new GenericException(e.getMessage());
        }
        return roles;
    }
}
