package io.github.overlordsiii;

import com.google.gson.JsonObject;

import net.minecraft.item.Item;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.TagEntry;
import net.minecraft.loot.function.LootingEnchantLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.tag.ServerTagManagerHolder;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.minecraft.util.registry.Registry;

import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;

public class MobDrop {

	private final Item item;

	private final Pair<Integer, Integer> provider;

	private final Pair<Integer, Integer> lootNumberProvider;

	private final Identifier lootingID;

	private final Tag<Item> itemTag;

	public MobDrop(Item item, Pair<Integer, Integer> dropRange, Pair<Integer, Integer> lootingRange, Identifier lootingID) {
		this.item = item;
		this.itemTag = null;
		this.provider = dropRange;
		this.lootNumberProvider = lootingRange;
		this.lootingID = lootingID;
	}

	public MobDrop(Tag<Item> itemTag, Pair<Integer, Integer> dropRange, Pair<Integer, Integer> lootingRange, Identifier lootingID) {
		this.item = null;
		this.itemTag = itemTag;
		this.provider = dropRange;
		this.lootNumberProvider = lootingRange;
		this.lootingID = lootingID;
	}



	public void appendItemToBuilder(FabricLootPoolBuilder builder) {

		if (item == null) {
			throw new RuntimeException("Item was null, tag is present. Call appendTagToBuilder instead");
		}

		builder
			.rolls(UniformLootNumberProvider.create(provider.getLeft(), provider.getRight()))
			.withFunction(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(lootNumberProvider.getLeft(), lootNumberProvider.getRight())).build())
			.with(ItemEntry.builder(item));
	}

	public void appendTagToBuilder(FabricLootPoolBuilder builder) {
		if (!hasTag()) {
			throw new RuntimeException("Tag was null, item is present. Call appendItemToBuilder");
		}

		builder
			.rolls(UniformLootNumberProvider.create(provider.getLeft(), provider.getRight()))
			.withFunction(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(lootNumberProvider.getLeft(), lootNumberProvider.getRight())).build())
			.with(TagEntry.expandBuilder(itemTag));
	}

	public boolean hasTag() {
		return itemTag != null;
	}

	public Identifier getLootingID() {
		return lootingID;
	}

	public JsonObject toJson() {
		JsonObject object = new JsonObject();
		object.addProperty("itemID", Registry.ITEM.getId(item).toString());
		JsonObject dropRangeObject = new JsonObject();
		dropRangeObject.addProperty("min", provider.getLeft());
		dropRangeObject.addProperty("max", provider.getRight());
		object.add("dropRange", dropRangeObject);
		JsonObject lootRange = new JsonObject();
		lootRange.addProperty("min", lootNumberProvider.getLeft());
		lootRange.addProperty("max", lootNumberProvider.getRight());
		object.add("lootRange", lootRange);
		object.addProperty("lootingID", lootingID.toString());
		if (hasTag()) {
			object.addProperty("tagID", ServerTagManagerHolder.getTagManager().getTagId(Registry.ITEM_KEY, this.itemTag, () -> new RuntimeException("Couldn't Parse Tag!")).toString());
		} else {
			object.addProperty("tagID", (String) null);
		}

		return object;
	}

	public static MobDrop fromJson(JsonObject object) {

		Item item;
		if (object.get("itemID").getAsString() != null) {
			item = Registry.ITEM.get(Identifier.tryParse(object.get("itemID").getAsString()));
		} else {
			item = null;
		}
		JsonObject dropRange = object.get("dropRange").getAsJsonObject();
		Pair<Integer, Integer> dropPair = new Pair<>(dropRange.get("min").getAsInt(), dropRange.get("max").getAsInt());
		JsonObject lootRange = object.get("lootRange").getAsJsonObject();
		Pair<Integer, Integer> lootPair = new Pair<>(lootRange.get("min").getAsInt(), lootRange.get("max").getAsInt());
		Identifier lootID = Identifier.tryParse(object.get("lootingID").getAsString());
		Tag<Item> itemTag;
		if (object.get("tagID").getAsString() != null) {
			itemTag = ServerTagManagerHolder.getTagManager().getTag(Registry.ITEM_KEY, Identifier.tryParse(object.get("tagID").getAsString()), (id) -> new RuntimeException("Failed to parse tag for id, \"" + id + "\""));
		} else {
			itemTag = null;
		}
		MobDrop drop;

		if (item == null && itemTag != null) {
			drop = new MobDrop(itemTag, dropPair, lootPair, lootID);
		} else if(item != null && itemTag == null) {
			drop = new MobDrop(item, dropPair, lootPair, lootID);
		} else {
			throw new RuntimeException("Couldn't parse item tag!");
		}

		return drop;

	}
}
