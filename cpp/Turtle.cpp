#include "Turtle.h"
#include <stdlib.h>
#include <iostream>

Turtle::Turtle(int X, int Y, World* world) :Animal(TURTLE, 2, 1, X, Y, world) {};

void Turtle::Action()
{
	//%75 stay
	int stayOrMove = rand() % 100;
	if (stayOrMove < 75)
	{
		std::cout << "'" << GetName() << "'(" << GetPosition().X << "," << GetPosition().Y << ") didn't move" << std::endl;
		return;
	}

	//%25 move
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
			attacker = new Organism(*this);
			attacker->SetPosition(Position(-1, -1));
			(Grid(defenderP.X, defenderP.Y))->Collision(attacker);
			//delete(attacker);
	//}
	}
	else
	//if they are different animal
	{
		std::cout << "'" << GetName() << "'(" << GetPosition().X << "," << GetPosition().Y << ") attack '" << (Grid(defenderP.X, defenderP.Y))->GetName() << "'(" << defenderP.X << "," << defenderP.Y << ")" << std::endl;

		Grid(GetPosition().X, GetPosition().Y) = new nullGrid(GetPosition());
		(Grid(defenderP.X, defenderP.Y))->Collision(attacker);
	}
}

void Turtle::Collision(Organism*& attacker)
{
	//default collision
	if (attacker->GetStrength() >= 5)
	{
		Grid(GetPosition().X, GetPosition().Y) = attacker;

		//Grid(attacker->GetPosition().X, attacker->GetPosition().Y) = new nullGrid(attacker->GetPosition());

		attacker->SetPosition(this->GetPosition());
		delete(this);

		//return false;					//attacker win return false
	}
	//less than 5, let it go back(nothing to do)
	else
	{
		std::cout << "'" << GetName() << "'(" << GetPosition().X << "," << GetPosition().Y << ") reflect attack" << std::endl;
		//if it is the baby turtle (of same animal)
		/*if (attacker->GetPosition().X == -1)
			delete(attacker);*/
		if (attacker->GetPosition().X == -1)
			delete(attacker);
		else {
			delete(Grid(attacker->GetPosition().X, attacker->GetPosition().Y));
			Grid(attacker->GetPosition().X, attacker->GetPosition().Y) = attacker;
		}
		//return true;					//defender win return true
	}
}