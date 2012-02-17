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
}
