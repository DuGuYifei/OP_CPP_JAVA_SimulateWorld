#ifndef _SOWTHISTLE_H_
#define _SOWTHISTLE_H_
#include "Plant.h"

class SowThistle :public Plant
{
public:
	SowThistle(int X, int Y, World* world);

	void Action()override;
};

#endif