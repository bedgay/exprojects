package jp.co.mti.mixjuke.service.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.mti.mixjuke.dao.GenreDao;
import jp.co.mti.mixjuke.dom.Genre;
import jp.co.mti.mixjuke.service.AbstractService;
import jp.co.mti.mixjuke.service.GenreService;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Xuan Nguyen
 * 
 */
@Service
@Transactional
public class GenreServiceImpl extends AbstractService<Genre> implements
        GenreService {

    @Autowired
    private GenreDao generDao;

    protected GenreServiceImpl() {
        super(Genre.class);
    }

    @Override
    public List<Genre> getGenreListByGIDs(List<String> gids) {
        if (CollectionUtils.isEmpty(gids)) {
            return new ArrayList<Genre>();
        }
        Criterion criterion = Restrictions.in("id", gids);
        return generDao.findByCriteria(criterion);
    }

    @Override
    public List<Genre> findByCriteria(Criterion criterion) {
        return findDao().findByCriteria(criterion);
    }

    @Override
    public List<Genre> getList() {
        return super.getList();
    }

    @Override
    public void saveOrUpdate(Genre e) {
        generDao.saveOrUpdate(e);
    }

    @Override
    public Genre findGenreByIdWFetchArtist(String gid) {
        Criteria criteria = generDao.createCriteria();
        criteria.add(Restrictions.eq("id", gid));
        criteria.createAlias("artists", "artists", JoinType.LEFT_OUTER_JOIN);
        if (generDao.findByCriteria(criteria).isEmpty()) {
            return null;
        }
        return generDao.findByCriteria(criteria).get(0);

    }
}
