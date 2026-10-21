package com.faboslav.friendsandfoes.forge;

import com.faboslav.friendsandfoes.common.FriendsAndFoes;
import com.faboslav.friendsandfoes.common.init.FriendsAndFoesItems;
import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

// Forge 1.20.1 equivalent of the fork's Fabric creative inventory ordering.
public final class FriendsAndFoesCreativeTabs1201 {
    private FriendsAndFoesCreativeTabs1201() {}

    public static void sort(BuildCreativeModeTabContentsEvent entries) {
        if (entries.getTabKey().equals(CreativeModeTabs.SPAWN_EGGS)) {
            var config = FriendsAndFoes.getConfig();
            after(entries, Items.AXOLOTL_SPAWN_EGG, FriendsAndFoesItems.BARNACLE_SPAWN_EGG, config.enableBarnacle);
            after(entries, Items.COD_SPAWN_EGG, FriendsAndFoesItems.COPPER_GOLEM_SPAWN_EGG, config.enableCopperGolem);
            after(entries, Items.COW_SPAWN_EGG, FriendsAndFoesItems.CRAB_SPAWN_EGG, config.enableCrab);
            after(entries, Items.GHAST_SPAWN_EGG, FriendsAndFoesItems.GLARE_SPAWN_EGG, config.enableGlare);
            ItemLike anchor = Items.HUSK_SPAWN_EGG;
            anchor = after(entries, anchor, FriendsAndFoesItems.ICEOLOGER_SPAWN_EGG, config.enableIceologer);
            after(entries, anchor, FriendsAndFoesItems.ILLUSIONER_SPAWN_EGG, config.enableIllusioner);
            after(entries, Items.MAGMA_CUBE_SPAWN_EGG, FriendsAndFoesItems.MAULER_SPAWN_EGG, config.enableMauler);
            after(entries, Items.MOOSHROOM_SPAWN_EGG, FriendsAndFoesItems.MOOBLOOM_SPAWN_EGG, config.enableMoobloom);
            after(entries, Items.PARROT_SPAWN_EGG, FriendsAndFoesItems.PENGUIN_SPAWN_EGG, config.enablePenguin);
            after(entries, Items.RABBIT_SPAWN_EGG, FriendsAndFoesItems.RASCAL_SPAWN_EGG, config.enableRascal);
            after(entries, Items.TROPICAL_FISH_SPAWN_EGG, FriendsAndFoesItems.TUFF_GOLEM_SPAWN_EGG, config.enableTuffGolem);
            after(entries, Items.WITCH_SPAWN_EGG, FriendsAndFoesItems.WILDFIRE_SPAWN_EGG, config.enableWildfire);
        }

        if (entries.getTabKey().equals(CreativeModeTabs.NATURAL_BLOCKS)) {
            after(entries, Items.DANDELION, FriendsAndFoesItems.BUTTERCUP, true);
            ItemLike anchor = Items.TURTLE_EGG;
            anchor = after(entries, anchor, FriendsAndFoesItems.CRAB_EGG, FriendsAndFoes.getConfig().enableCrab);
            after(entries, anchor, FriendsAndFoesItems.PENGUIN_EGG, FriendsAndFoes.getConfig().enablePenguin);
        }

        if (entries.getTabKey().equals(CreativeModeTabs.FUNCTIONAL_BLOCKS)) {
            var config = FriendsAndFoes.getConfig();
            ItemLike anchor = Items.BEEHIVE;
            anchor = after(entries, anchor, FriendsAndFoesItems.BIRCH_BEEHIVE, config.enableBirchBeehive);
            anchor = after(entries, anchor, FriendsAndFoesItems.SPRUCE_BEEHIVE, config.enableSpruceBeehive);
            anchor = after(entries, anchor, FriendsAndFoesItems.JUNGLE_BEEHIVE, config.enableJungleBeehive);
            anchor = after(entries, anchor, FriendsAndFoesItems.ACACIA_BEEHIVE, config.enableAcaciaBeehive);
            anchor = after(entries, anchor, FriendsAndFoesItems.DARK_OAK_BEEHIVE, config.enableDarkOakBeehive);
            anchor = after(entries, anchor, FriendsAndFoesItems.MANGROVE_BEEHIVE, config.enableMangroveBeehive);
            anchor = after(entries, anchor, FriendsAndFoesItems.CHERRY_BEEHIVE, config.enableCherryBeehive);
            anchor = after(entries, anchor, FriendsAndFoesItems.BAMBOO_BEEHIVE, config.enableBambooBeehive);
            anchor = after(entries, anchor, FriendsAndFoesItems.CRIMSON_BEEHIVE, config.enableCrimsonBeehive);
            after(entries, anchor, FriendsAndFoesItems.WARPED_BEEHIVE, config.enableWarpedBeehive);
        }

        if (entries.getTabKey().equals(CreativeModeTabs.REDSTONE_BLOCKS)) {
            var config = FriendsAndFoes.getConfig();
            ItemLike anchor = Items.STONE_BUTTON;
            anchor = after(entries, anchor, FriendsAndFoesItems.COPPER_BUTTON, config.enableCopperButtons);
            anchor = after(entries, anchor, FriendsAndFoesItems.EXPOSED_COPPER_BUTTON, config.enableCopperButtons);
            anchor = after(entries, anchor, FriendsAndFoesItems.WEATHERED_COPPER_BUTTON, config.enableCopperButtons);
            anchor = after(entries, anchor, FriendsAndFoesItems.OXIDIZED_COPPER_BUTTON, config.enableCopperButtons);
            anchor = after(entries, anchor, FriendsAndFoesItems.WAXED_COPPER_BUTTON, config.enableCopperButtons);
            anchor = after(entries, anchor, FriendsAndFoesItems.WAXED_EXPOSED_COPPER_BUTTON, config.enableCopperButtons);
            anchor = after(entries, anchor, FriendsAndFoesItems.WAXED_WEATHERED_COPPER_BUTTON, config.enableCopperButtons);
            after(entries, anchor, FriendsAndFoesItems.WAXED_OXIDIZED_COPPER_BUTTON, config.enableCopperButtons);

            anchor = Items.LIGHTNING_ROD;
            anchor = after(entries, anchor, FriendsAndFoesItems.EXPOSED_LIGHTNING_ROD, config.enableLightningRodOxidation);
            anchor = after(entries, anchor, FriendsAndFoesItems.WEATHERED_LIGHTNING_ROD, config.enableLightningRodOxidation);
            anchor = after(entries, anchor, FriendsAndFoesItems.OXIDIZED_LIGHTNING_ROD, config.enableLightningRodOxidation);
            anchor = after(entries, anchor, FriendsAndFoesItems.WAXED_LIGHTNING_ROD, config.enableLightningRodOxidation);
            anchor = after(entries, anchor, FriendsAndFoesItems.WAXED_EXPOSED_LIGHTNING_ROD, config.enableLightningRodOxidation);
            anchor = after(entries, anchor, FriendsAndFoesItems.WAXED_WEATHERED_LIGHTNING_ROD, config.enableLightningRodOxidation);
            after(entries, anchor, FriendsAndFoesItems.WAXED_OXIDIZED_LIGHTNING_ROD, config.enableLightningRodOxidation);
        }

        if (entries.getTabKey().equals(CreativeModeTabs.INGREDIENTS)) {
            after(entries, Items.BLAZE_POWDER, FriendsAndFoesItems.WILDFIRE_CROWN_FRAGMENT, true);
            after(entries, Items.RABBIT_FOOT, FriendsAndFoesItems.CRAB_CLAW, FriendsAndFoes.getConfig().enableCrab);
            after(entries, Items.FEATHER, FriendsAndFoesItems.PENGUIN_FEATHER, FriendsAndFoes.getConfig().enablePenguin);
            after(entries, Items.NAUTILUS_SHELL, FriendsAndFoesItems.BARNACLE_TOOTH, FriendsAndFoes.getConfig().enableBarnacle);
        }

        if (entries.getTabKey().equals(CreativeModeTabs.COMBAT)) {
            after(entries, Items.NETHERITE_HELMET, FriendsAndFoesItems.WILDFIRE_CROWN, true);
            ItemLike anchor = Items.TOTEM_OF_UNDYING;
            anchor = after(entries, anchor, FriendsAndFoesItems.TOTEM_OF_FREEZING, true);
            after(entries, anchor, FriendsAndFoesItems.TOTEM_OF_ILLUSION, true);
        }

        if (entries.getTabKey().equals(CreativeModeTabs.TOOLS_AND_UTILITIES)) {
            after(entries, Items.MUSIC_DISC_OTHERSIDE, FriendsAndFoesItems.MUSIC_DISC_AROUND_THE_CORNER, true);
        }
    }

    private static ItemLike after(BuildCreativeModeTabContentsEvent entries, ItemLike anchor,
                                  RegistryEntry<Item> entry, boolean enabled) {
        entries.getEntries().remove(new ItemStack(entry.get()));
        if (enabled) {
            entries.getEntries().putAfter(new ItemStack(anchor), new ItemStack(entry.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            return entry.get();
        }
        return anchor;
    }
}
