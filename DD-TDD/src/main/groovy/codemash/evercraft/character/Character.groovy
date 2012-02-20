package codemash.evercraft.character

import codemash.evercraft.classes.ClassType
import codemash.evercraft.races.Race
import codemash.evercraft.combat.Combat

class Character {
	def name
	
	int armorClass = 10, damage = 0, experiencePoints = 0, level = 1, attackAdjustment = 0
	Ability strength, dexterity, constitution, wisdom, intelligence, charisma
	Alignment alignment = Alignment.NEUTRAL
	def abilities = new EnumMap(AbilityName) 
	Race race;
	ClassType classType
	
	Character(String aName) {
		name = aName
		instantiateAbilities()
		classType = ClassType.PEASANT;
		race = Race.HUMAN;
	}
	
	Character(String aName, ClassType myClassType) {
		this(aName)
		classType = myClassType
	}
	
	public Character(String aName, Race myRace) {
		this(aName)
		race = myRace
	}
	
	private Character() {}
	
	void instantiateAbilities() {
		strength = new Ability(AbilityName.STRENGTH)
		dexterity = new Ability(AbilityName.DEXTERITY)
		constitution = new Ability(AbilityName.CONSTITUTION)
		wisdom = new Ability(AbilityName.WISDOM)
		intelligence = new Ability(AbilityName.INTELLIGENCE)
		charisma = new Ability(AbilityName.CHARISMA)
		abilities.put(AbilityName.STRENGTH, strength)
		abilities.put(AbilityName.DEXTERITY, dexterity)
		abilities.put(AbilityName.CONSTITUTION, constitution)
		abilities.put(AbilityName.WISDOM, wisdom)
		abilities.put(AbilityName.INTELLIGENCE, intelligence)
		abilities.put(AbilityName.CHARISMA, charisma)
	}

	boolean isAlive() {
		getHitPoints() > damage
	}
	
	int getHitPoints() {
		Math.max((classType.hitPointsPerLevel() + constitution.modifier) * getLevel(), getLevel())
	}
	
	int getLevel() {
		Math.floor(experiencePoints / 1000) + 1
	}
	
	int getAttackAdjustment() {
		classType.levelAttackAdjustment(getLevel()) + abilities.get(classType.abilityAttackModifier()).modifier
	}
	
	int calculateCriticalHitDamage(int damage) {
		if (damage < 1) {
			return  1
		} 
		damage * classType.criticalHitMultiplier()
	}
	
	int getEffectiveArmorClass() {
		Combat.effectiveArmorClass(this)
	}
	
	int getAlignmentDamage(Character victim) {
		classType.getAlignmentDamage(victim)
	}
	
	int getModifier(AbilityName ability) {
		abilities.get(ability).modifier + race.getModifier(ability)
	}
}