package me.colingrimes.root.listener;

import me.colingrimes.midnight.message.Placeholders;
import me.colingrimes.midnight.util.text.Text;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import javax.annotation.Nonnull;

public class Listeners implements Listener {

	@EventHandler
	public void onAsyncPlayerChat(@Nonnull AsyncPlayerChatEvent event) {
		event.setMessage(Placeholders.create(event.getPlayer()).apply(event.getMessage()).toText());
	}

	@EventHandler
	public void onEntityDamage(@Nonnull EntityDamageEvent event) {
		if (!(event.getEntity() instanceof Player player) || event.getDamage() < player.getHealth()) {
			return;
		}

		event.setCancelled(true);
		player.setFireTicks(0);
		player.setFoodLevel(20);
		player.setSaturation(20);
		player.setHealth(player.getAttribute(Attribute.MAX_HEALTH).getValue());
		player.sendMessage(Text.color("&cQuick respawn."));
	}
}
