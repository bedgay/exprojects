/**
 * 
 */
package jp.co.mti.mixjuke.service.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.mti.mixjuke.dao.GroupArtistDao;
import jp.co.mti.mixjuke.dom.GroupArtist;
import jp.co.mti.mixjuke.dom.Performance;
import jp.co.mti.mixjuke.service.AbstractService;
import jp.co.mti.mixjuke.service.GroupArtistService;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ntnxuan
 * 
 */
@Transactional
@Service
public class GroupArtistServiceImpl extends AbstractService<GroupArtist>
        implements GroupArtistService {

    @Autowired
    private GroupArtistDao groupArtistDao;

    protected GroupArtistServiceImpl() {
        super(GroupArtist.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<String> getGroupIdsByArtistIds(List<String> aid) {
        if (CollectionUtils.isEmpty(aid)) {
            return new ArrayList<String>();
        }
        Criteria criteria = groupArtistDao.createCriteria();
        criteria.setProjection(Projections.distinct(Projections.property("group.id")));
        criteria.createAlias("artist", "artist", JoinType.LEFT_OUTER_JOIN);
        criteria.add(Restrictions.in("artist.id", aid));
        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<GroupArtist> getArtistIdsByGroupIds(List<String> groupIds) {
        if (CollectionUtils.isEmpty(groupIds)) {
            return new ArrayList<GroupArtist>();
        }
        Criteria criteria = groupArtistDao.createCriteria("g");
        criteria.createAlias("artist", "a", JoinType.LEFT_OUTER_JOIN);
        // using exist clause to check artist valid
        DetachedCriteria sub = DetachedCriteria
                .forClass(Performance.class, "p");
        sub.add(Restrictions.eqProperty("p.artist.id", "g.artist.id"));
        sub.setProjection(Projections.property("p.id"));
        criteria.add(Subqueries.exists(sub));
        
        criteria.createAlias("a.favorites", "f", JoinType.LEFT_OUTER_JOIN);
        criteria.add(Restrictions.in("group.id", groupIds));
        criteria.addOrder(Order.asc("group.id"));
        // Distinct duplicate entity.
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }
}
