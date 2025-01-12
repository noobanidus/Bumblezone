package com.telepathicgrunt.the_bumblezone.modinit;

import com.telepathicgrunt.the_bumblezone.Bumblezone;
import com.telepathicgrunt.the_bumblezone.events.AddCreativeTabEntriesEvent;
import com.telepathicgrunt.the_bumblezone.events.RegisterCreativeTabsEvent;
import com.telepathicgrunt.the_bumblezone.modinit.registry.RegistryEntry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.stream.Stream;

public class BzCreativeTabs {

    public static final List<RegistryEntry<? extends Item>> CUSTOM_CREATIVE_TAB_ITEMS = List.of(
            BzItems.POROUS_HONEYCOMB,
            BzItems.FILLED_POROUS_HONEYCOMB,
            BzItems.EMPTY_HONEYCOMB_BROOD,
            BzItems.HONEYCOMB_BROOD,
            BzItems.STICKY_HONEY_RESIDUE,
            BzItems.STICKY_HONEY_REDSTONE,
            BzItems.HONEY_WEB,
            BzItems.REDSTONE_HONEY_WEB,
            BzItems.BEEHIVE_BEESWAX,
            BzItems.GLISTERING_HONEY_CRYSTAL,
            BzItems.HONEY_CRYSTAL,
            BzItems.CARVABLE_WAX,
            BzItems.CARVABLE_WAX_WAVY,
            BzItems.CARVABLE_WAX_FLOWER,
            BzItems.CARVABLE_WAX_CHISELED,
            BzItems.CARVABLE_WAX_DIAMOND,
            BzItems.CARVABLE_WAX_BRICKS,
            BzItems.CARVABLE_WAX_CHAINS,
            BzItems.HONEY_COCOON,
            BzItems.CRYSTALLINE_FLOWER,
            BzItems.SUGAR_INFUSED_STONE,
            BzItems.SUGAR_INFUSED_COBBLESTONE,
            BzItems.INCENSE_CANDLE,
            BzItems.SUPER_CANDLE,
            BzItems.SUPER_CANDLE_WHITE,
            BzItems.SUPER_CANDLE_LIGHT_GRAY,
            BzItems.SUPER_CANDLE_GRAY,
            BzItems.SUPER_CANDLE_BLACK,
            BzItems.SUPER_CANDLE_BROWN,
            BzItems.SUPER_CANDLE_RED,
            BzItems.SUPER_CANDLE_ORANGE,
            BzItems.SUPER_CANDLE_YELLOW,
            BzItems.SUPER_CANDLE_LIME,
            BzItems.SUPER_CANDLE_GREEN,
            BzItems.SUPER_CANDLE_CYAN,
            BzItems.SUPER_CANDLE_LIGHT_BLUE,
            BzItems.SUPER_CANDLE_BLUE,
            BzItems.SUPER_CANDLE_PURPLE,
            BzItems.SUPER_CANDLE_MAGENTA,
            BzItems.SUPER_CANDLE_PINK,
            BzItems.STRING_CURTAIN_WHITE,
            BzItems.STRING_CURTAIN_LIGHT_GRAY,
            BzItems.STRING_CURTAIN_GRAY,
            BzItems.STRING_CURTAIN_BLACK,
            BzItems.STRING_CURTAIN_BROWN,
            BzItems.STRING_CURTAIN_RED,
            BzItems.STRING_CURTAIN_ORANGE,
            BzItems.STRING_CURTAIN_YELLOW,
            BzItems.STRING_CURTAIN_LIME,
            BzItems.STRING_CURTAIN_GREEN,
            BzItems.STRING_CURTAIN_CYAN,
            BzItems.STRING_CURTAIN_LIGHT_BLUE,
            BzItems.STRING_CURTAIN_BLUE,
            BzItems.STRING_CURTAIN_PURPLE,
            BzItems.STRING_CURTAIN_MAGENTA,
            BzItems.STRING_CURTAIN_PINK,
            BzItems.SUGAR_WATER_BOTTLE,
            BzItems.SUGAR_WATER_BUCKET,
            BzItems.ROYAL_JELLY_BOTTLE,
            BzItems.ROYAL_JELLY_BUCKET,
            BzItems.ROYAL_JELLY_BLOCK,
            BzItems.HONEY_BUCKET,
            BzItems.HONEY_COMPASS,
            BzItems.BEE_BREAD,
            BzItems.POLLEN_PUFF,
            BzItems.HONEY_CRYSTAL_SHARDS,
            BzItems.BEE_STINGER,
            BzItems.STINGER_SPEAR,
            BzItems.BEE_CANNON,
            BzItems.CRYSTAL_CANNON,
            BzItems.HONEY_CRYSTAL_SHIELD,
            BzItems.STINGLESS_BEE_HELMET_1,
            BzItems.STINGLESS_BEE_HELMET_2,
            BzItems.BUMBLE_BEE_CHESTPLATE_1,
            BzItems.BUMBLE_BEE_CHESTPLATE_2,
            BzItems.TRANS_BUMBLE_BEE_CHESTPLATE_1,
            BzItems.TRANS_BUMBLE_BEE_CHESTPLATE_2,
            BzItems.HONEY_BEE_LEGGINGS_1,
            BzItems.HONEY_BEE_LEGGINGS_2,
            BzItems.CARPENTER_BEE_BOOTS_1,
            BzItems.CARPENTER_BEE_BOOTS_2,
            BzItems.MUSIC_DISC_FLIGHT_OF_THE_BUMBLEBEE_RIMSKY_KORSAKOV,
            BzItems.MUSIC_DISC_HONEY_BEE_RAT_FACED_BOY,
            BzItems.MUSIC_DISC_LA_BEE_DA_LOCA,
            BzItems.MUSIC_DISC_BEE_LAXING_WITH_THE_HOM_BEES,
            BzItems.HONEY_SLIME_SPAWN_EGG,
            BzItems.BEEHEMOTH_SPAWN_EGG,
            BzItems.BEE_QUEEN_SPAWN_EGG,
            BzItems.ESSENCE_OF_THE_BEES
    );

