package codemash.evercraft.combat;

import spock.lang.*
import codemash.evercraft.character.Character;

class CombatTest extends Specification {
	def character, victim
	
	void setup() {
		character = new Character("Joe")
		victim = new Character("Bob")
	}
	
	def "character can attack and hit when the roll is equal to the opponents armor class"() {
		expect:
			Combat.attack(character, victim, 10)
	}

	def "character's roll will hit when it is greater than opponents armor class"() {
		expect:
			Combat.attack(character, victim, 11)
	}
	
	def "character's roll will miss when it is less than opponents armor class"() {
		given:
			def successful = Combat.attack(character, victim, 9)
		expect:
			!successful
			victim.hitPoints == 5
	}
	
	def "an attack with a natural 1 will miss"() {
		given:
			victim.armorClass = 1
		expect:
			!Combat.attack(character, victim, 1)
	}
	
	def "character's roll cannot be greater than 20"() {
		when:
			Combat.attack(character, victim, 21)
		then:
			thrown(IllegalArgumentException)
	}
	
	def "character's roll cannot be less than 1"() {
		when:
			Combat.attack(character, victim, 0)
		then:
			thrown(IllegalArgumentException)
	}
	
	def "a successful attack will reduce attackee's hit points by 1"() {
		when:
			Combat.attack(character, victim, 19)
		then:
			victim.hitPoints == 4
	}
	
	def "an attack with a natural 20 does double damage"() {
		when:
			Combat.attack(character, victim, 20)
		then:
			victim.hitPoints == 3
	}
}
