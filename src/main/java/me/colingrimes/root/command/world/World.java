package me.colingrimes.root.command.world;

import me.colingrimes.midnight.command.Command;
import me.colingrimes.midnight.command.handler.util.ArgumentList;
import me.colingrimes.midnight.command.handler.util.CommandProperties;
import me.colingrimes.midnight.command.handler.util.Sender;
import me.colingrimes.midnight.util.bukkit.Worlds;
import me.colingrimes.midnight.util.text.Text;
import me.colingrimes.root.Root;
import org.bukkit.Location;

import javax.annotation.Nonnull;
import java.util.Optional;

public class World implements Command<Root> {

	@Override
	public void execute(@Nonnull Root plugin, @Nonnull Sender sender, @Nonnull ArgumentList args) {
		Optional<org.bukkit.World> world = Worlds.get("world");
		if (world.isEmpty()) {
			sender.message(Text.color("&cOverworld does not exist."));
			return;
		}

		double x = sender.world().getEnvironment() == org.bukkit.World.Environment.NETHER ? sender.x() * 8 : sender.x();
		double y = sender.y();
		double z = sender.world().getEnvironment() == org.bukkit.World.Environment.NETHER ? sender.z() * 8 : sender.z();
		sender.player().teleport(new Location(world.get(), x, y, z, sender.location().getYaw(), sender.location().getPitch()));
	}

	@Override
	public void configureProperties(@Nonnull CommandProperties properties) {
		properties.setPlayerRequired(true);
	}
}
