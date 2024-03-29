package net.xiaoyu233.mitemod.miteite.trans.world;

import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;

@Mixin(WorldGenMinable.class)
public class WorldGenMinableTrans {
   @Shadow
   private int minableBlockId;
//   @Shadow
//   private int minable_block_metadata;
//   @Shadow
//   private int blockToReplace;
//
//   @Overwrite
//   public int growVein(World world, Random rand, int blocks_to_grow, int x, int y, int z, boolean must_be_supported, boolean is_dirt) {
//      if (blocks_to_grow >= 1 && world.blockExists(x, y, z) && world.getBlockId(x, y, z) == this.blockToReplace) {
//         if (must_be_supported && (y < 1 || world.isAirOrPassableBlock(x, y - 1, z, true))) {
//            return 0;
//         } else {
//            if (is_dirt && world.canBlockSeeTheSky(x, y + 1, z)) {
//               BiomeBase biome = world.getBiomeGenForCoords(x, z);
//               world.setBlock(x, y, z, biome != BiomeBase.desert && biome != BiomeBase.desertHills ? Block.grass.blockID : Block.sand.blockID, 0, 2);
//               world.setBlock(x, y, z, biome != BiomeBases.volcano ? Block.anvilAdamantium.blockID : Block.sand.blockID, 0, 2);
//            } else {
//               world.setBlock(x, y, z, this.minableBlockId, this.minable_block_metadata, 2);
//            }
//
//            int ore_blocks_grown = 1;
//
//            for(int attempts = 0; attempts < 16; ++attempts) {
//               int dx = 0;
//               int dy = 0;
//               int dz = 0;
//               int axis = rand.nextInt(3);
//               if (axis == 0) {
//                  dx = rand.nextInt(2) == 0 ? -1 : 1;
//               } else if (axis == 1) {
//                  dy = rand.nextInt(2) == 0 ? -1 : 1;
//               } else {
//                  dz = rand.nextInt(2) == 0 ? -1 : 1;
//               }
//
//               ore_blocks_grown += this.growVein(world, rand, blocks_to_grow - ore_blocks_grown, x + dx, y + dy, z + dz, must_be_supported, is_dirt);
//               if (ore_blocks_grown == blocks_to_grow) {
//                  break;
//               }
//            }
//
//            return ore_blocks_grown;
//         }
//      } else {
//         return 0;
//      }
//   }

   @Overwrite
   public int getRandomVeinHeight(World world, Random rand) {
      Block block = Block.blocksList[this.minableBlockId];
      if (world.isUnderworld()) {
         if (world.underworld_y_offset != 0) {
            if (block == Block.oreAdamantium) {
               return this.getMinVeinHeight(world) + rand.nextInt(this.getMaxVeinHeight(world) - this.getMinVeinHeight(world));
            }

            if (block instanceof BlockOre && rand.nextFloat() < 0.75F) {
               return this.getMinVeinHeight(world) + rand.nextInt(this.getMaxVeinHeight(world) - this.getMinVeinHeight(world));
            }
//            if (block instanceof BlockOre && rand.nextFloat() < 0.75F) {
//               return rand.nextInt(16 + world.underworld_y_offset);
//            }
         }

         return rand.nextInt(256);
      } else {
         float relative_height;
         if (block == Block.dirt) {
            do {
               relative_height = rand.nextFloat();
            } while(relative_height <= rand.nextFloat());
         } else if (block == Block.gravel) {
            do {
               relative_height = rand.nextFloat();
            } while(relative_height <= rand.nextFloat());
         } else if (block == Block.oreCoal) {
            do {
               relative_height = rand.nextFloat();
            } while(relative_height <= rand.nextFloat());
         } else if (block == Block.oreCopper) {
            if (rand.nextInt(2) == 0) {
               relative_height = rand.nextFloat() * 0.6F + 0.4F;
            } else {
               do {
                  relative_height = rand.nextFloat();
               } while(relative_height >= rand.nextFloat());
            }
         } else if (block == Block.oreSilver) {
            do {
               relative_height = rand.nextFloat();
            } while(relative_height >= rand.nextFloat());
         } else if (block == Block.oreGold) {
            do {
               relative_height = rand.nextFloat();
            } while(relative_height >= rand.nextFloat());
         } else if (block == Block.oreIron) {
            do {
               relative_height = rand.nextFloat();
            } while(relative_height >= rand.nextFloat());
         } else if (block == Block.oreMithril) {
            do {
               relative_height = rand.nextFloat();
            } while(relative_height >= rand.nextFloat());
         } else if (block != Block.oreAdamantium && block != Block.silverfish) {
            if (block == Block.oreRedstone) {
               do {
                  relative_height = rand.nextFloat();
               } while(relative_height >= rand.nextFloat());
            } else if (block == Block.oreDiamond) {
               do {
                  relative_height = rand.nextFloat();
               } while(relative_height >= rand.nextFloat());
            } else if (block == Blocks.fancyRed) {
               do {
                  relative_height = rand.nextFloat();
               } while(relative_height >= rand.nextFloat());
            } else {
               if (block != Block.oreLapis) {
                  Minecraft.setErrorMessage("WorldGenMinable: unknown ore id " + this.minableBlockId);
                  return -1;
               }

               relative_height = (rand.nextFloat() + rand.nextFloat()) / 2.0F;
            }
         } else {
            do {
               relative_height = rand.nextFloat();
            } while(relative_height >= rand.nextFloat());
         }

         int min_height = this.getMinVeinHeight(world);
         int height_range = this.getMaxVeinHeight(world) - min_height + 1;
         return min_height + (int)(relative_height * (float)height_range);
      }
   }

