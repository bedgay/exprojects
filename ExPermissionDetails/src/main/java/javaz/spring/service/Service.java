package javaz.spring.service;

import java.util.Collection;
import java.util.List;

import javaz.data.dto.DTO;
import javaz.hibernate.entity.SuperEntity;

/**
 * @author SUCCESS\tungo
 * @date Oct 2, 2014 5:01:18 PM
 */
public interface Service {
	
	public List<? extends DTO> toDTOs(Collection<? extends SuperEntity> cols);

}
