package org.oddys.timetracking.util;

import org.modelmapper.ModelMapper;

public class ModelMapperWrapper {
    private static final ModelMapper MAPPER = new ModelMapper();

    private ModelMapperWrapper() {}

    public static ModelMapper getMapper() {
        return MAPPER;
    }
}
