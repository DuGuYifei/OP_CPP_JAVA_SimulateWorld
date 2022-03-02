#ifndef _HUMAN_H_
#define _HUMAN_H_
#include "Animal.h"

class Human:public Animal
{
public:
	Human(int X, int Y, World* world);

	void Action()override;
};

#endif