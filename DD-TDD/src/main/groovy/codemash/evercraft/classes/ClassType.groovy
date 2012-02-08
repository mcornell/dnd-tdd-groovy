package codemash.evercraft.classes

import codemash.evercraft.character.Character

enum ClassType {
	FIGHTER, ROGUE, PEASANT
	
	def hitPointsPerLevel() {
		switch (this) {
			case FIGHTER:
				return 10;
			default:
				return 5;
		}
	}
	
	def attackAdjustment(int level) {
		switch (this) {
			case FIGHTER:
				return --level
			default:
				return Math.floor(level / 2)
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
