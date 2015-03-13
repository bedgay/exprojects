package javaz.spring.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javaz.data.dto.DTO;
import javaz.hibernate.entity.SuperEntity;

/**
 * @author SUCCESS\tungo
 * @date Oct 2, 2014 5:06:16 PM
 */
public class ServiceImpl {

	public List<? extends DTO> toDTOs(Collection<? extends SuperEntity> cols) {
		List<DTO> list = new ArrayList<>();
		
		for (SuperEntity e : cols) {
			list.add(e.toDTO());
		}
		
		return list;
	}

}
