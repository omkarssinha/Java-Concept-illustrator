import java.util.*;
//import math.*;
public class Examples {

    public static void main(String[] args)
    {
        String name = "India";
        String capital = "Delhi";
        String language = "Hindi";

        Country india = new Country(name,capital,language);
//        india.print();
//        System.out.println("-------------XXX----------");

        State maharashtra = new State("Maharashtra","Mumbai","Marathi",india);
        State karnataka = new State("Karnataka","Bangalore","Kannada",india);
        State tamilNadu = new State("Tamil Nadu","Chennai","Tamil",india);
//        maharashtra.print();
//        System.out.println("-------------XXX----------");

        City mumbai = new City("Mumbai","BOM",maharashtra);
        City thane = new City("Thane","THA",maharashtra);
        City navi_mumbai = new City("Navi Mumbai","NVM",maharashtra);

        City bangalore = new City("Bangalore","BLR",karnataka);
        City mangalore = new City("Mangalore","MLR",karnataka);
        
        City chennai = new City("Chennai","CHN",tamilNadu);
        City vellore = new City("Vellore","VLR",tamilNadu);
//        mumbai.print();
//        System.out.println("-------------XXX----------");
        
        Country sriLanka =  new Country("Sri Lanka","Colombo","Sinhalese");
        State northernProvince = new State("Northern Province","jaffna","Tamil",sriLanka);
        City jaffna = new City("jaffna", "JFN",northernProvince);

        System.out.println("The current global population is "+Person.GLOBAL_POPULATION);
        
        Person kunal = new Person("Kunal", mumbai, 200000);
        Person sachin = new Person("Sachin", thane, 200000);
        Person ajit = new Person("Ajit", navi_mumbai, 200000);
        
        Person dravid = new Person("Dravid", bangalore, 200000);
        Person kumble = new Person("Kumble", bangalore, 200000);
        Person rahul = new Person("Rahul", mangalore, 200000);
        
        Person ashwin = new Person("Ashwin", chennai, 200000);
        Person karthik = new Person("Karthik", chennai, 200000);
        Person vijay = new Person("Vijay", vellore, 200000);

        Person dhoni = new Person("Dhoni", new City("Ranchi","RNC",new State("Jharkhand","Ranchi","Hindi",india)), 400000);
        City ranchi = dhoni.city;
        State jharkhand = dhoni.city.state;

        System.out.println("The current global population is "+Person.GLOBAL_POPULATION);

        sriLanka.print();
        northernProvince.print();
        jaffna.print();
        india.print();
        jharkhand.print();
        ranchi.print();

        dhoni.print();
        dhoni.migrate(jaffna);
        vijay.print();
        dhoni.print();

//        india.print();
//        jharkhand.print();
//        ranchi.print();
//        sriLanka.print();
//        northernProvince.print();
        jaffna.print();

        System.out.println("The current global population is "+Person.GLOBAL_POPULATION);

//        india.print();
//        maharashtra.print();
//        karnataka.print();
//        tamilNadu.print();
//        jharkhand.print();
//        
//        mumbai.print();
//        navi_mumbai.print();
//        thane.print();
//        vellore.print();
//        chennai.print();
//        bangalore.print();
//        mangalore.print();
//        ranchi.print();
    }
}

abstract class Area {

    int population;
    void birth(Person person){};
    void deceased(Person person){};
    protected void migrate(Person person, Area destination) {};
}

abstract class Govt extends Area {

    int tax;
}

class Country extends Govt {

    String name;
    String capital;
    String language;
    private List<String> assigned_uuids = new ArrayList<String>();
    List<State> states = new ArrayList<State>();

    public Country(String name, String capital, String language) {
        this.name= name;
        this.capital = capital;
        this.language = language;
    }
    @Override
    void birth(Person person)
    {
    	this.population++;
    }
    @Override
    void deceased(Person person)
    {
    	this.population--;
    }
    public String generateUUID()
    {
    	String new_uuid;
    	
    	do {
	    	String random = Double.toString(Math.random());
	    	new_uuid = random.substring(3,13);
    	} while(assigned_uuids.contains(new_uuid));
    
    	while(new_uuid.length()<10)
    		new_uuid+='0';
    	
    	assigned_uuids.add(new_uuid);
    	return new_uuid;
    }
    @Override
    protected void migrate(Person person, Area destinationCountry) {
    	
    	this.population--;
    	Country country = (Country) destinationCountry;
    	country.population++;
    	person.getIdentityNo(this);
    }
    

