package com.telepathicgrunt.the_bumblezone.blocks;

import com.telepathicgrunt.the_bumblezone.modinit.BzBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HoneyFluidBlock extends FlowingFluidBlock {

    public static final IntegerProperty BOTTOM_LEVEL = BlockStateProperties.LEVEL;

    public HoneyFluidBlock(java.util.function.Supplier<? extends FlowingFluid> supplier) {
        super(supplier, Properties.of(Material.WATER).noCollission().strength(100.0F, 100.0F).noDrops().speedFactor(0.3F));
        this.registerDefaultState(this.stateDefinition.any().setValue(LEVEL, 0).setValue(BOTTOM_LEVEL, 0));
    }

    @Override
    public void neighborChanged(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        if (this.receiveNeighborFluids(world, pos, state)) {
            world.getLiquidTicks().scheduleTick(pos, state.getFluidState().getType(), this.getFluid().getTickDelay(world));
        }
    }

    private boolean receiveNeighborFluids(World world, BlockPos pos, BlockState state)  {
        boolean flag = false;

        for (Direction direction : Direction.values()) {
            if (direction != Direction.DOWN && world.getFluidState(pos.relative(direction)).is(FluidTags.LAVA)) {
                flag = true;
                break;
            }
        }

        if (flag) {
            FluidState ifluidstate = world.getFluidState(pos);
            if (ifluidstate.isSource()) {
                world.setBlockAndUpdate(pos, BzBlocks.SUGAR_INFUSED_STONE.get().defaultBlockState());
                this.triggerMixEffects(world, pos);
                return false;
            }

            if (ifluidstate.getHeight(world, pos) >= 0.44444445F) {
                world.setBlockAndUpdate(pos, BzBlocks.SUGAR_INFUSED_COBBLESTONE.get().defaultBlockState());
                this.triggerMixEffects(world, pos);
                return false;
            }
        }

        return true;
    }


    /**
     * Heal bees if they are damaged or create honey source if pollinated
     */
    @Deprecated
    @Override
    public void entityInside(BlockState state, World world, BlockPos position, Entity entity) {
        if (entity instanceof BeeEntity) {
            BeeEntity beeEntity = ((BeeEntity) entity);
            if(beeEntity.hasNectar() && !state.getFluidState().isSource()) {
                ((BeeEntity)entity).setFlag(8, false);
                world.setBlock(position, state.setValue(LEVEL, 15).setValue(BOTTOM_LEVEL, 15), 3);
            }

            if (beeEntity.getHealth() < beeEntity.getMaxHealth()) {
                beeEntity.heal(1);
            }
        }

        super.entityInside(state, world, position, entity);
    }

    private void triggerMixEffects(World world, BlockPos pos) {
        world.levelEvent(1501, pos, 0);
    }
}