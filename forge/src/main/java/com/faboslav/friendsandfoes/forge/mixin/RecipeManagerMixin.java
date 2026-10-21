package com.faboslav.friendsandfoes.forge.mixin;

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

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Mixin(RecipeManager.class)
public abstract class RecipeManagerMixin {
    @Inject(method = "apply(Ljava/util/Map;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)V", at = @At("TAIL"))
    private void friendsandfoes$filterDisabledRecipes(Map<ResourceLocation, JsonElement> jsons,
                                                     ResourceManager resources, ProfilerFiller profiler,
                                                     CallbackInfo ci) {
        Set<ResourceLocation> disabled = FriendsAndFoesItems.CONFIG_ITEMS.entrySet().stream()
            .filter(entry -> !entry.getValue().getAsBoolean())
            .map(Map.Entry::getKey).collect(Collectors.toSet());
        if (disabled.isEmpty()) return;
        RecipeManager manager = (RecipeManager) (Object) this;
        // Rebuild both indexes through the public API. Never mutate immutable recipe maps.
        // Waxing recipes use the output item's ID followed by _from_honeycomb.
        var retained = manager.getRecipes().stream().filter(recipe -> disabled.stream().noneMatch(id ->
            recipe.getId().equals(id) || recipe.getId().equals(
                new ResourceLocation(id.getNamespace(), id.getPath() + "_from_honeycomb")))).toList();
        manager.replaceRecipes(retained);
    }
}
