package me.colingrimes.root.command.nether;

import me.colingrimes.midnight.command.Command;
import me.colingrimes.midnight.command.handler.util.ArgumentList;
import me.colingrimes.midnight.command.handler.util.CommandProperties;
import me.colingrimes.midnight.command.handler.util.Sender;
import me.colingrimes.midnight.util.bukkit.Worlds;
import me.colingrimes.midnight.util.text.Text;
import me.colingrimes.root.Root;
import org.bukkit.Location;
import org.bukkit.World;

import javax.annotation.Nonnull;
import java.util.Optional;

public class Nether implements Command<Root> {

	@Override
	public void execute(@Nonnull Root plugin, @Nonnull Sender sender, @Nonnull ArgumentList args) {
		Optional<World> nether = Worlds.get("world_nether");
		if (nether.isEmpty()) {
			sender.message(Text.color("&cNether does not exist."));
			return;
		}

		double x = sender.world().getEnvironment() == org.bukkit.World.Environment.NETHER ? sender.x() : sender.x() / 8;
		double y = sender.y();
		double z = sender.world().getEnvironment() == org.bukkit.World.Environment.NETHER ? sender.z() : sender.z() / 8;
		sender.player().teleport(new Location(nether.get(), x, y, z, sender.location().getYaw(), sender.location().getPitch()));
	}

	@Override
	public void configureProperties(@Nonnull CommandProperties properties) {
		properties.setPlayerRequired(true);
	}
}
