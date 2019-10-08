package org.oddys.timetracking.service;

import org.modelmapper.ModelMapper;
import org.oddys.timetracking.dao.DaoFactoryProvider;
import org.oddys.timetracking.dao.RoleDao;
import org.oddys.timetracking.dto.RoleDto;
import org.oddys.timetracking.util.ConfigManager;
import org.oddys.timetracking.util.ModelMapperWrapper;

import java.util.List;
import java.util.stream.Collectors;

public class RoleServiceImpl implements RoleService {
    private static final RoleService INSTANCE = new RoleServiceImpl();
    private RoleDao roleDao;
    private ModelMapper modelMapper = ModelMapperWrapper.getMapper();

    private RoleServiceImpl() {
        String dbmsName = ConfigManager.getInstance().getProperty(ConfigManager.DBMS);
        roleDao = DaoFactoryProvider.getInstance().getFactory(dbmsName).getRoleDao();
    }

    public static RoleService getInstance() {
        return INSTANCE;
    }

    @Override
    public List<RoleDto> findAllRoles() {
        return roleDao.findAll().stream()
                .map(r -> modelMapper.map(r, RoleDto.class))
                .collect(Collectors.toList());
    }
}
