package de.stuckdexter.twitteraddon;

import de.stuckdexter.twitteraddon.Events.ClientTickListener;
import de.stuckdexter.twitteraddon.Modules.FollowerModule;
import de.stuckdexter.twitteraddon.Modules.FollowingModule;
import de.stuckdexter.twitteraddon.Modules.LikesModule;
import de.stuckdexter.twitteraddon.Modules.TweetsModule;
import net.labymod.api.LabyModAddon;
import net.labymod.settings.elements.*;
import net.labymod.utils.Consumer;
import net.labymod.utils.Material;

import java.util.List;

public class TwitterAddon extends LabyModAddon{

    public static String name;
    public static int interval;

    public static String followers;
    public static String tweets;
    public static String following;
    public static String likes;

    @Override
    public void onEnable() {
        TwitterInfos ti = new TwitterInfos(name);
        followers = ti.getFollowers();
        tweets = ti.getTweets();
        following = ti.getFollowing();
        likes = ti.getLikes();

        this.getApi().registerModule(new FollowerModule());
        this.getApi().registerModule(new TweetsModule());
        this.getApi().registerModule(new FollowingModule());
        this.getApi().registerModule(new LikesModule());

        getApi().registerForgeListener( new ClientTickListener() );
    }

    @Override
    public void onDisable() {

    }

    @Override
    public void loadConfig() {
        this.name = getConfig().has( "Username" ) ? getConfig().get( "Username" ).getAsString() : "DexterHD5";
        this.interval = getConfig().has( "Interval" ) ? getConfig().get( "Interval" ).getAsInt() : 1200;
    }

    @Override
    protected void fillSettings(List<SettingsElement> subSettings) {
        StringElement channelStringElement = new StringElement( "Username", new ControlElement.IconData( Material.PAPER ), TwitterAddon.name, new Consumer<String>() {
            @Override
            public void accept(String accepted) {
                System.out.println("Username: " + accepted);
                TwitterAddon.name = accepted;
                TwitterAddon.this.getConfig().addProperty("Username", TwitterAddon.name);
                TwitterAddon.this.saveConfig();
            }
        });
        subSettings.add(channelStringElement);

        NumberElement numberElement = new NumberElement( "Update interval", new ControlElement.IconData( Material.WATCH ), interval);
        numberElement.addCallback( new Consumer<Integer>() {
            @Override
            public void accept( Integer accepted ) {
                System.out.println("Interval: " + accepted);
                TwitterAddon.interval = accepted;
                TwitterAddon.this.getConfig().addProperty("Interval", TwitterAddon.interval);
                TwitterAddon.this.saveConfig();
                ClientTickListener.i = 0;
            }
        } );

        subSettings.add( numberElement );
    }
}
