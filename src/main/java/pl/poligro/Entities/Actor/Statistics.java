/*
 * Copyright 2018 Marek Morawiec
 * User: marek
 * Date: 30.05.2018
 * Time: 19:54
 */

package pl.poligro.Entities.Actor;

public class Statistics {

    private Integer strength, dexterity, stamina, intelligence;
    private Integer attackPower;
    private Float hitChance, critChance;
    private Integer health, armor, dodgeChange, blockChance;

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Integer getDexterity() {
        return dexterity;
    }

    public void setDexterity(Integer dexterity) {
        this.dexterity = dexterity;
    }

    public Integer getStamina() {
        return stamina;
    }

    public void setStamina(Integer stamina) {
        this.stamina = stamina;
    }

    public Integer getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(Integer intelligence) {
        this.intelligence = intelligence;
    }

    public Integer getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(Integer attackPower) {
        this.attackPower = attackPower;
    }

    public Float getHitChance() {
        return hitChance;
    }

    public void setHitChance(Float hitChance) {
        this.hitChance = hitChance;
    }

    public Float getCritChance() {
        return critChance;
    }

    public void setCritChance(Float critChance) {
        this.critChance = critChance;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public Integer getArmor() {
        return armor;
    }

    public void setArmor(Integer armor) {
        this.armor = armor;
    }

    public Integer getDodgeChange() {
        return dodgeChange;
    }

    public void setDodgeChange(Integer dodgeChange) {
        this.dodgeChange = dodgeChange;
    }

    public Integer getBlockChance() {
        return blockChance;
    }

    public void setBlockChance(Integer blockChance) {
        this.blockChance = blockChance;
    }
}
