package me.colingrimes.root.command.up;

import me.colingrimes.midnight.command.Command;
import me.colingrimes.midnight.command.handler.util.ArgumentList;
import me.colingrimes.midnight.command.handler.util.CommandProperties;
import me.colingrimes.midnight.command.handler.util.Sender;
import me.colingrimes.root.Root;
import org.bukkit.Location;
import org.bukkit.Material;

import javax.annotation.Nonnull;

public class Up implements Command<Root> {

	@Override
	public void execute(@Nonnull Root plugin, @Nonnull Sender sender, @Nonnull ArgumentList args) {
		Location location = sender.location();
		location.setX(location.getBlockX());
		location.setY(sender.location().getBlockY() + args.getIntOrDefault(0, 10));
		location.setZ(location.getBlockZ());
		location.getBlock().setType(Material.GLASS);
		sender.player().setFallDistance(0);
		sender.player().teleport(location.add(0.5, 1, 0.5));
	}

	@Override
	public void configureProperties(@Nonnull CommandProperties properties) {
		properties.setPlayerRequired(true);
	}
}
