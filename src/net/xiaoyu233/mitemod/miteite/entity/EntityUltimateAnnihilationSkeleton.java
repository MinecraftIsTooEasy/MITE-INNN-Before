//package net.xiaoyu233.mitemod.miteite.entity;
//
//import net.minecraft.*;
//import net.xiaoyu233.fml.util.Utils;
//import net.xiaoyu233.mitemod.miteite.item.Items;
//import net.xiaoyu233.mitemod.miteite.item.enchantment.Enchantments;
//import net.xiaoyu233.mitemod.miteite.util.Configs;
//
//public class EntityUltimateAnnihilationSkeleton extends EntitySkeleton {
//    private Enchantment [] enhanceSpecialBookList = new Enchantment[] {Enchantment.protection, Enchantment.sharpness,  Enchantment.fortune, Enchantment.harvesting, Enchantments.EXTEND, Enchantment.efficiency, Enchantment.vampiric, Enchantment.butchering, Enchantment.featherFalling};
//    private Enchantment [] nonLevelsBookList = new Enchantment[] {Enchantments.enchantmentFixed, Enchantments.enchantmentChain, Enchantments.EMERGENCY, Enchantments.EnchantmentForge};
//    private boolean attackedByPlayer;
//    private int despawnCount;
//    private boolean haveTryToSpawnExchanger = false;
//    private final ItemStack weapon = Utils.safeMake(()-> {
//        ItemStack itemStack = new ItemStack(Items.infinitySword);
//        itemStack.addEnchantment(Enchantment.knockback,10);
//        itemStack.addEnchantment(Enchantments.EXTEND,5);
//        return itemStack;
//    }, null);
//    protected void addRandomEquipment() {
//        this.setCurrentItemOrArmor(0, new ItemStack(Items.VIBRANIUM_WAR_HAMMER, 1).randomizeForMob(this, true));
//        this.setCurrentItemOrArmor(1, new ItemStack(Items.VIBRANIUM_HELMET, 1).randomizeForMob(this, true));
//        this.setCurrentItemOrArmor(2, new ItemStack(Items.VIBRANIUM_CHESTPLATE, 1).randomizeForMob(this, true));
//        this.setCurrentItemOrArmor(3, new ItemStack(Items.VIBRANIUM_LEGGINGS, 1).randomizeForMob(this, true));
//        this.setCurrentItemOrArmor(4, new ItemStack(Items.VIBRANIUM_BOOTS, 1).randomizeForMob(this, true));
//        this.addPotionEffect(new MobEffect(1, 2147483647, 0));
//        this.addPotionEffect(new MobEffect(5, 2147483647, 0));
//    }
//    private int particleCount;
//    public EntityUltimateAnnihilationSkeleton(World par1World) {
//        super(par1World);
//        this.setHeldItemStack(weapon);
//    }
//
//    @Override
//    public boolean canCatchFire() {
//        return false;
//    }
//
//    @Override
//    public void calcFallDamage(float fall_distance, float[] damages) {
//        damages[0] = 0.0F;
//        damages[1] = 0.0F;
//    }
//
//    @Override
//    public void setAttackTarget(EntityLiving par1EntityLivingBase) {
//        super.setAttackTarget(par1EntityLivingBase);
//        this.attackedByPlayer = true;
//    }
//
//    @Override
//    public void setEntityToAttack(Entity par1Entity) {
//        super.setEntityToAttack(par1Entity);
//        this.attackedByPlayer = true;
//    }
//
//    @Override
//    public void addPotionEffect(MobEffect par1PotionEffect) {
//
//    }
//
//    @Override
//    public void addRandomWeapon() {
//        this.setHeldItemStack(weapon);
//    }
//
//    @Override
//    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
//        super.writeEntityToNBT(par1NBTTagCompound);
//        par1NBTTagCompound.setBoolean("AttackedByPlayer",attackedByPlayer);
//    }
//
//    @Override
//    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
//        super.readEntityFromNBT(par1NBTTagCompound);
//        if (par1NBTTagCompound.hasKey("AttackedByPlayer")) {
//            this.attackedByPlayer = par1NBTTagCompound.getBoolean("AttackedByPlayer");
//        }
//    }
//
//    @Override
//    protected void addRandomArmor() {
//
//    }
//
//    @Override
//    public void onLivingUpdate() {
//        super.onLivingUpdate();
//        if (this.onServer())
//            if (this.attackedByPlayer) {
//                if (despawnCount < Configs.wenscConfig.annihilationSkeletonDespawnTime.ConfigValue) {
//                    despawnCount++;
//                } else {
//                    this.entityFX(EnumEntityFX.summoned);
//                    this.setDead();
//                }
//            }
//        if (particleCount > 0) {
//            particleCount--;
//        } else {
//            this.particleCount = 20;
//            this.generateRandomParticles(EnumParticle.largesmoke);
//            this.generateRandomParticles(EnumParticle.largesmoke);
//        }
//        if(Configs.wenscConfig.isSpawnExchanger.ConfigValue) {
//            if (!this.getWorld().isRemote) {
//                EntityLiving target = this.getAttackTarget();
//                if (target instanceof EntityPlayer) {
//                    if (!haveTryToSpawnExchanger) {
//                        if (rand.nextInt(50) == 0) {
//                            EntityExchanger entityExchanger = new EntityExchanger(this.worldObj);
//                            entityExchanger.setPosition(this.posX, this.posY, this.posZ);
//                            entityExchanger.refreshDespawnCounter(-9600);
//                            this.worldObj.spawnEntityInWorld(entityExchanger);
//                            entityExchanger.onSpawnWithEgg(null);
//                            entityExchanger.setAttackTarget(this.getTarget());
//                            entityExchanger.entityFX(EnumEntityFX.summoned);
//                        }
//                        this.haveTryToSpawnExchanger = true;
//                    }
//                }
//            }
//        }
//    }
//
//    @Override
//    public boolean isHarmedByLava() {
//        return false;
//    }
//
//    @Override
//    public boolean isPushedByWater() {
//        return false;
//    }
//
//    @Override
//    public void setInWeb() {
//
//    }
//
//    @Override
//    public boolean handleWaterMovement() {
//        return true;
//    }
//
//    @Override
//    public boolean canPickUpLoot() {
//        return false;
//    }
//
//    @Override
//    public boolean canBeKnockedBack() {
//        return false;
//    }
//
//    @Override
//    public boolean handleLavaMovement() {
//        return false;
//    }
//
//    @Override
//    protected void applyEntityAttributes() {
//        super.applyEntityAttributes();
//        this.setEntityAttribute(GenericAttributes.maxHealth,100);
//        this.setEntityAttribute(GenericAttributes.attackDamage,2147483647);
//        this.setEntityAttribute(GenericAttributes.movementSpeed, 0.2992D);
//    }
//
//    @Override
//    public EntityDamageResult attackEntityFrom(Damage damage) {
//        if (damage.isIndirect() || !damage.isMelee() || damage.isExplosion() || damage.isArrowDamage() || !(damage.getResponsibleEntityP() instanceof EntityPlayer)){
//            damage.setAmount(0f);
//        }else if (damage.getResponsibleEntityP() instanceof EntityPlayer){
//            attackedByPlayer = true;
//            if (Math.abs(this.getFootPosY() - ((EntityPlayer) damage.getResponsibleEntityP()).getFootPosY()) >= 3.0D){
//                damage.setAmount(0f);
//                return null;
//            }
//        }
//        return super.attackEntityFrom(damage);
//
//    }
//
//    @Override
//    protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
//        if (recently_hit_by_player){
//            this.dropItem(Items.voucherUltimateAnnihilation);
//            this.dropItemStack(new ItemStack(Items.VIBRANIUM_NUGGET,20));
//            this.dropItemStack(new ItemStack(Item.diamond,10));
//            Enchantment dropEnchantment = enhanceSpecialBookList[rand.nextInt(enhanceSpecialBookList.length)];
//        }
//    }
//
//    @Override
//    public boolean getCanSpawnHere(boolean perform_light_check) {
//        return !this.worldObj.isOverworld()
//                || (this.worldObj.getDayOfOverworld() > 32 && this.rand.nextInt(4) < 1 && this.worldObj.getClosestPlayerToEntity(this,24,true) == null && (Configs.wenscConfig.annihilationSkeletonSpawnInLight.ConfigValue || this.isValidLightLevel()));
//    }
//
//    @Override
//    public boolean canSpawnInShallowWater() {
//        return false;
//    }
//
//    @Override
//    public GroupDataEntity onSpawnWithEgg(GroupDataEntity par1EntityLivingData) {
//        return null;
//    }
//
//    @Override
//    public boolean canBeDisarmed() {
//        return false;
//    }
//}
