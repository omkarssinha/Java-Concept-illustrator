Govt and People OOPs Model

Challenging implementations
- Interface

### CONCEPTS ###
- Access-specifier/modifier
1) private
2) protected
- Final
1) Not changing values
2) Improves speed by Creates read-only values
3) Child class can't modify value
Final methods cannot be define in child
Final class cannot have child class

- Static variables/Attributes Use Cases
1) Object counter: Count no of things in class across all objects
2) Check Range of allowed attributes values: e.g. age limit, list of valid values, etc
3) List of Global Constants:- used as Static final variables e.g. name of country, person etc.

- Static functions/methods Use Cases
1) Implement functionality with object variables: e.g System.out.println() where println is static function

Structure:
1) All OUR derived Attributes should be final
2)

### OUR USE CASE ###

 1) Area - abstract class
	  |
	Govt - abstract class
	  |
   Country; has State; has City; - Class  
   						has Person - Class   									

   Area - abstract class
	  |
   District, Continent - Class

2)Person - Class
City has Person

Person many objects

3)Income Tax Interface
- collect_income_tax_revenue

4)Area Interface
- get_Area
- get_Population
- get_Population_Density

Person
Attributes
- income
- City - Object
- identity_no
Constructor
- call City.add_person()
derived Attributes should be final
- state = city.state
- country = city.state.country

Country
Attributes
- Name
- Capital
- Language
- State - Arraylist
Calculated Derived Attributes
- Population
Functions
- assign_identity_no
Interfaces
- Income Tax
- Area

State
Attributes
- Name
- Capital
- Language
- District - Arraylist
- Country
Calculated Derived Attributes
- Population
Derived Attributes
- Country_Name
- Country_Capital
Functions
- secede - Convert State to Country object
Interfaces
- Income Tax
- Area

District
Attributes
- Name
- Capital
- City - Arraylist
- State
Calculated Derived Attributes
- Population
Derived Attributes
- State_Name
- State_Capital
Functions
-
Interfaces
- Area
- remove income tax

City
Attributes
- Name
- Airport_Code
- Person - Arraylist (People)
- District
Calculated Derived Attributes
- Population
Derived Attributes
- District_Name
Functions
- add_person
Interfaces
- Income Tax
- Area