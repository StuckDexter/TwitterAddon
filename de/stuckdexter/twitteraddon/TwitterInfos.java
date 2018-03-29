package de.stuckdexter.twitteraddon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class TwitterInfos {

    private String tweets = null;
    private String following = null;
    private String followers = null;
    private String likes = null;

    public TwitterInfos(String username){
        URL urlObject = null;
        try {
            urlObject = new URL("https://twitter.com/" + username);
            System.out.println(username);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        URLConnection urlConnection = null;
        try {
            urlConnection = urlObject.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");

        try {
            getInfos(urlConnection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getInfos(InputStream inputStream) throws IOException
    {
        System.out.println("Startet Request");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        try
        {
            String inputLine;
            StringBuilder stringBuilder = new StringBuilder();
            while ((inputLine = bufferedReader.readLine()) != null)
            {
                if(inputLine.contains("<span class=\"ProfileNav-value\"")){
                    if(tweets == null){
                        tweets = inputLine.replace("            <span class=\"ProfileNav-value\"  data-count=", "").replace(" data-is-compact=\"false\">", "").replace("</span>", "").replace(".", "").replace("data-is-compact=\"true\">", "");
                        tweets = tweets.substring(tweets.length() / 2);
                        System.out.println("Tweets " + tweets);


                    }else if(following == null){
                        following = inputLine.replace("          <span class=\"ProfileNav-value\" data-count=", "").replace(" data-is-compact=\"false\">", "").replace("</span>", "").replace(".", "").replace("data-is-compact=\"true\">", "");
                        following = following.substring(following.length() / 2);
                        System.out.println("Following " + following);


                    }else if(followers == null){
                        followers = inputLine.replace("          <span class=\"ProfileNav-value\" data-count=", "").replace(" data-is-compact=\"false\">", "").replace("</span>", "").replace(".", "").replace("data-is-compact=\"true\">", "");
                        followers = followers.substring(followers.length() / 2);
                        System.out.println("Followers " + followers);


                    }else if(likes == null){
                        likes = inputLine.replace("          <span class=\"ProfileNav-value\" data-count=", "").replace(" data-is-compact=\"false\">", "").replace("</span>", "").replace(".", "").replace("data-is-compact=\"true\">", "");
                        likes = likes.substring(likes.length() / 2);
                        System.out.println("Likes " + likes);
                    }else{
                        System.out.println("Stopped Request");
                        return;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getTweets(){
        if(!(tweets == null)){
            return tweets;
        }else{
            return "Loading...";
        }
    }

    public String getFollowers(){
        if(!(followers == null)){
            return followers;
        }else{
            return "Loading...";
        }
    }

    public String getFollowing(){
        if(!(following == null)){
            return following;
        }else{
            return "Loading...";
        }
    }

    public String getLikes(){
        if(!(likes == null)){
            return likes;
        }else{
            return "Loading...";
        }
    }
}
