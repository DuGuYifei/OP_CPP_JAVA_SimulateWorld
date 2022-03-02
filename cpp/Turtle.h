#ifndef _TURTLE_H_
#define _TURTLE_H_
#include "Animal.h"

class Turtle :public Animal
{
public:
	Turtle(int X, int Y, World* world);

	void Action()override;
	void Collision(Organism*& attacker)override;
};

#endif