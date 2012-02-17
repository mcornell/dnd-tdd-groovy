package codemash.evercraft.classes;

import spock.lang.*
import codemash.evercraft.character.Character
import codemash.evercraft.combat.Combat

class PaladinTest extends Specification {
	
	def paladin = new Character("Perry", ClassType.PALADIN)
	@Shared evilVictim 
	@Shared neutralVictim

	def setup() {
		evilVictim = new Character("Doofenschmirtz")
		evilVictim.alignment = "EVIL"
		neutralVictim = new Character("Candace")
	}
	
	def "Paladins get 8 hit points per level"() {
		given:
			paladin.experiencePoints = 2000
		expect:
			paladin.hitPoints == 24
	}
	
	def "Paladins get +2 to attack when attacking evil characters"() {
		expect:
			Combat.attack(paladin, victim, roll) == result
		where:
			victim << [evilVictim, neutralVictim, evilVictim, neutralVictim]
			roll   << [8         , 8            , 7         , 10           ]
			result << [true      , false        , false     , true         ]
	}

	def "Paladins get +2 to damage when attacking evil characters"() {
		expect:
			Combat.getAlignmentDamage(paladin, victim) == damageAdjustment
		where:
			victim           << [evilVictim, neutralVictim]
			damageAdjustment << [2         , 0            ]
	}
	
	def "Paladins do triple damage when attacking evil characters"() {
		when:
			Combat.attack(paladin, victim, 10)
		then:
			victim.damage == damage
		where:
			victim << [evilVictim, neutralVictim]
			damage << [5         , 1            ]
	}
		
	def "Paladins attack roll is increased by 1 every level"() {
		given:
			paladin.experiencePoints = xp
		expect:
			paladin.attackAdjustment == adj
		where:
			xp  << [1000, 2000, 4000]
			adj << [1   , 2   , 4   ]
	}
}
