#ifndef _PLANT_H_
#define _PLANT_H_
#include "Organism.h"

class Plant:public Organism
{
public:
	Plant(char n, int s, int i, int X, int Y, World* world);

	void Action()override;
};

#endif