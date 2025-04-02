package me.colingrimes.root.command.bye;

import me.colingrimes.midnight.command.Command;
import me.colingrimes.midnight.command.handler.util.ArgumentList;
import me.colingrimes.midnight.command.handler.util.Sender;
import me.colingrimes.midnight.util.bukkit.Entities;
import me.colingrimes.midnight.util.text.Text;
import me.colingrimes.root.Root;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;

public class Bye implements Command<Root> {

	@Override
	public void execute(@Nonnull Root plugin, @Nonnull Sender sender, @Nonnull ArgumentList args) {
		for (Entity entity : sender.world().getEntities()) {
			if (entity instanceof LivingEntity && !(entity instanceof Player)) {
				entity.remove();
			}
		}
		sender.message(Text.color("&cAll living mobs have been cleared."));
	}
}
