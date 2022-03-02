#include "Belladonna.h"
#include <iostream>

Belladonna::Belladonna(int X, int Y, World* world) :Plant(BELLADONNA, 99, 0, X, Y, world) {};

void Belladonna::Collision(Organism*& attacker)
{
	//who eats this will die(so this will be eaten)
	//Grid(attacker->GetPosition().X, attacker->GetPosition().Y) = new nullGrid(attacker->GetPosition());
	
	std::cout << "'" << GetName() << "' kills attacker who eats it'" << (Grid(attacker->GetPosition().X, attacker->GetPosition().Y))->GetName() << "'(" << attacker->GetPosition().X << "," << attacker->GetPosition().Y << ")" << std::endl;
	
	delete(attacker);

	Grid(GetPosition().X, GetPosition().Y) = new nullGrid(GetPosition());
	delete(this);
}
