package me.colingrimes.root;

import me.colingrimes.midnight.Midnight;
import me.colingrimes.midnight.util.Common;
import me.colingrimes.midnight.util.text.Text;
import me.colingrimes.root.listener.Listeners;
import org.bukkit.event.Listener;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.IntStream;

public class Root extends Midnight {

	@Override
	protected void enable() {
		IntStream.range(0, 3).forEach(__ -> Common.broadcast(""));
		Common.broadcast(Text.color("&c&m---------------------------------------------------"));
		Common.broadcast("");
		Common.broadcast(Text.color("&4&lRoot &cis active. This should &4&l&oNOT&c be on a production server."));
		Common.broadcast("");
		Common.broadcast(Text.color("&c&m---------------------------------------------------"));
		IntStream.range(0, 3).forEach(__ -> Common.broadcast(""));
	}

	@Override
	protected void registerListeners(@Nonnull List<Listener> listeners) {
		listeners.add(new Listeners());
	}
}
