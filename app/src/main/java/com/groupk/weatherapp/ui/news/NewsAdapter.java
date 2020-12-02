package com.groupk.weatherapp.ui.news;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.groupk.weatherapp.R;
import com.groupk.weatherapp.util.APIKey;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private final List<NewsItem> mNewsList;
    private final Context context;

    public NewsAdapter(Context context) {
        this.context = context;
        mNewsList = new ArrayList<>();

        String urlString = "https://newsapi.org/v2/everything?q=weather&apiKey=" + APIKey.getNewsKey();
        try {
            String s = "{\"status\":\"ok\",\"totalResults\":28979,\"articles\":[{\"source\":{\"id\":null,\"name\":\"New York Times\"},\"author\":\"Alison Stine\",\"title\":\"Can Cannabis Farms Weather Wildfire Season?\",\"description\":\"Many marijuana crops are uninsured, which means that in the wake of disaster, farmers can face financial ruin.\",\"url\":\"https://www.nytimes.com/2020/11/27/style/cannabis-farms-wildfires-weed.html\",\"urlToImage\":\"https://static01.nyt.com/images/2020/11/29/fashion/27WEED-FARMS1/27WEED-FARMS1-facebookJumbo.jpg\",\"publishedAt\":\"2020-11-27T10:00:17Z\",\"content\":\"Some cannabis farmers chose to stay on their farms, in some cases defying evacuation orders, to try and save crops from fire using methods like watering down the plants. One of those farmers was Ms. … [+1343 chars]\"},{\"source\":{\"id\":null,\"name\":\"New York Times\"},\"author\":\"Mike Seely\",\"title\":\"Is a Dive Bar Still a Dive Bar WIth Covid Safety Measures?\",\"description\":\"Measures to weather the coronavirus are transforming drinking holes beloved for their dankness.\",\"url\":\"https://www.nytimes.com/2020/11/10/style/best-dive-bars.html\",\"urlToImage\":\"https://static01.nyt.com/images/2020/11/12/fashion/10DIVEBARS-brothers-1/10DIVEBARS-brothers-1-facebookJumbo-v2.jpg\",\"publishedAt\":\"2020-11-10T17:32:03Z\",\"content\":\"Two gloves, a dustpan, a onetime-use broom and some cleaning solution: In Chicago, thats what the Health Department refers to as a vomit and diarrhea cleanup kit. And until a loathsome regular named … [+831 chars]\"},{\"source\":{\"id\":null,\"name\":\"New York Times\"},\"author\":\"Mike Seely\",\"title\":\"Is a Dive Bar Still a Dive Bar WIth Covid Safety Measures?\",\"description\":\"Measures to weather the coronavirus are transforming drinking holes beloved for their dankness.\",\"url\":\"https://www.nytimes.com/2020/11/10/style/coronavirus-dive-bars.html\",\"urlToImage\":\"https://static01.nyt.com/images/2020/11/12/fashion/10DIVEBARS-brothers-1/10DIVEBARS-brothers-1-facebookJumbo-v2.jpg\",\"publishedAt\":\"2020-11-13T00:12:56Z\",\"content\":\"While Simons was, until recently, able to offer limited seating indoors in addition to its evolving outdoor space, such a plan simply wasnt feasible for My Brothers Bar, which, at 147 years of age, i… [+1391 chars]\"},{\"source\":{\"id\":null,\"name\":\"Lifehacker.com\"},\"author\":\"Beth Skwarecki on Vitals, shared by Beth Skwarecki to Lifehacker\",\"title\":\"Outdoor Dining Isn't Safe if You're in a Plastic Tent\",\"description\":\"Restaurants have had to get creative to survive the pandemic. Takeout-centric operations and outdoor dining have allowed us to keep eating well throughout these long months. But as the weather gets chilly, some restaurants are now serving customers “outdoors”…\",\"url\":\"https://vitals.lifehacker.com/outdoor-dining-isnt-safe-if-youre-in-a-plastic-tent-1845553192\",\"urlToImage\":\"https://x.kinja-static.com/assets/images/logos/placeholders/vitals.png\",\"publishedAt\":\"2020-11-02T20:30:00Z\",\"content\":\"Restaurants have had to get creative to survive the pandemic. Takeout-centric operations and outdoor dining have allowed us to keep eating well throughout these long months. But as the weather gets c… [+3571 chars]\"},{\"source\":{\"id\":null,\"name\":\"Lifehacker.com\"},\"author\":\"Elizabeth Yuko\",\"title\":\"Cope With Gardening Withdrawal By Starting an Indoor Herb Garden\",\"description\":\"Though the weather has been unseasonably warm this week in many parts of the country, we know this isn’t going to last. And if you were one of the many people who really embraced gardening during the pandemic (or have been an avid gardener for years), you may…\",\"url\":\"https://lifehacker.com/cope-with-gardening-withdrawal-by-starting-an-indoor-he-1845766856\",\"urlToImage\":\"https://i.kinja-img.com/gawker-media/image/upload/c_fill,f_auto,fl_progressive,g_center,h_675,pg_1,q_80,w_1200/tyxcgr81loorjvec4yzs.jpg\",\"publishedAt\":\"2020-11-28T16:30:00Z\",\"content\":\"Though the weather has been unseasonably warm this week in many parts of the country, we know this isnt going to last. And if you were one of the many people who really embraced gardening during the … [+2124 chars]\"},{\"source\":{\"id\":\"techcrunch\",\"name\":\"TechCrunch\"},\"author\":\"Ingrid Lunden\",\"title\":\"Tourlane adds another $20M at a $242M valuation to help it weather the Covid storm\",\"description\":\"The tourism and travel industry has been one of the hardest hit by the global Covid-19 health pandemic, and today a promising startup in the space announced some funding to help it weather the storm. Berlin-based Tourlane, which has built a platform that mimi…\",\"url\":\"http://techcrunch.com/2020/11/18/tourlane-adds-another-20m-at-a-242m-valuation-to-help-it-weather-the-covid-storm/\",\"urlToImage\":\"https://techcrunch.com/wp-content/uploads/2020/11/Travel-Expert-Tourlane.jpg?w=619\",\"publishedAt\":\"2020-11-18T10:27:35Z\",\"content\":\"The tourism and travel industry has been one of the hardest hit by the global Covid-19 health pandemic, and today a promising startup in the space announced some funding to help it weather the storm.… [+5966 chars]\"},{\"source\":{\"id\":\"bbc-news\",\"name\":\"BBC News\"},\"author\":null,\"title\":\"Anthony Fauci: 'We have got to double down' to fight Covid\",\"description\":\"The top US infectious diseases expert warns colder weather will bring a \\\"challenging and ominous situation\\\".\",\"url\":\"https://www.bbc.co.uk/news/av/world-us-canada-54923856\",\"urlToImage\":\"https://ichef.bbci.co.uk/images/ic/400xn/p08y7wwr.jpg\",\"publishedAt\":\"2020-11-12T17:04:55Z\",\"content\":\"Dr Anthony Fauci, the top US infectious diseases expert, says the country needs to \\\"double down\\\" on public health measures such as mask-wearing and social distancing.\\r\\nSpeaking to Chatham House on Th… [+216 chars]\"},{\"source\":{\"id\":null,\"name\":\"New York Times\"},\"author\":\"Manny Fernandez, Campbell Robertson, Mitch Smith and Will Wright\",\"title\":\"Virus Outbreak, Once in the Nation’s Middle, Gains Steam All Around\",\"description\":\"With cases and deaths rising fast, scientists say they worry about the virus’s course in the United States as Thanksgiving celebrations and cold weather arrive.\",\"url\":\"https://www.nytimes.com/2020/11/25/us/covid-united-states-spread.html\",\"urlToImage\":\"https://static01.nyt.com/images/2020/11/25/us/25virus-stateofthevirus-1/merlin_180410535_1e2c1222-9633-4df1-bd3d-c54416c72103-facebookJumbo.jpg\",\"publishedAt\":\"2020-11-25T17:39:06Z\",\"content\":\"Any incremental improvements in the Midwest have been more than offset by growing outbreaks elsewhere in the country. In Los Angeles County, Calif., where cases have soared past the levels seen this … [+1642 chars]\"},{\"source\":{\"id\":\"bbc-news\",\"name\":\"BBC News\"},\"author\":null,\"title\":\"How critical is the weather for the SpaceX launch?\",\"description\":\"Nasa and SpaceX were due to send astronauts to the ISS on Saturday but the weather's changed their plans.\",\"url\":\"https://www.bbc.co.uk/news/av/science-environment-54934885\",\"urlToImage\":\"https://ichef.bbci.co.uk/images/ic/400xn/p08yf99l.jpg\",\"publishedAt\":\"2020-11-14T00:14:27Z\",\"content\":\"Nasa and SpaceX were due to send four astronauts to the International Space Station late on Saturday - but then the weather changed their plans.\\r\\nCrew-1 is currently scheduled to blast off on Sunday … [+206 chars]\"},{\"source\":{\"id\":null,\"name\":\"New York Times\"},\"author\":\"Daniel Bortz\",\"title\":\"Outdoor Winter Wedding Hacks\",\"description\":\"From heating lamps to snow machines, here are some must-have items and creative ideas for couples braving the colder weather.\",\"url\":\"https://www.nytimes.com/2020/11/25/fashion/weddings/outdoor-winter-wedding-hacks.html\",\"urlToImage\":\"https://static01.nyt.com/images/2020/11/29/fashion/24OutdoorWinterWed-Art/00OutdoorWinterWed-Art-facebookJumbo.jpg\",\"publishedAt\":\"2020-11-25T17:12:30Z\",\"content\":\"Its simple: Its about not having viruses, like the coronavirus, lingering in the air for a long period of time, he said. This doesnt guarantee that guests wont contract the coronavirus, but it reduce… [+1380 chars]\"},{\"source\":{\"id\":null,\"name\":\"New York Times\"},\"author\":\"Glenn Rifkin\",\"title\":\"Eleanor Schano, Trailblazing Journalist in Pittsburgh, Dies at 88\",\"description\":\"Ms. Schano rose from “weather girl” to reporter to anchor in firsts for the city, hiding three pregnancies along the way. She died of Covid-19.\",\"url\":\"https://www.nytimes.com/2020/11/17/obituaries/eleanor-schano-dead-covid.html\",\"urlToImage\":\"https://static01.nyt.com/images/2020/11/16/obituaries/16Schano/16Schano-facebookJumbo.jpg\",\"publishedAt\":\"2020-11-17T21:59:53Z\",\"content\":\"You couldnt use the word pregnant on the air, she said in a 2007 interview with the Pittsburgh Post Gazette. And God forbid if you were pregnant.\\r\\nShe delivered the weather forecast one Friday night … [+1173 chars]\"},{\"source\":{\"id\":\"techcrunch\",\"name\":\"TechCrunch\"},\"author\":\"Darrell Etherington\",\"title\":\"Astra targets December for next orbital launch attempt\",\"description\":\"Astra is set to launch it’s next orbital rocket, with a window that opens on December 7 and lasts for 12 days following until December 18, with an 11 AM to 2:30 PM PT block each day during which the launch could occur, depending on weather and conditions on t…\",\"url\":\"http://techcrunch.com/2020/11/19/astra-targets-december-for-next-orbital-launch-attempt/\",\"urlToImage\":\"https://techcrunch.com/wp-content/uploads/2020/09/Ehsp9KqUwAMT4XU.jpeg?w=711\",\"publishedAt\":\"2020-11-19T18:17:59Z\",\"content\":\"Astra is set to launch it’s next orbital rocket, with a window that opens on December 7 and lasts for 12 days following until December 18, with an 11 AM to 2:30 PM PT block each day during which the … [+1691 chars]\"},{\"source\":{\"id\":\"reuters\",\"name\":\"Reuters\"},\"author\":\"Naveen Thukral\",\"title\":\"GRAINS-Soybeans set for 3rd weekly gain on dry weather, Chinese demand - Reuters\",\"description\":\"* Strong Chinese demand, dry weather in South America support (Adds quote in paragraph 3; details on Argentine weather, fund positioning)\",\"url\":\"https://www.reuters.com/article/global-grains-idUSL1N2I604B\",\"urlToImage\":\"https://s1.reutersmedia.net/resources_v2/images/rcom-default.png?w=800\",\"publishedAt\":\"2020-11-20T02:46:00Z\",\"content\":\"* Soybean futures gain more than 12% in three weeks of rally\\r\\n* Strong Chinese demand, dry weather in South America support (Adds quote in paragraph 3; details on Argentine weather, fund positioning)… [+1748 chars]\"},{\"source\":{\"id\":\"mashable\",\"name\":\"Mashable\"},\"author\":\"Veronika Kero\",\"title\":\"28 deals on cold-weather gear, from heated gloves to ice scrapers\",\"description\":\"Winter is coming whether we like it or not. And on top of dealing with pandemic anxiety, we also have to deal with the usual pains of winter — like feeling extremely cold.\\nTo help you survive the subzero temperatures about to befall us, we've found 28 heaters…\",\"url\":\"https://mashable.com/shopping/nov-15-cold-weather-gear-sale/\",\"urlToImage\":\"https://mondrian.mashable.com/2020%252F11%252F15%252Fbf%252F8105a4a2f868485283c299df5d3521bc.437ad.jpg%252F1200x630.jpg?signature=sbkOBWG5PkVpqV-XnF2ul2ACMqA=\",\"publishedAt\":\"2020-11-15T10:00:00Z\",\"content\":\"Winter is coming whether we like it or not. And on top of dealing with pandemic anxiety, we also have to deal with the usual pains of winter like feeling extremely cold.\\r\\nTo help you survive the subz… [+6511 chars]\"},{\"source\":{\"id\":\"cnn\",\"name\":\"CNN\"},\"author\":\"Reuters and Ben Westcott\",\"title\":\"Super typhoon Goni makes two landfalls in the Philippines after mass evacuations\",\"description\":\"A super typhoon has barreled into the southern part of the Philippines' main island of Luzon on Sunday, bringing \\\"catastrophic\\\" violent winds and intense rain with two landfalls so far, the country's weather bureau said.\",\"url\":\"https://www.cnn.com/2020/11/01/asia/philippines-super-typhoon-goni-landfall-intl-hnk/index.html\",\"urlToImage\":\"https://cdn.cnn.com/cnnnext/dam/assets/201101123630-02-typhoon-goni-0111-super-tease.jpg\",\"publishedAt\":\"2020-11-01T05:24:47Z\",\"content\":\"A super typhoon has barreled into the southern part of the Philippines' main island of Luzon on Sunday, bringing \\\"catastrophic\\\" violent winds and intense rain with two landfalls so far, the country's… [+2570 chars]\"},{\"source\":{\"id\":\"reuters\",\"name\":\"Reuters\"},\"author\":\"Reuters Staff\",\"title\":\"Japan weather bureau says 60% chance La Nina to continue into spring - Reuters\",\"description\":\"Japan's weather bureau said on Tuesday there was a 90% chance of the La Nina weather phenomenon continuing through the northern hemisphere winter, and a 60% chance it will continue into spring.\",\"url\":\"https://www.reuters.com/article/us-japan-weather-idUSKBN27Q0JD\",\"urlToImage\":\"https://s1.reutersmedia.net/resources_v2/images/rcom-default.png?w=800\",\"publishedAt\":\"2020-11-10T05:29:00Z\",\"content\":\"By Reuters Staff\\r\\nTOKYO (Reuters) - Japans weather bureau said on Tuesday there was a 90% chance of the La Nina weather phenomenon continuing through the northern hemisphere winter, and a 60% chance … [+182 chars]\"},{\"source\":{\"id\":null,\"name\":\"The Weather Network\"},\"author\":\"Pelmorex Weather Networks Inc\",\"title\":\"Lava planet weather forecast: Supersonic winds and raining molten rock - The Weather Network\",\"description\":\"Lava planet weather forecast: Supersonic winds and raining molten rock  The Weather NetworkView Full coverage on Google News\",\"url\":\"https://www.theweathernetwork.com/ca/news/article/alien-lava-planet-weather-forecast-blazing-supersonic-winds-and-rocky-rains\",\"urlToImage\":\"https://www.theweathernetwork.com//images.twnmm.com/c55i45ef3o2a/1wwtBRIlbRNdsWmfrMyNdA/5ca1e7fdc61027bf26c33aa7a8e85a58/K2-141b-weather-forecast-NASA.jpg\",\"publishedAt\":\"2020-11-05T18:00:00Z\",\"content\":\"Thursday, November 5th 2020, 4:00 pm - Canadian-led research produces the first weather forecast for a hellish lava world.\\r\\nOn a distant alien planet, the weather forecast calls for supersonic winds … [+2738 chars]\"},{\"source\":{\"id\":null,\"name\":\"Gizmodo.com\"},\"author\":\"Andrew Couts\",\"title\":\"These Are the Creepiest Gadgets of 2020, According to Mozilla\",\"description\":\"The weather outside is turning frightful, and that means it’s time for the Mozilla Foundation to scare the crap out of you with its annual “Privacy Not Included” buyer’s guide. Each year, the Mozilla Foundation judges a handful of gadgets based on their priva…\",\"url\":\"https://gizmodo.com/these-are-the-36-creepiest-gadgets-according-to-mozill-1845664481\",\"urlToImage\":\"https://i.kinja-img.com/gawker-media/image/upload/c_fill,f_auto,fl_progressive,g_center,h_675,pg_1,q_80,w_1200/cpxga4ho5g1fdytdjcxy.png\",\"publishedAt\":\"2020-11-13T17:41:00Z\",\"content\":\"The weather outside is turning frightful, and that means its time for the Mozilla Foundation to scare the crap out of you with its annual Privacy Not Included buyers guide. Each year, the Mozilla Fou… [+4449 chars]\"},{\"source\":{\"id\":null,\"name\":\"Gizmodo.com\"},\"author\":\"George Dvorsky on Earther, shared by Andrew Liszewski to Gizmodo\",\"title\":\"‘Feedback Loop’ in Central East Asia Threatens Disturbing Changes to Mongolia's Climate\",\"description\":\"A review of weather patterns in inner East Asia over the past 260 years suggests the region is currently caught in a dangerous cycle of heatwaves and droughts that could forever reshape the area, and possibly turn the Mongolian Plateau into an arid wasteland.…\",\"url\":\"https://earther.gizmodo.com/feedback-loop-in-central-east-asia-threatens-disturbi-1845761575\",\"urlToImage\":\"https://i.kinja-img.com/gawker-media/image/upload/c_fill,f_auto,fl_progressive,g_center,h_675,pg_1,q_80,w_1200/mavgqc6o46owtgywgxdz.jpg\",\"publishedAt\":\"2020-11-26T19:00:00Z\",\"content\":\"A review of weather patterns in inner East Asia over the past 260 years suggests the region is currently caught in a dangerous cycle of heatwaves and droughts that could forever reshape the area, and… [+4205 chars]\"},{\"source\":{\"id\":\"ars-technica\",\"name\":\"Ars Technica\"},\"author\":\"Eric Berger\",\"title\":\"SpaceX seeks to fly unproven rocket, put engine issue to rest\",\"description\":\"Weather currently is expected to be 60 percent favorable for a launch.\",\"url\":\"https://arstechnica.com/science/2020/11/spacex-seeks-to-fly-unproven-rocket-put-engine-issue-to-rest/\",\"urlToImage\":\"https://cdn.arstechnica.net/wp-content/uploads/2020/11/Photo-Nov-05-1-57-25-PM-760x380.jpg\",\"publishedAt\":\"2020-11-05T20:35:05Z\",\"content\":\"7 with 7 posters participating, including story author\\r\\nSpaceX has not launched a brand-new rocket since June, when it boosted a GPS III satellite for the US Space Force on a Falcon 9 rocket. Since t… [+2584 chars]\"}]}";
            JsonElement jelement = new JsonParser().parse(s);
            JsonObject jobject = jelement.getAsJsonObject();
            JsonArray jarray = jobject.getAsJsonArray("articles");

            int i = 0;
            while (jarray.get(0) != null) {
                jobject = jarray.get(i).getAsJsonObject();
                mNewsList.add(new NewsItem(jobject.get("title").getAsString(), jobject.get("description").getAsString(), jobject.get("url").getAsString(), jobject.get("urlToImage").getAsString()));
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Collections.sort(mNewsList, new NewsComparator());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener((View v) -> {
            Intent urlIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mNewsList.get(holder.getAdapterPosition()).url));
            v.getContext().startActivity(urlIntent);
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mNewsList.get(position);
        holder.mTitleView.setText(mNewsList.get(position).title);
        holder.mContentView.setText(mNewsList.get(position).content);
        Glide.with(context)
                .load(mNewsList.get(position).imageUrl)
                .into(holder.mNewsLogo);
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    static class NewsComparator implements Comparator<NewsItem> {

        @Override
        public int compare(NewsItem s1, NewsItem s2) {
            String news1 = s1.title.toUpperCase();
            String news2 = s2.title.toUpperCase();

            // Alphabetical order
            return news1.compareTo(news2);
        }
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTitleView;
        public final TextView mContentView;
        public final ImageView mNewsLogo;
        public NewsItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitleView = view.findViewById(R.id.title);
            mContentView = view.findViewById(R.id.content);
            mNewsLogo = view.findViewById(R.id.news_icon);
        }
    }
}