package com.mosblinker.create_changed;

import net.ltxprogrammer.changed.entity.variant.TransfurVariant;
import net.ltxprogrammer.changed.init.ChangedRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Forge's config APIs
@Mod.EventBusSubscriber(modid = CreateChanged.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CreateChangedConfig
{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

//    private static final ForgeConfigSpec.BooleanValue LOG_DIRT_BLOCK = BUILDER
//            .comment("Whether to log the dirt block on common setup")
//            .define("logDirtBlock", true);
//
//    private static final ForgeConfigSpec.IntValue MAGIC_NUMBER = BUILDER
//            .comment("A magic number")
//            .defineInRange("magicNumber", 42, 0, Integer.MAX_VALUE);
//
//    public static final ForgeConfigSpec.ConfigValue<String> MAGIC_NUMBER_INTRODUCTION = BUILDER
//            .comment("What you want the introduction message to be for the magic number")
//            .define("magicNumberIntroduction", "The magic number is... ");
//
//    // a list of strings that are treated as resource locations for items
//    private static final ForgeConfigSpec.ConfigValue<List<? extends String>> ITEM_STRINGS = BUILDER
//            .comment("A list of items to log on common setup.")
//            .defineListAllowEmpty("items", List.of("minecraft:iron_ingot"), CreateChangedConfig::validateItemName);
    
    // a list of strings that are treated as resource locations for transfur variants
    private static final ForgeConfigSpec.ConfigValue<List<? extends String>> EXOSKELETON_HYPNO_VARIANTS_STRINGS = BUILDER
    		.comment("A list of transfur variants that the advanced exoskeleton will show the hypno visor for.","This excludes benign latexes as those will always show the hypno visor.")
    		.defineListAllowEmpty("advancedExoskeletonHypnoVisorVariants", List.of(), CreateChangedConfig::validateTransfurVariants);
    

    static final ForgeConfigSpec SPEC = BUILDER.build();

//    public static boolean logDirtBlock;
//    public static int magicNumber;
//    public static String magicNumberIntroduction;
//    public static Set<Item> items;

//    private static boolean validateItemName(final Object obj)
//    {
//        return obj instanceof final String itemName && ForgeRegistries.ITEMS.containsKey(new ResourceLocation(itemName));
//    }
    
    /**
     * A set containing the TransfurVariants that the advanced exoskeleton will show the hypno visor for.
     */
    public static Set<TransfurVariant<?>> advancedExoskeletonHypnoVisorVariants;
    
    private static boolean validateTransfurVariants(final Object obj) {
    		return obj instanceof final String variantName && ChangedRegistry.TRANSFUR_VARIANT.get().containsKey(new ResourceLocation(variantName));
    }

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
//        logDirtBlock = LOG_DIRT_BLOCK.get();
//        magicNumber = MAGIC_NUMBER.get();
//        magicNumberIntroduction = MAGIC_NUMBER_INTRODUCTION.get();
//
//        // convert the list of strings into a set of items
//        items = ITEM_STRINGS.get().stream()
//                .map(itemName -> ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemName)))
//                .collect(Collectors.toSet());
    	
    		// Convert the list of strings into a set of transfur variant
    		advancedExoskeletonHypnoVisorVariants = EXOSKELETON_HYPNO_VARIANTS_STRINGS.get().stream()
    				.map(variantName -> ChangedRegistry.TRANSFUR_VARIANT.get().getValue(new ResourceLocation(variantName)))
    				.collect(Collectors.toSet());
    }
}
