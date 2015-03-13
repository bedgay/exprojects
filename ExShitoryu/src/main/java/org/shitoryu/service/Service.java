package org.shitoryu.service;

import java.util.Collection;
import java.util.List;

import org.shitoryu.data.dto.DTO;
import org.shitoryu.data.entity.AbstractEntity;

/**
 * @author SUCCESS\tungo
 * @date Oct 21, 2014 9:43:46 AM
 */
public interface Service {
	
	/**
	 * @param cols
	 * @return
	 */
	List<? extends DTO> toDTOs(Collection<? extends AbstractEntity> cols);

}
