#ifndef _ANIMAL_H_
#define _ANIMAL_H_
#include "Organism.h"


class Animal:public Organism
{
public:
	Animal(char n, int s, int i, int X, int Y, World* world);

	void Action()override;
};

#endif