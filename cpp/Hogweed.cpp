#include "Hogweed.h"
#include <iostream>

Hogweed::Hogweed(int X, int Y, World* world) :Plant(HOGWEED, 10, 0, X, Y, world) {};

void Hogweed::Action()
{
	Position defenderP(-1, -1);
	
	std::cout << "'" << GetName() << "'(" << GetPosition().X << "," << GetPosition().Y << ") is killing animal around" << std::endl;

	//kill WASD animal around(so it didn't kill the plant)
	defenderP = GetPosition().Up(GetWorld());
	if ((Grid(defenderP.X, defenderP.Y))->GetName() == WOLF ||
		(Grid(defenderP.X, defenderP.Y))->GetName() == SHEEP ||
		(Grid(defenderP.X, defenderP.Y))->GetName() == FOX ||
		(Grid(defenderP.X, defenderP.Y))->GetName() == TURTLE ||
		(Grid(defenderP.X, defenderP.Y))->GetName() == ANTELOPE ||
		(Grid(defenderP.X, defenderP.Y))->GetName() == HUMAN)
	{
		
		Organism* temp = Grid(defenderP.X, defenderP.Y);
		Grid(defenderP.X, defenderP.Y) = new nullGrid(defenderP);
		delete(temp);
	}

	defenderP = GetPosition().Left(GetWorld());
	if ((Grid(defenderP.X, defenderP.Y))->GetName() == WOLF ||
		(Grid(defenderP.X, defenderP.Y))->GetName() == SHEEP ||
		(Grid(defenderP.X, defenderP.Y))->GetName() == FOX ||
		(Grid(defenderP.X, defenderP.Y))->GetName() == TURTLE ||
		(Grid(defenderP.X, defenderP.Y))->GetName() == ANTELOPE ||
		(Grid(defenderP.X, defenderP.Y))->GetName() == HUMAN)
	{
		Organism* temp = Grid(defenderP.X, defenderP.Y);
		Grid(defenderP.X, defenderP.Y) = new nullGrid(defenderP);
		delete(temp);
	}

	defenderP = GetPosition().Down(GetWorld());
	if ((Grid(defenderP.X, defenderP.Y))->GetName() == WOLF ||
		(Grid(defenderP.X, defenderP.Y))->GetName() == SHEEP ||
		(Grid(defenderP.X, defenderP.Y))->GetName() == FOX ||
		(Grid(defenderP.X, defenderP.Y))->GetName() == TURTLE ||
		(Grid(defenderP.X, defenderP.Y))->GetName() == ANTELOPE ||
		(Grid(defenderP.X, defenderP.Y))->GetName() == HUMAN)
	{
		Organism* temp = Grid(defenderP.X, defenderP.Y);
		Grid(defenderP.X, defenderP.Y) = new nullGrid(defenderP);
		delete(temp);
	}

	defenderP = GetPosition().Right(GetWorld());
	if ((Grid(defenderP.X, defenderP.Y))->GetName() == WOLF ||
		(Grid(defenderP.X, defenderP.Y))->GetName() == SHEEP ||
		(Grid(defenderP.X, defenderP.Y))->GetName() == FOX ||
		(Grid(defenderP.X, defenderP.Y))->GetName() == TURTLE ||
		(Grid(defenderP.X, defenderP.Y))->GetName() == ANTELOPE ||
		(Grid(defenderP.X, defenderP.Y))->GetName() == HUMAN)
	{
		Organism* temp = Grid(defenderP.X, defenderP.Y);
		Grid(defenderP.X, defenderP.Y) = new nullGrid(defenderP);
		delete(temp);;
	}
}

void Hogweed::Collision(Organism*& attacker)
{
	/*if (attacker->GetPosition().X != -1)
		Grid(attacker->GetPosition().X, attacker->GetPosition().Y) = new nullGrid(attacker->GetPosition());*/
	if (attacker->GetName() != CYBERSHEEP)
	{
		//who eats this will die(so this will be eaten)
		std::cout << "'" << GetName() << "' kills attacker who eats it'" << (Grid(attacker->GetPosition().X, attacker->GetPosition().Y))->GetName() << "'(" << attacker->GetPosition().X << "," << attacker->GetPosition().Y << ")" << std::endl;

		delete(attacker);

		Grid(GetPosition().X, GetPosition().Y) = new nullGrid(GetPosition());
	}
	else
	{
		std::cout << "'" << GetName() << "' is eaten by cybersheep" << std::endl;

		(Grid(GetPosition().X, GetPosition().Y)) = attacker;
		attacker->SetPosition(this->GetPosition());
		attacker->SetPosition(this->GetPosition());
	}

	delete(this);
}