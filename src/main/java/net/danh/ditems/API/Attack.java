package net.danh.ditems.API;

import net.danh.dcore.Resource.Files;
import net.danh.dcore.Utils.Chat;
import net.danh.ditems.DItems;
import net.danh.ditems.Manager.NBTItem;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

import static net.danh.ditems.Listeners.DamageEvent.getRandomOffset;
import static net.danh.ditems.Listeners.DamageEvent.indicators;

public class Attack {

    public static void CritAttack(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            Player k = (Player) e.getDamager();
            Entity t = e.getEntity();
            ItemStack item = k.getInventory().getItemInMainHand();
            if (t instanceof Player) {
                if (new NBTItem(item).hasDoubleStats("DAMAGE")) {
                    int f_damage = (int) Formula.getCritFormula(k);
                    e.setDamage(Math.max(f_damage, 0));
                    if (new Files(DItems.getInstance(), "config").getConfig().getBoolean("INDICATORS.ENABLE")) {
                        Location loc = t.getLocation().clone().add(getRandomOffset(), 1, getRandomOffset());
                        t.getWorld().spawn(loc, ArmorStand.class, armorStand -> {
                            armorStand.setMarker(true);
                            armorStand.setVisible(false);
                            armorStand.setGravity(false);
                            armorStand.setSmall(true);
                            armorStand.setCustomNameVisible(true);
                            armorStand.setCustomName(Chat.colorize(Objects.requireNonNull(new Files(DItems.getInstance(), "config").getConfig().getString("INDICATORS.CRIT_ATTACK")).replaceAll("#damage#", String.valueOf(f_damage))));
                            indicators.put(armorStand, 30);
                        });
                    }
                }
            }
            if (t instanceof Monster || t instanceof Animals) {
                if (new NBTItem(item).hasDoubleStats("DAMAGE")) {
                    int f_damage = (int) Formula.getCritFormula(k);
                    e.setDamage(Math.max(f_damage, 0));
                    if (new Files(DItems.getInstance(), "config").getConfig().getBoolean("INDICATORS.ENABLE")) {
                        Location loc = t.getLocation().clone().add(getRandomOffset(), 1, getRandomOffset());
                        t.getWorld().spawn(loc, ArmorStand.class, armorStand -> {
                            armorStand.setMarker(true);
                            armorStand.setVisible(false);
                            armorStand.setGravity(false);
                            armorStand.setSmall(true);
                            armorStand.setCustomNameVisible(true);
                            armorStand.setCustomName(Chat.colorize(Objects.requireNonNull(new Files(DItems.getInstance(), "config").getConfig().getString("INDICATORS.CRIT_ATTACK")).replaceAll("#damage#", String.valueOf(f_damage))));
                            indicators.put(armorStand, 30);
                        });
                    }
                }
            }
        }
        if (e.getDamager() instanceof Projectile) {
            Projectile a = (Projectile) e.getDamager();
            if (a.getShooter() instanceof Player) {
                Player k = (Player) a.getShooter();
                Entity t = e.getEntity();
                ItemStack item = k.getInventory().getItemInMainHand();
                if (t instanceof Player) {
                    if (new NBTItem(item).hasDoubleStats("DAMAGE")) {
                        int f_damage = (int) Formula.getCritFormula(k);
                        e.setDamage(Math.max(f_damage, 0));
                        if (new Files(DItems.getInstance(), "config").getConfig().getBoolean("INDICATORS.ENABLE")) {
                            Location loc = t.getLocation().clone().add(getRandomOffset(), 1, getRandomOffset());
                            t.getWorld().spawn(loc, ArmorStand.class, armorStand -> {
                                armorStand.setMarker(true);
                                armorStand.setVisible(false);
                                armorStand.setGravity(false);
                                armorStand.setSmall(true);
                                armorStand.setCustomNameVisible(true);
                                armorStand.setCustomName(Chat.colorize(Objects.requireNonNull(new Files(DItems.getInstance(), "config").getConfig().getString("INDICATORS.CRIT_ATTACK")).replaceAll("#damage#", String.valueOf(f_damage))));
                                indicators.put(armorStand, 30);
                            });
                        }
                    }
                }
                if (t instanceof Monster || t instanceof Animals) {
                    if (new NBTItem(item).hasDoubleStats("DAMAGE")) {
                        int f_damage = (int) Formula.getCritFormula(k);
                        e.setDamage(Math.max(f_damage, 0));
                        if (new Files(DItems.getInstance(), "config").getConfig().getBoolean("INDICATORS.ENABLE")) {
                            Location loc = t.getLocation().clone().add(getRandomOffset(), 1, getRandomOffset());
                            t.getWorld().spawn(loc, ArmorStand.class, armorStand -> {
                                armorStand.setMarker(true);
                                armorStand.setVisible(false);
                                armorStand.setGravity(false);
                                armorStand.setSmall(true);
                                armorStand.setCustomNameVisible(true);
                                armorStand.setCustomName(Chat.colorize(Objects.requireNonNull(new Files(DItems.getInstance(), "config").getConfig().getString("INDICATORS.CRIT_ATTACK")).replaceAll("#damage#", String.valueOf(f_damage))));
                                indicators.put(armorStand, 30);
                            });
                        }
                    }
                }
            }
        }
    }

    public static void NormalAttack(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            Player k = (Player) e.getDamager();
            Entity t = e.getEntity();
            ItemStack item = k.getInventory().getItemInMainHand();
            if (t instanceof Player) {
                if (new NBTItem(item).hasDoubleStats("DAMAGE")) {
                    int f_damage = (int) Formula.getNormalFormula(k);
                    e.setDamage(Math.max(f_damage, 0));
                    if (new Files(DItems.getInstance(), "config").getConfig().getBoolean("INDICATORS.ENABLE")) {
                        Location loc = t.getLocation().clone().add(getRandomOffset(), 1, getRandomOffset());
                        t.getWorld().spawn(loc, ArmorStand.class, armorStand -> {
                            armorStand.setMarker(true);
                            armorStand.setVisible(false);
                            armorStand.setGravity(false);
                            armorStand.setSmall(true);
                            armorStand.setCustomNameVisible(true);
                            armorStand.setCustomName(Chat.colorize(Objects.requireNonNull(new Files(DItems.getInstance(), "config").getConfig().getString("INDICATORS.NORMAL_ATTACK")).replaceAll("#damage#", String.valueOf(f_damage))));
                            indicators.put(armorStand, 30);
                        });
                    }
                }
            }
            if (t instanceof Monster || t instanceof Animals) {
                if (new NBTItem(item).hasDoubleStats("DAMAGE")) {
                    int f_damage = (int) Formula.getNormalFormula(k);
                    e.setDamage(Math.max(f_damage, 0));
                    if (new Files(DItems.getInstance(), "config").getConfig().getBoolean("INDICATORS.ENABLE")) {
                        Location loc = t.getLocation().clone().add(getRandomOffset(), 1, getRandomOffset());
                        t.getWorld().spawn(loc, ArmorStand.class, armorStand -> {
                            armorStand.setMarker(true);
                            armorStand.setVisible(false);
                            armorStand.setGravity(false);
                            armorStand.setSmall(true);
                            armorStand.setCustomNameVisible(true);
                            armorStand.setCustomName(Chat.colorize(Objects.requireNonNull(new Files(DItems.getInstance(), "config").getConfig().getString("INDICATORS.NORMAL_ATTACK")).replaceAll("#damage#", String.valueOf(f_damage))));
                            indicators.put(armorStand, 30);
                        });
                    }
                }
            }
        }
        if (e.getDamager() instanceof Projectile) {
            Projectile a = (Projectile) e.getDamager();
            if (a.getShooter() instanceof Player) {
                Player k = (Player) a.getShooter();
                Entity t = e.getEntity();
                ItemStack item = k.getInventory().getItemInMainHand();
                if (t instanceof Player) {
                    if (new NBTItem(item).hasDoubleStats("DAMAGE")) {
                        int f_damage = (int) Formula.getNormalFormula(k);
                        e.setDamage(Math.max(f_damage, 0));
                        if (new Files(DItems.getInstance(), "config").getConfig().getBoolean("INDICATORS.ENABLE")) {
                            Location loc = t.getLocation().clone().add(getRandomOffset(), 1, getRandomOffset());
                            t.getWorld().spawn(loc, ArmorStand.class, armorStand -> {
                                armorStand.setMarker(true);
                                armorStand.setVisible(false);
                                armorStand.setGravity(false);
                                armorStand.setSmall(true);
                                armorStand.setCustomNameVisible(true);
                                armorStand.setCustomName(Chat.colorize(Objects.requireNonNull(new Files(DItems.getInstance(), "config").getConfig().getString("INDICATORS.NORMAL_ATTACK")).replaceAll("#damage#", String.valueOf(f_damage))));
                                indicators.put(armorStand, 30);
                            });
                        }
                    }
                }
                if (t instanceof Monster || t instanceof Animals) {
                    if (new NBTItem(item).hasDoubleStats("DAMAGE")) {
                        int f_damage = (int) Formula.getNormalFormula(k);
                        e.setDamage(Math.max(f_damage, 0));
                        if (new Files(DItems.getInstance(), "config").getConfig().getBoolean("INDICATORS.ENABLE")) {
                            Location loc = t.getLocation().clone().add(getRandomOffset(), 1, getRandomOffset());
                            t.getWorld().spawn(loc, ArmorStand.class, armorStand -> {
                                armorStand.setMarker(true);
                                armorStand.setVisible(false);
                                armorStand.setGravity(false);
                                armorStand.setSmall(true);
                                armorStand.setCustomNameVisible(true);
                                armorStand.setCustomName(Chat.colorize(Objects.requireNonNull(new Files(DItems.getInstance(), "config").getConfig().getString("INDICATORS.NORMAL_ATTACK")).replaceAll("#damage#", String.valueOf(f_damage))));
                                indicators.put(armorStand, 30);
                            });
                        }
                    }
                }
            }
        }
    }

    public static void PvPCritAttack(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            Player k = (Player) e.getDamager();
            Entity t = e.getEntity();
            ItemStack item = k.getInventory().getItemInMainHand();
            if (t instanceof Player) {
                if (new NBTItem(item).hasDoubleStats("DAMAGE")) {
                    int f_damage = (int) Formula.getPvPCritFormula(k);
                    e.setDamage(Math.max(f_damage, 0));
                    if (new Files(DItems.getInstance(), "config").getConfig().getBoolean("INDICATORS.ENABLE")) {
                        Location loc = t.getLocation().clone().add(getRandomOffset(), 1, getRandomOffset());
                        t.getWorld().spawn(loc, ArmorStand.class, armorStand -> {
                            armorStand.setMarker(true);
                            armorStand.setVisible(false);
                            armorStand.setGravity(false);
                            armorStand.setSmall(true);
                            armorStand.setCustomNameVisible(true);
                            armorStand.setCustomName(Chat.colorize(Objects.requireNonNull(new Files(DItems.getInstance(), "config").getConfig().getString("INDICATORS.CRIT_ATTACK")).replaceAll("#damage#", String.valueOf(f_damage))));
                            indicators.put(armorStand, 30);
                        });
                    }
                }
            }
        }
        if (e.getDamager() instanceof Projectile) {
            Projectile a = (Projectile) e.getDamager();
            if (a.getShooter() instanceof Player) {
                Player k = (Player) a.getShooter();
                Entity t = e.getEntity();
                ItemStack item = k.getInventory().getItemInMainHand();
                if (t instanceof Player) {
                    if (new NBTItem(item).hasDoubleStats("DAMAGE")) {
                        int f_damage = (int) Formula.getPvPCritFormula(k);
                        e.setDamage(Math.max(f_damage, 0));
                        if (new Files(DItems.getInstance(), "config").getConfig().getBoolean("INDICATORS.ENABLE")) {
                            Location loc = t.getLocation().clone().add(getRandomOffset(), 1, getRandomOffset());
                            t.getWorld().spawn(loc, ArmorStand.class, armorStand -> {
                                armorStand.setMarker(true);
                                armorStand.setVisible(false);
                                armorStand.setGravity(false);
                                armorStand.setSmall(true);
                                armorStand.setCustomNameVisible(true);
                                armorStand.setCustomName(Chat.colorize(Objects.requireNonNull(new Files(DItems.getInstance(), "config").getConfig().getString("INDICATORS.CRIT_ATTACK")).replaceAll("#damage#", String.valueOf(f_damage))));
                                indicators.put(armorStand, 30);
                            });
                        }
                    }
                }
            }
        }
    }

    public static void PvPNormalAttack(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            Player k = (Player) e.getDamager();
            Entity t = e.getEntity();
            ItemStack item = k.getInventory().getItemInMainHand();
            if (t instanceof Player) {
                if (new NBTItem(item).hasDoubleStats("DAMAGE")) {
                    int f_damage = (int) Formula.getPvPNormalFormula(k);
                    e.setDamage(Math.max(f_damage, 0));
                    if (new Files(DItems.getInstance(), "config").getConfig().getBoolean("INDICATORS.ENABLE")) {
                        Location loc = t.getLocation().clone().add(getRandomOffset(), 1, getRandomOffset());
                        t.getWorld().spawn(loc, ArmorStand.class, armorStand -> {
                            armorStand.setMarker(true);
                            armorStand.setVisible(false);
                            armorStand.setGravity(false);
                            armorStand.setSmall(true);
                            armorStand.setCustomNameVisible(true);
                            armorStand.setCustomName(Chat.colorize(Objects.requireNonNull(new Files(DItems.getInstance(), "config").getConfig().getString("INDICATORS.NORMAL_ATTACK")).replaceAll("#damage#", String.valueOf(f_damage))));
                            indicators.put(armorStand, 30);
                        });
                    }
                }
            }
        }
        if (e.getDamager() instanceof Projectile) {
            Projectile a = (Projectile) e.getDamager();
            if (a.getShooter() instanceof Player) {
                Player k = (Player) a.getShooter();
                Entity t = e.getEntity();
                ItemStack item = k.getInventory().getItemInMainHand();
                if (t instanceof Player) {
                    if (new NBTItem(item).hasDoubleStats("DAMAGE")) {
                        int f_damage = (int) Formula.getPvPNormalFormula(k);
                        e.setDamage(Math.max(f_damage, 0));
                        if (new Files(DItems.getInstance(), "config").getConfig().getBoolean("INDICATORS.ENABLE")) {
                            Location loc = t.getLocation().clone().add(getRandomOffset(), 1, getRandomOffset());
                            t.getWorld().spawn(loc, ArmorStand.class, armorStand -> {
                                armorStand.setMarker(true);
                                armorStand.setVisible(false);
                                armorStand.setGravity(false);
                                armorStand.setSmall(true);
                                armorStand.setCustomNameVisible(true);
                                armorStand.setCustomName(Chat.colorize(Objects.requireNonNull(new Files(DItems.getInstance(), "config").getConfig().getString("INDICATORS.NORMAL_ATTACK")).replaceAll("#damage#", String.valueOf(f_damage))));
                                indicators.put(armorStand, 30);
                            });
                        }
                    }
                }
            }
        }
    }

    public static void PvECritAttack(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            Player k = (Player) e.getDamager();
            Entity t = e.getEntity();
            ItemStack item = k.getInventory().getItemInMainHand();
            if (t instanceof Monster || t instanceof Animals) {
                if (new NBTItem(item).hasDoubleStats("DAMAGE")) {
                    int f_damage = (int) Formula.getPvECritFormula(k);
                    e.setDamage(Math.max(f_damage, 0));
                    if (new Files(DItems.getInstance(), "config").getConfig().getBoolean("INDICATORS.ENABLE")) {
                        Location loc = t.getLocation().clone().add(getRandomOffset(), 1, getRandomOffset());
                        t.getWorld().spawn(loc, ArmorStand.class, armorStand -> {
                            armorStand.setMarker(true);
                            armorStand.setVisible(false);
                            armorStand.setGravity(false);
                            armorStand.setSmall(true);
                            armorStand.setCustomNameVisible(true);
                            armorStand.setCustomName(Chat.colorize(Objects.requireNonNull(new Files(DItems.getInstance(), "config").getConfig().getString("INDICATORS.CRIT_ATTACK")).replaceAll("#damage#", String.valueOf(f_damage))));
                            indicators.put(armorStand, 30);
                        });
                    }
                }
            }
        }
        if (e.getDamager() instanceof Projectile) {
            Projectile a = (Projectile) e.getDamager();
            if (a.getShooter() instanceof Player) {
                Player k = (Player) a.getShooter();
                Entity t = e.getEntity();
                ItemStack item = k.getInventory().getItemInMainHand();
                if (t instanceof Monster || t instanceof Animals) {
                    if (new NBTItem(item).hasDoubleStats("DAMAGE")) {
                        int f_damage = (int) Formula.getPvECritFormula(k);
                        e.setDamage(Math.max(f_damage, 0));
                        if (new Files(DItems.getInstance(), "config").getConfig().getBoolean("INDICATORS.ENABLE")) {
                            Location loc = t.getLocation().clone().add(getRandomOffset(), 1, getRandomOffset());
                            t.getWorld().spawn(loc, ArmorStand.class, armorStand -> {
                                armorStand.setMarker(true);
                                armorStand.setVisible(false);
                                armorStand.setGravity(false);
                                armorStand.setSmall(true);
                                armorStand.setCustomNameVisible(true);
                                armorStand.setCustomName(Chat.colorize(Objects.requireNonNull(new Files(DItems.getInstance(), "config").getConfig().getString("INDICATORS.CRIT_ATTACK")).replaceAll("#damage#", String.valueOf(f_damage))));
                                indicators.put(armorStand, 30);
                            });
                        }
                    }
                }
            }
        }
    }

    public static void PvENormalAttack(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            Player k = (Player) e.getDamager();
            Entity t = e.getEntity();
            ItemStack item = k.getInventory().getItemInMainHand();
            if (t instanceof Monster || t instanceof Animals) {
                if (new NBTItem(item).hasDoubleStats("DAMAGE")) {
                    int f_damage = (int) Formula.getPvENormalFormula(k);
                    e.setDamage(Math.max(f_damage, 0));
                    if (new Files(DItems.getInstance(), "config").getConfig().getBoolean("INDICATORS.ENABLE")) {
                        Location loc = t.getLocation().clone().add(getRandomOffset(), 1, getRandomOffset());
                        t.getWorld().spawn(loc, ArmorStand.class, armorStand -> {
                            armorStand.setMarker(true);
                            armorStand.setVisible(false);
                            armorStand.setGravity(false);
                            armorStand.setSmall(true);
                            armorStand.setCustomNameVisible(true);
                            armorStand.setCustomName(Chat.colorize(Objects.requireNonNull(new Files(DItems.getInstance(), "config").getConfig().getString("INDICATORS.NORMAL_ATTACK")).replaceAll("#damage#", String.valueOf(f_damage))));
                            indicators.put(armorStand, 30);
                        });
                    }
                }
            }
        }
        if (e.getDamager() instanceof Projectile) {
            Projectile a = (Projectile) e.getDamager();
            if (a.getShooter() instanceof Player) {
                Player k = (Player) a.getShooter();
                Entity t = e.getEntity();
                ItemStack item = k.getInventory().getItemInMainHand();
                if (t instanceof Monster || t instanceof Animals) {
                    if (new NBTItem(item).hasDoubleStats("DAMAGE")) {
                        int f_damage = (int) Formula.getPvENormalFormula(k);
                        e.setDamage(Math.max(f_damage, 0));
                        if (new Files(DItems.getInstance(), "config").getConfig().getBoolean("INDICATORS.ENABLE")) {
                            Location loc = t.getLocation().clone().add(getRandomOffset(), 1, getRandomOffset());
                            t.getWorld().spawn(loc, ArmorStand.class, armorStand -> {
                                armorStand.setMarker(true);
                                armorStand.setVisible(false);
                                armorStand.setGravity(false);
                                armorStand.setSmall(true);
                                armorStand.setCustomNameVisible(true);
                                armorStand.setCustomName(Chat.colorize(Objects.requireNonNull(new Files(DItems.getInstance(), "config").getConfig().getString("INDICATORS.NORMAL_ATTACK")).replaceAll("#damage#", String.valueOf(f_damage))));
                                indicators.put(armorStand, 30);
                            });
                        }
                    }
                }
            }
        }
    }
}