   @Overwrite
   public int getMinVeinHeight(World world) {
      Block block = Block.blocksList[this.minableBlockId];
      if (world.isUnderworld()) {
//         if (block == Block.oreAdamantium){
//            return 130;
//         }
         return 140;
      } else if (block == Block.dirt) {
         return 32;
      } else if (block == Block.gravel) {
         return 24;
      } else if (block == Block.oreCoal) {
         return 16;
      } else if (block == Block.oreCopper) {
         return 0;
      } else if (block == Block.oreSilver) {
         return 0;
      } else if (block == Block.oreGold) {
         return 0;
      } else if (block == Block.oreIron) {
         return 0;
      } else if (block == Block.oreMithril) {
         return 0;
      } else if (block == Block.oreAdamantium || block == Block.silverfish) {
         return 0;
      } else if (block == Block.oreRedstone) {
         return 0;
      } else if (block == Block.oreDiamond) {
         return 0;
      } else if (block == Blocks.fancyRed) {
         return 0;
      }else if (block == Block.oreLapis) {
         return 8;
      } else if(world.isTheNether()){
         return 35;
      }else {
         Minecraft.setErrorMessage("WorldGenMinable: unknown ore id " + block.blockID);
         return -1;
      }
   }

//   @Inject(method = "generate(Lnet/minecraft/World;Ljava/util/Random;IIIZ)Z",at = @At("HEAD"),cancellable = true)
//   private void injectMinableGenerate(World world, Random rand, int x, int y, int z, boolean vein_size_increases_with_depth,CallbackInfoReturnable<Boolean> callback){
//      if ((world.isUnderworld() || world.isTheNether()) && y < this.getMinVeinHeight(world)){
//         callback.setReturnValue(false);
//         callback.cancel();
//      }
//   }

   @Overwrite
   public int getMaxVeinHeight(World world) {
      Block block = Block.blocksList[this.minableBlockId];
      if (world.isUnderworld()) {
         return 225;
      } else if (world.isTheNether()){
         return 115;
      }else if (block == Block.dirt) {
         return 128;
      } else if (block == Block.gravel) {
         return 128;
      } else if (block == Block.oreCoal) {
         return 96;
      } else if (block == Block.oreCopper) {
         return 128;
      } else if (block == Block.oreSilver) {
         return 96;
      } else if (block == Block.oreGold) {
         return 48;
      } else if (block == Block.oreIron) {
         return 64;
      } else if (block == Block.oreMithril) {
         return 32;
      } else if (block == Block.oreAdamantium) {
         return 16;
      } else if (block == Block.silverfish) {
         return 24;
      } else if (block == Block.oreRedstone) {
         return 24;
      } else if (block == Block.oreDiamond) {
         return 32;
      } else if (block == Block.oreLapis) {
         return 40;
      } else {
         Minecraft.setErrorMessage("WorldGenMinable: unknown ore id " + block.blockID);
         return -1;
      }
   }
}
