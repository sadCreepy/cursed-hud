package io.github.sadcreepy.cursedhud.config;

import blue.endless.jankson.Comment;
import net.glasslauncher.mods.api.gcapi.api.ConfigName;

public class CursedHudConfig {

    @ConfigName("Air bar offset")
    @Comment("Int value")
    public Integer airbarOffset = 0;

    @ConfigName("Bars offset")
    @Comment("Int value")
    public Integer barsOffset = 0;

    @ConfigName("Top alignment")
    public Boolean topAlignment = false;

    @ConfigName("Hotbar offset")
    @Comment("Int value")
    public Integer hotbarOffset = 0;

}
