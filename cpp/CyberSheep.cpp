#include "CyberSheep.h"
#include <cmath>
#include <iostream>

CyberSheep::CyberSheep(int X, int Y, World* world) :Animal(CYBERSHEEP, 11, 4, X, Y, world) {};

void CyberSheep::Action()
{
	Position hogweedP = Position(-1, -1);
	int shortest = GetWorld()->GetSizeX() + GetWorld()->GetSizeY();
	for (int i = 0; i < GetWorld()->GetSizeX();i++)
		for (int j = 0; j < GetWorld()->GetSizeY(); j++)
		{
			if ((Grid(i,j))->GetName() == HOGWEED)
			{
				int d = abs(i - this->GetPosition().X) + abs(j - this->GetPosition().Y);
				if (d < shortest)
				{
					shortest = d;
					hogweedP = Position(i, j);

					std::cout << "'" << GetName() << "'(" << GetPosition().X << "," << GetPosition().Y << ") find '" << (Grid(hogweedP.X,hogweedP.Y))->GetName() << "'(" << hogweedP.X << "," << hogweedP.Y << ")" << std::endl;
				}
			}
		}

	Position defenderP(-1, -1);
	Organism* attacker = this;

	//if there is hogweed
	if (hogweedP.X != -1)
	{
		if (hogweedP.X > this->GetPosition().X)
			defenderP = GetPosition().Right(GetWorld());
		else if (hogweedP.X < this->GetPosition().X)
			defenderP = GetPosition().Left(GetWorld());
		else if (hogweedP.Y < this->GetPosition().Y)
			defenderP = GetPosition().Up(GetWorld());
		else if (hogweedP.Y > this->GetPosition().Y)
			defenderP = GetPosition().Down(GetWorld());
	}
	else	//no hog weed
	{
		int direction = rand() % 4;

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