package org.example;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManager;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.example.Listeners.EventListener;

import javax.security.auth.login.LoginException;

public class ratitasBot {

    private final Dotenv config;
    private final ShardManager sharedManager;
    public ratitasBot() throws LoginException {
        config = Dotenv.configure().load();
        String token = config.get("TOKEN");
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.listening("esta vida me encanta"));
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGES ,GatewayIntent.GUILD_PRESENCES);
        //Seteo cache
        builder.setMemberCachePolicy(MemberCachePolicy.ALL);
        builder.setChunkingFilter(ChunkingFilter.ALL);
        builder.enableCache(CacheFlag.ONLINE_STATUS);

        sharedManager = builder.build();
        //REGISTRAR OYENTES
        sharedManager.addEventListener(new EventListener());
    }
    public ShardManager getSharedManager() {
        return sharedManager;
    }
    public Dotenv getConfig() {
        return config;
    }
    public static void main (String args[]){
        try {
            ratitasBot bot = new ratitasBot();
        }catch(LoginException e){
            System.out.println("ERROR: token invalido");
        }
    }
}
