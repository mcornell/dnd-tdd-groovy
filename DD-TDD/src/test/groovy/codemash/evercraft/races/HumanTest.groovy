package codemash.evercraft.races;

import spock.lang.*
import codemash.evercraft.character.Character

class HumanTest extends Specification {

	def "All Characters Default to Human"() {
		expect:
			new Character("Bob").race == Race.HUMAN
	}
}
