package codemash.evercraft.classes;

import spock.lang.*
import codemash.evercraft.character.Character
import codemash.evercraft.combat.Combat

class WarMonkTest extends Specification {
	
	def warmonk = new Character("Squidward", ClassType.WAR_MONK)
	
	def "War Monks get 6 hit points per level"() {
		given:
			warmonk.experiencePoints = 1000
		expect:
			warmonk.hitPoints == 12
	}

}
