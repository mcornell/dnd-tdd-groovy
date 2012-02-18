package codemash.evercraft.classes

import static codemash.evercraft.character.AbilityName.*
import codemash.evercraft.character.AbilityName

enum ClassType {
	FIGHTER, ROGUE, WAR_MONK, PALADIN, PEASANT
	
	def hitPointsPerLevel() {
		switch (this) {
			case FIGHTER:
				return 10
			case WAR_MONK:
				return 6
			case PALADIN:
				return 8
			default:
				return 5
		}
	}
	
	def levelAttackAdjustment(int level) {
		switch (this) {
			case FIGHTER:
			case PALADIN:
				return --level
			case WAR_MONK:
				int levelAdj = 0
				(1..level).each { i ->
					if (level % 2 == 0 || level % 3 == 0) levelAdj++
				}
				return levelAdj 
			default:
				return Math.floor(level / 2)
		}
	}
	
	AbilityName abilityAttackModifier() {
		switch (this) {
			case ROGUE:
				return DEXTERITY
			default:
				return STRENGTH
		}
	}
	
	def criticalHitMultiplier() {
		switch (this) {
			case ROGUE:
				return 3
			default:
				return 2
		}
	}
	



}
