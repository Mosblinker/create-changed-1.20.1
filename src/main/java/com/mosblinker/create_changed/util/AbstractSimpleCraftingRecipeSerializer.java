package com.mosblinker.create_changed.util;

import org.jetbrains.annotations.Nullable;

import com.google.gson.JsonObject;
import com.mosblinker.create_changed.recipe.LatexTippedArrowRecipe;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;

public abstract class AbstractSimpleCraftingRecipeSerializer <T extends CraftingRecipe> implements RecipeSerializer<T> {
	
	protected abstract T constructRecipe(ResourceLocation id, CraftingBookCategory category, JsonObject json, FriendlyByteBuf buffer);

	@Override
	public T fromJson(ResourceLocation id, JsonObject json) {
		String categoryStr = GsonHelper.getAsString(json, "category", (String) null);
		CraftingBookCategory category = null;
		if (categoryStr != null) {
			try {
				category = CraftingBookCategory.valueOf(categoryStr);
			} catch (IllegalArgumentException ex) {}
		}
		if (category == null)
			category = CraftingBookCategory.MISC;
		return constructRecipe(id,category,json,null);
	}

	@Override
	public T fromNetwork(ResourceLocation id, FriendlyByteBuf buffer) {
		CraftingBookCategory category;
		try {
			category = buffer.readEnum(CraftingBookCategory.class);
		} catch (NullPointerException | ArrayIndexOutOfBoundsException ex) {
			category = CraftingBookCategory.MISC;
		}
		return constructRecipe(id,category,null,buffer);
	}

	@Override
	public void toNetwork(FriendlyByteBuf buffer, T recipe) {
		buffer.writeEnum(recipe.category());
	}

}
