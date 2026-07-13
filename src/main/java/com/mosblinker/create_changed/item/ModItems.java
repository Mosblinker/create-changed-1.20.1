package com.mosblinker.create_changed.item;

import com.mosblinker.create_changed.CreateChanged;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
      public static final DeferredRegister<Item> ITEMS =
              DeferredRegister.create(ForgeRegistries.ITEMS, CreateChanged.MODID);


      public static final RegistryObject<Item> TESTITEM = ITEMS.register("testitem",
              () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RAWITEM = ITEMS.register("rawitem",
            () -> new Item(new Item.Properties()));

      public static void register(IEventBus eventBus){
          ITEMS.register(eventBus);
      }
}
