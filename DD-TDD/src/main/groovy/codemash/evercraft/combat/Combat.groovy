package codemash.evercraft.combat

import static codemash.evercraft.classes.ClassType.*
import static codemash.evercraft.character.Alignment.*
import static codemash.evercraft.races.Race.*
import codemash.evercraft.character.Character

class Combat {
	static final int CRITICAL_HIT = 20
	static final int CRITICAL_MISS = 1
	
	private static rollRange = 1..20

	private static final int MINIMUM_DAMAGE_MODIFIER = 0
	private static final int BASE_DAMAGE = 1
	private static final int XP_INCREMENT = 10
	
	static attack(Character attacker, Character victim, int roll) {
		if (!validRoll(roll)) {
			throw new IllegalArgumentException("Invalid roll of ${roll} - Please pass a number 1 to 20")
		}
		if (hit (attacker, victim, roll)) {
			applyDamage(attacker, victim, roll)
			addExperience(attacker)
			true
		} else {
			false
		}
	}

	private static boolean validRoll(int roll) {
		rollRange.contains(roll)
	}
	
	private static boolean hit(Character attacker, Character victim, int roll) {
		int attackValue = roll + attacker.attackAdjustment + victimAdjustment(attacker, victim)
		
		int defenceValue = ignoreDefence(attacker, victim)
		
		(roll > CRITICAL_MISS && attackValue >= defenceValue)
	}
	
	private static int victimAdjustment(Character attacker, Character victim) {
		switch (attacker.classType) {
			case PALADIN:
				if (victim.alignment == EVIL) return 2
			default:
				return 0
		}
	}
	
	private static int ignoreDefence(Character attacker, Character victim) {
		switch (attacker.classType) {
			case ROGUE:
				return effectiveArmorClass(victim) - victim.dexterity.modifier
			default:
				return effectiveArmorClass(victim)
		}
	}
	
	private static int effectiveArmorClass(Character self) {
		int armorClass = self.armorClass
		switch (self.race) {
			case ORC:
				armorClass += 2
		}
		switch (self.classType) {
			case WAR_MONK:
				armorClass += self.wisdom.modifier
			default:
				return armorClass + self.dexterity.modifier
		}
	}
	
	
	private static applyDamage(Character attacker, Character victim, int roll) {
		int damage = damageMultiplier(attacker, victim) * getBaseDamage(attacker)
		
		if (roll == CRITICAL_HIT) {
			damage = attacker.calculateCriticalHitDamage(damage)
		} else {
			damage = Math.max(damage, 1)
		}
		
		damage += getAlignmentDamage(attacker, victim)
		victim.damage += damage
	}
	
	private static int damageMultiplier(Character attacker, Character victim) {
		switch (attacker.classType) {
			case PALADIN:
				if (victim.alignment == EVIL) return 3
			default:
				return 1
		}
	}
	
	private static int getAlignmentDamage(Character attacker, Character victim) {
		switch (attacker.classType) {
			case PALADIN:
				if (victim.alignment == EVIL) return 2
			default:
				return 0
		}
	}
	
	private static int getBaseDamage(Character attacker) {
		def damage = BASE_DAMAGE;
		switch (attacker.classType) {
			case WAR_MONK:
				damage = 3
		}
		damage + attacker.strength.modifier
	}
	
	private static addExperience(Character attacker) {
		attacker.experiencePoints += XP_INCREMENT
	}
}
