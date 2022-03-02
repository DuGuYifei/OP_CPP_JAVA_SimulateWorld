#ifndef _GUARANA_H_
#define _GUARANA_H_
#include "Plant.h"

class Guarana :public Plant
{
public:
	Guarana(int X, int Y, World* world);

	void Collision(Organism*& attacker)override;
};

#endif