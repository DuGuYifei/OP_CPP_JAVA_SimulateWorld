#include "Animal.h"
#include <stdlib.h>
#include <iostream>

Animal::Animal(char n, int s, int i, int X, int Y, World* world) :Organism(n, s, i, X, Y, world) {};

//action randomly to neighbour
void Animal::Action()
{
	int direction = rand() % 4;
	Organism* attacker = this;
	Position defenderP(-1, -1);
	switch (direction)
	{	
	case 0:	//left
		defenderP = GetPosition().Left(GetWorld());
		//this->SetPosition(GetPosition().Left(GetWorld()));
		break;
	case 1:	//right
		defenderP = GetPosition().Right(GetWorld());
		break;
	case 2:	//up
		defenderP = GetPosition().Up(GetWorld());
		break;
	case 3:	//down
		defenderP = GetPosition().Down(GetWorld());
		break;
	}

	//if they are same animal
	if ((Grid(defenderP.X, defenderP.Y))->GetName() == this->GetName())
	{
		//baby is born next to them
		Position babyPosition(-1, -1);
		defenderP.Y >= this->GetPosition().Y ?
			babyPosition = Position(defenderP.X, defenderP.Y + 1) :
			babyPosition = Position(this->GetPosition().X, this->GetPosition().Y + 1);

		if (babyPosition.Y >= GetWorld()->GetSizeY())
			babyPosition.Y = 0;

		std::cout << "'" << GetName() << "'(" << GetPosition().X << "," << GetPosition().Y << ") and '" << (Grid(defenderP.X, defenderP.Y))->GetName() << "'(" << defenderP.X << "," << defenderP.Y << ") has a baby." << std::endl;

		//baby's coliision
		/*if ((Grid(babyPosition.X, babyPosition.Y))->GetName() == this->GetName())
			return;*/
			//else
			//{
		attacker = new Animal(*this);
		attacker->SetPosition(Position(-1, -1));
		(Grid(defenderP.X, defenderP.Y))->Collision(attacker);
		//delete(attacker);
	//}
	}
	else
	//if they are different animal
	{
		std::cout << "'" << GetName() << "'(" << GetPosition().X << "," << GetPosition().Y << ") attack '" << (Grid(defenderP.X,defenderP.Y))->GetName() << "'(" << defenderP.X << "," << defenderP.Y << ")" << std::endl;
		Grid(GetPosition().X, GetPosition().Y) = new nullGrid(GetPosition());
		(Grid(defenderP.X, defenderP.Y))->Collision(attacker);
	}
}
