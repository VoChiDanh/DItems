package net.danh.ditems.API;

import net.danh.ditems.Manager.NBTItem;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

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
                }
            }
            if (t instanceof Monster || t instanceof Animals) {
                if (new NBTItem(item).hasDoubleStats("DAMAGE")) {
                    int f_damage = (int) Formula.getCritFormula(k);
                    e.setDamage(Math.max(f_damage, 0));
                }
            }
        }
        if (e.getDamager() instanceof Projectile) {
            Projectile a = (Projectile) e.getDamager();
            if (a instanceof Arrow) {
                if (a.getShooter() instanceof Player) {
                    Player k = (Player) a.getShooter();
                    Entity t = e.getEntity();
                    ItemStack item = k.getInventory().getItemInMainHand();
                    if (t instanceof Player) {
                        if (new NBTItem(item).hasDoubleStats("DAMAGE")) {
                            int f_damage = (int) Formula.getCritFormula(k);
                            e.setDamage(Math.max(f_damage, 0));
                        }
                    }
                    if (t instanceof Monster || t instanceof Animals) {
                        if (new NBTItem(item).hasDoubleStats("DAMAGE")) {
                            int f_damage = (int) Formula.getCritFormula(k);
                            e.setDamage(Math.max(f_damage, 0));
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
                }
            }
            if (t instanceof Monster || t instanceof Animals) {
                if (new NBTItem(item).hasDoubleStats("DAMAGE")) {
                    int f_damage = (int) Formula.getNormalFormula(k);
                    e.setDamage(Math.max(f_damage, 0));
                }
            }
        }
        if (e.getDamager() instanceof Projectile) {
            Projectile a = (Projectile) e.getDamager();
            if (a instanceof Arrow) {
                if (a.getShooter() instanceof Player) {
                    Player k = (Player) a.getShooter();
                    Entity t = e.getEntity();
                    ItemStack item = k.getInventory().getItemInMainHand();
                    if (t instanceof Player) {
                        if (new NBTItem(item).hasDoubleStats("DAMAGE")) {
                            int f_damage = (int) Formula.getNormalFormula(k);
                            e.setDamage(Math.max(f_damage, 0));
                        }
                    }
                    if (t instanceof Monster || t instanceof Animals) {
                        if (new NBTItem(item).hasDoubleStats("DAMAGE")) {
                            int f_damage = (int) Formula.getNormalFormula(k);
                            e.setDamage(Math.max(f_damage, 0));
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
                }
            }
        }
        if (e.getDamager() instanceof Projectile) {
            Projectile a = (Projectile) e.getDamager();
            if (a instanceof Arrow) {
                if (a.getShooter() instanceof Player) {
                    Player k = (Player) a.getShooter();
                    Entity t = e.getEntity();
                    ItemStack item = k.getInventory().getItemInMainHand();
                    if (t instanceof Player) {
                        if (new NBTItem(item).hasDoubleStats("DAMAGE")) {
                            int f_damage = (int) Formula.getPvPCritFormula(k);
                            e.setDamage(Math.max(f_damage, 0));
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
                }
            }
        }
        if (e.getDamager() instanceof Projectile) {
            Projectile a = (Projectile) e.getDamager();
            if (a instanceof Arrow) {
                if (a.getShooter() instanceof Player) {
                    Player k = (Player) a.getShooter();
                    Entity t = e.getEntity();
                    ItemStack item = k.getInventory().getItemInMainHand();
                    if (t instanceof Player) {
                        if (new NBTItem(item).hasDoubleStats("DAMAGE")) {
                            int f_damage = (int) Formula.getPvPNormalFormula(k);
                            e.setDamage(Math.max(f_damage, 0));
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
                }
            }
        }
        if (e.getDamager() instanceof Projectile) {
            Projectile a = (Projectile) e.getDamager();
            if (a instanceof Arrow) {
                if (a.getShooter() instanceof Player) {
                    Player k = (Player) a.getShooter();
                    Entity t = e.getEntity();
                    ItemStack item = k.getInventory().getItemInMainHand();
                    if (t instanceof Monster || t instanceof Animals) {
                        if (new NBTItem(item).hasDoubleStats("DAMAGE")) {
                            int f_damage = (int) Formula.getPvECritFormula(k);
                            e.setDamage(Math.max(f_damage, 0));
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
                }
            }
        }
        if (e.getDamager() instanceof Projectile) {
            Projectile a = (Projectile) e.getDamager();
            if (a instanceof Arrow) {
                if (a.getShooter() instanceof Player) {
                    Player k = (Player) a.getShooter();
                    Entity t = e.getEntity();
                    ItemStack item = k.getInventory().getItemInMainHand();
                    if (t instanceof Monster || t instanceof Animals) {
                        if (new NBTItem(item).hasDoubleStats("DAMAGE")) {
                            int f_damage = (int) Formula.getPvENormalFormula(k);
                            e.setDamage(Math.max(f_damage, 0));
                        }
                    }
                }
            }
        }
    }
}