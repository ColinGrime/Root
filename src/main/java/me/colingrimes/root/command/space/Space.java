package me.colingrimes.root.command.space;

import me.colingrimes.midnight.command.Command;
import me.colingrimes.midnight.command.handler.util.ArgumentList;
import me.colingrimes.midnight.command.handler.util.CommandProperties;
import me.colingrimes.midnight.command.handler.util.Sender;
import me.colingrimes.midnight.util.bukkit.Locations;
import me.colingrimes.root.Root;
import org.bukkit.Location;
import org.bukkit.Material;

import javax.annotation.Nonnull;

public class Space implements Command<Root> {

	@Override
	public void execute(@Nonnull Root plugin, @Nonnull Sender sender, @Nonnull ArgumentList args) {
		int space = args.getIntOrDefault(0, 10);
		Location location = sender.location();
		Location corner1 = location.clone().add(+space, +space, +space);
		Location corner2 = location.clone().add(-space,      0, -space);
		Locations.between(corner1, corner2).forEach(l -> l.getBlock().setType(Material.AIR));
	}

	@Override
	public void configureProperties(@Nonnull CommandProperties properties) {
		properties.setAliases("room");
		properties.setPlayerRequired(true);
	}
}
