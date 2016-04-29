#include<iostream>
#include<string.h>
#include<conio.h>
#include<stdio.h>
#include <typeinfo>
#include <fstream>  
#include <vector>
#include <fstream>
#include <iostream>
#include <sstream>
#include <string>

using namespace std;

class var{
public:
	string var_name;
	int val;

	void setname(string name)
	{

		var_name = name;
	
	}

	void setval(int value)
	{

		val = value;

	}

	int getval()
	{
		return val;
	}
	string getname()
	{
		return var_name;
	}
};


int main()
{
	var value;
	vector<var> genes;
	int k = 0;
	std::ifstream ifs;
	char *next_token1 = NULL;
	char seps[] = " =\t\n";
	// create a file-reading object
	ifstream fin;
	fin.open("program.txt"); // open a file
	if (!fin.good())
		return 1; // exit if file not found

	// read each line of the file
	while (!fin.eof())
	{
		// read an entire line into memory
		char buf[512];
		fin.getline(buf, 512);

		// parse the line into blank-delimited tokens
		int n = 0; // a for-loop index

		
		 char* token[20]; // initialize to 0

	
		token[0] = strtok_s(buf, seps, &next_token1); // first token
		
		stringstream myStreamString;
		myStreamString << token[0];
		string myString = myStreamString.str();

		// for declaring a valiable
		if (myString == "let")
		{
			for (n = 1; n < 20; n++)
			{
				token[n] = strtok_s(NULL, seps, &next_token1); // subsequent tokens
				if (!token[n]) break; // no more tokens
			}
			value.setname(token[1]);
			value.setval((int)token[2]);

			genes.push_back(value);

		}
		// check for syntax errors
		else if ((isnan(myString)) == 0)
		{
			cout << " The code cannot start with a number" << endl;
		}

		// for the expresseions
		else if ((isnan(myString)) == 1)
		{
			char del[] = " =+-*/\t\n";
			for (n = 1; n < 20; n++)
			{
				token[n] = strtok_s(NULL, del, &next_token1); // subsequent tokens
				if (!token[n]) break; // no more tokens
			}


		}
		else if (myString == "print")
		{
			cout << "print ";
			for (int i = 0; i < 10; i++)
			if (genes[k].getname() == token[1])
				cout << genes[k].getval() << endl;
			else
				cout << "This variable does not exist" << endl;
		}
		else
		{
			int i;
			bool flag = true;
			for (i = 0; i< myString.length() ; i++)
			{
				if (isalpha(myString[i]))
					flag = true;
				else
					cout << " Invalid Syntax"<<endl;
			}
		}
		
	
	
		// process (print) the tokens
		for (int i = 0; i < n; i++) // n = #of tokens
			cout << "Token[" << i << "] = " << token[i] << endl;
		cout << endl;
		
		

	}




	system("pause");
	return 0;


}
