#include "Headers.h"
#include <iostream>
#include <algorithm>

bool Compare_init_age(Organism* x, Organism* y)
{
	if (x->GetInitiative() != y->GetInitiative())
	{
		return x->GetInitiative() > y->GetInitiative();
	}
	else
	{
		return x->GetAge() > y->GetAge();
	}

}


int main()
{
	srand((int)time(0));

	std::cout << "s188026		Yifei Liu" << std::endl;

	//start game
	std::cout << "Input n start new game" << std::endl;
	std::cout << "Input l load game state file" << std::endl;
	std::cout << "inout q quit the game" << std::endl;

	char newGame = ' ';

	World* world = new World();
	int sizeX, sizeY;

	while (newGame != 'n' && newGame != 'q' && newGame!='l')
	{
		std::cin >> newGame;
		
		if (newGame == 'q')					//quit game
			return 0;	

		else if (newGame == 'l')			//load game filr
		{
			bool openFile = world->Load();
			
			if (!openFile)
				return 0;

			sizeX = world->GetSizeX();
			sizeY = world->GetSizeY();
		}

		else if (newGame == 'n')			//new game
		{
			//input size of world
			std::cout << "Input size \"X Y\" of the world(if you want a human, it need X,Y>1):" << std::endl;
			
			std::cin >> sizeX >> sizeY;

			delete(world);
			World* world = new World(sizeX, sizeY);
		}
	}

	world->Draw();

	char command = ' ';
	Organism** actionSort = (Organism**)malloc(sizeX * sizeY * sizeof(Organism));
	//Organism** actionSort;

	while (command != 'q')
	{
		std::cout << std::endl << "Please input command (r, q, s):" << std::endl;
		std::cin >> command;

		world->Draw();
		if (command == 'r')
		{
			for (int i = 0; i < sizeX * sizeY; i++)
			{
				(*(world->organisms + i))->SetAge((*(world->organisms) + i)->GetAge() + 1);
				if ((*(world->organisms + i))->GetName() != nullOrgan)
					(*(world->organisms + i))->SetActed(false);
				*(actionSort + i) = new Organism(*(*(world->organisms + i)));
			}

			std::sort(actionSort, actionSort + (sizeX * sizeY - 1), Compare_init_age);

			for (int i = 0; i < sizeX * sizeY; i++)
				//if it has not acted
				if (!((*(world->organisms + ((*(actionSort + i))->GetPosition().Y * sizeX + (*(actionSort + i))->GetPosition().X)))->GetActed()))
				{
					(*(world->organisms + ((*(actionSort + i))->GetPosition().Y * sizeX + (*(actionSort + i))->GetPosition().X)))->SetActed(true);
					(*(world->organisms + ((*(actionSort + i))->GetPosition().Y * sizeX + (*(actionSort + i))->GetPosition().X)))->Action();
				}
		
			for (int i = 0; i < sizeX * sizeY; i++)
				delete(*(actionSort + i));
		}

		else if (command == 's')
		{
			bool openFile = world->Save();

			if (!openFile)
				return 0;
		}
	}

	free(actionSort);
	delete(world);

	return 0;
}