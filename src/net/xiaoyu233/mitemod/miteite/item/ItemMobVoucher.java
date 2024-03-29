package net.xiaoyu233.mitemod.miteite.item;

import net.minecraft.*;

import java.util.List;

public class ItemMobVoucher extends Item {
    String entityName = "";
    public ItemMobVoucher(int id, String entityName) {
        super(id, Materials.copper, "voucher/voucher_" + entityName);
        this.entityName = entityName;
        this.setMaxStackSize(8);
        this.setCreativeTab(CreativeModeTab.tabMaterials);
    }

    @Override
    public boolean isHarmedByAcid() {
        return false;
    }

    @Override
    public boolean canCatchFire() {
        return false;
    }

    public void addInformation(ItemStack item_stack, EntityPlayer player, List info, boolean extended_info, Slot slot) {
        if (extended_info) {
            info.add(" ");
            switch (this.entityName) {
                case "core":
                    info.add(EnumChatFormat.BROWN + Translator.getFormatted("振金锭材料之一", new Object[0]));
                    info.add(EnumChatFormat.BLUE + Translator.getFormatted("由怪物击杀凭证合成", new Object[0]));
                    break;
                case "fishing":
                    info.add(EnumChatFormat.BROWN + Translator.getFormatted("振金棒凭证材料之一", new Object[0]));
                    info.add(EnumChatFormat.BLUE + Translator.getFormatted("钓鱼1/48概率获得", new Object[0]));
                    break;
                case "planting":
                    info.add(EnumChatFormat.BROWN + Translator.getFormatted("振金棒凭证材料之一", new Object[0]));
                    info.add(EnumChatFormat.BLUE + Translator.getFormatted("种植1/50概率获得", new Object[0]));
                    break;
                case "villager":
                    info.add(EnumChatFormat.BROWN + Translator.getFormatted("振金棒凭证材料之一", new Object[0]));
                    info.add(EnumChatFormat.BLUE + Translator.getFormatted("与村民交易1/50概率获得或村民繁殖1/10获得", new Object[0]));
                    break;
                case "club_core":
                    info.add(EnumChatFormat.BROWN + Translator.getFormatted("振金棒和攻伐之剑合成材料之一", new Object[0]));
                    info.add(EnumChatFormat.BLUE + Translator.getFormatted("垂钓凭证+村民凭证+种植凭证", new Object[0]));
                    break;
                case "zombieboss":
                    info.add(EnumChatFormat.BROWN + Translator.getFormatted("攻伐之剑合成材料之一", new Object[0]));
                    info.add(EnumChatFormat.BLUE + Translator.getFormatted("僵尸Boss掉落", new Object[0]));
                    break;
                case "ghast":
                    info.add(EnumChatFormat.BROWN + Translator.getFormatted("金身核心合成材料之一", new Object[0]));
                    info.add(EnumChatFormat.BLUE + Translator.getFormatted("恶魂掉落", new Object[0]));
                    break;
                case "bedrock":
                    info.add(EnumChatFormat.BROWN + Translator.getFormatted("金身核心合成材料之一", new Object[0]));
                    info.add(EnumChatFormat.BLUE + Translator.getFormatted("基岩元素掉落", new Object[0]));
                    break;
                case "vibraniumdoor":
                    info.add(EnumChatFormat.BROWN + Translator.getFormatted("金身核心合成材料之一", new Object[0]));
                    info.add(EnumChatFormat.BLUE + Translator.getFormatted("盗贼僵尸领主掉落", new Object[0]));
                    break;
                case "goldbody_core":
                    info.add(EnumChatFormat.BROWN + Translator.getFormatted("无懈可击之甲合成材料之一", new Object[0]));
                    info.add(EnumChatFormat.BLUE + Translator.getFormatted("由恶魂,基岩元素,盗贼僵尸领主,骷髅boss凭证合成", new Object[0]));
                    break;
                case "skeletonboss":
                    info.add(EnumChatFormat.BROWN + Translator.getFormatted("金身核心合成材料之一", new Object[0]));
                    info.add(EnumChatFormat.BLUE + Translator.getFormatted("骷髅boss掉落", new Object[0]));
                    break;
                case "ultimateannihilation":
                    info.add(EnumChatFormat.BROWN + Translator.getFormatted("???合成材料之一", new Object[0]));
                    info.add(EnumChatFormat.BLUE + Translator.getFormatted("终湮骷髅掉落", new Object[0]));
                    break;
                default:
                    info.add(EnumChatFormat.BROWN + Translator.getFormatted("振金核心材料之一", new Object[0]));
                    info.add(EnumChatFormat.BLUE + Translator.getFormatted("击杀特定怪物掉落", new Object[0]));
                    break;
            }
        }
    }
}
