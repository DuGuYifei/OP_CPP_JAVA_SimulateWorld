#include "Guarana.h"
#include <iostream>

Guarana::Guarana(int X, int Y, World* world) :Plant(GUARANA, 0, 0, X, Y, world) {};

void Guarana::Collision(Organism*& attacker)
{
	//default collision
	if (attacker->GetStrength() >= this->GetStrength())
	{
		Grid(GetPosition().X, GetPosition().Y) = attacker;

		//if = -1, it is nullorgan or baby of two same animal(here is !=)
		/*if (attacker->GetPosition().X != -1)
			Grid(attacker->GetPosition().X, attacker->GetPosition().Y) = new nullGrid(attacker->GetPosition());*/

		//if (attacker->GetPosition().X == -1)
		//	this->SetAge(0);				//same strength, attacker win, so baby win(even if there is something let this be stronger, attacker will not pass code to here)

		std::cout << "'" << GetName() << "'(" << GetPosition().X << "," << GetPosition().Y << ") increases 3 strength of attacker who eats it'" << (Grid(attacker->GetPosition().X, attacker->GetPosition().Y))->GetName() << "'(" << attacker->GetPosition().X << "," << attacker->GetPosition().Y << ")" << std::endl;

		attacker->SetPosition(this->GetPosition());
		attacker->SetStrength(attacker->GetStrength() + 3);
		
		delete(this);

		//return false;					//attacker win return false
	}
	else
	{
		//if = -1, it is nullorgan or baby of two same animal
		/*if (attacker->GetPosition().X != -1)
			Grid(attacker->GetPosition().X, attacker->GetPosition().Y) = new nullGrid(attacker->GetPosition());*/

		delete(attacker);

		//return true;					//defender win return true
	}
}