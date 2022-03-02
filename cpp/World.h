#ifndef _WORLD_H_
#define _WORLD_H_
class Organism;

#define Grid(X,Y) *((GetWorld()->organisms) + (Y * (GetWorld()->GetSizeX()) + X))

class World
{
private:
	int sizeX;
	int sizeY;

public:
	Organism **organisms;	//position is (a,b), then its pointer is b*sizeX+a.

	//gettersD
	int GetSizeX()const;
	int GetSizeY()const;

	//setters
	void SetSizeX(int X);
	void SetSizeY(int Y);

	//instructor and destructor
	World();
	World(int X, int Y);
	~World();

	//other functions
	void Draw();
	bool Save();
	bool Load();
};

#endif