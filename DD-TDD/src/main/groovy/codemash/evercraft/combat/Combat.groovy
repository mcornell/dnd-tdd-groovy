package codemash.evercraft.combat

import codemash.evercraft.character.Character;

class Combat {
	static final int CRITICAL_HIT = 20
	static final int CRITICAL_MISS = 1
	
	private static def rollRange = 1..20

	private static final int MINIMUM_DAMAGE_MODIFIER = 0
	private static final int BASE_DAMAGE = 1
	private static final int XP_INCREMENT = 10
	
	static attack(Character attacker, Character victim, int roll) {
		if (!validRoll(roll)) 
			throw new IllegalArgumentException("Invalid roll of ${roll} - Please pass a number 1 to 20")
		
		if (hit (attacker, victim, roll)) {
			applyDamage(attacker, victim, roll)
			addExperience(attacker)
			true
		} else {
			false
		}
	}

	private static boolean validRoll(int roll) {
		return (rollRange.contains(roll))
	}
	
	private static boolean hit(Character attacker, Character victim, int roll) {
		int attackValue = roll + attacker.attackAdjustment
		
		int defenceValue = attacker.classType.ignoreDefence(victim)
		
		return (roll > CRITICAL_MISS && attackValue >= defenceValue)
	}
	
	private static applyDamage(Character attacker, Character victim, int roll) {
		int damage = getBaseDamage(attacker)
		
		if (roll == CRITICAL_HIT) {
			damage = attacker.calculateCriticalHitDamage(damage)
		} else {
			damage = Math.max(damage, 1)
		}
		
		victim.damage += damage
	}
	
	private static int getBaseDamage(Character attacker) {
		int damage = attacker.classType.getBaseDamage()
		return damage + attacker.strength.modifier
	}
	
	private static addExperience(Character attacker) {
		attacker.experiencePoints += XP_INCREMENT
	}
}
