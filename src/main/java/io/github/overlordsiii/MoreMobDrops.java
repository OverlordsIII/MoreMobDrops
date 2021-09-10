package io.github.overlordsiii;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.util.Identifier;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;

public class MoreMobDrops implements ModInitializer {

	private static Map<Identifier, MobDrop> animalDropMap;

	static {
		reloadDataMap();
	}

	/**
	 * Runs the mod initializer.
	 */
	@Override
	public void onInitialize() {
		LootTableLoadingCallback.EVENT.register((resourceManager, manager, id, supplier, setter) -> {

			MobDrop drop = animalDropMap.get(id);

			if (animalDropMap.get(id) != null) {
				FabricLootPoolBuilder builder = FabricLootPoolBuilder.builder();

				if (drop.hasTag()) {
					drop.appendTagToBuilder(builder);
				} else {
					drop.appendItemToBuilder(builder);
				}

				supplier.pool(builder);
			}
		});
	}

	public static void reloadDataMap() {
		animalDropMap = new HashMap<>();
/*
		animalDropMap.put(EntityType.AXOLOTL.getLootTableId(), new MobDrop(TagEntry.expandBuilder(ItemTags.FISHES), UniformLootNumberProvider.create(0, 1), UniformLootNumberProvider.create(0, 1)));
		animalDropMap.put(EntityType.BAT.getLootTableId(), new MobDrop(ItemEntry.builder(Items.CHICKEN), UniformLootNumberProvider.create(0, 2), UniformLootNumberProvider.create(0, 1)));
		animalDropMap.put(EntityType.CAT.getLootTableId(), new MobDrop(ItemEntry.builder(Items.RABBIT), UniformLootNumberProvider.create(0, 1), UniformLootNumberProvider.create(0, 1)));
		animalDropMap.put(EntityType.DONKEY.getLootTableId(), new MobDrop(ItemEntry.builder(Items.BEEF), UniformLootNumberProvider.create(0, 1), UniformLootNumberProvider.create(0, 1)));
		animalDropMap.put(EntityType.FOX.getLootTableId(), new MobDrop(ItemEntry.builder(Items.RABBIT), UniformLootNumberProvider.create(0, 2), UniformLootNumberProvider.create(0, 1)));
		animalDropMap.put(EntityType.SQUID.getLootTableId(), new MobDrop(TagEntry.expandBuilder(ItemTags.FISHES), UniformLootNumberProvider.create(0, 1), UniformLootNumberProvider.create(0, 1)));
		animalDropMap.put(EntityType.GLOW_SQUID.getLootTableId(), new MobDrop(TagEntry.expandBuilder(ItemTags.FISHES), UniformLootNumberProvider.create(0, 1), UniformLootNumberProvider.create(0, 1)));
		animalDropMap.put(EntityType.HORSE.getLootTableId(), new MobDrop(ItemEntry.builder(Items.BEEF), UniformLootNumberProvider.create(0, 1), UniformLootNumberProvider.create(0, 1)));
		animalDropMap.put(EntityType.MULE.getLootTableId(), new MobDrop(ItemEntry.builder(Items.BEEF), UniformLootNumberProvider.create(0, 1), UniformLootNumberProvider.create(0, 1)));
		animalDropMap.put(EntityType.OCELOT.getLootTableId(), new MobDrop(ItemEntry.builder(Items.RABBIT), UniformLootNumberProvider.create(0, 2), UniformLootNumberProvider.create(0, 1)));
		animalDropMap.put(EntityType.PARROT.getLootTableId(), new MobDrop(ItemEntry.builder(Items.CHICKEN), UniformLootNumberProvider.create(0, 1), UniformLootNumberProvider.create(0, 1)));
		animalDropMap.put(EntityType.POLAR_BEAR.getLootTableId(), new MobDrop(ItemEntry.builder(Items.BEEF), UniformLootNumberProvider.create(0, 1), UniformLootNumberProvider.create(0, 1)));
		animalDropMap.put(EntityType.SKELETON_HORSE.getLootTableId(), new MobDrop(ItemEntry.builder(Items.BONE), UniformLootNumberProvider.create(0, 5), UniformLootNumberProvider.create(0, 1)));
		animalDropMap.put(EntityType.STRIDER.getLootTableId(), new MobDrop(ItemEntry.builder(Items.MUTTON), UniformLootNumberProvider.create(0, 1), UniformLootNumberProvider.create(0, 1)));
		animalDropMap.put(EntityType.TURTLE.getLootTableId(), new MobDrop(TagEntry.expandBuilder(ItemTags.FISHES), UniformLootNumberProvider.create(0, 1), UniformLootNumberProvider.create(0, 1)));
		animalDropMap.put(EntityType.BEE.getLootTableId(), new MobDrop(ItemEntry.builder(Items.HONEY_BOTTLE), UniformLootNumberProvider.create(0, 2), UniformLootNumberProvider.create(0, 1)));
		animalDropMap.put(EntityType.DOLPHIN.getLootTableId(), new MobDrop(ItemEntry.builder(Items.COD), UniformLootNumberProvider.create(0, 1), UniformLootNumberProvider.create(0, 1)));
		animalDropMap.put(EntityType.GOAT.getLootTableId(), new MobDrop(ItemEntry.builder(Items.MUTTON), UniformLootNumberProvider.create(0, 2), UniformLootNumberProvider.create(0, 1)));
		animalDropMap.put(EntityType.LLAMA.getLootTableId(), new MobDrop(ItemEntry.builder(Items.MUTTON), UniformLootNumberProvider.create(0, 1), UniformLootNumberProvider.create(0, 1)));
		animalDropMap.put(EntityType.PANDA.getLootTableId(), new MobDrop(ItemEntry.builder(Items.BEEF), UniformLootNumberProvider.create(0, 1), UniformLootNumberProvider.create(0, 1)));
		animalDropMap.put(EntityType.WOLF.getLootTableId(), new MobDrop(ItemEntry.builder(Items.PORKCHOP), UniformLootNumberProvider.create(0, 2), UniformLootNumberProvider.create(0, 1)));
		animalDropMap.put(EntityType.RAVAGER.getLootTableId(), new MobDrop(ItemEntry.builder(Items.BEEF), UniformLootNumberProvider.create(0, 10), UniformLootNumberProvider.create(0, 3)));
 */
	}




}
