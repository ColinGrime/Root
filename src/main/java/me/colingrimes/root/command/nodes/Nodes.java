package me.colingrimes.root.command.nodes;

import me.colingrimes.midnight.command.Command;
import me.colingrimes.midnight.command.handler.util.ArgumentList;
import me.colingrimes.midnight.command.handler.util.CommandProperties;
import me.colingrimes.midnight.command.handler.util.Sender;
import me.colingrimes.midnight.util.text.Markdown;
import me.colingrimes.midnight.util.text.Text;
import me.colingrimes.root.Root;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;

import javax.annotation.Nonnull;
import java.util.Collection;

public class Nodes implements Command<Root> {

    @Override
    public void execute(@Nonnull Root plugin, @Nonnull Sender sender, @Nonnull ArgumentList args) {
        LuckPerms lp = LuckPermsProvider.get();
        User user = lp.getUserManager().getUser(sender.player().getUniqueId());
        if (user == null) {
            return;
        }

        if (args.isEmpty()) {
            Collection<Node> nodeList = user.data().toCollection();
            sender.message("");
            sender.message(Text.color("&b" + sender.player().getName() + "'s Permissions:  &7(&f" + nodeList.size() + " &7entries)"));
            for (Node node : user.data().toCollection()) {
                String perm = "&3> &a" + node.getKey() + (node.getValue() ? "" : " &c(false)");
                Markdown.of("[" + perm + "](/lp_user_" + sender.player().getName() + "_permission_unset_" + node.getKey() + " &cClick to remove this permission.)").send(sender);
            }
        } else {
            String perm = args.getFirst();
            user.data().add(Node.builder(perm).value(true).build());
            lp.getUserManager().saveUser(user);
            sender.message(Text.color("&7Added permission: &a" + perm));
        }
    }

    @Override
    public void configureProperties(@Nonnull CommandProperties properties) {
        properties.setAliases("node");
        properties.setPlayerRequired(true);
    }
}
