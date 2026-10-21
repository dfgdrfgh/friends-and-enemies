#!/usr/bin/env python3
"""Prevent 1.21.1 Fabric-only fork options from disappearing after upstream sync.

This tests the actual shared source, but a Gradle build is still required to
verify Stonecutter preprocessing and the installable Fabric JAR.
"""
import json
from pathlib import Path

root = Path(__file__).resolve().parents[2]
def file(relative):
    return (root / relative).read_text(encoding="utf-8")
prefix = "common/src/main/java/com/faboslav/friendsandfoes/common/"
config = file(prefix + "config/FriendsAndFoesConfig.java")
screen = file(prefix + "config/client/gui/FriendsAndFoesConfigScreen.java")
items = file(prefix + "init/FriendsAndFoesItems.java")
groups = file(prefix + "init/FriendsAndFoesItemGroups.java")
rod = file(prefix + "block/OxidizableLightningRodBlock.java")
mixin = file(prefix + "mixin/LightningRodBlockMixin.java")
recipes = file("fabric/src/main/java/com/faboslav/friendsandfoes/fabric/mixin/RecipeManagerMixin.java")
tabs = file("fabric/src/main/java/com/faboslav/friendsandfoes/fabric/FriendsAndFoesCreativeTabs1211.java")
langs = json.loads(file("common/src/main/resources/assets/friendsandfoes/lang/en_us.json"))

assert '//? if =1.21.1 {' in config
assert 'ENABLE_LIGHTNING_RODS_DEFAULT_VALUE = true' in config
assert 'boolean enableLightningRods = ENABLE_LIGHTNING_RODS_DEFAULT_VALUE' in config
assert 'group("blocks", "lightning_rods")' in screen
assert 'option(bool("enableLightningRods"' in screen
assert langs["yacl3.config.friendsandfoes:friendsandfoes.enableLightningRods"] == "Enable lightning rods"

mob_flags = {
    "BARNACLE": "Barnacle", "COPPER_GOLEM": "CopperGolem",
    "CRAB": "Crab", "GLARE": "Glare", "ICEOLOGER": "Iceologer",
    "ILLUSIONER": "Illusioner", "MAULER": "Mauler",
    "MOOBLOOM": "Moobloom", "PENGUIN": "Penguin",
    "RASCAL": "Rascal", "TUFF_GOLEM": "TuffGolem",
    "WILDFIRE": "Wildfire"
}
for egg, flag in mob_flags.items():
    assert f"CONFIG_ITEMS.put({egg}_SPAWN_EGG.getId(), () -> FriendsAndFoes.getConfig().enable{flag})" in items, egg
assert '.filter(item -> FriendsAndFoesItems.CONFIG_ITEMS.getOrDefault(item.getId(), () -> true).getAsBoolean())' in groups

rods = (
    "EXPOSED_LIGHTNING_ROD", "WEATHERED_LIGHTNING_ROD",
    "OXIDIZED_LIGHTNING_ROD", "WAXED_LIGHTNING_ROD",
    "WAXED_EXPOSED_LIGHTNING_ROD", "WAXED_WEATHERED_LIGHTNING_ROD",
    "WAXED_OXIDIZED_LIGHTNING_ROD"
)
for name in rods:
    assert name in items and name in tabs, name
assert 'CONFIG_ITEMS.put(rod.getId(), () -> FriendsAndFoes.getConfig().enableLightningRods)' in items
assert tabs.count("config.enableLightningRods") == len(rods)
assert "config.enableLightningRodOxidation" not in tabs
assert "if (config.enableLightningRods)" in groups
for src in (rod, mixin):
    assert src.count("FriendsAndFoes.getConfig().enableLightningRods") >= 3
assert "if (!config.enableLightningRods)" in recipes
for rid in (
    "waxed_lightning_rod_from_honeycomb",
    "waxed_exposed_lightning_rod_from_honeycomb",
    "waxed_weathered_lightning_rod_from_honeycomb",
    "waxed_oxidized_lightning_rod_from_honeycomb",
):
    assert f'FriendsAndFoes.makeID("{rid}")' in recipes
assert 'FriendsAndFoes.makeID("lightning_rod")' not in recipes

print("PASS: Fabric 1.21.1 lightning rod option, all 7 rods, 12 mob eggs, and 4 recipe filters are present")
