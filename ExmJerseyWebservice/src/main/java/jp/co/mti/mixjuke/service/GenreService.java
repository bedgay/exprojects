package jp.co.mti.mixjuke.service;

import java.util.List;

import jp.co.mti.mixjuke.dom.Genre;

public interface GenreService extends MixjukeService<Genre> {

    /**
     * Get genre info list base on GIDs
     * 
     * @param gids
     *            : genre id list
     * @return Genre information list
     */
    List<Genre> getGenreListByGIDs(List<String> gids);

    /**
     * Get Genre by Id and fetch Artist.
     * 
     * @param gid
     *            genre ID.
     * @return Genre object.
     */
    Genre findGenreByIdWFetchArtist(String gid);
}
