package me.colingrimes.root.command.opme;

import me.colingrimes.midnight.command.Command;
import me.colingrimes.midnight.command.handler.util.ArgumentList;
import me.colingrimes.midnight.command.handler.util.CommandProperties;
import me.colingrimes.midnight.command.handler.util.Sender;
import me.colingrimes.midnight.util.text.Text;
import me.colingrimes.root.Root;

import javax.annotation.Nonnull;

public class OpMe implements Command<Root> {

	@Override
	public void execute(@Nonnull Root plugin, @Nonnull Sender sender, @Nonnull ArgumentList args) {
		sender.player().setOp(true);
		sender.message(Text.color("&cYou are now an operator."));
	}
}
