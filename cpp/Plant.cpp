#include "Plant.h"
#include <stdlib.h>
#include <iostream>

Plant::Plant(char n, int s, int i, int X, int Y, World* world) :Organism(n, s, i, X, Y, world) {};

void Plant::Action()
{
	bool WASD[4] = { false };
	int randomRange = 0;
	Position defenderP(-1, -1);

	defenderP = GetPosition().Up(GetWorld());
	if ((Grid(defenderP.X, defenderP.Y))->GetName() == nullOrgan)
	{
		randomRange++;
		WASD[0] = true;
	}

	defenderP = GetPosition().Left(GetWorld());
	if ((Grid(defenderP.X, defenderP.Y))->GetName() == nullOrgan)
	{
		randomRange++;
		WASD[1] = true;
	}

	defenderP = GetPosition().Down(GetWorld());
	if ((Grid(defenderP.X, defenderP.Y))->GetName() == nullOrgan)
	{
		randomRange++;
		WASD[2] = true;
	}

	defenderP = GetPosition().Right(GetWorld());
	if ((Grid(defenderP.X, defenderP.Y))->GetName() == nullOrgan)
	{
		randomRange++;
		WASD[3] = true;
	}

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

				std::cout << "'" << GetName() << "'(" << GetPosition().X << "," << GetPosition().Y << ") is tring to spread to (" << defenderP.X << "," << defenderP.Y << ")" << std::endl;
				
				delete(Grid(defenderP.X, defenderP.Y));						//delete the nullorgan in the free
				Grid(defenderP.X, defenderP.Y) = new Plant(*this);			//add the same plant in it
				(Grid(defenderP.X, defenderP.Y))->SetAge(0);
				(Grid(defenderP.X, defenderP.Y))->SetActed(true);
				(Grid(defenderP.X, defenderP.Y))->SetPosition(defenderP);	//set the position as current position
				return;
			}
		}
	}
}