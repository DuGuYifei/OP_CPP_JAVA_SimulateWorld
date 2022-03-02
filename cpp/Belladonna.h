#ifndef _BELLADONNA_H_
#define _BELLADONNA_H_
#include "Plant.h"

class Belladonna:public Plant
{
public:
	Belladonna(int X, int Y, World* world);

	void Collision(Organism*& attacker)override;
};

#endif