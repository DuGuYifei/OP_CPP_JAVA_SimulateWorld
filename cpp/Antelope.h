#ifndef _ANTELOPE_H_
#define _ANTELOPE_H_
#include "Animal.h"

class Antelope:public Animal
{
public:
	Antelope(int X, int Y, World* world);

	void Action()override;
	void Collision(Organism*& attacker)override;
};

#endif