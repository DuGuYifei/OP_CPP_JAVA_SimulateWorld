#ifndef _CYBERSHEEP_H_
#define _CYBERSHEEP_H_
#include "Animal.h"

class CyberSheep :public Animal
{
public:
	CyberSheep(int X, int Y, World* world);

	void Action()override;
};

#endif