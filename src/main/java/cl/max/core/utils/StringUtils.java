package cl.max.core.utils;

import net.md_5.bungee.api.ChatColor;

public class StringUtils {
    public static String listToStr(String[] L, int start, int end) {
        String result = "";
        for (int i = start; i < end; i++) {
            result += (L[i] + " ");
        }
        result += L[end];
        return result;
    }

    public static String colorize(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }
}
