package me.colingrimes.root.command.worlds.create;

import me.colingrimes.midnight.command.Command;
import me.colingrimes.midnight.command.handler.util.ArgumentList;
import me.colingrimes.midnight.command.handler.util.CommandProperties;
import me.colingrimes.midnight.command.handler.util.Sender;
import me.colingrimes.midnight.scheduler.Scheduler;
import me.colingrimes.midnight.util.bukkit.Players;
import me.colingrimes.midnight.util.bukkit.Worlds;
import me.colingrimes.midnight.util.text.Text;
import me.colingrimes.root.Root;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import javax.annotation.Nonnull;

public class WorldsCreate implements Command<Root> {

	@Override
	public void execute(@Nonnull Root plugin, @Nonnull Sender sender, @Nonnull ArgumentList args) {
		String name = args.getFirst();
		if (Worlds.exists(name)) {
			sender.message(Text.color("&cWorld already exists!"));
			return;
		}

		World world = Worlds.create(new WorldCreator(name));
		sender.message(Text.color("&aCreating the world!"));
		Scheduler.sync().runRepeating((task) -> {
			if (Worlds.exists(name)) {
				sender.player().teleport(new Location(world, 0, 80, 0, 0, 45));
				task.stop();
			}
		}, 20L, 20L);
	}

	@Override
	public void configureProperties(@Nonnull CommandProperties properties) {
		properties.setArgumentsRequired(1);
		properties.setPlayerRequired(true);
	}
}
