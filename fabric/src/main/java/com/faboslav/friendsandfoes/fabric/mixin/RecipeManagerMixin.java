//? if =1.21.1 {
/*package com.faboslav.friendsandfoes.fabric.mixin;

import com.faboslav.friendsandfoes.common.FriendsAndFoes;
import com.faboslav.friendsandfoes.common.init.FriendsAndFoesItems;
import com.google.gson.JsonElement;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.crafting.RecipeManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Mixin(RecipeManager.class)
public abstract class RecipeManagerMixin
{
    @Inject(
        method = "apply(Ljava/util/Map;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)V",
        at = @At("TAIL")
    )
    private void friendsandfoes$removeDisabledRecipes(
        Map<ResourceLocation, JsonElement> jsons,
        ResourceManager resourceManager,
        ProfilerFiller profiler,
        CallbackInfo ci
    ) {
        var config = FriendsAndFoes.getConfig();
        Set<ResourceLocation> disabledRecipes = new HashSet<>();

        if (!config.enableAcaciaBeehive) {
            disabledRecipes.add(FriendsAndFoesItems.ACACIA_BEEHIVE.getId());
        }
        if (!config.enableBambooBeehive) {
            disabledRecipes.add(FriendsAndFoesItems.BAMBOO_BEEHIVE.getId());
        }
        if (!config.enableBirchBeehive) {
            disabledRecipes.add(FriendsAndFoesItems.BIRCH_BEEHIVE.getId());
        }
        if (!config.enableCherryBeehive) {
            disabledRecipes.add(FriendsAndFoesItems.CHERRY_BEEHIVE.getId());
        }
        if (!config.enableCrimsonBeehive) {
            disabledRecipes.add(FriendsAndFoesItems.CRIMSON_BEEHIVE.getId());
        }
        if (!config.enableDarkOakBeehive) {
            disabledRecipes.add(FriendsAndFoesItems.DARK_OAK_BEEHIVE.getId());
        }
        if (!config.enableJungleBeehive) {
            disabledRecipes.add(FriendsAndFoesItems.JUNGLE_BEEHIVE.getId());
        }
        if (!config.enableMangroveBeehive) {
            disabledRecipes.add(FriendsAndFoesItems.MANGROVE_BEEHIVE.getId());
        }
        if (!config.enableSpruceBeehive) {
            disabledRecipes.add(FriendsAndFoesItems.SPRUCE_BEEHIVE.getId());
        }
        if (!config.enableWarpedBeehive) {
            disabledRecipes.add(FriendsAndFoesItems.WARPED_BEEHIVE.getId());
        }

        if (!config.enableCopperButtons) {
            disabledRecipes.add(FriendsAndFoesItems.COPPER_BUTTON.getId());
            disabledRecipes.add(FriendsAndFoesItems.EXPOSED_COPPER_BUTTON.getId());
            disabledRecipes.add(FriendsAndFoesItems.WEATHERED_COPPER_BUTTON.getId());
            disabledRecipes.add(FriendsAndFoesItems.OXIDIZED_COPPER_BUTTON.getId());
            disabledRecipes.add(FriendsAndFoesItems.WAXED_COPPER_BUTTON.getId());
            disabledRecipes.add(FriendsAndFoesItems.WAXED_EXPOSED_COPPER_BUTTON.getId());
            disabledRecipes.add(FriendsAndFoesItems.WAXED_WEATHERED_COPPER_BUTTON.getId());
            disabledRecipes.add(FriendsAndFoesItems.WAXED_OXIDIZED_COPPER_BUTTON.getId());
        }

        // These four recipes craft modded rods. Never remove the vanilla rod recipe.
        if (!config.enableLightningRods) {
            disabledRecipes.add(FriendsAndFoes.makeID("waxed_lightning_rod_from_honeycomb"));
            disabledRecipes.add(FriendsAndFoes.makeID("waxed_exposed_lightning_rod_from_honeycomb"));
            disabledRecipes.add(FriendsAndFoes.makeID("waxed_weathered_lightning_rod_from_honeycomb"));
            disabledRecipes.add(FriendsAndFoes.makeID("waxed_oxidized_lightning_rod_from_honeycomb"));
        }

        if (disabledRecipes.isEmpty()) {
            return;
        }

        // In Minecraft 1.21.1, RecipeManager has two indexes (byName and byType).
        // Use its public API to rebuild both instead of touching an obsolete
        // "recipes" field or mutating an immutable backing map.
        RecipeManager manager = (RecipeManager) (Object) this;
        var loadedRecipes = manager.getRecipes();
        var retainedRecipes = loadedRecipes.stream()
            .filter(recipe -> !disabledRecipes.contains(recipe.id()))
            .toList();

        if (retainedRecipes.size() != loadedRecipes.size()) {
            manager.replaceRecipes(retainedRecipes);
        }
    }
}
*///?}
