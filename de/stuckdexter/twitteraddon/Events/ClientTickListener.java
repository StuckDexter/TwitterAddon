package de.stuckdexter.twitteraddon.Events;

import de.stuckdexter.twitteraddon.TwitterAddon;
import de.stuckdexter.twitteraddon.TwitterInfos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class ClientTickListener {

    public static int i = 0;

    @SubscribeEvent
    public void onTick( TickEvent.ClientTickEvent event){
        if(i == TwitterAddon.interval){
            TwitterInfos ti = new TwitterInfos(TwitterAddon.name);
            TwitterAddon.followers = ti.getFollowers();
            TwitterAddon.tweets = ti.getTweets();
            TwitterAddon.following = ti.getFollowing();
            TwitterAddon.likes = ti.getLikes();
            i = 0;
        }else{
            i++;
        }
    }
}
