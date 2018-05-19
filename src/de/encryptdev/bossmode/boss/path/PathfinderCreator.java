package de.encryptdev.bossmode.boss.path;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.ref.Reflection;
import net.minecraft.server.v1_12_R1.EntitySpider;

/**
 * Created by EncryptDev
 */
public class PathfinderCreator extends Reflection {

    private Class<?> entityInsentientClass;
    private Class<?> entityCreatureClass;

    public PathfinderCreator() {
        super(BossMode.getInstance().getNmsVersion());
        this.entityInsentientClass = getNMSClass("EntityInsentient");
        this.entityCreatureClass = getNMSClass("EntityCreature");
    }

    public Object createPathfinderGoalFloat(Object entityInsentient) {
        return getNewInstanceConstructor(getNMSClass("PathfinderGoalFloat"), new Class<?>[]{entityInsentientClass}, new Object[]{entityInsentient});
    }

    public Object createPathfinderGoalMeleeAttack(Object entityCreature, Class<?> attackClass, double attacKWalkSpeed) {
        return getNewInstanceConstructor(getNMSClass("PathfinderGoalMeleeAttack"), new Class<?>[]{entityCreatureClass,
                Class.class, double.class, boolean.class}, new Object[]{entityCreature, attackClass, attacKWalkSpeed, false});
    }

    public Object createPathfinderGoalMeleeAttack(Object entityCreature, double attackWalkSpeed) {
        return getNewInstanceConstructor(getNMSClass("PathfinderGoalMeleeAttack"), new Class<?>[]{entityCreatureClass, double.class, boolean.class}, new Object[]{
                entityCreature, attackWalkSpeed, false
        });
    }

    public Object createPathfinderGoalNearestAttackableTarget(Object entityCreature, Class<?> toAttack) {
        return getNewInstanceConstructor(getNMSClass("PathfinderGoalNearestAttackableTarget"), new Class<?>[]{entityCreatureClass, Class.class, boolean.class},
                new Object[]{entityCreature, toAttack, true});
    }

    public Object createPathfinderGoalArrowAttack(Object irangedEntity, double speed) {
        /**
         *
         * double = walkspeed
         * int = cooldown
         * float = maxDistance
         *
         */

        return getNewInstanceConstructor(getNMSClass("PathfinderGoalArrowAttack"), new Class<?>[]{getNMSClass("IRangedEntity"), double.class, int.class, float.class},
                new Object[]{irangedEntity, speed, 12, 40});
    }

    public Object createPatfhinderCreeper(Object creeperEntity) {
        return getNewInstanceConstructor(getNMSClass("PathfinderGoalSwell"), new Class<?>[]{getNMSClass("EntityCreeper")}, new Object[]{creeperEntity});
    }

    public Object createPafhinderGoalMeleeAttackSpider(Object entitySpider) {
        return getNewInstanceConstructor(getNMSClass("EntitySpider$PathfinderGoalSpiderMeleeAttack"), new Class<?>[]{entitySpider.getClass()}, new Object[]{entitySpider});
    }

    public Object createPathfinderGoalMoveTowardsRestriction(Object entityCreature) {
        return getNewInstanceConstructor(getNMSClass("PathfinderGoalMoveTowardsRestriction"), new Class<?>[]{entityCreatureClass, double.class},
                new Object[]{entityCreature, 1.0D});
    }

    public Object createPathfinderNearestAttackableTargetSpider(Object entitySpider) {
        return getNewInstanceConstructor(getNMSClass("PathfinderGoalSpiderNearestAttackableTarget"), new Class<?>[]{entitySpider.getClass(), Class.class},
                new Object[]{entitySpider, getNMSClass("EntityHuman")});
    }

    public Object createPathfinderGoalRandomStrollLand(Object entityCreature) {
        return getNewInstanceConstructor(getNMSClass("PathfinderGoalRandomStroll"), new Class<?>[]{entityCreatureClass, double.class},
                new Object[]{entityCreature, 1.0D});
    }

    public Object createPathfinderGoalLookAtPlayer(Object entityInsentient) {
        return getNewInstanceConstructor(getNMSClass("PathfinderGoalLookAtPlayer"), new Class<?>[]{entityInsentientClass, Class.class, float.class},
                new Object[]{entityInsentient, getNMSClass("EntityHuman"), 8.0f});
    }

    public Object createPathfinderGoalRandomLookaround(Object entityInsentient) {
        return getNewInstanceConstructor(getNMSClass("PathfinderGoalRandomLookaround"), new Class<?>[]{entityInsentientClass}, new Object[]{entityInsentient});
    }

}
