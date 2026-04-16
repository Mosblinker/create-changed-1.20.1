package com.mosblinker.create_changed.recipe;

import org.jetbrains.annotations.NotNull;
import com.google.gson.JsonObject;
import com.mojang.logging.LogUtils;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import com.mosblinker.create_changed.CreateChanged;
import com.mosblinker.create_changed.util.AbstractSimpleCraftingRecipeSerializer;
import net.ltxprogrammer.changed.init.ChangedItems;
import net.ltxprogrammer.changed.util.TagUtil;

public class LatexTippedArrowRecipe extends CustomRecipe{

	public LatexTippedArrowRecipe(ResourceLocation id, CraftingBookCategory category) {
		super(id, category);
	}

	@Override
	public boolean matches(CraftingContainer items, Level level) {
		if (items.getWidth() == 3 && items.getHeight() == 3) {
			for (int i = 0; i < 9; i++) {
				ItemStack item = items.getItem(i);
				if (item.isEmpty())
					return false;
				else if (i == 4 && !item.is(ChangedItems.LATEX_FLASK.get()))
					return false;
				else if (i != 4 && !item.is(Items.ARROW))
					return false;
			}
		}
		return false;
	}

	@Override
	public @NotNull ItemStack assemble(CraftingContainer items, RegistryAccess registry) {
		ItemStack flask = items.getItem(4);
		if (!flask.is(ChangedItems.LATEX_FLASK.get()))
			return ItemStack.EMPTY;
		CompoundTag flaskTags = flask.getOrCreateTag();
		ItemStack arrows = new ItemStack(ChangedItems.LATEX_TIPPED_ARROW.get(),8);
		CompoundTag arrowTags = arrows.getOrCreateTag();
		TagUtil.putResourceLocation(arrowTags, "form", TagUtil.getResourceLocation(flaskTags, "form"));
		arrowTags.putBoolean("safe", flaskTags.contains("safe")?flaskTags.getBoolean("safe"):false);
		if (flaskTags.contains("owner")) 
			arrowTags.putUUID("owner", flaskTags.getUUID("owner"));
		return arrows;
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return width >= 2 && height >= 2;
	}

	@Override
	public @NotNull RecipeSerializer<?> getSerializer() {
		return CreateChanged.LATEX_TIPPED_ARROW_RECIPE.get();
	}
	
	public static class Serializer extends AbstractSimpleCraftingRecipeSerializer<LatexTippedArrowRecipe>{
		@Override
		protected LatexTippedArrowRecipe constructRecipe(ResourceLocation id, CraftingBookCategory category,
				JsonObject json, FriendlyByteBuf buffer) {
			LogUtils.getLogger().info("Creating latex tipped arrow recipe");
			return new LatexTippedArrowRecipe(id,category);
		}
		
	}
}
