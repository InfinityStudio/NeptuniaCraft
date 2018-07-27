package norg.neptunia.capability;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import norg.neptunia.inventory.InventoryExtended;
import norg.neptunia.item.weapon.*;

import java.util.ArrayList;

/**
 * Created by The Sea on 2016/4/16.
 */
public class ListCharacter {

    public static class Information {
        int superPower, exeDriver, exp, level, maxSP, maxDriver, exeLevel;
        boolean HDD;
        String name;
        ResourceLocation resourceLocation;
        InventoryExtended inventory;
        Item weapon;

        Information(String name, String pack, String location, Item weapon) {
            this.name = name;
            this.resourceLocation = new ResourceLocation(pack, location);
            this.superPower = 0;
            this.exeDriver = 0;
            this.exp = 0;
            this.level = 1;
            this.exeLevel = 0;
            this.maxDriver = 1000;
            this.maxSP = 1000;
            this.HDD = false;
            this.inventory = new InventoryExtended();
            this.weapon = weapon;
        }

        public int getSuperPower() {
            return this.superPower;
        }

        public int getExeDriver() {
            return this.exeDriver;
        }

        public int getExp() {
            return this.exp;
        }

        public int getLevel() {
            return this.level;
        }

        public int getMaxSP(){
            return this.maxSP;
        }

        public int getMaxDriver() {
            return this.maxDriver;
        }

        public int getExeLevel() { return this.exeLevel; }

        public boolean getHDD() {
            return this.HDD;
        }

        public Item getWeapon() { return this.weapon; }

        public String getName() {
            return this.name;
        }

        public ResourceLocation getResourceLocation() {
            return this.resourceLocation;
        }

        public InventoryExtended getInventory() { return this.inventory; }

        public void setSuperPower(int superPower) { this.superPower = superPower; }

        public void setExeDriver(int exeDriver) { this.exeDriver = exeDriver; }

        public void setExp(int exp) {
            this.exp = exp;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public void setMaxSP(int maxSP) {
            this.maxSP = maxSP;
        }

        public void setMaxDriver(int maxDriver) {
            this.maxDriver = maxDriver;
        }

        public void setExeLevel(int exeLevel) { this.exeLevel = exeLevel; }

        public void setHDD(boolean HDD) {
            this.HDD = HDD;
        }
    }

    public ArrayList<Information> characterList = new ArrayList<Information>();

    public ListCharacter() {
        String pack = "neptunia";
        String location = "textures/gui/charactor/";
        characterList.add(new Information("Neptune", pack, location + "neptune.png", new ItemSwordNeptune()));
        characterList.add(new Information("Noire", pack, location + "noire.png", new ItemKnifeNoire()));
        characterList.add(new Information("Blanc", pack, location + "blanc.png", new ItemAxeBlanc()));
        characterList.add(new Information("Vert", pack, location + "vert.png", new ItemGunVert()));
        characterList.add(new Information("Nepgear", pack, location + "nepgear.png", new ItemWandNepgear()));
        characterList.add(new Information("Uni", pack, location + "uni.png", null));
    }
}
