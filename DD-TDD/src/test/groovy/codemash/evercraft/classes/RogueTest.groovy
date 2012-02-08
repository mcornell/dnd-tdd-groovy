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

	def "Rogue will ignore an opponent's dexterity modifier to AC when attacking"() {
		given:
			victim.dexterity.score = 12
		expect:
			Combat.attack(rogue, victim, 10)
	}
	
	def "Rouge applies the dexterity to the attack roll instead of strength"() {
		given:
			rogue.strength.score = 13
			rogue.dexterity.score = 14
		expect:
			Combat.attack(rogue, victim, 8)
	}
}