    void print() {
    	System.out.println("-------------XXX----------");
        System.out.println(String.format("This is a Country\nName: %s\nCapital: %s\nLanguage: %s\nPopulation: %d",this.name,this.capital,this.language,this.population));
        System.out.println("These are the states of the "+this.name);
        for (State state : states)
        	System.out.println(state.name);
        System.out.println("-------------XXX----------");
    }

}

class State extends Govt {

    String name;
    String capital;
    String language;
    Country country;
    //List<String> districts = new ArrayList<String>();
    List<City> cities = new ArrayList<City>();

    public State(String name, String capital, String language, Country cntry) {
        this.name= name;
        this.capital = capital;
        this.language = language;
        this.country = cntry;
        
        this.country.states.add(this);
    }
    @Override
    void birth(Person person)
    {
    	this.population++;
    	this.country.birth(person);
    	
    }
    @Override
    void deceased(Person person)
    {
    	this.population--;
    	this.country.deceased(person);
    	
    }
    @Override
    protected void migrate(Person person, Area destinationState) {
    	this.population--;
    	State state = (State) destinationState;
    	state.population++;
    	if(this.country != state.country)
    		this.country.migrate(person, state.country);
    }

    void print() {
    	System.out.println("-------------XXX----------");
        System.out.println(String.format("This is a State\nName: %s\nCapital: %s\nLanguage: %s\nPopulation: %d\nCountry: %s",this.name,this.capital,this.language,this.population,this.country.name));
        System.out.println("These are the cities of the "+this.name);
        for (City city : cities)
        	System.out.println(city.name);
        System.out.println("-------------XXX----------");
    }
}

class City extends Govt {

    String name;
    String airport_code;
    State state;
    List<Person> people = new ArrayList<Person>();
    //population = people.size();

    public City(String name, String aircode, State state) {
        this.name= name;
        this.airport_code = aircode;
        this.state =  state;
        
        this.state.cities.add(this);    
    }
    @Override
    void birth(Person person)
    {
    	people.add(person);
    	this.population++;
    	this.state.birth(person);
    }
    @Override
    void deceased(Person person)
    {
    	people.remove(person);
    	this.population--;
    	this.state.deceased(person);
    }
    @Override
    protected void migrate(Person person, Area destinationCity) {
    	
    	this.population--;
    	City city = (City) destinationCity;
    	city.population++;
    	this.people.remove(person);
    	city.people.add(person);
    	
    	if(this.state != city.state)
    		this.state.migrate(person, city.state);
    }

    void print() {
    	System.out.println("-------------XXX----------");
        System.out.println(String.format("This is a City\nName: %s\nAirport Code: %s\nPopulation: %d\nState: %s",this.name,this.airport_code,this.population,this.state.name));
        System.out.println("These are the denizens of the "+this.name);
        for (Person person : people)
        	System.out.println(person.name);
        System.out.println("-------------XXX----------");
    }
}

class Person {

    static int GLOBAL_POPULATION=0;

    String name;
    City city;
    int income;
    private String identity_no;

    public Person(String name, City city, int income) {
        this.name = name;
        this.city = city;
        this.income = income;
        this.city.birth(this);
        getIdentityNo(this.city.state.country);

        GLOBAL_POPULATION++;
    }
    
     void migrate(City destinationCity) {
    	
    	if(this.city == destinationCity)
    		return;
    	
    	else {    		    		
    		this.city.migrate(this, destinationCity); 
    		this.city = destinationCity;
    	}	
    }
    void getIdentityNo(Country country) {
    	this.identity_no = country.generateUUID();
    }

    void print() {
        System.out.println("-------------XXX----------");
        System.out.println(String.format("This is a Person\nName: %s\nCity: %s\nIncome: %d\nIdentity No: %s",this.name,this.city.name,this.income,this.identity_no));
        System.out.println("-------------XXX----------");
    }
}




