package me.colingrimes.root.command.see;

import me.colingrimes.midnight.command.Command;
import me.colingrimes.midnight.command.handler.util.ArgumentList;
import me.colingrimes.midnight.command.handler.util.Sender;
import me.colingrimes.midnight.scheduler.Scheduler;
import me.colingrimes.midnight.util.bukkit.Worlds;
import me.colingrimes.midnight.util.text.Text;
import me.colingrimes.root.Root;
import org.bukkit.World;

import javax.annotation.Nonnull;

public class See implements Command<Root> {

	private boolean toggle = true;

	public See() {
		Scheduler.sync().runRepeating(() -> {
			World world = Worlds.get("world").orElseThrow();
			if (toggle) {
				world.setTime(0);
				world.setClearWeatherDuration(Integer.MAX_VALUE);
			}
		}, 0L, 20L * 30);
	}

	@Override
	public void execute(@Nonnull Root plugin, @Nonnull Sender sender, @Nonnull ArgumentList args) {
		World world = Worlds.get("world").orElseThrow();
		toggle = !toggle;
		if (toggle) {
			world.setTime(0);
			world.setClearWeatherDuration(Integer.MAX_VALUE);
			sender.message(Text.color("&cForever day / sun."));
		} else {
			world.setClearWeatherDuration(Integer.MAX_VALUE);
			sender.message(Text.color("&cIt can become night / rain again."));
		}
	}
}
