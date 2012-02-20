package codemash.evercraft.races

import static codemash.evercraft.character.AbilityName.*
import codemash.evercraft.character.AbilityName

enum Race {
	HUMAN, ORC

	def getModifier(AbilityName ability) {
		switch (ability) {
			case STRENGTH:
				return modifyStrength()
			case INTELLIGENCE:
				return modifyIntelligence()
			case WISDOM:
				return modifyWisdom()
			case CHARISMA:
				return modifyCharisma()
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

	def modifyIntelligence() {
		switch (this) {
			case ORC:
				return -1
			default:
				return 0
		}
	}

	def modifyWisdom() {
		switch (this) {
			case ORC:
				return -1
			default:
				return 0
		}
	}

	def modifyCharisma() {
		switch (this) {
			case ORC:
				return -1
			default:
				return 0
		}
	}
}
