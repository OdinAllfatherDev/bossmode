# BossMode
- BossMode is a plugin where you can create custom bosses, or edit a boss. With many features

# Features
- 100% Customizable
- Everyone Boss have a custom AI
- Create spawner (100% Customizable)
- Easy GUI
- Special Attacks
- Natural spawning (in a biome)
- When a mob spawn, there is a chance that it becomes a boss
- Easy API

# Commands
- /boss - show all commands
- /boss editor - open the boss editor
- /boss list - show all bosses
- /boss spawn <id> - spawn a boss manual
- /boss spawner <id> - get a spawner from the boss, if the spawner exist
Permissions are bossmode.admin or bossmoded.*

# API
If you want to create a new boss, extend from the abstract class APIBoss:
`
package de.encryptdev.bossmode.test;

import de.encryptdev.bossmode.boss.APIBoss;
import de.encryptdev.bossmode.boss.util.BossSettings;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

public class MyBoss extends APIBoss {
 
    public MyBoss(BossSettings bossSettings, int livingId, String name, Location spawnLocation, EntityType type) {
        super(bossSettings, livingId, name, spawnLocation, type);
    }
}
`

Now you can override all methodes from the class

The API have events:
`
package de.encryptdev.bossmode.test;

import de.encryptdev.bossmode.boss.IBoss;
import de.encryptdev.bossmode.boss.event.BossDamageEvent;
import de.encryptdev.bossmode.boss.event.BossDeathEvent;
import de.encryptdev.bossmode.boss.event.BossSpawnEvent;
import de.encryptdev.bossmode.boss.event.BossUseSpecialAttackEvent;
import org.bukkit.event.EventHandler;

public class BossEvent {
 
    @EventHandler
    public void onSpawn(BossSpawnEvent event) {
        IBoss iBoss = event.getBoss();
    }
 
    @EventHandler
    public void onDamage(BossDamageEvent event) {
        IBoss iBoss = event.getBoss();
    }
 
    @EventHandler
    public void onDeath(BossDeathEvent event) {
        IBoss iBoss = event.getBoss();
    }
 
    @EventHandler
    public void onUseSpecialAttack(BossUseSpecialAttackEvent event) {
        IBoss iBoss = event.getBoss();
    }
 
}
`
Or use the BossManager class:
`
package de.encryptdev.bossmode.test;

import de.encryptdev.bossmode.boss.util.BossManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MyPlugin extends JavaPlugin {

    private BossManager bossManager;
 
    @Override
    public void onEnable() {
        this.bossManager = BossMode.getInstance().getBossManager();
    }
}
 
 `
The interface IBoss is in everyone boss implemented

# Help?
Join my [Discord](https://discord.gg/NUPDFR7)!
