package codemash.evercraft.character

import codemash.evercraft.classes.*
import codemash.evercraft.races.Race

class Character {
	def name
	
	int armorClass = 10, damage = 0, experiencePoints = 0, level = 1, attackAdjustment = 0
	Ability strength, dexterity, constitution, wisdom, intelligence, charisma
	Alignment alignment = Alignment.NEUTRAL
	def abilities = new EnumMap(AbilityName.class) 
	Race race;
	ClassType classType
	
	public Character(String aName) {
		name = aName
		instantiateAbilities()
		classType = ClassType.PEASANT;
		race = Race.HUMAN;
	}
	
	public Character(String aName, ClassType myClassType) {
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
		return getHitPoints() > damage
	}
	
	int getHitPoints() {
		return Math.max((classType.hitPointsPerLevel() + constitution.modifier) * getLevel(), getLevel())
	}
	
	int getLevel() {
		return Math.floor(experiencePoints / 1000) + 1
	}
	
	int getAttackAdjustment() {
		return classType.levelAttackAdjustment(getLevel()) + classType.abilityAttackAdjustment(this)
	}
	
	int calculateCriticalHitDamage(int damage) {
		return classType.calculateCriticalHitDamage(damage)
	}
	
	int getEffectiveArmorClass() {
		return classType.effectiveArmorClass(this)
	}
	
	int getAlignmentDamage(Character victim) {
		return classType.getAlignmentDamage(victim)
	}
	
	int getModifier(AbilityName ability) {
		println "base modifier: ${abilities.get(ability).modifier}"
		println "race modifier: ${race.getModifier(ability)}"
		return abilities.get(ability).modifier + race.getModifier(ability)
	}
}