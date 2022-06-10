package com.telepathicgrunt.the_bumblezone.modinit;

import com.telepathicgrunt.the_bumblezone.Bumblezone;
import com.telepathicgrunt.the_bumblezone.blocks.blockentities.HoneyCocoonBlockEntity;
import com.telepathicgrunt.the_bumblezone.items.functions.DropContainerItems;
import com.telepathicgrunt.the_bumblezone.items.functions.UniquifyIfHasItems;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BzLootFunctionTypes {

    public static final DeferredRegister<LootItemFunctionType> LOOT_ITEM_FUNCTION_TYPE = DeferredRegister.create(Registry.LOOT_FUNCTION_REGISTRY, Bumblezone.MODID);

    public static final RegistryObject<LootItemFunctionType> DROP_CONTAINER_ITEMS = LOOT_ITEM_FUNCTION_TYPE.register("drop_container_loot", () -> new LootItemFunctionType(new DropContainerItems.Serializer()));
    public static final RegistryObject<LootItemFunctionType> UNIQUIFY_IF_HAS_ITEMS = LOOT_ITEM_FUNCTION_TYPE.register("uniquify_if_has_items", () -> new LootItemFunctionType(new UniquifyIfHasItems.Serializer()));
}