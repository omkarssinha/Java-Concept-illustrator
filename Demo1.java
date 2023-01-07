import java.util.*;
//import math.*;
public class Demo1 {

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
        State tamil_nadu = new State("Tamil Nadu","Chennai","Tamil",india);
//        maharashtra.print();
//        System.out.println("-------------XXX----------");

        City mumbai = new City("Mumbai","BOM",maharashtra);
        City thane = new City("Thane","THA",maharashtra);
        City navi_mumbai = new City("Navi Mumbai","NVM",maharashtra);

        City bangalore = new City("Bangalore","BLR",karnataka);
        City mangalore = new City("Mangalore","MLR",karnataka);
        
        City chennai = new City("Chennai","CHN",tamil_nadu);
        City vellore = new City("Vellore","VLR",tamil_nadu);
//        mumbai.print();
//        System.out.println("-------------XXX----------");
        
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
        
        
        kunal.print();
        sachin.print();
        karthik.print();
        vijay.print();
        dhoni.print();

//        india.print();
//        maharashtra.print();
//        karnataka.print();
//        tamil_nadu.print();
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
//        
//        System.out.println("-------------XXX----------");
    }
}

abstract class Area {

    int population;
    void addPopulation(Person person)
    {}

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
    void addPopulation(Person person)
    {
    	this.population++;
    	person.identity_no = generateUUID();
    }
    private String generateUUID()
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
    
    void addPopulation(Person person)
    {
    	this.population++;
    	this.country.addPopulation(person);
    	
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
    
    void addPopulation(Person person)
    {
    	people.add(person);
    	this.population++;
    	this.state.addPopulation(person);
    	
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

    String name;
    City city;
    int income;
    String identity_no;

    public Person(String name, City city, int income) {
        this.name = name;
        this.city = city;
        this.income = income;
        
        this.city.addPopulation(this);
        
    }

    void print() {
        System.out.println(String.format("This is a Person\nName: %s\nCity: %s\nIncome: %d\nIdentity No: %s",this.name,this.city.name,this.income,this.identity_no));
    }
}




