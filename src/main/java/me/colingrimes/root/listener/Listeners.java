package me.colingrimes.root.listener;

import me.colingrimes.midnight.util.text.Text;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import javax.annotation.Nonnull;

public class Listeners implements Listener {

	@EventHandler
	public void onAsyncPlayerChat(@Nonnull AsyncPlayerChatEvent event) {
		event.setMessage(Text.color(event.getMessage()));
	}
}
