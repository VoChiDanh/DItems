# DItems [1.8 - 1.20.x]

### Simple plugins to make item with custom stats

# Commands

```
/ditems setname [name] - Set name
/ditems reload - Reload file
/ditems show [stats] - Show amount of stats in hand
/ditems stats [stats] [number] - Set stats of item in hand
/ditems addlore [lore] - Add lore
/ditems removelore [line] - Remove lore
/ditems setlore [line] [name] - Set lore
/ditems help - Show help
/ditems addenchant [Enchant] [Level] - Add enchant
/ditems removeenchant [Enchant] - Remove enchant
/ditems save [Key] - Save items
/ditems unbreakable [true/false] - Set item unbreakabl
/ditems custom_model_data [number] - Set Custom Model Data
/ditems load [key] [amount] - Load items from file
/ditems setflag [flag] - Add flag
/ditems removeflag [flag] - Remove flag
/ditems ability_command [action] [command] [delay] - Add ability command
/ditems remove_ability_command [action] - Remove ability command
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