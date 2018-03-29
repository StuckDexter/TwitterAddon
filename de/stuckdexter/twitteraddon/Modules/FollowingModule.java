package de.stuckdexter.twitteraddon.Modules;

import de.stuckdexter.twitteraddon.TwitterAddon;
import net.labymod.ingamegui.ModuleCategory;
import net.labymod.ingamegui.ModuleCategoryRegistry;
import net.labymod.ingamegui.moduletypes.SimpleModule;
import net.labymod.settings.elements.ControlElement;
import net.labymod.utils.Material;

public class FollowingModule extends SimpleModule {

    @Override
    public ControlElement.IconData getIconData() {
        return new ControlElement.IconData( Material.PAPER );
    }

    @Override
    public void loadSettings() {

    }

    @Override
    public String getSettingName() {
        return "TwitterFollowing";
    }

    @Override
    public String getDescription() {
        return "Show how mutch people you Follow on Twitter";
    }

    @Override
    public int getSortingId() {
        return 0;
    }

    @Override
    public String getDisplayName() {
        return "Following";
    }

    @Override
    public String getDisplayValue() {
        return TwitterAddon.following;
    }

    @Override
    public String getDefaultValue() {
        return "?";
    }

    @Override
    public ModuleCategory getCategory() {
        return ModuleCategoryRegistry.CATEGORY_EXTERNAL_SERVICES;
    }
}
