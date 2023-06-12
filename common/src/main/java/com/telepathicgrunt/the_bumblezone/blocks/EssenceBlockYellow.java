package com.telepathicgrunt.the_bumblezone.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;


public class EssenceBlockYellow extends EssenceBlock {
    public EssenceBlockYellow() {
        super(Properties.of().mapColor(MapColor.COLOR_YELLOW));
    }

    @Override
    public ResourceLocation getArenaNbt() {
        return null;
    }

    @Override
    public long getEventTimeFrame() {
        return 2000;
    }
}
