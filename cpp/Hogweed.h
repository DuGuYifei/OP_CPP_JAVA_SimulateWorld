#ifndef _HOGWEED_H_
#define _HOGWEED_H_
#include "Plant.h"

class Hogweed :public Plant
{
public:
	Hogweed(int X, int Y, World* world);

	void Action()override;
	void Collision(Organism*& attacker)override;
};

#endif