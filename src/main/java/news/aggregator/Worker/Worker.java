package news.aggregator.Worker;

import news.aggregator.Entity.Source;
import news.aggregator.Service.FeedParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Worker{

    @Autowired
    private FeedParser feedParser;

    /**
     * Worker factory. Creates separate RequestWorker for each source.
     *
     * @param source
     * @return
     */
    public RequestWorker make(Source source)
    {
        return new RequestWorker(source, feedParser);
    }
}
