#ifndef _FOX_H_
#define _FOX_H_
#include "Animal.h"

class Fox:public Animal
{
public:
	Fox(int X, int Y, World* world);

	void Action()override;
};

#endif