#include "Headers.h"
#include <iostream>
#include <fstream>

#define Length_FIleName 20

//getters
int World::GetSizeX()const { return sizeX; }
int World::GetSizeY()const { return sizeY; }

//setters
void World::SetSizeX(int X) { sizeX = X; }
void World::SetSizeY(int Y) { sizeY = Y; }

//constructor and destructor
World::World() { sizeX = 0; sizeY = 0; }

World::World(int X, int Y) :sizeX(X), sizeY(Y) 
{
	//initialize the world with one human and some other organisms
	organisms = (Organism**)malloc(sizeX * sizeY * sizeof(Organism));
	for (int i = 0; i < sizeY; i++)
		for(int j=0;j<sizeX;j++)
			switch (rand() % 12)
			{
			case 0:
				*(organisms + (i * sizeX + j)) = new Organism(nullOrgan, -1, -1, j, i, this);
				break;
			case 1:
				*(organisms + (i * sizeX + j)) = new Animal(WOLF, 9, 5, j, i, this);
				break;
			case 2:
				*(organisms + (i * sizeX + j)) = new Animal(SHEEP, 4, 4, j, i, this);
				break;
			case 3:
				*(organisms + (i * sizeX + j)) = new Fox(j, i, this);
				break;
			case 4:
				*(organisms + (i * sizeX + j)) = new Turtle(j, i, this);
				break;
			case 5:
				*(organisms + (i * sizeX + j)) = new Antelope(j, i, this);
				break;
			case 6:
				*(organisms + (i * sizeX + j)) = new CyberSheep(j, i, this);
				break;
			case 7:
				*(organisms + (i * sizeX + j)) = new Plant(GRASS, 0, 0, j, i, this);
				break;
			case 8:
				*(organisms + (i * sizeX + j)) = new SowThistle(j, i, this);
				break;
			case 9:
				*(organisms + (i * sizeX + j)) = new Guarana(j, i, this);
				break;
			case 10:
				*(organisms + (i * sizeX + j)) = new Belladonna(j, i, this);
				break;
			case 11:
				*(organisms + (i * sizeX + j)) = new Hogweed(j, i, this);
				break;
			}
	if (sizeX >= 2 && sizeY >= 2)
	{
		delete(*(organisms + (sizeX / 2 + sizeY / 2 * sizeX)));
		*(organisms + (sizeX / 2 + sizeY / 2 * sizeX)) = new Human(sizeX / 2, sizeY / 2, this);
	}
}

World::~World()
{
	for (int i = 0; i < sizeX * sizeY; i++)
		delete(*(organisms + i));
	free(organisms);
}

//other functions
void World::Draw()
{
	system("cls");

	std::cout << "s188026		Yifei Liu" << std::endl;
	std::cout << "r : next round" << std::endl;
	std::cout << "q : end game" << std::endl;
	std::cout << "s : save world to file" << std::endl;
	std::cout << "You can control human when you get tips('h 'and arrow key)" << std::endl;

	std::cout << "WOLF		'w'	SHEEP		's'"<<std::endl;
	std::cout << "FOX		'f'	TURTLE		't'"<<std::endl;
	std::cout << "ANTELOPE	'a'	CYBERSHEEP	'c'"<<std::endl;

	std::cout << "GRASS		'!'	SOWTHISTLE	'@'"<<std::endl;
	std::cout << "GUARANA		'#'	BELLADONNA	'$'"<<std::endl;
	std::cout << "HOGWEED		'%'"				<<std::endl;

	std::cout << "HUMAN		'h'"				<<std::endl;

	std::cout << "nullOrgan	'_'"				<<std::endl << std::endl;

	//draw top outline
	std::cout << "* ";
	for (int j = 0; j < GetSizeX(); j++)
		std::cout << j << " ";
	std::cout << "*" << std::endl;

	//draw grids
	for(int i = 0;i<GetSizeY();i++)
	{ 
		std::cout << i << " ";
		for (int j = 0; j < GetSizeX(); j++)
		{
			(*(organisms + (i * GetSizeX() + j)))->Draw();
		}
		std::cout << i << std::endl;
	}

	//draw bottom outline
	std::cout << "* ";
	for (int j = 0; j < GetSizeX(); j++)
		std::cout << j << " ";
	std::cout << "*" << std::endl;
}

bool World::Save()
{
	//open file
	std::string fileName;
	
	std::cout << std::endl << "Please name your file:" << std::endl;
	std::cin >> fileName;

	std::ofstream fout(fileName);

	if (!fout.is_open())
	{
		std::cout << "fail to open file";
		return false;
	}

	//out put parameter of world
	fout << sizeX << " " << sizeY << std::endl;

	//oit put all organisms
	for (int i = 0; i < sizeX * sizeY; i++)
	{
		fout << (*(organisms + i))->GetName() << " "
			<< (*(organisms + i))->GetAge() << " "
			<< (*(organisms + i))->GetStrength() << " "
			<< (*(organisms + i))->GetInitiative() << " "
			<< (*(organisms + i))->GetPosition().X << " "
			<< (*(organisms + i))->GetPosition().Y << std::endl;
	}

	//close file
	fout.close();

	std::cout << std::endl;

	return true;
}

bool World::Load()
{
	//open file
	std::string fileName;

	std::cout << std::endl << "Please inout file name:" << std::endl;
	std::cin >> fileName;

	std::ifstream fin(fileName);

	if (!fin.is_open())
	{
		std::cout << "fail to open file";
		return false;
	}

	int x, y, age, strength, initiative;
	char name;

	fin >> x >> y;
	SetSizeX(x);
	SetSizeY(y);

	organisms = (Organism**)malloc(sizeX * sizeY * sizeof(Organism));
	int i = 0;			//the index of organism pointer

	while (!fin.eof())
	{
		fin >> name >> age >> strength >> initiative >> x >> y;

		switch (name)
		{
		case '_':
			*(organisms + i) = new Organism(nullOrgan, -1, -1, x, y, this);
			break;
		case 'w':
			*(organisms + i) = new Animal(WOLF, 9, 5, x, y, this);
			break;
		case 's':
			*(organisms + i) = new Animal(SHEEP, 4, 4, x, y, this);
			break;
		case 'f':
			*(organisms + i) = new Fox(x, y, this);
			break;
		case 't':
			*(organisms + i) = new Turtle(x, y, this);
			break;
		case 'a':
			*(organisms + i) = new Antelope(x, y, this);
			break;
		case 'c':
			*(organisms + i) = new CyberSheep(x, y, this);
			break;
		case '!':
			*(organisms + i) = new Plant(GRASS, 0, 0, x, y, this);
			break;
		case '@':
			*(organisms + i) = new SowThistle(x, y, this);
			break;
		case '#':
			*(organisms + i) = new Guarana(x, y, this);
			break;
		case '$':
			*(organisms + i) = new Belladonna(x, y, this);
			break;
		case '%':
			*(organisms + i) = new Hogweed(x, y, this);
			break;
		case 'h':
			*(organisms + i) =  new Human(x, y, this);
			break;
		}

		i++;
	}

	//close file
	fin.close();

	return true;
}