package com.telepathicgrunt.the_bumblezone.entities.nonliving;

import com.google.common.base.Objects;
import com.telepathicgrunt.the_bumblezone.blocks.EssenceBlockYellow;
import com.telepathicgrunt.the_bumblezone.blocks.blockentities.EssenceBlockEntity;
import com.telepathicgrunt.the_bumblezone.modinit.BzParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.Vec3;

public class ElectricRingEntity extends Entity {

    public BlockEntity blockEntity = null;

    public ElectricRingEntity(EntityType<? extends ElectricRingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void defineSynchedData() {}

    @Override
    protected float getEyeHeight(Pose pose, EntityDimensions entityDimensions) {
        return entityDimensions.height * 0.5f;
    }

    public void tick() {
        super.tick();
        if (this.level().isClientSide()) {
            if (this.tickCount % 2 == 0) {
                this.makeParticle(1);
            }
        }
        else if (tickCount == 1) {
            this.makeServerParticle(50, (ServerLevel) this.level());
        }
        this.setRot(this.getYRot(), this.getXRot());
    }

    @Override
    public void baseTick() {
        super.baseTick();
        this.yRotO = this.getYRot();
        this.xRotO = this.getXRot();
    }

    private void makeParticle(int particlesToSpawn) {
        if (particlesToSpawn > 0) {
            double size = this.getBoundingBox().getSize();
            double extraScale = 1.5;
            double x = this.getX() + ((this.random.nextFloat() - 0.5f) * size * extraScale);
            double y = this.getY() + (size / 2) + ((this.random.nextFloat() - 0.5f) * size * extraScale);
            double z = this.getZ() + ((this.random.nextFloat() - 0.5f) * size * extraScale);

            for(int i = 0; i < particlesToSpawn; ++i) {
                this.level().addParticle(
                        ParticleTypes.ELECTRIC_SPARK,
                        x,
                        y,
                        z,
                        (this.random.nextFloat() - 0.5f) * 0.5f,
                        (this.random.nextFloat() - 0.5f) * 0.5f,
                        (this.random.nextFloat() - 0.5f) * 0.5f);
            }
        }
    }

    private void makeServerParticle(int particlesToSpawn, ServerLevel serverLevel) {
        if (particlesToSpawn > 0) {
            double xOffset = (random.nextFloat() * 0.3) - 0.15;
            double yOffset = (random.nextFloat() * 0.3) - 0.15;
            double zOffset = (random.nextFloat() * 0.3) - 0.15;

            serverLevel.sendParticles(
                    ParticleTypes.CRIT,
                    this.getX(),
                    this.getEyeY(),
                    this.getZ(),
                    particlesToSpawn,
                    random.nextGaussian() * xOffset,
                    (random.nextGaussian() * yOffset) + 0.2f,
                    random.nextGaussian() * zOffset,
                    1.5f
            );

            serverLevel.sendParticles(
                    ParticleTypes.ENCHANTED_HIT,
                    this.getX(),
                    this.getEyeY(),
                    this.getZ(),
                    particlesToSpawn,
                    random.nextGaussian() * xOffset,
                    (random.nextGaussian() * yOffset) + 0.2f,
                    random.nextGaussian() * zOffset,
                    1f
            );

            serverLevel.sendParticles(
                    ParticleTypes.FIREWORK,
                    this.getX(),
                    this.getEyeY(),
                    this.getZ(),
                    particlesToSpawn,
                    random.nextGaussian() * xOffset,
                    (random.nextGaussian() * yOffset) + 0.2f,
                    random.nextGaussian() * zOffset,
                    0.2f
            );
        }
    }

    @Override
    public void playerTouch(Player player) {
        if (!this.level().isClientSide() && !this.isRemoved()) {

            double ringRadiusSq = Math.pow((this.getBoundingBox().getSize() / 2) + 0.2, 2);
            Vec3 centerOfRing = this.getEyePosition();
            if (!intersectedPassed(player, ringRadiusSq, centerOfRing, player.getEyePosition())) {
                if (!intersectedPassed(player, ringRadiusSq, centerOfRing, player.getBoundingBox().getCenter())) {
                    intersectedPassed(player, ringRadiusSq, centerOfRing, player.getEyePosition().subtract(0, player.getEyeHeight(), 0));
                }
            }
        }

        super.playerTouch(player);
    }

    private boolean intersectedPassed(Player player, double ringRadiusSq, Vec3 centerOfRing, Vec3 playerPosToCheck) {
        Vec3 playerPoint = playerPosToCheck.subtract(centerOfRing);

        if ((playerPoint.x() * playerPoint.x()) +
            (playerPoint.y() * playerPoint.y()) +
            (playerPoint.z() * playerPoint.z()) < ringRadiusSq)
        {

            Vec3 normalVector = this.getLookAngle();
            // Source https://www.geeksforgeeks.org/distance-between-a-point-and-a-plane-in-3-d/#
            double numerator = Math.abs(
                    (normalVector.x() * playerPoint.x()) +
                    (normalVector.y() * playerPoint.y()) +
                    (normalVector.z() * playerPoint.z())
            );
            double divisor = Math.sqrt(
                    (normalVector.x() * normalVector.x()) +
                    (normalVector.y() * normalVector.y()) +
                    (normalVector.z() * normalVector.z())
            );
            double distanceBetweenPlayerAndPlane = numerator / divisor;
            double playerSpeed = (Math.pow(player.getSpeed() + 1 , 3) - 1) * 2;
            double rangeCheck = Math.max(0.2f, playerSpeed);
            if (distanceBetweenPlayerAndPlane < rangeCheck) {
                // Notify Essence Block
                if (this.blockEntity instanceof EssenceBlockEntity essenceBlockEntity && essenceBlockEntity.getBlockState().getBlock() instanceof EssenceBlockYellow essenceBlockYellow) {
                    essenceBlockYellow.ringActivated(essenceBlockEntity, (ServerPlayer) player);
                }
                this.makeServerParticle(50, (ServerLevel) this.level());
                this.remove(RemovalReason.DISCARDED);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean shouldRender(double x, double y, double z) {
        return true;
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag) {}

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundTag) {}

    @Override
    public void recreateFromPacket(ClientboundAddEntityPacket clientboundAddEntityPacket) {
        double d = clientboundAddEntityPacket.getX();
        double e = clientboundAddEntityPacket.getY();
        double f = clientboundAddEntityPacket.getZ();
        float g = clientboundAddEntityPacket.getYRot();
        float h = clientboundAddEntityPacket.getXRot();
        this.syncPacketPositionCodec(d, e, f);
        this.setId(clientboundAddEntityPacket.getId());
        this.setUUID(clientboundAddEntityPacket.getUUID());
        this.absMoveTo(d, e, f, g, h);
        this.setDeltaMovement(clientboundAddEntityPacket.getXa(), clientboundAddEntityPacket.getYa(), clientboundAddEntityPacket.getZa());
    }
}