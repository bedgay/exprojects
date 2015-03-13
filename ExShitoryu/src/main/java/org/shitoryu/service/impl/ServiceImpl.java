package org.shitoryu.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.shitoryu.data.dto.DTO;
import org.shitoryu.data.entity.AbstractEntity;
import org.shitoryu.service.Service;

/**
 * @author SUCCESS\tungo
 * @date Oct 21, 2014 9:45:45 AM
 */
public class ServiceImpl implements Service {
	
	@Override
	public List<? extends DTO> toDTOs(Collection<? extends AbstractEntity> cols) {
		List<DTO> list = new ArrayList<>();
		
		for (AbstractEntity e : cols) {
			list.add(e.toDTO());
		}
		
		return list;
	}

}
