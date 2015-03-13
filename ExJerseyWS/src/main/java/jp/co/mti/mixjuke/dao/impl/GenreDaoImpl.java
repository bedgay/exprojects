package jp.co.mti.mixjuke.dao.impl;

import jp.co.mti.mixjuke.dao.AbstractDao;
import jp.co.mti.mixjuke.dao.GenreDao;
import jp.co.mti.mixjuke.dom.Genre;

import org.springframework.stereotype.Repository;

@Repository("genreDao")
public class GenreDaoImpl extends AbstractDao<Genre> implements GenreDao {

    protected GenreDaoImpl() {
        super(Genre.class);
    }
}
