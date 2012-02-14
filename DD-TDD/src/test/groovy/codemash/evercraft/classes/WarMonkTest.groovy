package codemash.evercraft.classes;

import spock.lang.*
import codemash.evercraft.character.Character
import codemash.evercraft.combat.Combat

class WarMonkTest extends Specification {
	
	def warmonk = new Character("Squidward", ClassType.WAR_MONK)
	def victim = new Character("Plankton")
	
	def "War Monks get 6 hit points per level"() {
		given:
			warmonk.experiencePoints = 1000
		expect:
			warmonk.hitPoints == 12
	}
	
	def "War Monks do 3 points of damage instead of 1"() {
		when:
			Combat.attack(warmonk, victim, 12)
		then:
			victim.damage == 3
	}

	def "War Monks get Wisdom + Dexterity modifiers to Armor Class"() {
		given:
			warmonk.wisdom.score = 12
			warmonk.dexterity.score = 12
		expect:
			warmonk.effectiveArmorClass == 12
	}
	
	def "War Monks attack roll is increased by 1 every 2nd level"() {
		given:
			warmonk.experiencePoints = 1000
		expect:
			Combat.attack(warmonk, victim, 9)
	}
	
	def "War Monks attack roll is increased by 1 every 3rd level"() {
		given:
			warmonk.experiencePoints = 2000
		expect:
			Combat.attack(warmonk, victim, 8)
	}
	
	def "War Monks attack roll is increase does not double increase on 6th level"() {
		given:
			warmonk.experiencePoints = 6000
		expect:
			Combat.attack(warmonk, victim, 5) == false
	}
}
