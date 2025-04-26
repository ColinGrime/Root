package me.colingrimes.root.command.worlds.teleport;

import me.colingrimes.midnight.command.Command;
import me.colingrimes.midnight.command.handler.util.ArgumentList;
import me.colingrimes.midnight.command.handler.util.CommandProperties;
import me.colingrimes.midnight.command.handler.util.Sender;
import me.colingrimes.midnight.scheduler.Scheduler;
import me.colingrimes.midnight.util.bukkit.Worlds;
import me.colingrimes.midnight.util.text.Text;
import me.colingrimes.root.Root;
import org.bukkit.Location;
import org.bukkit.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class WorldsTeleport implements Command<Root> {

	@Override
	public void execute(@Nonnull Root plugin, @Nonnull Sender sender, @Nonnull ArgumentList args) {
		if (!Worlds.exists(args.getFirst())) {
			sender.message(Text.color("&cWorld does not exist!"));
			return;
		}

		Optional<World> world = Worlds.load(args.getFirst());
		if (world.isEmpty()) {
			sender.message(Text.color("&cWorld does not exist!"));
			return;
		}

		Scheduler.sync().runRepeating((task) -> {
			if (Worlds.get(args.getFirst()).isPresent()) {
				sender.message(Text.color("&aTeleported to the world!"));
				sender.player().teleport(new Location(world.get(), 0, -50, 0, 0, 45));
				task.stop();
			}
		}, 20L, 20L);
	}

	@Nullable
	@Override
	public List<String> tabComplete(@Nonnull Root plugin, @Nonnull Sender sender, @Nonnull ArgumentList args) {
		if (args.size() == 1) {
			return Worlds.all().stream().filter(w -> w.contains(args.getFirst())).toList();
		}
		return null;
	}

	@Override
	public void configureProperties(@Nonnull CommandProperties properties) {
		properties.setAliases("tp");
		properties.setPlayerRequired(true);
	}
}
