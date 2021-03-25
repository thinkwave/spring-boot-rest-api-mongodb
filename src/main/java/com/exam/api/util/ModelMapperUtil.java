package com.exam.api.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class ModelMapperUtil {
    
    private static ModelMapper modelMapper = new ModelMapper();

	public static ModelMapper get() {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}
    
}
