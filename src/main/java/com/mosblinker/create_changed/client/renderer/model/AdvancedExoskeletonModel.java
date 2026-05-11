package com.mosblinker.create_changed.client.renderer.model;

import com.mosblinker.create_changed.CreateChangedConfig;

import net.ltxprogrammer.changed.client.renderer.model.ExoskeletonModel;
import net.ltxprogrammer.changed.entity.ChangedEntity;
import net.ltxprogrammer.changed.entity.variant.TransfurVariant;
import net.ltxprogrammer.changed.util.EntityUtil;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class AdvancedExoskeletonModel extends ExoskeletonModel{

	public AdvancedExoskeletonModel(ModelPart root) {
		super(root);
	}
	
	
	public static class VisorModel extends ExoskeletonModel.VisorModel{

		public VisorModel(ModelPart root) {
			super(root);
		}
		
		@Override
		public ResourceLocation getTexture(LivingEntity wearer, ItemStack itemStack) {
				// Get the wearer's entity. 
			LivingEntity entity = EntityUtil.maybeGetOverlaying(wearer);
				// If the wearer is a ChangedEntity (they are a latex beast or a transfurred player)
			if (entity instanceof ChangedEntity) {
					// Get the wearer's variant
				TransfurVariant<?> variant = ((ChangedEntity)entity).getSelfVariant();
					// If the wearer's variant is one of the variants which will show the 
					// hypno visor
				if (CreateChangedConfig.advancedExoskeletonHypnoVisorVariants.contains(variant))
					return wearer.tickCount % 12 < 6 ? VISOR_HYPNO.get(0) : VISOR_HYPNO.get(1);
			}
			return super.getTexture(wearer, itemStack);
		}
	}
}