    public static void registerCreativeTabs(RegisterCreativeTabsEvent event) {
        ItemStack iconStack = BzItems.HONEYCOMB_BROOD.get().getDefaultInstance();
        iconStack.getOrCreateTag().putBoolean("isCreativeTabIcon", true);
        event.register(
                new ResourceLocation(Bumblezone.MODID, "main_tab"),
                builder -> builder.icon(() -> iconStack)
                        .title(Component.translatable("itemGroup." + Bumblezone.MODID + ".main_tab")),
                items -> CUSTOM_CREATIVE_TAB_ITEMS.stream().map(item -> item.get().getDefaultInstance()).forEach(items::add)

        );
    }

    public static void addCreativeTabEntries(AddCreativeTabEntriesEvent event) {
        if (event.type() == AddCreativeTabEntriesEvent.Type.REDSTONE) {
            Stream.of(
                    BzItems.STICKY_HONEY_REDSTONE,
                    BzItems.REDSTONE_HONEY_WEB,
                    BzItems.STRING_CURTAIN_WHITE,
                    BzItems.ROYAL_JELLY_BLOCK
            ).map(item -> item.get().getDefaultInstance()).forEach(event::add);
        }

        if (event.type() == AddCreativeTabEntriesEvent.Type.FUNCTIONAL) {
            Stream.of(
                    BzItems.HONEYCOMB_BROOD,
                    BzItems.GLISTERING_HONEY_CRYSTAL,
                    BzItems.HONEY_COCOON,
                    BzItems.CRYSTALLINE_FLOWER,
                    BzItems.STICKY_HONEY_RESIDUE,
                    BzItems.HONEY_WEB,
                    BzItems.INCENSE_CANDLE,
                    BzItems.SUPER_CANDLE,
                    BzItems.SUPER_CANDLE_WHITE,
                    BzItems.SUPER_CANDLE_LIGHT_GRAY,
                    BzItems.SUPER_CANDLE_GRAY,
                    BzItems.SUPER_CANDLE_BLACK,
                    BzItems.SUPER_CANDLE_BROWN,
                    BzItems.SUPER_CANDLE_RED,
                    BzItems.SUPER_CANDLE_ORANGE,
                    BzItems.SUPER_CANDLE_YELLOW,
                    BzItems.SUPER_CANDLE_LIME,
                    BzItems.SUPER_CANDLE_GREEN,
                    BzItems.SUPER_CANDLE_CYAN,
                    BzItems.SUPER_CANDLE_LIGHT_BLUE,
                    BzItems.SUPER_CANDLE_BLUE,
                    BzItems.SUPER_CANDLE_PURPLE,
                    BzItems.SUPER_CANDLE_MAGENTA,
                    BzItems.SUPER_CANDLE_PINK
            ).map(item -> item.get().getDefaultInstance()).forEach(event::add);
        }

        if (event.type() == AddCreativeTabEntriesEvent.Type.COLORED) {
            Stream.of(
                    BzItems.SUPER_CANDLE,
                    BzItems.SUPER_CANDLE_WHITE,
                    BzItems.SUPER_CANDLE_LIGHT_GRAY,
                    BzItems.SUPER_CANDLE_GRAY,
                    BzItems.SUPER_CANDLE_BLACK,
                    BzItems.SUPER_CANDLE_BROWN,
                    BzItems.SUPER_CANDLE_RED,
                    BzItems.SUPER_CANDLE_ORANGE,
                    BzItems.SUPER_CANDLE_YELLOW,
                    BzItems.SUPER_CANDLE_LIME,
                    BzItems.SUPER_CANDLE_GREEN,
                    BzItems.SUPER_CANDLE_CYAN,
                    BzItems.SUPER_CANDLE_LIGHT_BLUE,
                    BzItems.SUPER_CANDLE_BLUE,
                    BzItems.SUPER_CANDLE_PURPLE,
                    BzItems.SUPER_CANDLE_MAGENTA,
                    BzItems.SUPER_CANDLE_PINK,
                    BzItems.STRING_CURTAIN_WHITE,
                    BzItems.STRING_CURTAIN_LIGHT_GRAY,
                    BzItems.STRING_CURTAIN_GRAY,
                    BzItems.STRING_CURTAIN_BLACK,
                    BzItems.STRING_CURTAIN_BROWN,
                    BzItems.STRING_CURTAIN_RED,
                    BzItems.STRING_CURTAIN_ORANGE,
                    BzItems.STRING_CURTAIN_YELLOW,
                    BzItems.STRING_CURTAIN_LIME,
                    BzItems.STRING_CURTAIN_GREEN,
                    BzItems.STRING_CURTAIN_CYAN,
                    BzItems.STRING_CURTAIN_LIGHT_BLUE,
                    BzItems.STRING_CURTAIN_BLUE,
                    BzItems.STRING_CURTAIN_PURPLE,
                    BzItems.STRING_CURTAIN_MAGENTA,
                    BzItems.STRING_CURTAIN_PINK
            ).map(item -> item.get().getDefaultInstance()).forEach(event::add);
        }

        if (event.type() == AddCreativeTabEntriesEvent.Type.COMBAT) {
            Stream.of(
                    BzItems.BEE_STINGER,
                    BzItems.STINGER_SPEAR,
                    BzItems.BEE_CANNON,
                    BzItems.CRYSTAL_CANNON,
                    BzItems.HONEY_CRYSTAL_SHIELD,
                    BzItems.STINGLESS_BEE_HELMET_1,
                    BzItems.STINGLESS_BEE_HELMET_2,
                    BzItems.BUMBLE_BEE_CHESTPLATE_1,
                    BzItems.BUMBLE_BEE_CHESTPLATE_2,
                    BzItems.TRANS_BUMBLE_BEE_CHESTPLATE_1,
                    BzItems.TRANS_BUMBLE_BEE_CHESTPLATE_2,
                    BzItems.HONEY_BEE_LEGGINGS_1,
                    BzItems.HONEY_BEE_LEGGINGS_2,
                    BzItems.CARPENTER_BEE_BOOTS_1,
                    BzItems.CARPENTER_BEE_BOOTS_2
            ).map(item -> item.get().getDefaultInstance()).forEach(event::add);
        }

        if (event.type() == AddCreativeTabEntriesEvent.Type.SPAWN_EGGS) {
            Stream.of(
                    BzItems.HONEY_SLIME_SPAWN_EGG,
                    BzItems.BEEHEMOTH_SPAWN_EGG,
                    BzItems.BEE_QUEEN_SPAWN_EGG
            ).map(item -> item.get().getDefaultInstance()).forEach(event::add);
        }
    }
}
