package io.github.sadcreepy.cursedhud.events.init;

import io.github.sadcreepy.cursedhud.config.CursedHudConfig;
import net.glasslauncher.mods.api.gcapi.api.GConfig;

public class ConfigListener {

    @GConfig(value = "cursedhudconfig", visibleName = "Cursed Hud Config")
    public static final CursedHudConfig cursedHudConfig = new CursedHudConfig();

}