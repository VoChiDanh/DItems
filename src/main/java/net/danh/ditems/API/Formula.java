package net.danh.ditems.API;

import me.clip.placeholderapi.PlaceholderAPI;
import net.danh.dcore.Calculator.Calculator;
import net.danh.dcore.Resource.Files;
import net.danh.ditems.DItems;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.math.BigDecimal;

public class Formula {

    public static double getNormalFormula(Player p) {
        FileConfiguration file = new Files(DItems.getInstance(), "stats").getConfig();
        String formula = file.getString("FORMULA.NORMAL_ATTACK");
        if (formula == null) return 0;
        String papi = PlaceholderAPI.setPlaceholders(p, formula);
        String calculator = Calculator.calculator(papi, 0);
        int index = calculator.indexOf('.');
        calculator = calculator.substring(0, index == -1 ? calculator.length() : index);
        return BigDecimal.valueOf(Long.parseLong(calculator)).doubleValue();
    }

    public static double getCritFormula(Player p) {
        FileConfiguration file = new Files(DItems.getInstance(), "stats").getConfig();
        String formula = file.getString("FORMULA.CRIT_ATTACK");
        if (formula == null) return 0;
        String papi = PlaceholderAPI.setPlaceholders(p, formula);
        String replace = papi.replaceAll("%normal_attack%", String.valueOf(getNormalFormula(p)));
        String calculator = Calculator.calculator(replace, 0);
        int index = calculator.indexOf('.');
        calculator = calculator.substring(0, index == -1 ? calculator.length() : index);
        return BigDecimal.valueOf(Long.parseLong(calculator)).doubleValue();
    }

    public static double getPvPNormalFormula(Player p) {
        FileConfiguration file = new Files(DItems.getInstance(), "stats").getConfig();
        String formula = file.getString("FORMULA.PVP_NORMAL_ATTACK");
        if (formula == null) return 0;
        String papi = PlaceholderAPI.setPlaceholders(p, formula);
        String replace = papi.replaceAll("%normal_attack%", String.valueOf(getNormalFormula(p)));
        String calculator = Calculator.calculator(replace, 0);
        int index = calculator.indexOf('.');
        calculator = calculator.substring(0, index == -1 ? calculator.length() : index);
        return BigDecimal.valueOf(Long.parseLong(calculator)).doubleValue();
    }

    public static double getPvPCritFormula(Player p) {
        FileConfiguration file = new Files(DItems.getInstance(), "stats").getConfig();
        String formula = file.getString("FORMULA.PVP_CRIT_ATTACK");
        if (formula == null) return 0;
        String papi = PlaceholderAPI.setPlaceholders(p, formula);
        String replace = papi.replaceAll("%crit_attack%", String.valueOf(getCritFormula(p)));
        String calculator = Calculator.calculator(replace, 0);
        int index = calculator.indexOf('.');
        calculator = calculator.substring(0, index == -1 ? calculator.length() : index);
        return BigDecimal.valueOf(Long.parseLong(calculator)).doubleValue();
    }

    public static double getPvENormalFormula(Player p) {
        FileConfiguration file = new Files(DItems.getInstance(), "stats").getConfig();
        String formula = file.getString("FORMULA.PVE_NORMAL_ATTACK");
        if (formula == null) return 0;
        String papi = PlaceholderAPI.setPlaceholders(p, formula);
        String replace = papi.replaceAll("%normal_attack%", String.valueOf(getNormalFormula(p)));
        String calculator = Calculator.calculator(replace, 0);
        int index = calculator.indexOf('.');
        calculator = calculator.substring(0, index == -1 ? calculator.length() : index);
        return BigDecimal.valueOf(Long.parseLong(calculator)).doubleValue();
    }

    public static double getPvECritFormula(Player p) {
        FileConfiguration file = new Files(DItems.getInstance(), "stats").getConfig();
        String formula = file.getString("FORMULA.PVE_CRIT_ATTACK");
        if (formula == null) return 0;
        String papi = PlaceholderAPI.setPlaceholders(p, formula);
        String replace = papi.replaceAll("%crit_attack%", String.valueOf(getCritFormula(p)));
        String calculator = Calculator.calculator(replace, 0);
        int index = calculator.indexOf('.');
        calculator = calculator.substring(0, index == -1 ? calculator.length() : index);
        return BigDecimal.valueOf(Long.parseLong(calculator)).doubleValue();
    }
}
