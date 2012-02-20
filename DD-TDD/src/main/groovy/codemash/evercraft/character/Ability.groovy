package codemash.evercraft.character

class Ability {
	def scoreRange = 1..20
	
	AbilityName name
	int score = 10
	
	Ability(AbilityName aName) {
		name = aName
	}
	
	def setScore(int aScore) {
		if (!validScore(aScore)) {
			throw new IllegalArgumentException()
		}
		
		score = aScore
	}
	
	int getModifier() {
		Math.floor( score/2 ) - 5	
	}
	
	private boolean validScore(int score) {
		scoreRange.contains(score)
	}
}