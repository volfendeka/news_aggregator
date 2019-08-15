package news.aggregator.Adapter;

import java.lang.reflect.Constructor;

public class AdapterFactory {

    public FeedAdapter make(String name)
    {

        String sourceName = String.format("%s%s", name.substring(0, 1).toUpperCase(), name.substring(1));

        try {
            Class<?> Adapter = Class.forName("news.aggregator.Adapter." + sourceName);
            Constructor<?> cons = Adapter.getConstructor(String.class.getClasses());
            Object adapterObject = cons.newInstance();

            System.out.println(adapterObject.getClass());
            return (FeedAdapter) adapterObject;
        }catch(Exception exception){
            System.out.println(exception.toString());
        }

        return new FeedAdapter();
    }
}
