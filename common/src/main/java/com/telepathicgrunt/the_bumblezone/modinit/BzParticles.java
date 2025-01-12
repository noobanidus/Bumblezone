package com.telepathicgrunt.the_bumblezone.modinit;

import com.telepathicgrunt.the_bumblezone.Bumblezone;
import com.telepathicgrunt.the_bumblezone.modinit.registry.RegistryEntry;
import com.telepathicgrunt.the_bumblezone.modinit.registry.ResourcefulRegistries;
import com.telepathicgrunt.the_bumblezone.modinit.registry.ResourcefulRegistry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;

public class BzParticles {
    public static final ResourcefulRegistry<ParticleType<?>> PARTICLE_TYPES = ResourcefulRegistries.create(BuiltInRegistries.PARTICLE_TYPE, Bumblezone.MODID);

    public static final RegistryEntry<SimpleParticleType> POLLEN_PARTICLE = PARTICLE_TYPES.register("pollen_puff_particle", () -> new SimpleParticleType(false){});
    public static final RegistryEntry<SimpleParticleType> HONEY_PARTICLE = PARTICLE_TYPES.register("honey_particle", () -> new SimpleParticleType(false){});
    public static final RegistryEntry<SimpleParticleType> SPARKLE_PARTICLE = PARTICLE_TYPES.register("sparkle_particle", () -> new SimpleParticleType(false){});
    public static final RegistryEntry<SimpleParticleType> ROYAL_JELLY_PARTICLE = PARTICLE_TYPES.register("royal_jelly_particle", () -> new SimpleParticleType(false){});
}
