package de.encryptdev.bossmode.boss.path;

import de.encryptdev.bossmode.BossMode;
import de.encryptdev.bossmode.boss.IBoss;
import de.encryptdev.bossmode.ref.Reflection;
import org.bukkit.entity.LivingEntity;

import java.util.List;
import java.util.Set;

/**
 * Created by EncryptDev
 */
public class BossPathfinderEdited extends Reflection {

    private IBoss iBoss;
    private Object handleLivingEntity;
    private Object goalSelector;
    private Object targetSelector;
    private PathfinderCreator pathfinderCreator;
    private boolean lookAtPlayer;
    private List<String> nearAttackEntities;

    public BossPathfinderEdited(IBoss iBoss) {
        super(BossMode.getInstance().getNmsVersion());
        this.iBoss = iBoss;
        this.pathfinderCreator = new PathfinderCreator();
        handleLivingEntity = invokeMethod(iBoss.getBossEntity().getClass(), "getHandle", iBoss.getBossEntity(), new Class<?>[0], new Object[0]);
        this.lookAtPlayer = iBoss.getBossSettings().isLookAtPlayer();
        this.nearAttackEntities = iBoss.getBossSettings().getNearAttackEntities();
        initPathfinder();
    }

    public BossPathfinderEdited(LivingEntity livingEntity, boolean lookAtPlayer, List<String> nearAttackEntities) {
        super(BossMode.getInstance().getNmsVersion());
        this.pathfinderCreator = new PathfinderCreator();
        handleLivingEntity = invokeMethod(livingEntity.getClass(), "getHandle", livingEntity, new Class<?>[0], new Object[0]);
        this.lookAtPlayer = lookAtPlayer;
        this.nearAttackEntities = nearAttackEntities;
    }

