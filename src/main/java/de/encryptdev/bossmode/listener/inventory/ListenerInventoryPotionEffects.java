package de.encryptdev.bossmode.listener.inventory;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.boss.util.BossEditor;
import de.encryptdev.bossmode.util.CheckNull;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by EncryptDev
 */
public class ListenerInventoryPotionEffects implements Listener {

    @EventHandler
    public void on(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (CheckNull.checkNull(event))
            return;

        if (event.getInventory().getName().equalsIgnoreCase("§ePotion Effects")) {
            event.setCancelled(true);
            String itemName = event.getCurrentItem().getItemMeta().getDisplayName();

            switch (itemName) {
                case "§eRegeneration":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().potionEffectSettings("§eRegeneration"));
                    break;
                case "§eSwiftness":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().potionEffectSettings("§eSwiftness"));
                    break;
                case "§eFire Resistance":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().potionEffectSettings("§eFire Resistance"));
                    break;
                case "§eHealing":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().potionEffectSettings("§eHealing"));
                    break;
                case "§eStrength":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().potionEffectSettings("§eStrength"));
                    break;
                case "§eSlowness":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().potionEffectSettings("§eSlowness"));
                    break;
                case "§eInvisibility":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().potionEffectSettings("§eInvisibility"));
                    break;
                case "§ePoison":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().potionEffectSettings("§ePoison"));
                    break;
                case "§eClear":
                    BossMode.getInstance().getBossManager().getBossEditor(player).clearPotionEffect();
                    player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(BossMode.getInstance().getBossManager().getBossEditor(player).isNaturalSpawn()));
                    break;
                case "§eBack":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().bossSettings(BossMode.getInstance().getBossManager().getBossEditor(player).isNaturalSpawn()));
                    break;
                case "§eBlindness":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().potionEffectSettings("§eBlindness"));
                    break;
                case "§eNausea":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().potionEffectSettings("§eNausea"));
                    break;
                case "§eHunger":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().potionEffectSettings("§eHunger"));
                    break;
                case "§eWither":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().potionEffectSettings("§eWither"));
                    break;
            }
        } else if (event.getInventory().getTitle().startsWith("§eSettings")) {
            event.setCancelled(true);
            String title = event.getInventory().getTitle();
            String potionEffect = title.split(" ")[2].trim();

            String itemName = event.getCurrentItem().getItemMeta().getDisplayName();

            BossEditor editor = BossMode.getInstance().getBossManager().getBossEditor(player);

            switch (itemName) {
                case "§eTier 2":
                    switch (potionEffect) {
                        case "§eRegeneration":
                            editor.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100000, 1));
                            player.openInventory(BossMode.getInstance().getInventoryStorage().potionEffects());
                            break;
                        case "§eSwiftness":
                            editor.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100000, 1));
                            player.openInventory(BossMode.getInstance().getInventoryStorage().potionEffects());
                            break;
                        case "§eFire Resistance":
                            editor.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 100000, 1));
                            player.openInventory(BossMode.getInstance().getInventoryStorage().potionEffects());
                            break;
                        case "§ePoison":
                            editor.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100000, 1));
                            player.openInventory(BossMode.getInstance().getInventoryStorage().potionEffects());
                            break;
                        case "§eHealing":
                            editor.addPotionEffect(new PotionEffect(PotionEffectType.HARM, 100000, 1));
                            player.openInventory(BossMode.getInstance().getInventoryStorage().potionEffects());
                            break;
                        case "§eNight Vision":
                            editor.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 100000, 1));
                            player.openInventory(BossMode.getInstance().getInventoryStorage().potionEffects());
                            break;
                        case "§eStrength":
                            editor.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100000, 1));
                            player.openInventory(BossMode.getInstance().getInventoryStorage().potionEffects());
                            break;
                        case "§eSlowness":
                            editor.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100000, 1));
                            player.openInventory(BossMode.getInstance().getInventoryStorage().potionEffects());
                            break;
                        case "§eHarming":
                            editor.addPotionEffect(new PotionEffect(PotionEffectType.HARM, 100000, 1));
                            player.openInventory(BossMode.getInstance().getInventoryStorage().potionEffects());
                            break;
                        case "§eInvisibility":
                            editor.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 100000, 1));
                            player.openInventory(BossMode.getInstance().getInventoryStorage().potionEffects());
                            break;
                        case "§eBlindness":
                            editor.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100000, 1));
                            player.openInventory(BossMode.getInstance().getInventoryStorage().potionEffects());
                            break;
                        case "§eNausea":
                            editor.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 100000, 1));
                            player.openInventory(BossMode.getInstance().getInventoryStorage().potionEffects());
                            break;
                        case "§eHunger":
                            editor.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 100000, 1));
                            player.openInventory(BossMode.getInstance().getInventoryStorage().potionEffects());
                            break;
                        case "§eWither":
                            editor.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 100000, 1));
                            player.openInventory(BossMode.getInstance().getInventoryStorage().potionEffects());
                            break;
                    }
                    break;
                case "§eTier 1":
                    switch (potionEffect) {
                        case "§eRegeneration":
                            editor.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100000, 0));
                            player.openInventory(BossMode.getInstance().getInventoryStorage().potionEffects());
                            break;
                        case "§eSwiftness":
                            editor.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100000, 0));
                            player.openInventory(BossMode.getInstance().getInventoryStorage().potionEffects());
                            break;
                        case "§eFire Resistance":
                            editor.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 100000, 0));
                            player.openInventory(BossMode.getInstance().getInventoryStorage().potionEffects());
                            break;
                        case "§ePoison":
                            editor.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100000, 0));
                            player.openInventory(BossMode.getInstance().getInventoryStorage().potionEffects());
                            break;
                        case "§eHealing":
                            editor.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 100000, 0));
                            player.openInventory(BossMode.getInstance().getInventoryStorage().potionEffects());
                            break;
                        case "§eStrength":
                            editor.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100000, 0));
                            player.openInventory(BossMode.getInstance().getInventoryStorage().potionEffects());
                            break;
                        case "§eSlowness":
                            editor.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100000, 0));
                            player.openInventory(BossMode.getInstance().getInventoryStorage().potionEffects());
                            break;
                        case "§eInvisibility":
                            editor.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 100000, 0));
                            player.openInventory(BossMode.getInstance().getInventoryStorage().potionEffects());
                            break;
                        case "§eBlindness":
                            editor.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100000, 0));
                            player.openInventory(BossMode.getInstance().getInventoryStorage().potionEffects());
                            break;
                        case "§eNausea":
                            editor.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 100000, 0));
                            player.openInventory(BossMode.getInstance().getInventoryStorage().potionEffects());
                            break;
                        case "§eHunger":
                            editor.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 100000, 0));
                            player.openInventory(BossMode.getInstance().getInventoryStorage().potionEffects());
                            break;
                        case "§eWither":
                            editor.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 100000, 0));
                            player.openInventory(BossMode.getInstance().getInventoryStorage().potionEffects());
                            break;
                    }
                    break;
                case "§eBack":
                    player.openInventory(BossMode.getInstance().getInventoryStorage().potionEffects());
                    break;
            }


        }

    }

}
