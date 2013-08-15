package com.github.idragonfire.RemoteEntitesTestPlugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import de.kumpelblase2.remoteentities.EntityManager;
import de.kumpelblase2.remoteentities.RemoteEntities;
import de.kumpelblase2.remoteentities.api.RemoteEntity;
import de.kumpelblase2.remoteentities.api.RemoteEntityType;
import de.kumpelblase2.remoteentities.api.thinking.goals.DesireLookAtNearest;
import de.kumpelblase2.remoteentities.api.thinking.goals.DesireWanderAround;
import de.kumpelblase2.remoteentities.persistence.serializers.YMLSerializer;

public class RE_Test_Plugin extends JavaPlugin {
    private EntityManager manager;

    @Override
    public void onEnable() {
        manager = RemoteEntities.createManager(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command,
            String label, String[] args) {
        try {

            if (!(sender instanceof Player)) {
                sender.sendMessage("not supported");
                return false;
            }
            Player player = (Player) sender;
            String cmd = args[0];
            if (cmd.equalsIgnoreCase("h")) {
                spawnHuman(player.getLocation(), "test-h");
            } else if (cmd.equalsIgnoreCase("h2")) {
                spawnHumanWander(player.getLocation(), "test-h2");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public void spawnHuman(Location loc, String name) {
        RemoteEntity entity = manager.createNamedEntity(RemoteEntityType.Human,
                loc, name, true);
        entity.getMind().addMovementDesire(
                new DesireLookAtNearest(Player.class, 8), 0);
    }

    public void spawnHumanWander(Location loc, String name) {
        RemoteEntity entity = manager.createNamedEntity(RemoteEntityType.Human,
                loc, name, true);
        entity.getMind().addMovementDesire(new DesireWanderAround(), 0);
    }
}
