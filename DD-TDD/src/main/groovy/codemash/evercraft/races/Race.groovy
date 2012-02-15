package codemash.evercraft.races

import codemash.evercraft.character.AbilityName

enum Race {
	HUMAN, ORC
	
	def getModifier(AbilityName ability) {
		switch (ability) {
			case AbilityName.STRENGTH:
				return modifyStrength()
			default:
				return 0
		}
	}
	
	def modifyStrength() {
		switch (this) {
			case ORC:
				return 2
			default:
				return 0
		}
	}
}
