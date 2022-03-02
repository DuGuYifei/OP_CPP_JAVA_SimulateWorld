#include "Fox.h"
#include <stdlib.h>
#include <iostream>

Fox::Fox(int X, int Y, World* world) :Animal(FOX, 3, 7, X, Y, world) {};

void Fox::Action()	//fox never go to the grid that have stronger organism
{
	bool WASD[4] = { false };
	int randomRange = 0;
	Position defenderP(-1, -1);

	defenderP = GetPosition().Up(GetWorld());
	if ((Grid(defenderP.X, defenderP.Y))->GetStrength() <= this->GetStrength())
	{
		randomRange++;
		WASD[0] = true;
	}

	defenderP = GetPosition().Left(GetWorld());
	if ((Grid(defenderP.X, defenderP.Y))->GetStrength() <= this->GetStrength())
	{
		randomRange++;
		WASD[1] = true;
	}

	defenderP = GetPosition().Down(GetWorld());
	if ((Grid(defenderP.X, defenderP.Y))->GetStrength() <= this->GetStrength())
	{
		randomRange++;
		WASD[2] = true;
	}

	defenderP = GetPosition().Right(GetWorld());
	if ((Grid(defenderP.X, defenderP.Y))->GetStrength() <= this->GetStrength())
	{
		randomRange++;
		WASD[3] = true;
	}

	//if there is place can move(otherwise doesn't move)
	if (randomRange > 0)
	{
		int direction = rand() % randomRange;
		for (int i = 0; i < 4; i++)
		{
			if (WASD[i] == true)
			{
				direction--;
			}
			if (direction < 0)
			{
				switch (i)
				{
				case 0:
					defenderP = GetPosition().Up(GetWorld());
					break;
				case 1:
					defenderP = GetPosition().Left(GetWorld());
					break;
				case 2:
					defenderP = GetPosition().Down(GetWorld());
					break;
				case 3:
					defenderP = GetPosition().Right(GetWorld());
					break;
				}

				std::cout << "'" << GetName() << "'(" << GetPosition().X << "," << GetPosition().Y << ") find safe place (" << defenderP.X << "," << defenderP.Y << ") to move" << std::endl;

				Organism* attacker = this;

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
				break;
			}
		}
	}
	else
		std::cout << "'" << GetName() << "'(" << GetPosition().X << "," << GetPosition().Y << ") didn't find safe place tp move so stay there" << std::endl;
}