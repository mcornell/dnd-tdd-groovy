package codemash.evercraft.classes

import codemash.evercraft.character.Character

enum ClassType {
	FIGHTER, ROGUE, WAR_MONK, PEASANT
	
	def hitPointsPerLevel() {
		switch (this) {
			case FIGHTER:
				return 10;
			case WAR_MONK:
				return 6;
			default:
				return 5;
		}
	}
	
	def levelAttackAdjustment(int level) {
		switch (this) {
			case FIGHTER:
				return --level
			default:
				return Math.floor(level / 2)
		}
	}
	
	def abilityAttackAdjustment(attacker) {
		switch (this) {
				case ROGUE:
					return attacker.dexterity.modifier
				default:
					return attacker.strength.modifier
		}
	}
	
	def calculateCriticalHitDamage(int damage) {
		if (damage < 1) {
			return  1
		} else {
			switch (this) {
				case ROGUE:
					return damage * 3
				default:
					return damage * 2
			}
		}
	}
	
	def ignoreDefence(Character victim) {
		switch (this) {
			case ROGUE:
				return victim.armorClass
			default:
				return victim.armorClass + victim.dexterity.modifier
		}
	}
	
}