    private void initPathfinder() {

        goalSelector = getField(getNMSClass("EntityInsentient"), handleLivingEntity, "goalSelector");
        targetSelector = getField(getNMSClass("EntityInsentient"), handleLivingEntity, "targetSelector");

        if (BossMode.getInstance().getNmsVersion() == NMSVersion.V1_8_R1 || BossMode.getInstance().getNmsVersion() == NMSVersion.V1_8_R2 ||
                BossMode.getInstance().getNmsVersion() == NMSVersion.V1_8_R3) {

            List<Object> goalB = (List<Object>) getField(getNMSClass("PathfinderGoalSelector"), goalSelector, "b");
            List<Object> goalC = (List<Object>) getField(getNMSClass("PathfinderGoalSelector"), goalSelector, "b");
            List<Object> targetB = (List<Object>) getField(getNMSClass("PathfinderGoalSelector"), targetSelector, "b");
            List<Object> targetC = (List<Object>) getField(getNMSClass("PathfinderGoalSelector"), targetSelector, "c");

            goalB.clear();
            goalC.clear();
            targetB.clear();
            targetC.clear();

            goalSelectorR(0, pathfinderCreator.createPathfinderGoalFloat(handleLivingEntity));
            goalSelectorR(5, pathfinderCreator.createPathfinderGoalMoveTowardsRestriction(handleLivingEntity));
            goalSelectorR(7, pathfinderCreator.createPathfinderGoalRandomStrollLand(handleLivingEntity));
            if (iBoss.getBossSettings().isLookAtPlayer()) {
                goalSelectorR(8, pathfinderCreator.createPathfinderGoalLookAtPlayer(handleLivingEntity));
            }
            goalSelectorR(8, pathfinderCreator.createPathfinderGoalRandomLookaround(handleLivingEntity));
            targetSelectorR(1, pathfinderCreator.createPathfinderGoalNearestAttackableTarget(handleLivingEntity, getNMSClass("EntityHuman")));

            if (nearAttackEntities.isEmpty()) {
                if (handleLivingEntity.getClass().equals(getNMSClass("EntitySpider")) || handleLivingEntity.getClass().equals("EntityCaveSpider"))
                    goalSelectorR(2, pathfinderCreator.createPafhinderGoalMeleeAttackSpider(handleLivingEntity));
                else if (handleLivingEntity.getClass().equals(getNMSClass("EntitySkeleton")))
                    goalSelectorR(2, pathfinderCreator.createPathfinderGoalArrowAttack(handleLivingEntity, iBoss.getBossSettings().getSpeed()));
                else if (handleLivingEntity.getClass().equals(getNMSClass("EntityCreeper")))
                    goalSelectorR(2, pathfinderCreator.createPatfhinderCreeper(handleLivingEntity));
                else
                    goalSelectorR(2, pathfinderCreator.createPathfinderGoalMeleeAttack(handleLivingEntity, getNMSClass("EntityHuman"), iBoss.getBossSettings().getSpeed()));
            } else {
                int s = 1;
                targetSelectorR(1, pathfinderCreator.createPathfinderGoalMeleeAttack(handleLivingEntity, getNMSClass("EntityHuman"), iBoss.getBossSettings().getSpeed()));
                goalSelectorR(1, pathfinderCreator.createPathfinderGoalMeleeAttack(handleLivingEntity, getNMSClass("EntityHuman"), iBoss.getBossSettings().getSpeed()));

                for (int i = 0; i < nearAttackEntities.size(); i++) {
                    try {
                        targetSelectorR(s++, pathfinderCreator.createPathfinderGoalNearestAttackableTarget(handleLivingEntity, Class.forName(nearAttackEntities.get(i))));
                        goalSelectorR(s++, pathfinderCreator.createPathfinderGoalMeleeAttack(handleLivingEntity, Class.forName(nearAttackEntities.get(i)), iBoss.getBossSettings().getSpeed()));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }

        } else {
            Set<Object> goalB = (Set<Object>) getField(getNMSClass("PathfinderGoalSelector"), goalSelector, "b");
            Set<Object> goalC = (Set<Object>) getField(getNMSClass("PathfinderGoalSelector"), goalSelector, "c");
            Set<Object> targetB = (Set<Object>) getField(getNMSClass("PathfinderGoalSelector"), targetSelector, "b");
            Set<Object> targetC = (Set<Object>) getField(getNMSClass("PathfinderGoalSelector"), targetSelector, "c");
            goalB.clear();
            goalC.clear();
            targetB.clear();
            targetC.clear();

            goalSelectorR(0, pathfinderCreator.createPathfinderGoalFloat(handleLivingEntity));
            if (handleLivingEntity.getClass().equals(getNMSClass("EntitySpider")) || handleLivingEntity.getClass().equals("EntityCaveSpider"))
                goalSelectorR(2, pathfinderCreator.createPafhinderGoalMeleeAttackSpider(handleLivingEntity));
            else if (handleLivingEntity.getClass().equals(getNMSClass("EntitySkeleton")))
                goalSelectorR(2, pathfinderCreator.createPathfinderGoalArrowAttack(handleLivingEntity, iBoss.getBossSettings().getSpeed()));
            else if (handleLivingEntity.getClass().equals(getNMSClass("EntityCreeper")))
                goalSelectorR(2, pathfinderCreator.createPatfhinderCreeper(handleLivingEntity));
            else
                goalSelectorR(2, pathfinderCreator.createPathfinderGoalMeleeAttack(handleLivingEntity, iBoss.getBossSettings().getSpeed()));
            goalSelectorR(5, pathfinderCreator.createPathfinderGoalMoveTowardsRestriction(handleLivingEntity));
            goalSelectorR(7, pathfinderCreator.createPathfinderGoalRandomStrollLand(handleLivingEntity));
            if (lookAtPlayer) {
                goalSelectorR(8, pathfinderCreator.createPathfinderGoalLookAtPlayer(handleLivingEntity));
            }
            goalSelectorR(8, pathfinderCreator.createPathfinderGoalRandomLookaround(handleLivingEntity));
            targetSelectorR(3, pathfinderCreator.createPathfinderGoalNearestAttackableTarget(handleLivingEntity, getNMSClass("EntityHuman")));
            if (!nearAttackEntities.isEmpty()) {
                int s = 4;
                for (int i = 0; i < nearAttackEntities.size(); i++)
                    try {
                        targetSelectorR(s++, pathfinderCreator.createPathfinderGoalNearestAttackableTarget(handleLivingEntity, Class.forName(nearAttackEntities.get(i))));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
            }
        }
    }

    private void targetSelectorR(int i, Object pathfinderGoal) {
        invokeMethod(targetSelector.getClass(), "a", targetSelector, new Class<?>[]{int.class, getNMSClass("PathfinderGoal")}, new Object[]{i, pathfinderGoal});
    }

    private void goalSelectorR(int i, Object pathfinderGoal) {
        invokeMethod(goalSelector.getClass(), "a", goalSelector, new Class<?>[]{int.class, getNMSClass("PathfinderGoal")}, new Object[]{i, pathfinderGoal});
    }

}
