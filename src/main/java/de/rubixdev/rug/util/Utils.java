package de.rubixdev.rug.util;

import net.minecraft.util.Identifier;

import static de.rubixdev.rug.RugServer.MOD_ID;

public class Utils {
    public static Identifier id(String path) {
        return id(MOD_ID, path);
    }

    public static Identifier id(String namespace, String path) {
        //#if MC >= 12101
        return Identifier.of(namespace, path);
        //#else
        //$$ return new Identifier(namespace, path);
        //#endif
    }
}
