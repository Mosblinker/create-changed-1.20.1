package com.mosblinker.create_changed.item;

import com.mosblinker.create_changed.CreateChanged;
import com.mosblinker.create_changed.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CreateChanged.MODID);

    //creative mode tab for all test/placeholder stuff.
    public static final RegistryObject<CreativeModeTab> TEST_TAB = CREATIVE_MODE_TABS.register("test_tab",
            () -> CreativeModeTab.builder( ).icon(() -> new ItemStack(ModItems.TESTITEM.get()))
                    .title(Component.translatable("creativetab.test_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.TESTITEM.get()); //test item
                        pOutput.accept(ModItems.RAWITEM.get()); //raw variant for recipes

                        pOutput.accept(ModBlocks.Test_Block.get()); //test block
                        pOutput.accept(ModBlocks.TEST_ORE.get()); //test ore
                    })
                    .build());

    //creative mode tab for Shattered
    public static final RegistryObject<CreativeModeTab> SHATTERED_TAB = CREATIVE_MODE_TABS.register("shattered_tab",
            () -> CreativeModeTab.builder( ).icon(() -> new ItemStack(ModItems.TESTITEM.get())) //change later
                    .title(Component.translatable("creativetab.shattered_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.TESTITEM.get());    //placeholder to make tab visible
                    })
                    .build());

    //creative mode tab for Create Changed
    public static final RegistryObject<CreativeModeTab> CREATE_CHANGED_TAB = CREATIVE_MODE_TABS.register("create_changed_tab",
            () -> CreativeModeTab.builder( ).icon(() -> new ItemStack(ModItems.TESTITEM.get())) //change later
                    .title(Component.translatable("creativetab.create_changed_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.TESTITEM.get());    //placeholder to make tab visible
                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
