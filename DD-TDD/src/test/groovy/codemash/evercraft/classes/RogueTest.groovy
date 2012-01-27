package codemash.evercraft.classes

import spock.lang.*
import codemash.evercraft.character.Character
import codemash.evercraft.combat.Combat

class RogueTest extends Specification {

	def rogue = new Character("Moulon", ClassType.ROGUE)
	def victim = new Character("I hate you guys")
	
	def "Rogue will do triple damage on critical hits"(){
			when:
				Combat.attack(rogue, victim, Combat.CRITICAL_HIT)
			then:
				victim.damage == 3
	}
	
}
