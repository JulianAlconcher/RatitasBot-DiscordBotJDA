package org.example.Listeners;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Invite;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.events.StatusChangeEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateOnlineStatusEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.Presence;

import javax.swing.text.html.parser.Entity;

public class EventListener extends ListenerAdapter {
    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {
        User user = event.getUser();

        String emoji = event.getReaction().getEmoji().getAsReactionCode();
        String channelMention = event.getChannel().getAsMention();
        String jumpLink = event.getJumpUrl();
        String message = user.getAsTag() + " reaccciono al mensaje con " + emoji + " en el canal " + channelMention  ;
        event.getGuild().getDefaultChannel().asStandardGuildMessageChannel().sendMessage(message).queue();
    }

    @Override
    public void onUserUpdateOnlineStatus(UserUpdateOnlineStatusEvent event) {
        User user = event.getUser();
        String message = user.getAsTag() + " --> ";
        String status = event.getNewOnlineStatus().getKey();

        switch (status){
            case "online":
                message +="MUY BIEN PAPA, A LABURAR!";
                break;
            case "dnd":
                message +="Modo Concentracion ACTIVADO, excelente!";
                break;
            case "idle":
                message += "A vos te parece?";
                break;
            case "offline":
                message += "VOLVE PRONTO!";
                break;
        }
        event.getGuild().getDefaultChannel().asStandardGuildMessageChannel().sendMessage(message).queue();

    }
}
