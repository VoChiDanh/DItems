# DItems [1.8 - 1.19.x]

### Simple plugins to make item with custom stats

# Commands

```
    - "&9/ditems setname [name] - Set name"
    - "&9/ditems reload - Reload file"
    - "&9/ditems show [stats] - Show amount of stats in hand"
    - "&9/ditems stats [stats] [number] - Set stats of item in hand"
    - "&9/ditems addlore [lore] - Add lore"
    - "&9/ditems removelore [line] - Remove lore"
    - "&9/ditems setlore [line] [name] - Set lore"
    - "&9/ditems help - Show help"
    - "&9/ditems addenchant [Enchant] [Level] - Add enchant"
    - "&9/ditems removeenchant [Enchant] - Remove enchant"
    - "&9/ditems save [Key] - Save items"
    - "&9/ditems unbreakable [true/false] - Set item unbreakable"
    - "&9/ditems load [key] [amount] - Load items from file"
    - "&9/ditems setflag [flag] - Add flag"
    - "&9/ditems removeflag [flag] - Remove flag"
```

# API

## NBTItems

```
NBTItem nbt = new NBTItem(itemstack);
```

## Make Custom Stats (Example)

### stats.yml

```
STATS:
  CUSTOM_STATS: "&7Stats: &a#amount#"
```

### Code

```
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        Entity target = e.getEntity();
        Entity killer = e.getDamager();
        if (killer instanceof Player) {
            e.setDamage(new NBTItem(killer.getInventory().getItemInMainHand()).getDoubleStats("CUSTOM_STATS"));
        }   
    }
```