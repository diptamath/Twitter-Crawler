import java.util.*;
import twitter4j.api.*;

import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.Twitter;
import twitter4j.Query;
import twitter4j.QueryResult;

import twitter4j.conf.ConfigurationBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import java.io.BufferedReader;
import java.io.FileReader;
import twitter4j.Paging;
import twitter4j.StatusUpdate;






public class TwitterCrawler
{
    public static int i=0;

    public String[] input() throws Exception
    {
        //int j;
        FileReader file = new FileReader("C:\\Users\\User\\Desktop\\keywords.txt");
        BufferedReader reader = new BufferedReader(file);
        String[] arr = new String[800];
        String line = reader.readLine();
        while(line!=null)
        {
            arr[i++]=line;
            line = reader.readLine();
        }
        return arr;
    }



    public void searchtwits(Twitter tw) throws Exception
    {
        int j;
        String[] arr = new String[800];
        TwitterCrawler tci = new TwitterCrawler();
        arr = tci.input();

        try
        {
            PrintStream myconsole1 = new PrintStream(new File("C:\\Users\\User\\Desktop\\tweets.txt"));
            PrintStream myconsole2 = new PrintStream(new File("C:\\Users\\User\\Desktop\\retweets.txt"));
            System.setOut(myconsole1);
            System.setOut(myconsole2);
            try
            {
                for(j=0;j<i;j++)
                {
                    myconsole1.println("Search result for : "+arr[j]);
                    myconsole2.println("Search result for : "+arr[j]);
                    myconsole1.println("\n"+"\n");
                    myconsole2.println("\n"+"\n");
                    //System.out.println("\n");
                    Query q = new Query(arr[j]);  //text
                    QueryResult qr;
                    do
                    {
                        qr = tw.search(q);
                        List<Status> st = qr.getTweets();
                        for (Status s:st)
                        {
                            //System.setOut(myconsole);

                            //myconsole.println("Location :"+s.getGeoLocation());                    //The location that this tweet refers to if available.
                            //myconsole.println("Twit  : "+s.getPlace());                          //place attached to this status
                            //myconsole.println("Twit : "+s.getWithheldInCountries());             //list of country codes where the tweet is withheld
                            /*
                            if(true==s.isFavorited())                                              //if the tweet is favorited
                            {
                                myconsole.println("Favorite Counts : "+s.getFavoriteCount());          //how many times this Tweet has been "favorited" by Twitter users
                            }
                            */

                            //myconsole.println("Contributors : "+s.getContributors());              //an array of contributors, or null if no contributor is associated with this status.


                            //myconsole.println(""+s.getInReplyToUserId());


                            if(s.isRetweet()==true)                                                 //the status is retweet or not
                            {
//                                myconsole2.println("User Name : "+s.getUser().getName());
//                                myconsole2.println("User Id : "+s.getUser().getScreenName());
                                myconsole2.println("Twit : "+s.getText());                              //text of the tweet
//                                myconsole2.println("Time : "+s.getCreatedAt());                         //date, time, day
//                                myconsole2.println("Language : "+s.getLang());                          //lang of the status text if available.
//                                myconsole2.println("Retweet Counts : "+s.getRetweetCount());          //the number of times this tweet has been retweeted, or -1 when the tweet was created before this feature was enabled.
//                                myconsole2.println("Twit Id : "+s.getId());                             //id of the tweet
                                //myconsole.println("ReTweet Id : "+s.getCurrentUserRetweetId());      //Retweet id (-1 if tweet before freature created)
                                myconsole2.println("\n");
                            }
                            /*
                            if(s.isRetweeted()==true)                                                //if the status is retweeted
                            {
                                //myconsole.println("Retweet Counts : "+s.getRetweetCount());          //the number of times this tweet has been retweeted, or -1 when the tweet was created before this feature was enabled.
                                //myconsole1.println("ReTweet : "+s.getRetweetedStatus());             //retweeted status
                                //myconsole.println("Twit : "+s.getCurrentUserRetweetId());            //Retweet id (-1 if tweet before freature created)
                                myconsole1.println("User Name : "+s.getUser().getName());
                                myconsole1.println("User Id : "+s.getUser().getScreenName());
                                myconsole1.println("Twit : "+s.getText());
                                myconsole1.println("ReTweet : "+s.getRetweetedStatus());
                            }
                            */
                            else
                            {
//                                myconsole1.println("User Name : "+s.getUser().getName());
//                                myconsole1.println("User Id : "+s.getUser().getScreenName());
                                myconsole1.println(s.getText());                              //text of the tweet
//                                myconsole1.println("Time : "+s.getCreatedAt());                         //date, time, day
//                                myconsole1.println("Language : "+s.getLang());                          //lang of the status text if available.
//                                myconsole1.println("Favorite Counts : "+s.getFavoriteCount());          //approximately how many times this Tweet has been "favorited" by Twitter users
//                                myconsole1.println("Retweet Counts : "+s.getRetweetCount());
//                                myconsole1.println("Twit Id : "+s.getId());                             //id of the tweet
//                                myconsole1.println("Reply Twit Id : "+s.getInReplyToStatusId());               //in_reply_to_tweet - id
//                                myconsole1.println("Reply User Id : "+s.getInReplyToScreenName());             //in_reply_to_user - id
                                myconsole1.println("\n");
                            }

                        }
                    }while ((q = qr.nextQuery()) != null);
                }
                //System.exit(0);
            }
            catch (TwitterException te)
            {
                System.out.println("Failed to search tweets: " + te.getMessage());
                System.exit(-1);
            }
        }
        catch(FileNotFoundException fx)
        {
            System.out.println(fx);
        }
    }






    public static void main(String[] args) throws TwitterException,  Exception
    {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("your AuthConsumerKey")
                .setOAuthConsumerSecret("your AuthConsumerSecret key")
                .setOAuthAccessToken("your AuthAccessToken key ")
                .setOAuthAccessTokenSecret("your AuthAccessTokenSecret key");

        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter tw = tf.getInstance();

        TwitterCrawler tc = new TwitterCrawler();

        tc.searchtwits(tw);


    }

}
