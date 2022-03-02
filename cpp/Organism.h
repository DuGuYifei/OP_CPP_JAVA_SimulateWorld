#ifndef _ORGANISM_H_
#define _ORGANISM_H_
#include "World.h"

#define WOLF 'w'
#define SHEEP 's'
#define FOX 'f'
#define TURTLE 't'
#define ANTELOPE 'a'
#define CYBERSHEEP 'c'

#define GRASS '!'
#define SOWTHISTLE '@'
#define GUARANA '#'
#define BELLADONNA '$'
#define HOGWEED '%'

#define HUMAN 'h'

#define nullOrgan '_'
#define nullGrid(position) Organism(nullOrgan,-1,-1,position.X,position.Y,GetWorld())

struct Position					//the coordinate
{
	int X;
	int Y;
	Position();
	Position(int xCoordinate, int yCoordinate);
	Position Left(World* world);
	Position Right(World* world);
	Position Up(World* world);
	Position Down(World* world);
};

class Organism
{
private:
	bool acted = false;

	int age = 0;
	char name;

	int strength;
	int initiative;
	Position position;			//coordinate in the world
	World* world;
	
public:
	//costructor
	Organism(char n, int s, int i, int X, int Y, World* world);
	Organism(const Organism& o);

	//getters
	bool GetActed()const;
	int GetAge()const;
	char GetName()const;
	int GetStrength()const;
	int GetInitiative()const;
	Position GetPosition()const;
	World* GetWorld()const;
	
	//setters
	void SetActed(bool a);
	void SetAge(int a);
	void SetName(char n);
	void SetStrength(int s);
	void SetInitiative(int i);
	void SetPosition(Position p);
	void SetWorld(World* w);

	//other functions
	virtual void Action();

	virtual void Collision(Organism*& attacker);

	virtual void Draw();
};

#endif