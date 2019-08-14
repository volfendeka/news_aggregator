package news.aggregator.Service;

import news.aggregator.Adapter.FeedAdapter;
import news.aggregator.Entity.Feed;
import news.aggregator.Entity.FeedCategory;
import news.aggregator.Entity.Source;
import news.aggregator.Repository.FeedCategoryRepository;
import news.aggregator.Repository.FeedRepository;
import news.aggregator.Repository.SourceRepository;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileWriter;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FeedParser {

    @Autowired
    private FeedRepository feedRepository;
    @Autowired
    private FeedCategoryRepository feedCategoryRepository;
   /*
    @Autowired
    private SourceRepository sourceRepository;
*/
    public void parse(Source source, ResponseEntity<String> responseEntity)
    {
        try{
            FileWriter fw = new FileWriter("C:\\Users\\Administrator\\Desktop\\log\\" + source.getName() + ".txt");
            FileWriter fw1 = new FileWriter("C:\\Users\\Administrator\\Desktop\\log\\" + source.getName() + "_nodes.txt");
            Document doc = loadXMLFromString(responseEntity.getBody());

            NodeList nl = doc.getElementsByTagName("*");
            Node n;

            FeedAdapter feedAdapter = new FeedAdapter(source.getName());
            FeedAdapter adapter = feedAdapter.make();
            Iterable<Feed> feedRep= this.feedRepository.findAll();
            Iterable<FeedCategory> category = this.feedCategoryRepository.findAll();

            FeedCategory feedCategory = category.iterator().next();

            fw.write(responseEntity.getBody());
            fw.close();

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
                        feed.setFeedCategory(feedCategory);
                        if(field.getNodeName().equals(adapter.getTitle())){
                            feed.setTitle(field.getTextContent());
                        }
                        if(field.getNodeName().equals(adapter.getDescription())){
                            feed.setDescription(field.getTextContent());
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

                        if(feed.isValid()){
                            feed.setSource(source);
                            feedRepository.save(feed);
                            feed = new Feed();
                        }
                    }
                }

                fw1.write(n.getNodeName() + ": " + getNodeString(n.getChildNodes()) + "\r\n");
                fw1.write(n.getNodeName() + ": " + n.getTextContent() + "\r\n");

            }
            fw1.close();

        }catch (Exception e){
            System.out.println(e.toString());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    private String getNodeString(NodeList nodes)
    {
        StringBuilder result = new StringBuilder();
        int len = nodes.getLength();
        for(int i = 0; i < len; ++ i) {
            result.append(nodes.item(i).getNodeName());
        }

        return result.toString();
    }

    public Document loadXMLFromString(String xml) throws Exception
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xml));
        return builder.parse(is);
    }

}
