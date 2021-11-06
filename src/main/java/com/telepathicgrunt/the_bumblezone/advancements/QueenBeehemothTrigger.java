package com.telepathicgrunt.the_bumblezone.advancements;

import com.google.gson.JsonObject;
import com.telepathicgrunt.the_bumblezone.Bumblezone;
import net.minecraft.advancements.criterion.AbstractCriterionTrigger;
import net.minecraft.advancements.criterion.CriterionInstance;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.loot.ConditionArrayParser;
import net.minecraft.util.ResourceLocation;

public class QueenBeehemothTrigger extends AbstractCriterionTrigger<QueenBeehemothTrigger.Instance> {
    private static final ResourceLocation ID = new ResourceLocation(Bumblezone.MODID, "created_queen_beehemoth");

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    public QueenBeehemothTrigger.Instance createInstance(JsonObject jsonObject, EntityPredicate.AndPredicate predicate, ConditionArrayParser conditionArrayParser) {
        return new QueenBeehemothTrigger.Instance(predicate);
    }

    public void trigger(ServerPlayerEntity serverPlayerEntity) {
        super.trigger(serverPlayerEntity, (e) -> true);
    }

    public static class Instance extends CriterionInstance {
        public Instance(EntityPredicate.AndPredicate predicate) {
            super(QueenBeehemothTrigger.ID, predicate);
        }
    }
}