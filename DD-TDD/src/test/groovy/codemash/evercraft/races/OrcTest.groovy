package codemash.evercraft.races;

import spock.lang.*
import codemash.evercraft.character.Character
import codemash.evercraft.character.AbilityName

class OrcTest extends Specification {

	@Shared orc = new Character("Thrall", Race.ORC)
	
	def "Orcs have a +2 to strength modifiers"() {
		when:
			orc.strength.score = score 
		then:
			orc.getModifier(AbilityName.STRENGTH) == modifier 
		where:
			score    << [10, 7,  5, 19]
			modifier << [2 , 0, -1,  6]
	}
	
	def "Orcs have a -1 to intelligence modifiers"() {
		when:
			orc.intelligence.score = score
		then:
			orc.getModifier(AbilityName.INTELLIGENCE) == modifier
		where:
			score    << [10, 7,  5, 19]
			modifier << [-1 , -3, -4,  3]
	}
	
	def "Orcs have a -1 to wisdom modifiers"() {
		when:
			orc.wisdom.score = score
		then:
			orc.getModifier(AbilityName.WISDOM) == modifier
		where:
			score    << [10, 7,  5, 19]
			modifier << [-1 , -3, -4,  3]
	}
	
	def "Orcs have a -1 to charisma modifiers"() {
		when:
			orc.charisma.score = score
		then:
			orc.getModifier(AbilityName.CHARISMA) == modifier
		where:
			score    << [10, 7,  5, 19]
			modifier << [-1 , -3, -4,  3]
	}
	
	def "Orcs have a +2 to Armor Class due to thick hide"() {
		expect:
			orc.getEffectiveArmorClass() == 12
	}
}
