package slimeknights.tconstruct.tools.modifiers.traits.skull;

import io.github.fabricators_of_create.porting_lib.extensions.MobEffectInstanceExtensions;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class PlagueModifier extends NoLevelsModifier {
  @Override
  public void attackWithArmor(IToolStackView tool, int level, EquipmentContext context, EquipmentSlot slotType, LivingEntity target, DamageSource source, float amount, boolean isDirectDamage) {
    if (isDirectDamage && !source.isProjectile()) {
      // copy all negative effects to target
      for (MobEffectInstance effect : context.getEntity().getActiveEffects()) {
        if (!effect.getEffect().isBeneficial() && !((MobEffectInstanceExtensions)effect).getCurativeItems().isEmpty()) {
          target.addEffect(new MobEffectInstance(effect));
        }
      }
    }
  }
}
