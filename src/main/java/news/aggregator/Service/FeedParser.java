package news.aggregator.Service;

import news.aggregator.Adapter.AdapterFactory;
import news.aggregator.Adapter.FeedAdapter;
import news.aggregator.Entity.*;
import news.aggregator.Repository.FeedCategoriesRepository;
import news.aggregator.Repository.FeedCategoryRepository;
import news.aggregator.Repository.FeedRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileWriter;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.*;

@Service
public class FeedParser {

    @Autowired
    private FeedRepository feedRepository;
    @Autowired
    private FeedCategoryRepository feedCategoryRepository;
    @Autowired
    private FeedCategoriesRepository feedCategoriesRepository;

    /**
     * Parser core logic
     * @param source
     * @param responseEntity
     */
    public void parse(Source source, ResponseEntity<String> responseEntity)
    {
        try{
            Document doc = loadXMLFromString(responseEntity.getBody());

            NodeList nl = doc.getElementsByTagName("*");
            Node n;

            AdapterFactory adapterFactory = new AdapterFactory();
            FeedAdapter adapter = adapterFactory.make(source.getName());
            adapter.setConfigurations(source.getSourceConfigurations());

            Iterable<FeedCategory> feedCategories = this.feedCategoryRepository.findAll();

            for (int i=0; i < nl.getLength(); i++)
            {
                n = nl.item(i);
                if(n.getNodeName().equals(adapter.getItem())){
                    NodeList itemChilds = n.getChildNodes();
                    Node field;
                    Feed feed = new Feed();

                    for(int j=0; j < itemChilds.getLength(); j++){

                        field = itemChilds.item(j);

                        Date date = new Date();
                        feed.setDateCreated(date);
                        if(field.getNodeName().equals(adapter.getTitle())){
                            feed.setTitle(this.getCharacterDataFromElement(field));
                        }
                        if(field.getNodeName().equals(adapter.getDescription())){
                            feed.setDescription(this.getCharacterDataFromElement(field));
                        }
                        if(field.getNodeName().equals(adapter.getLink())){
                            feed.setLink(field.getTextContent());
                        }
                        if(field.getNodeName().equals(adapter.getGuid())){
                            feed.setGuid(field.getTextContent());
                        }
                        if(field.getNodeName().equals(adapter.getPubDate())){
                            List<String> parsePatterns = adapter.getParsePatterns();
                            Date pubDate = adapter.convertDate(field.getTextContent(), parsePatterns);
                            feed.setDatePublished(pubDate);
                        }
                        if(field.getNodeName().equals(adapter.getMediaContent())){
                            feed.setMediaContent(field.getAttributes().getNamedItem(adapter.getUrl()).getNodeValue());
                        }

                        if(field.getNodeName().equals(adapter.getCategory())){
                            FeedCategory category = this.lookupForCategory(field.getTextContent(), feedCategories);

                            feed.addCategory(category);
                        }
                    }
                    if(feed.isValid()){
                        feed.setSource(source);
                        //save feed
                        try {
                            feedRepository.save(feed);
                            //save feed categories
                            try{
                                for(FeedCategory feedCategory: feed.getCategories()){
                                    FeedCategories feedCategoriesItem = new FeedCategories();
                                    feedCategoriesItem.setFeedId(feed.getId());
                                    feedCategoriesItem.setCategoryId(feedCategory.getId());
                                    feedCategoriesRepository.save(feedCategoriesItem);
                                }
                            }catch (Exception exception){
                                System.out.println("FeedCategory save exception: " + exception.getMessage());
                                feed = new Feed();
                                continue;
                            }
                            feed = new Feed();
                            continue;
                        }
                        catch (Exception exception){
                            System.out.println("Feed save exception: " + exception.getMessage());
                            feed = new Feed();
                            continue;
                        }
                    }
                }
            }
        }catch (Exception e){
            System.out.println(e.toString());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    /**
     * Process node ![CDATA[..]]
     * @param e
     * @return
     */
    private String getCharacterDataFromElement(Node e) {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData) child;
            return this.decodeContent(cd.getData(), "ISO-8859-1");
        }
        return this.decodeContent(e.getTextContent(), "ISO-8859-1");
    }

    /**
     * Process different type of charset encoding (need to add other possible variants here)
     * @param potentiallyEncodedContent
     * @param charset
     * @return
     */
    private String decodeContent(String potentiallyEncodedContent, String charset)
    {
        Charset encoder = Charset.forName(charset);
        boolean dataEncoded = encoder.newEncoder().canEncode(potentiallyEncodedContent);

        if(dataEncoded){
            try{
                return new String(potentiallyEncodedContent.getBytes(charset));
            }catch(Exception exception){
                System.out.println("Encoding failed" + exception.getMessage());
                return potentiallyEncodedContent;
            }
        }

        return potentiallyEncodedContent;
    }

    /**
     * @param nodes
     * @return
     */
    private String getNodeString(NodeList nodes)
    {
        StringBuilder result = new StringBuilder();
        int len = nodes.getLength();
        for(int i = 0; i < len; ++ i) {
            result.append(nodes.item(i).getNodeName());
        }

        return result.toString();
    }

    /**
     * Load xml DOM from string returned by source
     * @param xml
     * @return
     * @throws Exception
     */
    private Document loadXMLFromString(String xml) throws Exception
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xml));
        return builder.parse(is);
    }

    /**
     * Lookup if we already have category or create new one
     * @param categoryName
     * @param feedCategories
     * @return
     */
    private FeedCategory lookupForCategory(String categoryName, Iterable<FeedCategory>feedCategories)
    {
        for (final FeedCategory category : feedCategories) {
            if (category.getName().equals(categoryName.toLowerCase())) {
                return category;
            }
        }

        FeedCategory newCategory = new FeedCategory();
        newCategory.setName(categoryName.toLowerCase());

        feedCategoryRepository.save(newCategory);
        return newCategory;
    }

}
