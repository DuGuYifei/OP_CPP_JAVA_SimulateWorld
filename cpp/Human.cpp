#include "Human.h"
#include <iostream>
#include <conio.h>

#define KEY_UP 72
#define KEY_DOWN 80
#define KEY_LEFT 75
#define KEY_RIGHT 77

Human::Human(int X, int Y, World* world) :Animal(HUMAN, 5, 4, X, Y, world) {};

void Human::Action()
{
	static int abilityRound = 0;

	Organism* attacker = this;
	Position defenderP(-1, -1);

	
	//press the arrow key to get the arrow and h get ability
	while (defenderP.X == -1)
	{
		

		std::cout << std::endl << "Please press the arrow key to control human:" << std::endl;

		if (abilityRound == 0)
			std::cout << "Hint: If press h, it will active human ability 5 rounds: purication" << std::endl;

		int key = _getch();
		if (key == 0 || key == 0xE0)
		{
			switch ((_getch())) {
			case KEY_UP:
				defenderP = GetPosition().Up(GetWorld());   //key up
				break;
			case KEY_DOWN:
				defenderP = GetPosition().Down(GetWorld()); // key down
				break;
			case KEY_LEFT:
				defenderP = GetPosition().Left(GetWorld()); // key left
				break;
			case KEY_RIGHT:
				defenderP = GetPosition().Right(GetWorld());// key right
				break;
			default:
				std::cout << "This is not arrow key or h(human ability)" << std::endl;
				break;
			}
		}
		else if (key == 'h')
		{
			if (abilityRound != 0)
				std::cout << "You have started the human ability" << std::endl;
			else
				abilityRound = 5;
		}
		else
			std::cout << "This is not arrow key or h(human ability)" << std::endl;
	}


	if (abilityRound == 0) {
		std::cout << "'" << GetName() << "'(" << GetPosition().X << "," << GetPosition().Y << ") attack '" << (Grid(defenderP.X, defenderP.Y))->GetName() << "'(" << defenderP.X << "," << defenderP.Y << ")" << std::endl;

		Grid(GetPosition().X, GetPosition().Y) = new nullGrid(GetPosition());
		(Grid(defenderP.X, defenderP.Y))->Collision(attacker);
	}
	//ability actived
	else
	{
		std::cout << "	 -- human is tring to pure the grid around(ability remain " << abilityRound << " round)" << std::endl;

		abilityRound--;

		Position pureP = Position(-1, -1);	//which will be pured after movement

		//defenderP
		Organism* temp = Grid(defenderP.X, defenderP.Y);
		Grid(defenderP.X, defenderP.Y) = this;
		delete(temp);
		Grid(GetPosition().X, GetPosition().Y) = new nullGrid(GetPosition());
		SetPosition(defenderP);

		//up
		pureP = defenderP.Up(GetWorld());
		temp = Grid(pureP.X, pureP.Y);
		Grid(pureP.X, pureP.Y) = new nullGrid(pureP);
		delete(temp);
		
		//down
		pureP = defenderP.Down(GetWorld());
		temp = Grid(pureP.X, pureP.Y);
		Grid(pureP.X, pureP.Y) = new nullGrid(pureP);
		delete(temp);

		//left
		pureP = defenderP.Left(GetWorld());
		temp = Grid(pureP.X, pureP.Y);
		Grid(pureP.X, pureP.Y) = new nullGrid(pureP);
		delete(temp);

		//right
		pureP = defenderP.Right(GetWorld());
		temp = Grid(pureP.X, pureP.Y);
		Grid(pureP.X, pureP.Y) = new nullGrid(pureP);
		delete(temp);

		
	}
}