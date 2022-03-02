#include "Antelope.h"
#include <stdlib.h>
#include <iostream>

Antelope::Antelope(int X, int Y, World* world) :Animal (ANTELOPE, 4, 4, X, Y, world) {};


void Antelope::Action()
{
	//used to be free grid that can be escape
	Grid(GetPosition().X,GetPosition().Y) = new nullGrid(GetPosition());

	int direction = rand() % 12;
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
	case 4:	//left left
		defenderP = GetPosition().Left(GetWorld());
		defenderP = defenderP.Left(GetWorld());
		break;
	case 5:	//left up
		defenderP = GetPosition().Left(GetWorld());
		defenderP = defenderP.Up(GetWorld());
		break;
	case 6:	//left down
		defenderP = GetPosition().Left(GetWorld());
		defenderP = defenderP.Down(GetWorld());
		break;
	case 7:	//right right
		defenderP = GetPosition().Right(GetWorld());
		defenderP = defenderP.Right(GetWorld());
		break;
	case 8:	//right up
		defenderP = GetPosition().Right(GetWorld());
		defenderP = defenderP.Up(GetWorld());
		break;
	case 9:	//right down
		defenderP = GetPosition().Right(GetWorld());
		defenderP = defenderP.Down(GetWorld());
		break;
	case 10://up up
		defenderP = GetPosition().Up(GetWorld());
		defenderP = defenderP.Up(GetWorld());
		break;
	case 11://down down
		defenderP = GetPosition().Down(GetWorld());
		defenderP = defenderP.Down(GetWorld());
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

		delete(Grid(GetPosition().X, GetPosition().Y)); //let it come back
		Grid(GetPosition().X, GetPosition().Y) = this;
	}
	else
	{
		std::cout << "'" << GetName() << "'(" << GetPosition().X << "," << GetPosition().Y << ") attack '" << (Grid(defenderP.X, defenderP.Y))->GetName() << "'(" << defenderP.X << "," << defenderP.Y << ")" << std::endl;

		//they are different animal(including free cell)
		//if not free cell, 50% chance to escapse
		if ((Grid(defenderP.X, defenderP.Y))->GetName() == WOLF ||
			(Grid(defenderP.X, defenderP.Y))->GetName() == SHEEP ||
			(Grid(defenderP.X, defenderP.Y))->GetName() == FOX ||
			(Grid(defenderP.X, defenderP.Y))->GetName() == TURTLE ||
			(Grid(defenderP.X, defenderP.Y))->GetName() == ANTELOPE ||
			(Grid(defenderP.X, defenderP.Y))->GetName() == HUMAN ||
			(Grid(defenderP.X, defenderP.Y))->GetName() == CYBERSHEEP)
		{
			int escape = rand() % 2;
			//escape: find a free cell
			if (escape == 0)
			{
				std::cout << "	 -- antelope escape from fight" << std::endl;

				bool WASD[4] = { false };
				int randomRange = 0;
				Position defenderFreeP(-1, -1);
				defenderFreeP = defenderP.Up(GetWorld());
				if ((Grid(defenderP.X, defenderP.Y))->GetName() == nullOrgan)
				{
					randomRange++;
					WASD[0] = true;
				}

				defenderFreeP = defenderP.Left(GetWorld());
				if ((Grid(defenderP.X, defenderP.Y))->GetName() == nullOrgan)
				{
					randomRange++;
					WASD[1] = true;
				}

				defenderFreeP = defenderP.Down(GetWorld());
				if ((Grid(defenderP.X, defenderP.Y))->GetName() == nullOrgan)
				{
					randomRange++;
					WASD[2] = true;
				}

				defenderFreeP = defenderP.Right(GetWorld());
				if ((Grid(defenderP.X, defenderP.Y))->GetName() == nullOrgan)
				{
					randomRange++;
					WASD[3] = true;
				}

				//if have free cell
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
								defenderP = defenderP.Up(GetWorld());
								break;
							case 1:
								defenderP = defenderP.Left(GetWorld());
								break;
							case 2:
								defenderP = defenderP.Down(GetWorld());
								break;
							case 3:
								defenderP = defenderP.Right(GetWorld());
								break;
							}
							break;
						}
					}
				}
				return;
			}
		}
		(Grid(defenderP.X, defenderP.Y))->Collision(attacker);
	}
}

void Antelope::Collision(Organism*& attacker)
{
	//used to be free grid that can be escape
	Grid(attacker->GetPosition().X, attacker->GetPosition().Y) = new nullGrid(attacker->GetPosition());

	int escape = rand() % 2;
	//escape: find a free cell
	if (escape == 0)
	{
		std::cout << "'" << GetName() << "'(" << GetPosition().X << "," << GetPosition().Y << ") escape from fight" << std::endl;

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
					delete(Grid(defenderP.X, defenderP.Y));							//delete the nullorgan in the free
					Grid(defenderP.X, defenderP.Y) = this;							//let this move to new position
					Grid(GetPosition().X, GetPosition().Y) = attacker;				//move attacker to here
					//Grid(attacker->GetPosition().X, attacker->GetPosition().Y) = new nullGrid(attacker->GetPosition());//let attacker origional position be nullgrid
					attacker->SetPosition(this->GetPosition());
					(Grid(defenderP.X, defenderP.Y))->SetPosition(defenderP);		//set the position as new position
					
					return;
				}
			}
		}
	}
	//not escape
	//default collision
	if (attacker->GetStrength() >= this->GetStrength())
	{
		Grid(GetPosition().X, GetPosition().Y) = attacker;

		//if = -1, it is nullorgan or baby of two same animal
		/*if (attacker->GetPosition().X != -1)
			Grid(attacker->GetPosition().X, attacker->GetPosition().Y) = new nullGrid(attacker->GetPosition());*/

		if (attacker->GetPosition().X == -1)
			attacker->SetActed(true);				//same strength, attacker win, so baby win(even if there is something let this be stronger, attacker will not pass code to here)
		
		attacker->SetPosition(this->GetPosition());
		delete(this);
	}
	else
	{
		//if = -1, it is nullorgan or baby of two same animal
		/*if (attacker->GetPosition().X != -1)
			Grid(attacker->GetPosition().X, attacker->GetPosition().Y) = new nullGrid(attacker->GetPosition());*/

		delete(attacker);
	}
}