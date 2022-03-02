#include "Organism.h"
#include <iostream>
//Position
Position::Position() { X = -1, Y = -1; }
Position::Position(int xCoordinate, int yCoordinate) :X(xCoordinate), Y(yCoordinate) {}

	//the world is a sphere,
	//so it will go to the other size in the map when it move out of side
Position Position::Left(World* world)			
{
	int x;
	if (X > 0)
		x = X - 1;
	else
		x = world->GetSizeX() - 1;
	return Position(x,Y);
}
Position Position::Right(World* world)
{
	int x;
	if (X < world->GetSizeX() - 1)
		x = X + 1;
	else
		x = 0;
	return Position(x,Y);
}
Position Position::Up(World* world)
{
	int y;
	if (Y > 0)
		y = Y - 1;
	else
		y = world->GetSizeY() - 1;
	return Position(X, y);
}
Position Position::Down(World* world)
{
	int y;
	if (Y < world->GetSizeY() - 1)
		y = Y + 1;
	else
		y = 0;
	return Position(X, y);
}


//constructor
Organism::Organism(char n, int s, int i, int X, int Y, World* world) :
	name(n), strength(s), initiative(i), position(X, Y), world(world) 
{
	if (n == nullOrgan)
		SetActed(true);
}
Organism::Organism(const Organism& o)
{
	SetName(o.GetName());
	SetStrength(o.GetStrength());
	SetInitiative(o.GetInitiative());
	SetPosition(o.GetPosition());
	SetWorld(o.GetWorld());
}


//getters
bool Organism::GetActed()const { return acted; }
int Organism::GetAge()const { return age; }
char Organism::GetName()const { return name; }
int Organism::GetStrength()const { return strength; }
int Organism::GetInitiative()const { return initiative;}
Position Organism::GetPosition()const { return position; }
World* Organism::GetWorld()const { return world; }

//setters
void Organism::SetActed(bool a) { acted = a; }
void Organism::SetAge(int a) { age = a; }
void Organism::SetName(char n) { name = n; }
void Organism::SetStrength(int s) { strength = s; }
void Organism::SetInitiative(int i) { initiative = i; }
void Organism::SetPosition(Position p) { position= p; }
void Organism::SetWorld(World* w) { world = w; }

//other functions
void Organism::Action() {};

void Organism::Collision(Organism*& attacker)
{
	//default collision
	if (attacker->GetStrength() >= this->GetStrength())
	{
		Grid(GetPosition().X, GetPosition().Y) = attacker;

		//if = -1, it is nullorgan or baby of two same animal
		/*if (attacker->GetPosition().X != -1)
			Grid(attacker->GetPosition().X, attacker->GetPosition().Y) = new nullGrid(attacker->GetPosition());*/

		if (attacker->GetPosition().X == -1)
			attacker->SetActed(true);				//same strength, attacker win, so baby win(even if there is something let this be stronger, attacker will not pass code to here)

		attacker->SetPosition(GetPosition());
		delete(this);

		//return false;					//attacker win return false
	}
	else
	{
		//if = -1, it is nullorgan or baby of two same animal
		/*if (attacker->GetPosition().X != -1)
			Grid(attacker->GetPosition().X, attacker->GetPosition().Y) = new nullGrid(attacker->GetPosition());*/

		delete(attacker);

		//return true;					//defender win return true
	}
}

void Organism::Draw() 
{
	std::cout << name <<" ";
}