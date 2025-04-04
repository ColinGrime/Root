package me.colingrimes.root.command.teleport;

import me.colingrimes.midnight.command.Command;
import me.colingrimes.midnight.command.handler.util.ArgumentList;
import me.colingrimes.midnight.command.handler.util.CommandProperties;
import me.colingrimes.midnight.command.handler.util.Sender;
import me.colingrimes.midnight.util.bukkit.Worlds;
import me.colingrimes.midnight.util.text.Text;
import me.colingrimes.root.Root;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import javax.annotation.Nonnull;
import java.util.Optional;

public class Teleport implements Command<Root> {

	@Override
	public void execute(@Nonnull Root plugin, @Nonnull Sender sender, @Nonnull ArgumentList args) {
		if (args.size() <= 1) {
			Location eye = sender.player().getEyeLocation();
			RayTraceResult result = sender.world().rayTrace(eye, eye.getDirection(), 200, FluidCollisionMode.NEVER, true, 0, e -> !e.equals(sender.player()));
			if (result != null) {
				Vector hit = result.getHitPosition();
				sender.player().teleport(new Location(sender.world(), hit.getX(), hit.getY(), hit.getZ(), sender.location().getYaw(), sender.location().getPitch()));
			} else {
				sender.message(Text.color("&cThere is nowhere to teleport to."));
			}
		} else if (args.size() == 2) {
			double x = args.getDoubleOrDefault(0, sender.x());
			double z = args.getDoubleOrDefault(1, sender.z());
			sender.player().teleport(new Location(sender.world(), x, sender.y(), z, sender.location().getYaw(), sender.location().getPitch()));
		} else if (args.size() == 3) {
			double x = args.getDoubleOrDefault(0, sender.x());
			double y = args.getDoubleOrDefault(1, sender.y());
			double z = args.getDoubleOrDefault(2, sender.z());
			sender.player().teleport(new Location(sender.world(), x, y, z, sender.location().getYaw(), sender.location().getPitch()));
		} else {
			double x = args.getDoubleOrDefault(0, sender.x());
			double y = args.getDoubleOrDefault(1, sender.y());
			double z = args.getDoubleOrDefault(2, sender.z());
			String worldArg = args.getLowercase(3);
			worldArg = worldArg.equals("nether") ? "world_nether" : worldArg.equals("end") ? "world_the_end" : worldArg;

			Optional<World> world = Worlds.get(worldArg);
			if (world.isEmpty()) {
				sender.message(Text.color("&cWorld does not exist. Defaulting to your current world."));
			} else {
				sender.player().teleport(new Location(world.get(), x, y, z, sender.location().getYaw(), sender.location().getPitch()));
			}
		}
	}

	@Override
	public void configureProperties(@Nonnull CommandProperties properties) {
		properties.setAliases("tp");
		properties.setPlayerRequired(true);
	}
}
