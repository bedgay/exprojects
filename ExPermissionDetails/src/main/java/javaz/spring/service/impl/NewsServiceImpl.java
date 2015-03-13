package javaz.spring.service.impl;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import javaz.data.dto.NewsDTO;
import javaz.hibernate.dao.NewsDAO;
import javaz.hibernate.dao.RoleDAO;
import javaz.hibernate.dao.RoleDetailDAO;
import javaz.hibernate.entity.News;
import javaz.hibernate.entity.Role;
import javaz.hibernate.entity.RoleDetail;
import javaz.spring.aop.AuthoritySubject;
import javaz.spring.service.NewsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author SUCCESS\tungo
 * @date Oct 16, 2014 9:45:35 AM
 */
@Service
public class NewsServiceImpl extends ServiceImpl implements NewsService {

	@Autowired
	private NewsDAO newsDAO;

	@Autowired
	private RoleDAO roleDAO;
	
	@Autowired
	private RoleDetailDAO roleDetailDAO;

	@Override
	public void init() {
		List<News> list = newsDAO.findAll();
		if (CollectionUtils.isEmpty(list)) {
			
			Role role = roleDAO.findByName("News").get(0);
			
			try {
				String url = "http://feeds.bbci.co.uk/news/technology/rss.xml";
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(new URL(url).openStream());
				doc.getDocumentElement().normalize();
				NodeList items = doc.getElementsByTagName("item");
				for (int i = 0; i < items.getLength(); i++) {
					Element e = (Element) items.item(i);
					String title = e.getElementsByTagName("title").item(0).getNodeValue();
					String description = e.getElementsByTagName("description").item(0).getNodeValue();
					News news = new News(title, description);
					news = newsDAO.save(news);
					RoleDetail roleDetail = new RoleDetail(AuthoritySubject.NEWS.name(), news.getId(), role); 
					roleDetailDAO.save(roleDetail);
				}
			} catch (ParserConfigurationException | SAXException | IOException e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NewsDTO> findByIDs(List<Integer> ids) {
		List<News> list = newsDAO.findByIDs(ids);
		return (List<NewsDTO>) toDTOs(list);
	}
	
}
